package gunb0s.toy.ciazza.comment.controller.dto;

import gunb0s.toy.ciazza.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentDto {
	private Long id;
	private String content;
	private Long parentCommentId;
	private Long userId;
	private String username;

	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
		this.parentCommentId = comment.getParentComment() == null
				? null
				: comment.getParentComment().getId();
		this.userId = comment.getUser().getId();
		this.username = comment.getUser().getName();
	}
}
