package gunb0s.toy.ciazza.enrollment.repository;

import gunb0s.toy.ciazza.enrollment.entity.Enrollment;
import gunb0s.toy.ciazza.user.controller.dto.StudentLectureSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnrollmentQueryRepository {
	Page<Enrollment> searchEnrollment(Long studentId,
									  StudentLectureSearchCondition studentLectureSearchCondition,
									  Pageable pageable);
}
