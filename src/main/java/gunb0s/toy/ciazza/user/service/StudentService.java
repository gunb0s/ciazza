package gunb0s.toy.ciazza.user.service;

import gunb0s.toy.ciazza.user.controller.dto.CreateStudentDto;
import gunb0s.toy.ciazza.user.entity.Student;
import gunb0s.toy.ciazza.user.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;

	public Long create(CreateStudentDto createStudentDto) {
		Student student = Student.builder()
				.name(createStudentDto.getName())
				.build();
		studentRepository.save(student);
		return student.getId();
	}
}
