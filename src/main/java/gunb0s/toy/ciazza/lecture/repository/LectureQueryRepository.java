package gunb0s.toy.ciazza.lecture.repository;

import gunb0s.toy.ciazza.lecture.controller.dto.LectureSearchCondition;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LectureQueryRepository {
	Page<Lecture> findLectureByLectureSearchCondition(LectureSearchCondition lectureSearchCondition, Pageable pageable);
}
