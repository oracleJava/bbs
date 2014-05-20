package com.bbs.df.exception;

public class UserNotFoundErrorException extends RuntimeException {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundErrorException(String message){
    	 super(message);
     }
}
