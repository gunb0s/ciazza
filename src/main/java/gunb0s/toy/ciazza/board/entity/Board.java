package gunb0s.toy.ciazza.board.entity;

import gunb0s.toy.ciazza.common.entity.BaseEntity;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
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
public class Board extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "board_id")
	private Long id;

	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lecture_id")
	private Lecture lecture;
}
