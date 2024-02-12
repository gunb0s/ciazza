package gunb0s.toy.ciazza.post.repository;

import gunb0s.toy.ciazza.post.controller.dto.PostSearchCondition;
import gunb0s.toy.ciazza.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostQueryRepository {
	public Page<Post> search(
			Long lectureId,
			Long boardID,
			PostSearchCondition postSearchCondition,
			Pageable pageable);
}
