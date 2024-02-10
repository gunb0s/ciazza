package gunb0s.toy.ciazza.lecture.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EnrollLectureDto {
	@NotNull
	private Long studentId;

	@NotNull
	private String registrationCode;
}
