package com.intuit.services;

import com.intuit.contracts.AccountResponse;
import com.intuit.contracts.BankBaseResponse;
import com.intuit.contracts.BanksBaseResponse;
import com.intuit.contracts.CommonBaseResponse;
import com.intuit.contracts.TransactionDataResponse;
import com.intuit.contracts.UserRequest;

public interface Service {

	CommonBaseResponse registerUser(UserRequest userRequest);

	CommonBaseResponse loginUser(UserRequest userRequest);

	BanksBaseResponse getbanklist();

	BankBaseResponse getbankdata(String bankname);
	
	AccountResponse getAccontData(String bankname);
	
	TransactionDataResponse getTransactionData(String accountnumber);

	CommonBaseResponse logintobank(UserRequest userRequest, String corpId);

}