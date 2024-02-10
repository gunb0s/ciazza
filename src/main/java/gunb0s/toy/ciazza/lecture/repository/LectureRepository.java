package gunb0s.toy.ciazza.lecture.repository;

import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.user.entity.Educator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
	Optional<Lecture> findByIdAndEducator(Long lectureId, Educator educator);

	@Query("select l from Lecture l join fetch l.educator where l.id = :lectureId")
	Optional<Lecture> findByIdWitEducator(Long lectureId);
}
