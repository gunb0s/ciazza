package gunb0s.toy.ciazza.lecture.controller;

import gunb0s.toy.ciazza.lecture.controller.dto.CreateLectureDto;
import gunb0s.toy.ciazza.lecture.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LectureController {
	private final LectureService lectureService;

	@PostMapping("/lecture")
	public Long createLecture(@RequestBody @Valid CreateLectureDto createLectureDto) {
		return lectureService.createLecture(createLectureDto);
	}
}