package gunb0s.toy.ciazza.common.errorController;

import gunb0s.toy.ciazza.common.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<ResponseDto<?>> handleNoSuchElementException(NoSuchElementException e) {
		ResponseDto<?> responseDto = ResponseDto.error(
				HttpStatus.NOT_FOUND,
				e.getMessage()
		);

		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(responseDto);
	}
}
