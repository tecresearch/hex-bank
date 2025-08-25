package com.corexfin.repositories;

import com.corexfin.models.Account;

public interface AccountRepository 
{
	void persistAccount(Account account);
	int getAccountNoByUserId(String userid);
	int getAmount(int accountno);
	void updateAmount(int amount, int accountno);
	String getNameByAccountno(int accountno);
}
