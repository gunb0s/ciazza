package gunb0s.toy.ciazza.user.controller.dto;

import gunb0s.toy.ciazza.enrollment.entity.Enrollment;
import gunb0s.toy.ciazza.lecture.entity.Semester;
import lombok.Getter;

@Getter
public class LectureDto {
	private Long id;
	private String name;
	private String lectureCode;
	private Long educatorId;
	private String educatorName;
	private Semester semester;

	public LectureDto(Enrollment enrollment) {
		this.id = enrollment.getLecture().getId();
		this.name = enrollment.getLecture().getName();
		this.lectureCode = enrollment.getLecture().getLectureCode();
		this.educatorId = enrollment.getLecture().getEducator().getId();
		this.educatorName = enrollment.getLecture().getEducator().getName();
		this.semester = enrollment.getLecture().getSemester();
	}
}
