package gunb0s.toy.ciazza.comment.service;

import gunb0s.toy.ciazza.comment.controller.dto.CreateCommentDto;
import gunb0s.toy.ciazza.comment.controller.dto.GetCommentOfPostDto;
import gunb0s.toy.ciazza.comment.entity.Comment;
import gunb0s.toy.ciazza.comment.repository.CommentQueryRepository;
import gunb0s.toy.ciazza.comment.repository.CommentRepository;
import gunb0s.toy.ciazza.enrollment.repository.EnrollmentRepository;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.lecture.repository.LectureRepository;
import gunb0s.toy.ciazza.post.entity.Post;
import gunb0s.toy.ciazza.post.repository.PostRepository;
import gunb0s.toy.ciazza.user.entity.Educator;
import gunb0s.toy.ciazza.user.entity.Student;
import gunb0s.toy.ciazza.user.entity.User;
import gunb0s.toy.ciazza.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
	private final CommentRepository commentRepository;
	private final CommentQueryRepository commentQueryRepository;
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final LectureRepository lectureRepository;
	private final EnrollmentRepository enrollmentRepository;

	@Transactional
	public Long create(CreateCommentDto createCommentDto) {
		User user = userRepository.findById(createCommentDto.getUserId()).orElseThrow();
		Post post = postRepository.findByIdWithBoard(createCommentDto.getPostId()).orElseThrow();
		Lecture lecture = post.getBoard().getLecture();

		if (user.getDtype().equals("E")) {
			lectureRepository.findByIdAndEducator(lecture.getId(), (Educator) user).orElseThrow();
		} else {
			enrollmentRepository.findByStudentAndLecture((Student) user, lecture).orElseThrow();
		}

		if (createCommentDto.isRootCommentRequest()) {
			Comment saveComment = saveRootComment(createCommentDto, post, user);
			saveComment.setCommentGroupId(saveComment.getId());
			return saveComment.getId();
		} else {
			Comment parentComment = commentRepository.findById(createCommentDto.getParentCommentId()).orElseThrow();
			if (parentComment.isRootComment()) {
				return saveFirstCommentOfRoot(createCommentDto, parentComment, post, user, parentComment.getDepth());
			} else {
				Integer maxCommentOrderInParent = commentRepository
						.maxCommentOrderByCommentGroupIdAndParentComment(parentComment.getCommentGroupId(), parentComment);
				if (isFirstCommentOfParent(maxCommentOrderInParent)) {
					commentRepository.updateCommentOrderGoeThanOrder(parentComment.getCommentGroupId(), parentComment.getCommentOrder() + 1);
					return saveFirstCommentOfParent(createCommentDto, post, user, parentComment, parentComment.getDepth());
				} else {
					commentRepository.updateCommentOrderGoeThanOrder(parentComment.getCommentGroupId(), maxCommentOrderInParent + 1);
					return saveCommentOfParent(createCommentDto, post, user, parentComment, maxCommentOrderInParent, parentComment.getDepth());
				}

			}
		}
	}

	public Page<Comment> getCommentOfPost(GetCommentOfPostDto getCommentOfPostDto, Pageable pageable) {
		Long postId = getCommentOfPostDto.getPostId();
		boolean exists = postRepository.existsById(postId);
		if (!exists) {
			throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
		}

		return commentQueryRepository.findAllCommentsOfPost(postId, pageable);
	}

	private boolean isFirstCommentOfParent(Integer maxCommentOrderInParent) {
		return maxCommentOrderInParent == null;
	}

	private Long saveCommentOfParent(CreateCommentDto createCommentDto, Post post, User user, Comment parentComment, Integer maxCommentOrderInParent, Integer parentCommentDepth) {
		Comment comment = Comment.builder()
				.content(createCommentDto.getContent())
				.post(post)
				.user(user)
				.parentComment(parentComment)
				.commentOrder(maxCommentOrderInParent + 1)
				.depth(parentCommentDepth + 1)
				.commentGroupId(parentComment.getCommentGroupId())
				.build();

		return commentRepository.save(comment).getId();
	}

	private Long saveFirstCommentOfParent(CreateCommentDto createCommentDto, Post post, User user, Comment parentComment, Integer parentCommentDepth) {
		Comment comment = Comment.builder()
				.content(createCommentDto.getContent())
				.post(post)
				.user(user)
				.parentComment(parentComment)
				.commentOrder(parentComment.getCommentOrder() + 1)
				.depth(parentCommentDepth + 1)
				.commentGroupId(parentComment.getCommentGroupId())
				.build();

		return commentRepository.save(comment).getId();
	}

	private Long saveFirstCommentOfRoot(CreateCommentDto createCommentDto, Comment parentComment, Post post, User user, Integer parentCommentDepth) {
		Integer maxCommentOrder = commentRepository
				.maxCommentOrderByCommentGroupId(parentComment.getCommentGroupId());
		Comment comment = Comment.builder()
				.content(createCommentDto.getContent())
				.post(post)
				.user(user)
				.parentComment(parentComment)
				.commentOrder(maxCommentOrder + 1)
				.depth(parentCommentDepth + 1)
				.commentGroupId(parentComment.getCommentGroupId())
				.build();
		return commentRepository.save(comment).getId();
	}

	private Comment saveRootComment(CreateCommentDto createCommentDto, Post post, User user) {
		Comment comment = Comment.builder()
				.content(createCommentDto.getContent())
				.post(post)
				.user(user)
				.commentOrder(0)
				.depth(0)
				.commentGroupId(null)
				.build();
		Comment save = commentRepository.save(comment);
		return save;
	}

}
