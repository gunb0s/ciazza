package gunb0s.toy.ciazza.lecture.repository;

import gunb0s.toy.ciazza.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
