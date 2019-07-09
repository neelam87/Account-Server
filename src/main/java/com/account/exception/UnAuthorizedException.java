/**
 * 
 */
package com.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author x169185
 *
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnAuthorizedException(String message, Exception ex) {
		super(message, ex);
	}

}
