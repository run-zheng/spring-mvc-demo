package org.demo.spring.mvc.exception;

public class BizFlowConfigException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1283744748930654480L;

	public BizFlowConfigException() {
		super(); 
	}
	
	public BizFlowConfigException(String message) {
		super(message); 
	}
	
	public BizFlowConfigException(String message, Throwable t) {
		super(message, t); 
	}
	
	public BizFlowConfigException(Throwable t) {
		super(t); 
	}
}
