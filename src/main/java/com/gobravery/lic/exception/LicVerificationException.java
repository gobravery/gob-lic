package com.gobravery.lic.exception;

public class LicVerificationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LicVerificationException(String msg,Exception e){
		super(msg,e);
	}
	public LicVerificationException(String msg){
		super(msg);
	}
}
