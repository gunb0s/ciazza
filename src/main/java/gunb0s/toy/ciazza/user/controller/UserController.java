package gunb0s.toy.ciazza.user.controller;

import gunb0s.toy.ciazza.comment.controller.dto.CommentDto;
import gunb0s.toy.ciazza.comment.entity.Comment;
import gunb0s.toy.ciazza.user.servicew.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping("/user/{userId}/comment")
	public Page<CommentDto> getUserComments(@PathVariable Long userId, Pageable pageable) {
		Page<Comment> userComments = userService.getUserComments(userId, pageable);
		return userComments.map(CommentDto::new);
	}
}
