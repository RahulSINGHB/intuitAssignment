package com.intuit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.contracts.AccountResponse;
import com.intuit.contracts.BankBaseResponse;
import com.intuit.contracts.BanksBaseResponse;
import com.intuit.contracts.CommonBaseResponse;
import com.intuit.contracts.TransactionDataResponse;
import com.intuit.contracts.UserRequest;
import com.intuit.services.Service;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(maxAge=4000)
public class UserRestController {

	@Autowired
	private Service service;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public CommonBaseResponse registerUser(@RequestBody UserRequest userRequest) {
		return service.registerUser(userRequest);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public CommonBaseResponse loginUser(@RequestBody UserRequest userRequest) {
		CommonBaseResponse customers = service.loginUser(userRequest);
		return customers;
	}
	@RequestMapping(value = "/getbanklist", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public BanksBaseResponse getbanklist() {
		return service.getbanklist();
	}
	@RequestMapping(value = "/getbankdata/{bankname}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public BankBaseResponse getbankdata(@PathVariable("bankname") String bankname) {
		return service.getbankdata(bankname);
	}
	@RequestMapping(value = "/logintobank/{corpId}", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public CommonBaseResponse logintobank(@RequestBody UserRequest userRequest, @PathVariable("bankname") String bankname) {
		CommonBaseResponse customers = service.logintobank(userRequest,bankname);
		return customers;
	}
	@RequestMapping(value = "/getaccounts/{bankname}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public AccountResponse getaccounts(@PathVariable("bankname") String bankname) {
		AccountResponse accounts = service.getAccontData(bankname);
		return accounts;
	}
	@RequestMapping(value = "/gettransactiondata/{accountnumber}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public TransactionDataResponse gettransactiondata(@PathVariable("accountnumber") String accountnumber) {
		TransactionDataResponse transactionData = service.getTransactionData(accountnumber);
		return transactionData;
	}
}