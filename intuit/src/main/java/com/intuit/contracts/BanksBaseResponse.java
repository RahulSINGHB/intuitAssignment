package com.intuit.contracts;

import java.util.List;

import com.intuit.model.Bank;

public class BanksBaseResponse extends BaseResponse{
	
	List<Bank> banks;

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}
	
	
}
