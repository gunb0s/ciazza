package gunb0s.toy.ciazza.board.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateBoardDto {
	@NotNull
	private String title;

	@NotNull
	private Long lectureId;

	@NotNull
	private Long educatorId;
}
