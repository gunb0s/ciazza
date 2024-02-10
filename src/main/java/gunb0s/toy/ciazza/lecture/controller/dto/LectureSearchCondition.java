package gunb0s.toy.ciazza.lecture.controller.dto;

import gunb0s.toy.ciazza.lecture.entity.Semester;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureSearchCondition {
	@NotNull
	Semester semester;

	@NotNull
	String lectureCode;

	@NotNull
	Long educatorId;
}
