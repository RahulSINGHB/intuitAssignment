package com.intuit.contracts;

import com.intuit.model.Bank;

public class BankBaseResponse extends BaseResponse{
	
	Bank bank;

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	
}
