package gunb0s.toy.ciazza.user.controller.dto;

import gunb0s.toy.ciazza.lecture.entity.Semester;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentLectureSearchCondition {
	Semester semester;

	String lectureCode;
}
