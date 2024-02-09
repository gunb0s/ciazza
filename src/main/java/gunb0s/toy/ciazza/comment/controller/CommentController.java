package gunb0s.toy.ciazza.comment.controller;

import gunb0s.toy.ciazza.comment.controller.dto.CreateCommentDto;
import gunb0s.toy.ciazza.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@PostMapping("/comment")
	public Long createComment(@RequestBody @Valid CreateCommentDto createCommentDto) {
		return commentService.create(createCommentDto);
	}
}
