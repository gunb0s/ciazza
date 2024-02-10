package gunb0s.toy.ciazza.comment.entity;

import gunb0s.toy.ciazza.common.entity.BaseEntity;
import gunb0s.toy.ciazza.post.entity.Post;
import gunb0s.toy.ciazza.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostPersist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	private Long id;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_comment_id")
	private Comment parentComment;

	@OneToMany(mappedBy = "parentComment")
	private List<Comment> childComments = new ArrayList<>();

	@Column(name = "comment_group_id")
	private Long commentGroupId;

	@Column(name = "comment_order")
	private Integer commentOrder;
	
	private Integer depth;

	@Builder
	public Comment(String content, Post post, User user, Comment parentComment, Long commentGroupId, Integer commentOrder, Integer depth) {
		this.content = content;
		this.post = post;
		this.user = user;
		this.parentComment = parentComment;
		this.commentGroupId = commentGroupId;
		this.commentOrder = commentOrder;
		this.depth = depth;
	}

	public void setCommentGroupId(Long commentGroupId) {
		this.commentGroupId = commentGroupId;
	}

	@PostPersist
	public void postPersist() {
		if (commentGroupId == null) {
			commentGroupId = id;
		}
	}
}
