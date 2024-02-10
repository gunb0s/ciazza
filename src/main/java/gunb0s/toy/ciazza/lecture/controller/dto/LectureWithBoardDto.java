package gunb0s.toy.ciazza.lecture.controller.dto;

import gunb0s.toy.ciazza.board.entity.Board;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.lecture.entity.Semester;
import lombok.Getter;

import java.util.List;

@Getter
public class LectureWithBoardDto {
	private Long id;
	private String name;
	private String lectureCode;
	private Long educatorId;
	private String educatorName;
	private Semester semester;
	private List<BoardDto> boards;

	public LectureWithBoardDto(Lecture lecture) {
		this.id = lecture.getId();
		this.name = lecture.getName();
		this.lectureCode = lecture.getLectureCode();
		this.educatorId = lecture.getEducator().getId();
		this.educatorName = lecture.getEducator().getName();
		this.semester = lecture.getSemester();
		this.boards = lecture.getBoards()
				.stream()
				.map(BoardDto::new)
				.toList();
	}

	@Getter
	private class BoardDto {
		private Long id;
		private String title;

		public BoardDto(Board board) {
			this.id = board.getId();
			this.title = board.getTitle();
		}
	}
}
