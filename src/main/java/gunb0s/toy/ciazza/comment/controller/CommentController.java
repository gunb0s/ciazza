package gunb0s.toy.ciazza.comment.controller;

import gunb0s.toy.ciazza.comment.controller.dto.CommentDto;
import gunb0s.toy.ciazza.comment.controller.dto.CreateCommentDto;
import gunb0s.toy.ciazza.comment.controller.dto.GetCommentOfPostDto;
import gunb0s.toy.ciazza.comment.entity.Comment;
import gunb0s.toy.ciazza.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/comment")
	public Page<CommentDto> getCommentOfPost(@RequestBody @Valid GetCommentOfPostDto getCommentOfPostDto, Pageable pageable) {
		Page<Comment> comments = commentService.getCommentOfPost(getCommentOfPostDto, pageable);
		return comments.map(CommentDto::new);
	}
}
