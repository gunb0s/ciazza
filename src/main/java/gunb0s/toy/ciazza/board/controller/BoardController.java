package gunb0s.toy.ciazza.board.controller;

import gunb0s.toy.ciazza.board.controller.dto.BoardDto;
import gunb0s.toy.ciazza.board.controller.dto.CreateBoardDto;
import gunb0s.toy.ciazza.board.entity.Board;
import gunb0s.toy.ciazza.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;

	@PostMapping("/board")
	public Long create(@RequestBody @Valid CreateBoardDto createBoardDto) {
		return boardService.create(createBoardDto);
	}

	@GetMapping("/board/{boardId}")
	public BoardDto get(@PathVariable Long boardId) {
		Board board = boardService.get(boardId);
		return new BoardDto(board);
	}
}
