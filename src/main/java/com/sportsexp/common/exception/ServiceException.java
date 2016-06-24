package com.sportsexp.common.exception;

 

/**
 * 
 * <p>ServiceException.java</p> 
 * <p>Title: Service层异常类</p>
 * <p>Description: Service层代码统一抛出的异常类，Controller层必须对此类异常进行捕获和处理。 </p>
 * <p>Department: 技术部 </p>
 * @author 王海波
 * @since 2013年8月21日  下午3:21:29
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -2440105546095427374L;
	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
