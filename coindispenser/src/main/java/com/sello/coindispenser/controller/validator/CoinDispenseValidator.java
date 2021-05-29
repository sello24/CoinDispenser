package com.sello.coindispenser.controller.validator;

import com.sello.coindispenser.handleexceptions.ChangeUnavailableException;
import com.sello.coindispenser.handleexceptions.ErrorCode;
import com.sello.coindispenser.handleexceptions.ErrorType;
/*
 * Validator Class for the end point
 * 
 */
public class CoinDispenseValidator {
	
	
	public static void validateDispenseRequest(int amount, String leastNumberOfCoins) {
		if (amount < 1) {
			throw new ChangeUnavailableException(ErrorCode.COINDISPENSE_003_BUSINESS_LOGIC, ErrorType.BUSINESS_LOGIC,
					"Invalid input, amount should be a greater than 0");
		} else if (!(String.valueOf(Boolean.TRUE).equalsIgnoreCase(leastNumberOfCoins)
				|| String.valueOf(Boolean.FALSE).equalsIgnoreCase(leastNumberOfCoins))) {
			throw new ChangeUnavailableException(ErrorCode.COINDISPENSE_003_BUSINESS_LOGIC, ErrorType.BUSINESS_LOGIC,
					"Invalid input, amount should be a greater than 0");
		}
	}
	
	

}
