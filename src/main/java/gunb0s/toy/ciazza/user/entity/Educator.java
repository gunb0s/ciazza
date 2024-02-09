package gunb0s.toy.ciazza.user.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("E")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Educator extends User {
	@Builder
	public Educator(String name) {
		super(name);
	}
}
