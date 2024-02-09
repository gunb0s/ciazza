package gunb0s.toy.ciazza.board.repository;

import gunb0s.toy.ciazza.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
