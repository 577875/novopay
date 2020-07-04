package com.example.ewallet.service;

import java.util.List;

import com.example.ewallet.exceptions.UserNotFoundException;
import com.example.ewallet.models.UserAccount;


/** Service for Users */
public interface UserAccountService {

	/**
	 * Save a user account
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	UserAccount save(UserAccount t) throws Exception;

	/**
	 * Update a user account
	 * 
	 * @param user account
	 * @param user id
	 * @return
	 * @throws Exception
	 */
	UserAccount update(UserAccount t, Long id) throws Exception;

	/**
	 * @return list of users
	 */
	List<UserAccount> getList();

	/**
	 * @param accountId
	 * @return user account by account id
	 * @throws Exception
	 */
	UserAccount userAccountByPK(Long accountId) throws UserNotFoundException;
}