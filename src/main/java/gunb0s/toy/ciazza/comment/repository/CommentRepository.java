package gunb0s.toy.ciazza.comment.repository;

import gunb0s.toy.ciazza.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
