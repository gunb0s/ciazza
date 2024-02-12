package gunb0s.toy.ciazza.comment.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCommentOfPostDto {
	@NotNull
	private Long postId;
}
