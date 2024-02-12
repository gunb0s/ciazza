package gunb0s.toy.ciazza.board.controller.dto;

import gunb0s.toy.ciazza.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardDto {
	private Long id;
	private String title;

	public BoardDto(Board board) {
		this.id = board.getId();
		this.title = board.getTitle();
	}
}
