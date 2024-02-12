package gunb0s.toy.ciazza.post.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSearchCondition {
	private String title;
	private String content;
	private Long userId;
}
