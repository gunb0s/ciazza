package gunb0s.toy.ciazza.lecture.controller;

import gunb0s.toy.ciazza.lecture.controller.dto.CreateLectureDto;
import gunb0s.toy.ciazza.lecture.controller.dto.EnrollLectureDto;
import gunb0s.toy.ciazza.lecture.controller.dto.GetLectureDto;
import gunb0s.toy.ciazza.lecture.controller.dto.LectureSearchCondition;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.lecture.service.LectureService;
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
public class LectureController {
	private final LectureService lectureService;

	@PostMapping("/lecture")
	public Long create(@RequestBody @Valid CreateLectureDto createLectureDto) {
		return lectureService.createLecture(createLectureDto);
	}

	@PostMapping("/lecture/{lectureId}")
	public Long enroll(@PathVariable Long lectureId, @RequestBody @Valid EnrollLectureDto enrollLectureDto) {
		return lectureService.enroll(lectureId, enrollLectureDto);
	}

	@GetMapping("/lecture")
	public Page<GetLectureDto> getList(LectureSearchCondition lectureSearchCondition, Pageable pageable) {
		Page<Lecture> lectures = lectureService.getList(lectureSearchCondition, pageable);
		return lectures.map(GetLectureDto::new);
	}
}
