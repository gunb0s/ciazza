package gunb0s.toy.ciazza.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseDto<T> {
	private Boolean result;
	private HttpStatus status;
	private T data;

	private ResponseDto(T data) {
		this.data = data;
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
}
