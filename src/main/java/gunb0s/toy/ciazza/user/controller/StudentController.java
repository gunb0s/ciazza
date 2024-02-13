package gunb0s.toy.ciazza.user.controller;

import gunb0s.toy.ciazza.enrollment.entity.Enrollment;
import gunb0s.toy.ciazza.user.controller.dto.CreateStudentDto;
import gunb0s.toy.ciazza.user.controller.dto.CreateStudentReposeDto;
import gunb0s.toy.ciazza.user.controller.dto.GetStudentDto;
import gunb0s.toy.ciazza.user.controller.dto.LectureDto;
import gunb0s.toy.ciazza.user.controller.dto.StudentLectureSearchCondition;
import gunb0s.toy.ciazza.user.entity.Student;
import gunb0s.toy.ciazza.user.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "student", description = "the student API")
public class StudentController {
	private final StudentService studentService;

	@Operation(summary = "Add student", description = "add a new student to system", tags = {"student"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful operation", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CreateStudentReposeDto.class))}),
//			@ApiResponse(responseCode = "405", description = "Invalid input")
	})
	@PostMapping("/student")
	public ResponseEntity<CreateStudentReposeDto> create(@RequestBody @Valid CreateStudentDto createStudentDto) {
		Long id = studentService.create(createStudentDto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new CreateStudentReposeDto(id));
	}

	@Operation(summary = "get student", description = "get student with pagination", tags = {"student"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", useReturnTypeSchema = true),
	})
	@GetMapping("/student")
	public ResponseEntity<Page<GetStudentDto>> getList(@ParameterObject Pageable pageable) {
		Page<Student> students = studentService.getList(pageable);
		Page<GetStudentDto> studentDtoPage = students.map(GetStudentDto::new);
		return ResponseEntity
				.ok(studentDtoPage);
	}

	@Operation(summary = "get student detail", description = "get student by id", tags = {"student"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", useReturnTypeSchema = true),
	})
	@GetMapping("/student/{id}")
	public ResponseEntity<GetStudentDto> get(@PathVariable Long id) {
		Student student = studentService.get(id);
		return ResponseEntity
				.ok(new GetStudentDto(student));
	}

	@Operation(summary = "get student lectures", description = "get student lectures with pagination", tags = {"student"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", useReturnTypeSchema = true),
	})
	@GetMapping("/student/{id}/lectures")
	public Page<LectureDto> getLectures(
			@PathVariable Long id,
			@ParameterObject StudentLectureSearchCondition studentLectureSearchCondition,
			@ParameterObject Pageable pageable
	) {
		Page<Enrollment> lectures = studentService.getLectures(id, studentLectureSearchCondition, pageable);
		return lectures.map(LectureDto::new);
	}
}
