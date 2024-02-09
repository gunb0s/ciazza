package gunb0s.toy.ciazza.enrollment.entity;

import gunb0s.toy.ciazza.common.entity.BaseEntity;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.user.entity.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name = "uk_enrollment_user_lecture",
						columnNames = {"user_id", "lecture_id"}
				)
		}
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Enrollment extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "enrollment_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lecture_id")
	private Lecture lecture;

	@Builder
	public Enrollment(Student student, Lecture lecture) {
		this.student = student;
		this.lecture = lecture;
	}
}
