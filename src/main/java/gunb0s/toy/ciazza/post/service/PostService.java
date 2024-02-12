package gunb0s.toy.ciazza.post.service;

import gunb0s.toy.ciazza.board.entity.Board;
import gunb0s.toy.ciazza.board.repository.BoardRepository;
import gunb0s.toy.ciazza.enrollment.repository.EnrollmentRepository;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.lecture.repository.LectureRepository;
import gunb0s.toy.ciazza.post.controller.dto.CreatePostDto;
import gunb0s.toy.ciazza.post.controller.dto.GetPostListDto;
import gunb0s.toy.ciazza.post.controller.dto.PostSearchCondition;
import gunb0s.toy.ciazza.post.entity.Post;
import gunb0s.toy.ciazza.post.repository.PostQueryRepository;
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
public class PostService {
	private final PostRepository postRepository;
	private final PostQueryRepository postQueryRepository;
	private final BoardRepository boardRepository;
	private final EnrollmentRepository enrollmentRepository;
	private final LectureRepository lectureRepository;
	private final UserRepository userRepository;

	@Transactional
	public Long create(CreatePostDto createPostDto) {
		Long userId = createPostDto.getUserId();
		Long lectureId = createPostDto.getLectureId();

		User user = userRepository.findById(userId).orElseThrow();
		if (user.getDtype().equals("E")) {
			lectureRepository.findByIdAndEducator(lectureId, (Educator) user).orElseThrow();
		} else {
			Lecture lecture = lectureRepository.findById(lectureId).orElseThrow();
			enrollmentRepository.findByStudentAndLecture((Student) user, lecture).orElseThrow();
		}

		Board board = boardRepository.findById(createPostDto.getBoardId()).orElseThrow();
		if (!board.getLecture().getId().equals(lectureId)) {
			throw new IllegalArgumentException("The board does not belong to the lecture.");
		}
		Post post = Post.builder()
				.title(createPostDto.getTitle())
				.content(createPostDto.getContent())
				.board(board)
				.user(user)
				.build();

		postRepository.save(post);
		return post.getId();
	}

	public Post get(Long postId) {
		return postRepository.findByIdWithUser(postId).orElseThrow();
	}

	public Page<Post> getList(
			GetPostListDto getPostListDto,
			PostSearchCondition postSearchCondition,
			Pageable pageable) {
		return postQueryRepository.search(getPostListDto.getLectureId(), getPostListDto.getBoardId(), postSearchCondition, pageable);
	}
}
