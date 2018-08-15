package com.intuit.contracts;

import java.util.List;

import com.intuit.model.Account;

public class AccountResponse extends BaseResponse {
	
	private List<Account> accounts;

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> account) {
		this.accounts = account;
	}
	
	
	
	

}
