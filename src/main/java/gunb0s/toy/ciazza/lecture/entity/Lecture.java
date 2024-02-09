package gunb0s.toy.ciazza.lecture.entity;

import gunb0s.toy.ciazza.common.entity.BaseEntity;
import gunb0s.toy.ciazza.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Lecture extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "lecture_id")
	private Long id;

	private String name;

	@Column(length = 10)
	private String lectureCode;

	@Column(unique = true)
	private String registrationCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Enumerated(EnumType.STRING)
	private Semester semester;
}
