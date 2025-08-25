package com.corexfin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corexfin.models.Account;
import com.corexfin.models.User;
import com.corexfin.repositories.AccountRepository;
import com.corexfin.repositories.UserRepository;
import com.corexfin.services.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired private UserRepository userRepository;
	@Autowired private AccountRepository accountRepository;

	public int saveUser(User user) 
	{
		//Command to persist user object
		userRepository.persistUser(user);
		Account account=new Account();
		account.setUserid(user.getUserid());
		//Command to account object;
		accountRepository.persistAccount(account);
		return account.getAccountno();
	}

	public User getUser(String userid) 
	{
		return userRepository.findUserById(userid);
	}
}
