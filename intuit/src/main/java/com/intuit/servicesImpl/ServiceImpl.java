package com.intuit.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.intuit.contracts.AccountResponse;
import com.intuit.contracts.BankBaseResponse;
import com.intuit.contracts.BanksBaseResponse;
import com.intuit.contracts.CommonBaseResponse;
import com.intuit.contracts.TransactionDataResponse;
import com.intuit.contracts.UserRequest;
import com.intuit.contracts.UserResponse;
import com.intuit.dao.BankDao;
import com.intuit.dao.UserDao;
import com.intuit.model.Account;
import com.intuit.model.Bank;
import com.intuit.model.TransactionData;
import com.intuit.model.User;
import com.intuit.services.Service;
import com.intuit.util.CONSTANT;

@Repository
public class ServiceImpl implements Service {
	
	Mapper mapper = new DozerBeanMapper();
	
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BankDao bankDao;
	
	@Override
	public CommonBaseResponse registerUser(UserRequest userRequest) {
		CommonBaseResponse commonBaseResponse = new CommonBaseResponse();
		UserResponse userResponse = null;
		User userEntity = new User();
		Boolean exits = userDao.findByUsername(userRequest.getUserName());
		if(exits){
			commonBaseResponse.setStatus(CONSTANT.FAIL);
			commonBaseResponse.setStatusCode(CONSTANT.FAIL_CODE);
			commonBaseResponse.setMessage(CONSTANT.USER_NAME_ALREADY_EXISTS);
		}else{
			
			mapper.map(userRequest, userEntity);
			userEntity = userDao.saveUser(userEntity);
			if(userEntity != null){
				userResponse = new UserResponse();
				mapper.map(userEntity, userResponse);
				commonBaseResponse.setStatus(CONSTANT.SUCCESS);
				commonBaseResponse.setStatusCode(CONSTANT.SUCCESS_CODE);
				commonBaseResponse.setMessage(CONSTANT.DATA_PERSIST_SUCCESS);
				commonBaseResponse.setUser(userResponse);
			}else{
				commonBaseResponse.setStatus(CONSTANT.FAIL);
				commonBaseResponse.setStatusCode(CONSTANT.FAIL_CODE);
				commonBaseResponse.setMessage(CONSTANT.DATA_PERSISTENT_ERROR);
			}
		}
		return commonBaseResponse;
	}

	@Override
	public CommonBaseResponse loginUser(UserRequest userRequest) {
		CommonBaseResponse commonBaseResponse = new CommonBaseResponse();
		UserResponse userResponse = new UserResponse();
		User user = userDao.loginUser(userRequest.getUserName(), userRequest.getPassword());
		if(user != null){
			mapper.map(user, userResponse);
			commonBaseResponse.setStatus(CONSTANT.SUCCESS);
			commonBaseResponse.setStatusCode(CONSTANT.SUCCESS_CODE);
			commonBaseResponse.setUser(userResponse);
		}else{
			commonBaseResponse.setStatus(CONSTANT.FAIL);
			commonBaseResponse.setStatusCode(CONSTANT.FAIL_CODE);
			commonBaseResponse.setMessage(CONSTANT.LOGIN_FAILED_MESSAGE);
		}
		return commonBaseResponse;
	}

	@Override
	public BanksBaseResponse getbanklist() {
		BanksBaseResponse bankBaseResponse = new BanksBaseResponse();
		List<Bank> banks = bankDao.getbanklist();
		if(banks != null && !banks.isEmpty()){
			bankBaseResponse.setBanks(banks);
			bankBaseResponse.setMessage(CONSTANT.FOUND_RECORD);
			bankBaseResponse.setStatus(CONSTANT.SUCCESS);
			bankBaseResponse.setStatusCode(CONSTANT.SUCCESS_CODE);
		}else{
			banks  = new ArrayList<>();
			bankBaseResponse.setBanks(banks);
			bankBaseResponse.setMessage(CONSTANT.FOUND_RECORD);
			bankBaseResponse.setStatus(CONSTANT.SUCCESS);
			bankBaseResponse.setStatusCode(CONSTANT.SUCCESS_CODE);
		}
		return bankBaseResponse;
	}

