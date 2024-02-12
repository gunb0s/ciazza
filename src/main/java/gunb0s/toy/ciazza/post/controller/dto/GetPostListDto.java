package gunb0s.toy.ciazza.post.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPostListDto {
	@NotNull
	private Long lectureId;

	private Long boardId;
}
