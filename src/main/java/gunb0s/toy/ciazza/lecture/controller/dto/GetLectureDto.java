package gunb0s.toy.ciazza.lecture.controller.dto;

import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.lecture.entity.Semester;
import lombok.Getter;

@Getter
public class GetLectureDto {
	private Long id;
	private String name;
	private String lectureCode;
	private Long educatorId;
	private String educatorName;
	private Semester semester;

	public GetLectureDto(Lecture lecture) {
		this.id = lecture.getId();
		this.name = lecture.getName();
		this.lectureCode = lecture.getLectureCode();
		this.educatorId = lecture.getEducator().getId();
		this.educatorName = lecture.getEducator().getName();
		this.semester = lecture.getSemester();
	}
}
