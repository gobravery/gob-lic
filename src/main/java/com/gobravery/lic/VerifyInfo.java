package com.gobravery.lic;

import java.io.Serializable;
import java.util.Date;

public class VerifyInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String info;
	private Date notBefore;
	private Date notAfter;
	private Date issued;
	private int consumerAmount;
	private String consumerType;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Date getIssued() {
		return issued;
	}
	public void setIssued(Date issued) {
		this.issued = issued;
	}
	public String getConsumerType() {
		return consumerType;
	}
	public void setConsumerType(String consumerType) {
		this.consumerType = consumerType;
	}
	public Date getNotBefore() {
		return notBefore;
	}
	public void setNotBefore(Date notBefore) {
		this.notBefore = notBefore;
	}
	public Date getNotAfter() {
		return notAfter;
	}
	public void setNotAfter(Date notAfter) {
		this.notAfter = notAfter;
	}
	public int getConsumerAmount() {
		return consumerAmount;
	}
	public void setConsumerAmount(int consumerAmount) {
		this.consumerAmount = consumerAmount;
	}
	
}
