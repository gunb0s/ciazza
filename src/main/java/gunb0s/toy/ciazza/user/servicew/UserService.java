package gunb0s.toy.ciazza.user.servicew;

import gunb0s.toy.ciazza.comment.entity.Comment;
import gunb0s.toy.ciazza.comment.repository.CommentQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
	private final CommentQueryRepository commentQueryRepository;

	public Page<Comment> getUserComments(Long userId, Pageable pageable) {
		return commentQueryRepository.findUserComments(userId, pageable);
	}
}
