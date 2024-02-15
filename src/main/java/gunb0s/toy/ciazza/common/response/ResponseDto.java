package gunb0s.toy.ciazza.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseDto<T> {
	private Boolean result;
	private HttpStatus status;
	private T data;
	private String error = null;

	private ResponseDto(T data) {
		this.data = data;
	}

	private ResponseDto(HttpStatus status, String error) {
		this.status = status;
		this.error = error;
	}

	public static <T> ResponseDto<T> ok(T data) {
		ResponseDto<T> response = new ResponseDto<>(data);
		response.result = true;
		response.status = HttpStatus.OK;
		return response;
	}

	public static <T> ResponseDto<T> created(T data) {
		ResponseDto<T> response = new ResponseDto<>(data);
		response.result = true;
		response.status = HttpStatus.CREATED;
		return response;
	}

	public static <T> ResponseDto<T> error(HttpStatus status, String error) {
		ResponseDto<T> response = new ResponseDto<>(status, error);
		response.result = false;
		return response;
	}
}
