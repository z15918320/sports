package com.sportsexp.common.exception;


public class BeanFactoryException extends ServiceException {
	private static final long serialVersionUID = -3174210907716779183L;
	public BeanFactoryException(String message) {
		super(message);
	}
	public BeanFactoryException(String message, Throwable cause) {
		super(message, cause);
	}
}
