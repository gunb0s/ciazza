package gunb0s.toy.ciazza.post.service;

import gunb0s.toy.ciazza.board.entity.Board;
import gunb0s.toy.ciazza.board.repository.BoardRepository;
import gunb0s.toy.ciazza.enrollment.entity.Enrollment;
import gunb0s.toy.ciazza.enrollment.repository.EnrollmentRepository;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.lecture.repository.LectureRepository;
import gunb0s.toy.ciazza.post.controller.dto.CreatePostDto;
import gunb0s.toy.ciazza.post.entity.Post;
import gunb0s.toy.ciazza.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
	private final PostRepository postRepository;
	private final BoardRepository boardRepository;
	private final EnrollmentRepository enrollmentRepository;
	private final LectureRepository lectureRepository;

	@Transactional
	public Long create(CreatePostDto createPostDto) {
		Long userId = createPostDto.getUserId();
		Long lectureId = createPostDto.getLectureId();

		Optional<Enrollment> enrollment = enrollmentRepository.findByStudentIdAndLectureId(userId, lectureId);
		if (enrollment.isEmpty()) {
			Optional<Lecture> lecture = lectureRepository.findByIdAndEducatorId(lectureId, userId);
			if (lecture.isEmpty()) {
				throw new IllegalArgumentException("You are not enrolled in this lecture.");
			}
		}

		Board board = boardRepository.findById(createPostDto.getBoardId()).orElseThrow();
		Post post = Post.builder()
				.title(createPostDto.getTitle())
				.content(createPostDto.getContent())
				.board(board)
				.build();

		postRepository.save(post);
		return post.getId();
	}
}
