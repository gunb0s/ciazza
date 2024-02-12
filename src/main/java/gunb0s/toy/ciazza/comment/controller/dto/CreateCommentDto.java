package gunb0s.toy.ciazza.comment.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommentDto {
	@NotNull
	private String content;

	@NotNull
	private Long postId;

	@NotNull
	private Long userId;

	private Long parentCommentId;

	public Boolean isRootCommentRequest() {
		return parentCommentId == null;
	}
}
