package com.sello.coindispenser.handleexceptions;

import org.springframework.http.HttpStatus;


public class Exception extends RuntimeException {
	

		protected HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		
		public void setHttpStatus(HttpStatus httpStatus) {
			this.httpStatus = httpStatus;
		}

		public void setErrorCode(ErrorCode errorCode) {
			this.errorCode = errorCode;
		}

		public void setType(ErrorType type) {
			this.type = type;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		private ErrorCode errorCode;
		private ErrorType type;
		private String message;

		public Exception(ErrorCode errorCode, ErrorType type, String message) {

			super(message);
			this.errorCode = errorCode;
			this.type = type;
			this.message = message;
		}

		public Exception() {
			// TODO Auto-generated constructor stub
		}

		public ErrorCode getErrorCode() {
			return errorCode;
		}

		public ErrorType getType() {
			return type;
		}

		public String getMessage() {
			return message;
		}

		public HttpStatus getHttpStatus() {
			return httpStatus;
		}

}
