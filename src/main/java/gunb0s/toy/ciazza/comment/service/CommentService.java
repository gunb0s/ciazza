package gunb0s.toy.ciazza.comment.service;

import gunb0s.toy.ciazza.comment.controller.dto.CreateCommentDto;
import gunb0s.toy.ciazza.comment.entity.Comment;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final LectureRepository lectureRepository;
	private final EnrollmentRepository enrollmentRepository;

	@Transactional
	public Long create(CreateCommentDto createCommentDto) {
		User user = userRepository.findById(createCommentDto.getUserId()).orElseThrow();
		Post post = postRepository.findPostWithLecture(createCommentDto.getPostId()).orElseThrow();
		Comment parentComment = createCommentDto.getParentCommentId() == null
				? null
				: commentRepository.findById(createCommentDto.getParentCommentId()).orElseThrow();
		Lecture lecture = post.getBoard().getLecture();

		if (user.getDtype().equals("E")) {
			lectureRepository.findByIdAndEducator(lecture.getId(), (Educator) user).orElseThrow();
		} else {
			enrollmentRepository.findByStudentAndLecture((Student) user, lecture).orElseThrow();
		}

		Comment comment = new Comment(createCommentDto.getContent(), post, user, parentComment);

		commentRepository.save(comment);
		return comment.getId();
	}
}
