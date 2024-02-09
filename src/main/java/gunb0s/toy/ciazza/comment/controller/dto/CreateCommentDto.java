package gunb0s.toy.ciazza.comment.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCommentDto {
	@NotNull
	private String content;

	@NotNull
	private Long postId;

	@NotNull
	private Long userId;
}
