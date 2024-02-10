package gunb0s.toy.ciazza.lecture.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gunb0s.toy.ciazza.lecture.controller.dto.LectureSearchCondition;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.lecture.entity.Semester;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static gunb0s.toy.ciazza.lecture.entity.QLecture.lecture;
import static gunb0s.toy.ciazza.user.entity.QEducator.educator;

@Repository
public class LectureQueryRepositoryImpl implements LectureQueryRepository {
	private final JPAQueryFactory queryFactory;

	public LectureQueryRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<Lecture> findLectureByLectureSearchCondition(LectureSearchCondition lectureSearchCondition, Pageable pageable) {
		List<Lecture> results = queryFactory
				.selectFrom(lecture)
				.join(lecture.educator, educator)
				.fetchJoin()
				.where(
						semesterEq(lectureSearchCondition.getSemester()),
						educatorIdEq(lectureSearchCondition.getEducatorId()),
						lectureCodeContains(lectureSearchCondition.getLectureCode())
				)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		JPAQuery<Long> countQuery = queryFactory
				.select(lecture.count())
				.from(lecture)
				.where(
						semesterEq(lectureSearchCondition.getSemester()),
						educatorIdEq(lectureSearchCondition.getEducatorId()),
						lectureCodeContains(lectureSearchCondition.getLectureCode())
				);

		return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchCount);
	}

	private BooleanExpression semesterEq(Semester semester) {
		return semester != null ? lecture.semester.eq(semester) : null;
	}

	private BooleanExpression educatorIdEq(Long educatorId) {
		return educatorId != null ? lecture.educator.id.eq(educatorId) : null;
	}

	private BooleanExpression lectureCodeContains(String lectureCode) {
		return lectureCode != null ? lecture.lectureCode.contains(lectureCode) : null;
	}
}