	@Override
	public BankBaseResponse getbankdata(String bankname) {
		BankBaseResponse bankBaseResponse = new BankBaseResponse();
		Bank bank = bankDao.getbankdata(bankname);
		if(bank != null){
			bankBaseResponse.setBank(bank);
			bankBaseResponse.setStatus(CONSTANT.SUCCESS);
			bankBaseResponse.setStatus(CONSTANT.SUCCESS_CODE);
			bankBaseResponse.setMessage(CONSTANT.BANK_FOUND_MESSAGE);
		}else{
			bankBaseResponse.setBank(null);
			bankBaseResponse.setStatus(CONSTANT.SUCCESS);
			bankBaseResponse.setStatus(CONSTANT.SUCCESS_CODE);
			bankBaseResponse.setMessage(CONSTANT.BANK_NOT_FOUND_MESSAGE);
		}
		return bankBaseResponse;
	}

	@Override
	public AccountResponse getAccontData(String bankname) {
		AccountResponse accountResponse = new AccountResponse();
		List<Account> accounts = bankDao.getAccontData(bankname);
		if(accounts != null && !accounts.isEmpty()){
			accountResponse.setAccounts(accounts);
			accountResponse.setMessage(CONSTANT.FOUND_RECORD);
			accountResponse.setStatus(CONSTANT.SUCCESS);
			accountResponse.setStatusCode(CONSTANT.SUCCESS_CODE);
		}else{
			accounts  = new ArrayList<>();
			accountResponse.setAccounts(accounts);
			accountResponse.setMessage(CONSTANT.FOUND_RECORD);
			accountResponse.setStatus(CONSTANT.SUCCESS);
			accountResponse.setStatusCode(CONSTANT.SUCCESS_CODE);
		}
		return accountResponse;
	}

	@Override
	public TransactionDataResponse getTransactionData(String accountnumber) {
		TransactionDataResponse transactionDataResponse = new TransactionDataResponse();
		List<TransactionData> transactions = bankDao.getTransactionData(accountnumber);
		if(transactions != null && !transactions.isEmpty()){
			transactionDataResponse.setTransactionData(transactions);
			transactionDataResponse.setMessage(CONSTANT.FOUND_RECORD);
			transactionDataResponse.setStatus(CONSTANT.SUCCESS);
			transactionDataResponse.setStatusCode(CONSTANT.SUCCESS_CODE);
		}else{
			transactions  = new ArrayList<>();
			transactionDataResponse.setTransactionData(transactions);
			transactionDataResponse.setMessage(CONSTANT.FOUND_RECORD);
			transactionDataResponse.setStatus(CONSTANT.SUCCESS);
			transactionDataResponse.setStatusCode(CONSTANT.SUCCESS_CODE);
		}
		return transactionDataResponse;
	}

	@Override
	public CommonBaseResponse logintobank(UserRequest userRequest, String corpId) {
		CommonBaseResponse commonBaseResponse = new CommonBaseResponse();
		Bank babkResponse = null;
		CommonBaseResponse response = loginUser(userRequest);
		babkResponse = bankDao.findBankById(corpId);
		if(CONSTANT.SUCCESS.equals(response.getStatus())){
			commonBaseResponse.setStatus(CONSTANT.SUCCESS);
			commonBaseResponse.setStatusCode(CONSTANT.SUCCESS_CODE);
			if(babkResponse != null){
				commonBaseResponse.setUser(response.getUser());
				commonBaseResponse.setBank(babkResponse);
			}
		}else{
			commonBaseResponse.setStatus(CONSTANT.FAIL);
			commonBaseResponse.setStatusCode(CONSTANT.FAIL_CODE);
			if(babkResponse != null){
				commonBaseResponse.setMessage(CONSTANT.AUTHORIZED_INVALID + " " +  babkResponse.getBankName());
			}else{
				commonBaseResponse.setMessage(CONSTANT.AUTHORIZED_INVALID + " " + "given bank" );
			}
			
		}
		return commonBaseResponse;
	}

}
