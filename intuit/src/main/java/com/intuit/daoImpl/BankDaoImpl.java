package com.intuit.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.intuit.dao.BankDao;
import com.intuit.model.Account;
import com.intuit.model.Bank;
import com.intuit.model.TransactionData;

@Repository
public class BankDaoImpl implements BankDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Bank> getbanklist() {
		return mongoTemplate.findAll(Bank.class);
	}

	@Override
	public Bank getbankdata(String bankname) {
		Query query = new Query();
		query.addCriteria(Criteria.where("bankName").is(bankname));
		return mongoTemplate.findOne(query, Bank.class);
	}

	@Override
	public List<Account> getAccontData(String bankname) {
		Query query = new Query();
		query.addCriteria(Criteria.where("bankName").is(bankname));
		return mongoTemplate.find(query, Account.class);
	}

	@Override
	public List<TransactionData> getTransactionData(String accountNumber) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(accountNumber));
		return mongoTemplate.find(query, TransactionData.class);
	}

	@Override
	public Bank findBankById(String corpId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(corpId));
		return mongoTemplate.findOne(query, Bank.class);

	}
	
	
}
