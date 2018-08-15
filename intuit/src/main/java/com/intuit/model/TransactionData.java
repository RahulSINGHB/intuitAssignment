package com.intuit.model;

import java.util.Date;

public class TransactionData {
	private Long transactionId;
	private Bank Bank;
	private Account account;
	private Date transactionDate;
	
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Bank getBank() {
		return Bank;
	}
	public void setBank(Bank bank) {
		Bank = bank;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
}
