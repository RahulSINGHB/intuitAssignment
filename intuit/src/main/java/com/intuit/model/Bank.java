package com.intuit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Bank {
	
	@Id
	private String bankid;
	private String bankName;
	private String bankEmail;
	private String bankPhone;
	
	public Bank(String bankName, String bankEmail, String bankPhone) {
		super();
		this.bankName = bankName;
		this.bankEmail = bankEmail;
		this.bankPhone = bankPhone;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankEmail() {
		return bankEmail;
	}
	public void setBankEmail(String bankEmail) {
		this.bankEmail = bankEmail;
	}
	public String getBankPhone() {
		return bankPhone;
	}
	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}
	
	

}
