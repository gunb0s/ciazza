package gunb0s.toy.ciazza.post.repository;

import gunb0s.toy.ciazza.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
