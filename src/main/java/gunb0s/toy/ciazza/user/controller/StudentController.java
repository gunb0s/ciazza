package gunb0s.toy.ciazza.user.controller;

import gunb0s.toy.ciazza.enrollment.entity.Enrollment;
import gunb0s.toy.ciazza.user.controller.dto.CreateStudentDto;
import gunb0s.toy.ciazza.user.controller.dto.GetStudentDto;
import gunb0s.toy.ciazza.user.controller.dto.LectureDto;
import gunb0s.toy.ciazza.user.controller.dto.StudentLectureSearchCondition;
import gunb0s.toy.ciazza.user.entity.Student;
import gunb0s.toy.ciazza.user.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/student")
	public Page<GetStudentDto> getList(Pageable pageable) {
		Page<Student> students = studentService.getList(pageable);
		return students.map(GetStudentDto::new);
	}

	@GetMapping("/student/{id}")
	public GetStudentDto get(@PathVariable Long id) {
		Student student = studentService.get(id);
		return GetStudentDto.builder()
				.id(student.getId())
				.name(student.getName())
				.build();
	}

	@GetMapping("/student/{id}/lectures")
	public Page<LectureDto> getLectures(@PathVariable Long id, StudentLectureSearchCondition studentLectureSearchCondition, Pageable pageable) {
		Page<Enrollment> lectures = studentService.getLectures(id, studentLectureSearchCondition, pageable);
		return lectures.map(LectureDto::new);
	}
}
