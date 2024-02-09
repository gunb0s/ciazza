package gunb0s.toy.ciazza.post.entity;

import gunb0s.toy.ciazza.board.entity.Board;
import gunb0s.toy.ciazza.common.entity.BaseEntity;
import gunb0s.toy.ciazza.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Post extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "post_id")
	private Long id;

	private String title;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
