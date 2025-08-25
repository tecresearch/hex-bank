package com.corexfin.repositories;

import java.util.List;

import com.corexfin.models.TransactionInfo;

public interface TransactionRepository 
{
	void saveTransaction(TransactionInfo transactionInfo);
	List<TransactionInfo> getList(int accountno);
}
