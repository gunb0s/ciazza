package gunb0s.toy.ciazza.post.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreatePostDto {
	@NotNull
	private String title;

	@NotNull
	private String content;

	@NotNull
	private Long boardId;

	@NotNull
	private Long lectureId;

	@NotNull
	private Long userId;
}
