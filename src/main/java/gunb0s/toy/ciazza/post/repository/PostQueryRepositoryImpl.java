package gunb0s.toy.ciazza.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gunb0s.toy.ciazza.post.controller.dto.PostSearchCondition;
import gunb0s.toy.ciazza.post.entity.Post;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static gunb0s.toy.ciazza.board.entity.QBoard.board;
import static gunb0s.toy.ciazza.post.entity.QPost.post;
import static gunb0s.toy.ciazza.user.entity.QUser.user;

@Repository
public class PostQueryRepositoryImpl implements PostQueryRepository {
	private JPAQueryFactory queryFactory;

	public PostQueryRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<Post> search(Long lectureId, Long boardId, PostSearchCondition postSearchCondition, Pageable pageable) {
		List<Post> results = queryFactory
				.selectFrom(post)
				.join(post.board, board)
				.join(post.user, user).fetchJoin()
				.where(
						lectureEq(lectureId),
						boardEq(boardId),
						titleContains(postSearchCondition.getTitle()),
						contentContains(postSearchCondition.getContent()),
						userEq(postSearchCondition.getUserId())
				).fetch();

		JPAQuery<Long> countQuery = queryFactory.select(post.count())
				.join(post.board, board)
				.where(
						lectureEq(lectureId),
						boardEq(boardId),
						titleContains(postSearchCondition.getTitle()),
						contentContains(postSearchCondition.getContent()),
						userEq(postSearchCondition.getUserId())
				);
		return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchCount);
	}

	private BooleanExpression contentContains(String content) {
		return content != null ? post.content.contains(content) : null;
	}

	private BooleanExpression userEq(Long userId) {
		return userId != null ? post.user.id.eq(userId) : null;
	}

	private BooleanExpression titleContains(String title) {
		return title != null ? post.title.contains(title) : null;
	}

	private BooleanExpression boardEq(Long boardId) {
		return boardId != null ? board.id.eq(boardId) : null;
	}

	private BooleanExpression lectureEq(Long lectureId) {
		return lectureId != null ? post.board.lecture.id.eq(lectureId) : null;
	}


}
