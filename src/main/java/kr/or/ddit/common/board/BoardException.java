package kr.or.ddit.common.board;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class BoardException extends RuntimeException{

	public BoardException() {
		super();
	}

	public BoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BoardException(String message, Throwable cause) {
		super(message, cause);
	}

	public BoardException(String message) {
		super(message);
	}

	public BoardException(Throwable cause) {
		super(cause);
	}
	
}
