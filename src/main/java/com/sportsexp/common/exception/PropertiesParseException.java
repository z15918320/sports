package com.sportsexp.common.exception;

public class PropertiesParseException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PropertiesParseException() {
		super();
	}
	public PropertiesParseException(String message) {
		super(message);
	}
	public PropertiesParseException(String message, Throwable cause) {
		super(message, cause);
	}
	public PropertiesParseException(Throwable cause) {
		super(cause);
	}

}
