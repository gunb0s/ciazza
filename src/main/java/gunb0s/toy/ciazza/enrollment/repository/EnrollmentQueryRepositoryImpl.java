package gunb0s.toy.ciazza.enrollment.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gunb0s.toy.ciazza.enrollment.entity.Enrollment;
import gunb0s.toy.ciazza.lecture.entity.Semester;
import gunb0s.toy.ciazza.user.controller.dto.StudentLectureSearchCondition;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static gunb0s.toy.ciazza.enrollment.entity.QEnrollment.enrollment;
import static gunb0s.toy.ciazza.lecture.entity.QLecture.lecture;
import static gunb0s.toy.ciazza.user.entity.QEducator.educator;

@Repository
public class EnrollmentQueryRepositoryImpl implements EnrollmentQueryRepository {
	private JPAQueryFactory queryFactory;

	public EnrollmentQueryRepositoryImpl(EntityManager em) {
		queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<Enrollment> searchEnrollment(
			Long studentId,
			StudentLectureSearchCondition studentLectureSearchCondition,
			Pageable pageable) {
		List<Enrollment> results = queryFactory
				.selectFrom(enrollment)
				.join(enrollment.lecture, lecture).fetchJoin()
				.join(lecture.educator, educator).fetchJoin()
				.where(
						enrollment.student.id.eq(studentId),
						semesterEq(studentLectureSearchCondition.getSemester()),
						lectureCodeContains(studentLectureSearchCondition.getLectureCode())
				)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		JPAQuery<Long> countQuery = queryFactory
				.select(enrollment.count())
				.from(enrollment)
				.where(
						enrollment.student.id.eq(studentId),
						semesterEq(studentLectureSearchCondition.getSemester()),
						lectureCodeContains(studentLectureSearchCondition.getLectureCode())
				);
		return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchCount);
	}

	private BooleanExpression semesterEq(Semester semester) {
		return semester != null ? lecture.semester.eq(semester) : null;
	}

	private BooleanExpression lectureCodeContains(String lectureCode) {
		return lectureCode != null ? lecture.lectureCode.contains(lectureCode) : null;
	}
}
