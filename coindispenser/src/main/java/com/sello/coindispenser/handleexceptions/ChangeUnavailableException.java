package com.sello.coindispenser.handleexceptions;


import org.springframework.http.HttpStatus;

public class ChangeUnavailableException extends Exception{

	


	    public ChangeUnavailableException(ErrorCode errorCode, ErrorType type, String message) {
	    	super(errorCode, type, message );
	    	httpStatus = HttpStatus.NOT_FOUND;
		}

		public ChangeUnavailableException(HttpStatus internalServerError, ErrorCode coindispense004ApplicationErr,
				ErrorType applicationError, String string) {
			// TODO Auto-generated constructor stub
		}

	
}
