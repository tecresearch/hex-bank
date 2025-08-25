package com.corexfin.repositories.impl;

import org.hibernate.*;
import org.springframework.stereotype.Repository;

import com.corexfin.models.User;
import com.corexfin.repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository
{
	private Session session;
	private Transaction transaction;
	public UserRepositoryImpl(SessionFactory sessionFactory)
	{
		session=sessionFactory.openSession();
		transaction=session.getTransaction();
	}
	public void persistUser(User user) 
	{
		transaction.begin();
		session.persist(user);
		transaction.commit();
	}
	public User findUserById(String userid) 
	{
		User user=session.get(User.class,userid);
		return user;
	}
}
