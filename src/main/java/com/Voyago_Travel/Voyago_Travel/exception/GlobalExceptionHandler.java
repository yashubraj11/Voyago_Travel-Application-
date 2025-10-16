package com.Voyago_Travel.Voyago_Travel.exception;

import java.util.InputMismatchException;
import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Voyago_Travel.Voyago_Travel.dto.ErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
@ExceptionHandler(MethodArgumentNotValidException.class)
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public ErrorDto handle(MethodArgumentNotValidException exception) {
	return new ErrorDto(exception.getMessage());
}

@ExceptionHandler(DataExistsException.class)
@ResponseStatus(code = HttpStatus.CONFLICT)
public ErrorDto handle(DataExistsException exception) {
	return new ErrorDto(exception.getMessage());
}

@ExceptionHandler(DataNotFoundException.class)
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public ErrorDto handle(DataNotFoundException exception) {
	return new ErrorDto(exception.getMessage());
}

@ExceptionHandler(TimeoutException.class)
@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT)
public ErrorDto handler(TimeoutException exception) {
return new ErrorDto(exception.getMessage());
}

@ExceptionHandler(InputMismatchException.class)
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public ErrorDto handle(InputMismatchException exception) {
	return new ErrorDto(exception.getMessage());
}

@ExceptionHandler(NullPointerException.class)
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public ErrorDto handler(NullPointerException exception) {
	return new ErrorDto("Sorry , something Went Wrong , WE Will fix it");
}

}
