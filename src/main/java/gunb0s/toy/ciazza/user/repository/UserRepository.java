package gunb0s.toy.ciazza.user.repository;

import gunb0s.toy.ciazza.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
