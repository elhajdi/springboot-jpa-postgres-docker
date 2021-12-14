package com.topcoder.trains.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.HttpRequestMethodNotSupportedException;


/*import  com.topcoder.trains.demo.exception.ErrorMessage;
import  com.topcoder.trains.demo.exception.ResourceNotFoundException;
import  com.topcoder.trains.demo.exception.InvalidRequestException;
import java.util.Date;*/

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException rnfex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
			HttpStatus.NOT_FOUND.value(),
			// new Date(),
			rnfex.getMessage()
			// request.getDescription(false)
		);

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	}

	/*@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ErrorMessage> invalidRequestException(InvalidRequestException ire) {
		ErrorMessage message = new ErrorMessage(
		 	HttpStatus.METHOD_NOT_ALLOWED.value(),
		 	ire.getMessage()
		);

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.METHOD_NOT_ALLOWED);
	}*/

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorMessage> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException hrmex) /*throws IOException */ {
		ErrorMessage message = new ErrorMessage(
			HttpStatus.METHOD_NOT_ALLOWED.value(),
			// new Date(),
			"invalid endpoint" //hrmex.getMessage()
			// request.getDescription(false)
		);
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(EmptyMessageException.class)
	public ResponseEntity<ErrorMessage> okSimpleMessage(EmptyMessageException emexcp) {
		ErrorMessage message = new ErrorMessage(
			HttpStatus.OK.value(),
			// new Date(),
			emexcp.getMessage()
			// request.getDescription(false)
		);
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.OK);
	}
}