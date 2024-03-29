package gunb0s.toy.ciazza.lecture.service;

import gunb0s.toy.ciazza.enrollment.entity.Enrollment;
import gunb0s.toy.ciazza.enrollment.repository.EnrollmentRepository;
import gunb0s.toy.ciazza.lecture.controller.dto.CreateLectureDto;
import gunb0s.toy.ciazza.lecture.controller.dto.EnrollLectureDto;
import gunb0s.toy.ciazza.lecture.controller.dto.LectureSearchCondition;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.lecture.repository.LectureQueryRepository;
import gunb0s.toy.ciazza.lecture.repository.LectureRepository;
import gunb0s.toy.ciazza.user.entity.Educator;
import gunb0s.toy.ciazza.user.entity.Student;
import gunb0s.toy.ciazza.user.repository.EducatorRepository;
import gunb0s.toy.ciazza.user.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureService {
	private final LectureRepository lectureRepository;
	private final LectureQueryRepository lectureQueryRepository;
	private final EducatorRepository educatorRepository;
	private final StudentRepository studentRepository;
	private final EnrollmentRepository enrollmentRepository;

	@Transactional
	public Long createLecture(CreateLectureDto createLectureDto) {
		Educator educator = educatorRepository.findById(createLectureDto.getEducatorId()).orElseThrow();

		String registrationCode = String.valueOf(new Random().nextInt(10000000, 100000000 - 1));

		Lecture lecture = Lecture.builder()
				.name(createLectureDto.getName())
				.lectureCode(createLectureDto.getLectureCode())
				.educator(educator)
				.registrationCode(registrationCode)
				.semester(createLectureDto.getSemester())
				.build();

		lectureRepository.save(lecture);

		return lecture.getId();
	}

	@Transactional
	public Long enroll(Long lectureId, EnrollLectureDto enrollLectureDto) {
		Lecture lecture = lectureRepository.findById(lectureId).orElseThrow();
		Student student = studentRepository.findById(enrollLectureDto.getStudentId()).orElseThrow();

		if (!lecture.getRegistrationCode().equals(enrollLectureDto.getRegistrationCode())) {
			throw new IllegalArgumentException("Invalid registration code");
		}

		Enrollment enrollment = Enrollment.builder()
				.lecture(lecture)
				.student(student)
				.build();

		enrollmentRepository.save(enrollment);
		return enrollment.getId();
	}

	public Page<Lecture> getList(LectureSearchCondition lectureSearchCondition, Pageable pageable) {
		return lectureQueryRepository.findAllBySearchCondition(lectureSearchCondition, pageable);
	}

	public Lecture get(Long lectureId) {
		return lectureRepository.findByIdWitEducator(lectureId).orElseThrow();
	}
}
