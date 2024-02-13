package gunb0s.toy.ciazza.user.controller.dto;

import lombok.Getter;

@Getter
public class CreateStudentReposeDto {
	private final Long id;

	public CreateStudentReposeDto(Long id) {
		this.id = id;
	}
}
