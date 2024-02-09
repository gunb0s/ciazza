package gunb0s.toy.ciazza.lecture.controller.dto;

import gunb0s.toy.ciazza.lecture.entity.Semester;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateLectureDto {
	@NotNull
	private String name;

	@NotNull
	private String lectureCode;

	@NotNull
	private Long educatorId;

	@NotNull
	private Semester semester;
}
