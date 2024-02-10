package gunb0s.toy.ciazza.user.service;

import gunb0s.toy.ciazza.user.controller.dto.CreateStudentDto;
import gunb0s.toy.ciazza.user.entity.Student;
import gunb0s.toy.ciazza.user.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {
	private final StudentRepository studentRepository;

	@Transactional
	public Long create(CreateStudentDto createStudentDto) {
		Student student = Student.builder()
				.name(createStudentDto.getName())
				.build();
		studentRepository.save(student);
		return student.getId();
	}

	public Page<Student> getList(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

	public Student get(Long id) {
		return studentRepository.findById(id).orElseThrow();
	}
}
