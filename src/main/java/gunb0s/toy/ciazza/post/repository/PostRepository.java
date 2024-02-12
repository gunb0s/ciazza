package gunb0s.toy.ciazza.post.repository;

import gunb0s.toy.ciazza.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
	@Query("select p from Post p join fetch p.user where p.id = :postId")
	Optional<Post> findByIdWithUser(Long postId);

	@Query("select p from Post p join fetch p.board where p.id = :postId")
	Optional<Post> findByIdWithBoard(Long postId);
}
