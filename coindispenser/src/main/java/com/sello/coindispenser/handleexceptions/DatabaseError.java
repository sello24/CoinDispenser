package com.sello.coindispenser.handleexceptions;

import org.springframework.http.HttpStatus;

public class DatabaseError extends Exception{
	public DatabaseError(HttpStatus status,ErrorCode errorCode, ErrorType type, String message) {
		super(errorCode, type, message);
	}

	public DatabaseError() {
		// TODO Auto-generated constructor stub
	}
}
