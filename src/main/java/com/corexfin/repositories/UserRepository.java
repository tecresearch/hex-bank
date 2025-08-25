package com.corexfin.repositories;

import com.corexfin.models.User;

public interface UserRepository 
{
	void persistUser(User user);
	User findUserById(String userid);
}
