package com.sportsexp.common.exception;
/**
 * 
 * <p>ControllerException.java</p> 
 * <p>Title: Controller统一异常处理</p>
 * <p>Description: Detail Description </p>
 * <p>Department: 技术部 </p>
 * @author 王海波
 * @since 2013年8月23日  下午4:46:43
 */
public class ControllerException extends RuntimeException{
	private static final long serialVersionUID = -7645633187085897783L;
	public ControllerException() {
		super();
	}
	public ControllerException(String message) {
		super(message);
	}
	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}
	public ControllerException(Throwable cause) {
		super(cause);
	}
}
