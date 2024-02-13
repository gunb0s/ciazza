package gunb0s.toy.ciazza.user.controller.dto;

import gunb0s.toy.ciazza.user.entity.Student;
import lombok.Getter;

@Getter
public class GetStudentDto {
	private Long id;
	private String name;

	public GetStudentDto(Student student) {
		this.id = student.getId();
		this.name = student.getName();
	}
}
