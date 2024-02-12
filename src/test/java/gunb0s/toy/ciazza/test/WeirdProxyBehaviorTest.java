package gunb0s.toy.ciazza.test;

import gunb0s.toy.ciazza.board.entity.Board;
import gunb0s.toy.ciazza.enrollment.entity.Enrollment;
import gunb0s.toy.ciazza.enrollment.repository.EnrollmentRepository;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.lecture.entity.Semester;
import gunb0s.toy.ciazza.post.entity.Post;
import gunb0s.toy.ciazza.post.repository.PostRepository;
import gunb0s.toy.ciazza.user.entity.Educator;
import gunb0s.toy.ciazza.user.entity.Student;
import gunb0s.toy.ciazza.user.entity.User;
import gunb0s.toy.ciazza.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class WeirdProxyBehaviorTest {
	@Autowired
	private EntityManager em;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	private Long studentId;
	private Long postId;

	@BeforeEach
	void setUp() {
		setUpForCreatingComment();
	}

	@Test
	void errorCast() {
		Post post = postRepository.findByIdWithBoard(postId).orElseThrow();
		User user = userRepository.findById(studentId).orElseThrow();
		Lecture lecture = post.getBoard().getLecture();

		/**
		 * java.lang.ClassCastException: class gunb0s.toy.ciazza.user.entity.User$HibernateProxy$7x7pTUSd cannot be cast to class gunb0s.toy.ciazza.user.entity.Student (gunb0s.toy.ciazza.user.entity.User$HibernateProxy$7x7pTUSd and gunb0s.toy.ciazza.user.entity.Student are in unnamed module of loader 'app')
		 */
		assertThatThrownBy(() -> {
			enrollmentRepository.findByStudentAndLecture((Student) user, lecture).orElseThrow();
		}).isInstanceOf(ClassCastException.class);
	}

	@Test
	void noErrorCast() {
		User user = userRepository.findById(studentId).orElseThrow();
		Post post = postRepository.findByIdWithBoard(postId).orElseThrow();
		Lecture lecture = post.getBoard().getLecture();

		assertThatNoException().isThrownBy(() -> {
			enrollmentRepository.findByStudentAndLecture((Student) user, lecture).orElseThrow();
		});
	}

	private void setUpForCreatingComment() {
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

		studentId = student.getId();
		postId = post.getId();
	}
}
