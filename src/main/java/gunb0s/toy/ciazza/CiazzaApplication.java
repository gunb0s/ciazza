package gunb0s.toy.ciazza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CiazzaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CiazzaApplication.class, args);
	}

}
