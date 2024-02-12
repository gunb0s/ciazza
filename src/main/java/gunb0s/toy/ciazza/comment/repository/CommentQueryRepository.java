package gunb0s.toy.ciazza.comment.repository;

import gunb0s.toy.ciazza.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentQueryRepository {
	public Page<Comment> findAllCommentsOfPost(Long postId, Pageable pageable);

	public Page<Comment> findUserComments(Long userId, Pageable pageable);
}
