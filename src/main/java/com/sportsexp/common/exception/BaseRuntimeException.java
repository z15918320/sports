package com.sportsexp.common.exception;



/**
 * 
 * <p>BaseRuntimeException.java</p> 
 * <p>Title: 基本异常, 继承自RuntimeException</p>
 * <p>Description: 从由Spring管理事务的函数中抛出时会触发事务回滚 </p>
 * <p>Department: 技术部 </p>
 * @author 王海波
 * @since 2013年8月21日  下午3:19:54
 */
public class BaseRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -5020667319636948983L;
	public BaseRuntimeException() {
		super();
	}
	public BaseRuntimeException(String message) {
		super(message);
	}
	public BaseRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	public BaseRuntimeException(Throwable cause) {
		super(cause);
	}
}

