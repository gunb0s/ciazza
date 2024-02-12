package gunb0s.toy.ciazza.comment.service;

import gunb0s.toy.ciazza.board.entity.Board;
import gunb0s.toy.ciazza.comment.controller.dto.CreateCommentDto;
import gunb0s.toy.ciazza.comment.controller.dto.GetCommentOfPostDto;
import gunb0s.toy.ciazza.comment.entity.Comment;
import gunb0s.toy.ciazza.enrollment.entity.Enrollment;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.lecture.entity.Semester;
import gunb0s.toy.ciazza.post.entity.Post;
import gunb0s.toy.ciazza.user.entity.Educator;
import gunb0s.toy.ciazza.user.entity.Student;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
class CommentServiceTest {
	@Autowired
	private EntityManager em;

	@Autowired
	private CommentService commentService;

	private Long postId;
	private Long studentId;
	private Long educatorId;

	@BeforeEach
	void beforeEach() {
		Educator educator = new Educator("educator");
		Student student = new Student("student");
		em.persist(educator);
		em.persist(student);

		Lecture lecture = Lecture.builder()
				.name("lecture-1")
				.lectureCode("lecture code")
				.registrationCode("123456")
				.educator(educator)
				.semester(Semester.SPRING_2024)
				.build();
		em.persist(lecture);
		Enrollment enrollment = Enrollment.builder()
				.lecture(lecture)
				.student(student)
				.build();
		em.persist(enrollment);
		Board board = Board.builder()
				.title("title")
				.lecture(lecture)
				.build();
		em.persist(board);
		Post post = Post.builder()
				.title("post title")
				.content("post content")
				.board(board)
				.user(student)
				.build();
		em.persist(post);

		em.flush();
		em.clear();

		postId = post.getId();
		studentId = student.getId();
	}

	@Test
	void createCommentHierarchy() {
		CreateCommentDto firstCommentDto = new CreateCommentDto("1st", postId, studentId, null);
		Long firstId = commentService.create(firstCommentDto);

		CreateCommentDto secondCommentDto = new CreateCommentDto("2st", postId, studentId, null);
		Long secondId = commentService.create(secondCommentDto);

		CreateCommentDto first_firstCommentDto = new CreateCommentDto("1st-1st", postId, studentId, firstId);
		Long first_firstId = commentService.create(first_firstCommentDto);

		CreateCommentDto first_secondCommentDto = new CreateCommentDto("1st-2st", postId, studentId, firstId);
		Long first_secondId = commentService.create(first_secondCommentDto);

		CreateCommentDto first_thirdCommentDto = new CreateCommentDto("1st-3st", postId, studentId, firstId);
		Long first_thirdId = commentService.create(first_thirdCommentDto);

		CreateCommentDto first_first_firstCommentDto = new CreateCommentDto("1st-1st-1st", postId, studentId, first_firstId);
		Long first_first_firstId = commentService.create(first_first_firstCommentDto);

		CreateCommentDto first_first_secondCommentDto = new CreateCommentDto("1st-1st-2st", postId, studentId, first_firstId);
		Long first_first_secondId = commentService.create(first_first_secondCommentDto);

		CreateCommentDto first_first_first_firstCommentDto = new CreateCommentDto("1st-1st-1st-1st", postId, studentId, first_first_firstId);
		Long first_first_first_firstId = commentService.create(first_first_first_firstCommentDto);

		em.flush();
		em.clear();

		Page<Comment> comments = commentService.getCommentOfPost(new GetCommentOfPostDto(postId), PageRequest.of(0, 10));

		assertThat(comments.get().map(Comment::getId)).containsExactly(
				firstId, first_firstId, first_first_firstId, first_first_first_firstId, first_first_secondId, first_secondId, first_thirdId, secondId
		);
	}
}