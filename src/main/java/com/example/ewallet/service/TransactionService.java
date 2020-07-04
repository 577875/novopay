package com.example.ewallet.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.ewallet.datatransferobject.TransactionDTO;
import com.example.ewallet.exceptions.BalanceLowException;
import com.example.ewallet.exceptions.UserNotFoundException;
import com.example.ewallet.models.Transaction;

/** Service for Transaction */
public interface TransactionService {

	/**
	 * Save a transaction
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	Transaction save(Transaction t) throws Exception;

	/**
	 * Update a transaction
	 * 
	 * @param t
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Transaction update(Transaction t, Long id) throws Exception;

	/**
	 * @return list of transactions
	 */
	List<Transaction> getList();

	/**
	 * gets list of transactions by account id
	 * 
	 * @param accountId
	 * @return transaction
	 * @throws Exception
	 */
	List<Transaction> transactionsByUserAccountID(Long accountId) throws UserNotFoundException;

	/**
	 * gets transaction by ref id
	 * 
	 * @param transaction reference id
	 * @return transaction
	 * @throws Exception
	 */
	List<Transaction> transactionByRef(Long txnRef) throws Exception;

	/**
	 * @param accountId
	 * @return balance of user
	 * @throws Exception
	 */
	BigDecimal balanceByUserAccountID(Long accountId) throws UserNotFoundException;

	List<Transaction> transactions();

	Transaction createTransaction(Transaction txn) throws BalanceLowException;

	/**
	 * Transfer Money from one user to other
	 * 
	 * @param transaction       details
	 * @param toUserAccountId
	 * @param fromUserAccountId
	 * @return list of parties involved if successful
	 */
	List<Transaction> transfer(TransactionDTO walletDTO, Long toUserAccountId, Long fromUserAccountId) throws UserNotFoundException,BalanceLowException;

	/**
	 * reverse transaction by ref id
	 *
	 * @param transaction reference id
	 * @return transaction
	 * @throws Exception
	 */
	Transaction reverseTransactionByRef(Long txnRef) throws Exception;
}