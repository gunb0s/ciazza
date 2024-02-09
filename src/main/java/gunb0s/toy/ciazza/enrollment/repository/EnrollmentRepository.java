package gunb0s.toy.ciazza.enrollment.repository;

import gunb0s.toy.ciazza.enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	Optional<Enrollment> findByUserIdAndLectureId(Long userId, Long lectureId);
}