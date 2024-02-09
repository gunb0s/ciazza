package gunb0s.toy.ciazza.enrollment.repository;

import gunb0s.toy.ciazza.enrollment.entity.Enrollment;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.user.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	Optional<Enrollment> findByStudentAndLecture(Student student, Lecture lecture);
}