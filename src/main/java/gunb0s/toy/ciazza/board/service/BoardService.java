package gunb0s.toy.ciazza.board.service;

import gunb0s.toy.ciazza.board.controller.dto.CreateBoardDto;
import gunb0s.toy.ciazza.board.entity.Board;
import gunb0s.toy.ciazza.board.repository.BoardRepository;
import gunb0s.toy.ciazza.lecture.entity.Lecture;
import gunb0s.toy.ciazza.lecture.repository.LectureRepository;
import gunb0s.toy.ciazza.user.repository.EducatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
	private final BoardRepository boardRepository;
	private final LectureRepository lectureRepository;
	private final EducatorRepository educatorRepository;

	@Transactional
	public Long create(CreateBoardDto createBoardDto) {
		boolean exists = educatorRepository.existsById(createBoardDto.getEducatorId());
		if (!exists) {
			throw new IllegalArgumentException("Educator not found");
		}
		Lecture lecture = lectureRepository.findById(createBoardDto.getLectureId()).orElseThrow();
		if (lecture.getId() != createBoardDto.getEducatorId()) {
			throw new IllegalArgumentException("Educator and lecture do not match");
		}

		Board board = Board.builder()
				.title(createBoardDto.getTitle())
				.lecture(lecture)
				.build();

		boardRepository.save(board);
		return board.getId();
	}
}
