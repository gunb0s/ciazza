package gunb0s.toy.ciazza.comment.repository;

import gunb0s.toy.ciazza.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	@Query("select max(c.commentOrder) from Comment c where c.commentGroupId = :commentGroupId")
	public Integer maxCommentOrderByCommentGroupId(Long commentGroupId);

	@Query("select max(c.commentOrder) from Comment c where c.commentGroupId = :commentGroupId and c.parentComment = :parentComment")
	public Integer maxCommentOrderByCommentGroupIdAndParentComment(Long commentGroupId, Comment parentComment);

	@Modifying
	@Query("update Comment c set c.commentOrder = c.commentOrder + 1 where c.commentGroupId = :commentGroupId and c.commentOrder >= :order")
	public Integer updateCommentOrderGoeThanOrder(Long commentGroupId, Integer order);
}
