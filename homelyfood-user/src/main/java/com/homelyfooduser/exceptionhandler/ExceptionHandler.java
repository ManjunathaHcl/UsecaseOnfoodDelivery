package com.homelyfooduser.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.homelyfooduser.dto.ErrorDto;
import com.homelyfooduser.exception.InvalidDataException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, ErrorDto> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {

			ErrorDto errorResponseDto = new ErrorDto();
			errorResponseDto.setErrorCode(403);
			errorResponseDto.setErrorMessage(error.getDefaultMessage());

			String fieldName = ((FieldError) error).getField();
			errors.put(fieldName, errorResponseDto);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidDataException.class)
	public ErrorDto handleMethodArgumentNotValid(InvalidDataException exp) {

		ErrorDto errorResponseDto = new ErrorDto();
		errorResponseDto.setErrorCode(404);
		errorResponseDto.setErrorMessage(exp.getMessage());
		return errorResponseDto;
	}

}