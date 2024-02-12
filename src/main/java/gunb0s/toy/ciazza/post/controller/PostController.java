package gunb0s.toy.ciazza.post.controller;

import gunb0s.toy.ciazza.post.controller.dto.CreatePostDto;
import gunb0s.toy.ciazza.post.controller.dto.GetPostListDto;
import gunb0s.toy.ciazza.post.controller.dto.PostDto;
import gunb0s.toy.ciazza.post.controller.dto.PostSearchCondition;
import gunb0s.toy.ciazza.post.entity.Post;
import gunb0s.toy.ciazza.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@PostMapping("/post")
	public Long create(@RequestBody @Valid CreatePostDto createPostDto) {
		return postService.create(createPostDto);
	}

	@GetMapping("/post/{postId}")
	public PostDto get(@PathVariable Long postId) {
		Post post = postService.get(postId);
		return new PostDto(post);
	}

	@GetMapping("/post")
	public Page<PostDto> getList(
			@Valid GetPostListDto getPostListDto,
			PostSearchCondition postSearchCondition,
			Pageable pageable) {
		Page<Post> list = postService.getList(getPostListDto, postSearchCondition, pageable);
		return list.map(PostDto::new);
	}
}
