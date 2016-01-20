package edu.tcu.mi.spring.web.exception;

import org.apache.log4j.Logger;

public class CustomGenericException extends RuntimeException {

	public static Logger logger = Logger.getLogger(CustomGenericException.class);
	private static final long serialVersionUID = 1L;
 
	private String errCode;
	private String errMsg;
 
	//getter and setter methods
 
	public CustomGenericException(String errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;

		logger.info(errCode);
		logger.info(errMsg);
	}
 
}
