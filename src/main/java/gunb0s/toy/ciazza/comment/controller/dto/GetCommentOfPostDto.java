package gunb0s.toy.ciazza.comment.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GetCommentOfPostDto {
	@NotNull
	private Long postId;
}
