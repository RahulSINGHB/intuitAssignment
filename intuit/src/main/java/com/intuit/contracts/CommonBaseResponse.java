package com.intuit.contracts;

import com.intuit.model.Bank;

public class CommonBaseResponse extends BaseResponse{
	
	UserResponse user;
	Bank bank;

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	
	
}
