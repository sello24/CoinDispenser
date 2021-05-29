package com.sello.coindispenser.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sello.coindispenser.handleexceptions.ChangeUnavailableException;
import com.sello.coindispenser.handleexceptions.DatabaseError;
import com.sello.coindispenser.handleexceptions.ErrorCode;
import com.sello.coindispenser.handleexceptions.ErrorType;
import com.sello.coindispenser.handleexceptions.Exception;
@ControllerAdvice
public class DispenserControllerAdvice {
	
	@ExceptionHandler(value = ChangeUnavailableException.class)	
	public ResponseEntity<Object>exception(ChangeUnavailableException infe){
		
		Exception errorResp = new Exception();
		errorResp.setErrorCode(infe.getErrorCode());
		errorResp.setType(infe.getType());
		errorResp.setMessage(infe.getMessage());
		
		return ResponseEntity.status(infe.getHttpStatus()).body(errorResp);
	}
	
	@ExceptionHandler(value = RuntimeException.class)	
	public ResponseEntity<Object>exception(RuntimeException re){
		
		ChangeUnavailableException applicationException = new ChangeUnavailableException(ErrorCode.COINDISPENSE_004_APPLICATION_ERR, ErrorType.APPLICATION_ERROR, "Application error");
		DatabaseError errorResp = new DatabaseError();
		errorResp.setErrorCode(applicationException.getErrorCode());
		errorResp.setType(applicationException.getType());
		errorResp.setMessage(applicationException.getMessage());
		
		return ResponseEntity.status(applicationException.getHttpStatus()).body(errorResp);
	}


}
