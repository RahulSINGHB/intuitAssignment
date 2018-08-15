package com.intuit.contracts;

import java.util.List;

import com.intuit.model.TransactionData;

public class TransactionDataResponse extends BaseResponse{
	private List<TransactionData> transactionData;

	public List<TransactionData> getTransactionData() {
		return transactionData;
	}

	public void setTransactionData(List<TransactionData> transactionData) {
		this.transactionData = transactionData;
	}
	

}
