package com.corexfin.services;

import com.corexfin.models.TransactionInfo;

public interface TransactionService 
{
	TransactionInfo saveWthdrawTransaction(int saccountno, int amount);
	TransactionInfo saveDepositTransaction(int accountno, int amount);
	TransactionInfo saveTransferTransaction(int saccountno, int amount, int raccountno);
}
