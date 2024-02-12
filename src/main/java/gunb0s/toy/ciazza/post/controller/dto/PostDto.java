package gunb0s.toy.ciazza.post.controller.dto;

import gunb0s.toy.ciazza.post.entity.Post;
import lombok.Getter;

@Getter
public class PostDto {
	private Long id;
	private String title;
	private String content;
	private Long userId;
	private String username;

	public PostDto(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.userId = post.getUser().getId();
		this.username = post.getUser().getName();
	}
}
