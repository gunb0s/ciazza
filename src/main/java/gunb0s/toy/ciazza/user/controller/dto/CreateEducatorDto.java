package gunb0s.toy.ciazza.user.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateEducatorDto {
	@NotNull
	private String name;
}
