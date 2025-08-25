package com.corexfin.services;

import com.corexfin.models.User;

public interface UserService 
{
	int saveUser(User user);
	User getUser(String userid);
}
