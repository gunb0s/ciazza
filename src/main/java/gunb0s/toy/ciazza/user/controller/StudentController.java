package gunb0s.toy.ciazza.user.controller;

import gunb0s.toy.ciazza.user.controller.dto.CreateStudentDto;
import gunb0s.toy.ciazza.user.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {
	private final StudentService studentService;

	@PostMapping("/student")
	public Long create(@RequestBody @Valid CreateStudentDto createStudentDto) {
		return studentService.create(createStudentDto);
	}
}
