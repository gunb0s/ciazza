package gunb0s.toy.ciazza.user.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateStudentDto {
	@NotNull
	private String name;
}
