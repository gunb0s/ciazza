package gunb0s.toy.ciazza.user.repository;

import gunb0s.toy.ciazza.user.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
