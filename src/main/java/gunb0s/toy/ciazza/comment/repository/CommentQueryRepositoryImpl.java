package gunb0s.toy.ciazza.comment.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gunb0s.toy.ciazza.comment.entity.Comment;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static gunb0s.toy.ciazza.comment.entity.QComment.comment;
import static gunb0s.toy.ciazza.user.entity.QUser.user;

@Repository
public class CommentQueryRepositoryImpl implements CommentQueryRepository {
	private final JPAQueryFactory queryFactory;

	public CommentQueryRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	public Page<Comment> findAllCommentsOfPost(Long postId, Pageable pageable) {
		List<Comment> result = queryFactory.selectFrom(comment)
				.join(comment.user, user).fetchJoin()
				.where(comment.post.id.eq(postId))
				.orderBy(comment.commentGroupId.asc(), comment.commentOrder.asc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		JPAQuery<Comment> countQuery = queryFactory.selectFrom(comment)
				.where(comment.post.id.eq(postId));

		return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchCount);
	}
}
