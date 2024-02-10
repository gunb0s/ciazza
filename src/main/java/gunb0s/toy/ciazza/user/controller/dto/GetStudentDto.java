package gunb0s.toy.ciazza.user.controller.dto;

import gunb0s.toy.ciazza.user.entity.Student;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetStudentDto {
	private Long id;
	private String name;

	@Builder
	public GetStudentDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public GetStudentDto(Student student) {
		this.id = student.getId();
		this.name = student.getName();
	}
}
