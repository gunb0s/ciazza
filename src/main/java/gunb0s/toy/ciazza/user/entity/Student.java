package gunb0s.toy.ciazza.user.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("S")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student extends User {
	@Builder
	public Student(String name) {
		super(name);
	}
}
