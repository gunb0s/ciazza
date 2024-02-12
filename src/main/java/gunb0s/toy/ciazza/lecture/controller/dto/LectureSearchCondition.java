package gunb0s.toy.ciazza.lecture.controller.dto;

import gunb0s.toy.ciazza.lecture.entity.Semester;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureSearchCondition {
	Semester semester;

	String lectureCode;

	Long educatorId;
}
