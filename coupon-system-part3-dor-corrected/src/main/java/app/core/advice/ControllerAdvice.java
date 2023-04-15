package app.core.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import app.core.exceptions.CouponsSystemException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(value = CouponsSystemException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleException(CouponsSystemException e) {
		return e.getMessage();
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleException(Exception e) {
		return e.getMessage();
	}

}
