package gunb0s.toy.ciazza.lecture.repository;

import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.user.entity.Educator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
	Optional<Lecture> findByIdAndEducator(Long lectureId, Educator educator);
}
