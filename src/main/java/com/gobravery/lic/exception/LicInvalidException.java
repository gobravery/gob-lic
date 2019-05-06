package com.gobravery.lic.exception;

public class LicInvalidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LicInvalidException(String msg,Exception e){
		super(msg,e);
	}
	public LicInvalidException(String msg){
		super(msg);
	}
}
