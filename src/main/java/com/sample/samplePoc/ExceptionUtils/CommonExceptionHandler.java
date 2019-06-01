package com.sample.samplePoc.ExceptionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CommonExceptionHandler {

	@Autowired
	public CommonExceptionHandler(MessageSource messageSource) {
		this.messageSourceAccesor = new MessageSourceAccessor(messageSource);
	}

	private MessageSourceAccessor messageSourceAccesor;

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public ResponseEntity<ResponseMessage> handleIllegalArgumentException(IllegalArgumentException ex,
			HttpServletResponse httpResponse) {
		return new ResponseEntity<>(new ResponseMessage(ResponseMessage.Code.INTERNAL_ERROR_GENERIC, ex.getMessage()),
				HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> handleTypeMismatchException(HttpServletRequest req,
			IllegalStateException ex) {
		return new ResponseEntity<>(new ResponseMessage(ResponseMessage.Code.INTERNAL_ERROR_GENERIC,
				messageSourceAccesor.getMessage("error.typeMismatch")), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> ValidationException(HttpServletRequest req, ValidationException ex) {
		return new ResponseEntity<>(
				new ResponseMessage(ex.getCode(), messageSourceAccesor.getMessage(ex.getMessage(), ex.getArgs())),
				HttpStatus.BAD_REQUEST);
	}

}
