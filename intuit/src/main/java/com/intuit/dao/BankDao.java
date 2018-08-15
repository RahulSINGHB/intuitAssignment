package com.intuit.dao;

import java.util.List;

import com.intuit.model.Account;
import com.intuit.model.Bank;
import com.intuit.model.TransactionData;

public interface BankDao {

	List<Bank> getbanklist();

	Bank getbankdata(String bankname);
	
	List<Account> getAccontData(String bankname);
	
	List<TransactionData> getTransactionData(String userId);

	Bank findBankById(String corpId);
	
}