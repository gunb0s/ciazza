package gunb0s.toy.ciazza.lecture.entity;

import gunb0s.toy.ciazza.board.entity.Board;
import gunb0s.toy.ciazza.common.entity.BaseEntity;
import gunb0s.toy.ciazza.user.entity.Educator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(name = "uk_lecture_registration_code", columnNames = {"registration_code"}),
				@UniqueConstraint(name = "uk_lecture_lecture_code_and_semester", columnNames = {"lecture_code", "semester"})
		}
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "lecture_id")
	private Long id;

	private String name;

	private String lectureCode;

	@Column(length = 8)
	private String registrationCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Educator educator;

	@OneToMany(mappedBy = "lecture")
	private List<Board> boards = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private Semester semester;

	@Builder
	public Lecture(String name, String lectureCode, String registrationCode, Educator educator, Semester semester) {
		this.name = name;
		this.lectureCode = lectureCode;
		this.registrationCode = registrationCode;
		this.educator = educator;
		this.semester = semester;
	}
}
