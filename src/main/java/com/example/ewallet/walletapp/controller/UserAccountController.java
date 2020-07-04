package com.example.ewallet.walletapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ewallet.datatransferobject.TransactionDTO;
import com.example.ewallet.datatransferobject.UserAccountDTO;
import com.example.ewallet.datatransferobject.mapper.TransactionMapper;
import com.example.ewallet.datatransferobject.mapper.UserAccountMapper;
import com.example.ewallet.exceptions.UserNotFoundException;
import com.example.ewallet.models.Transaction;
import com.example.ewallet.models.UserAccount;
import com.example.ewallet.service.TransactionService;
import com.example.ewallet.service.UserAccountService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/users")
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private TransactionService transactionService;

	@GetMapping
	@ApiOperation(value = "Get All users ", response = List.class)
	public ResponseEntity getUsers() {
		List<UserAccount> userAccounts = userAccountService.getList();
		return new ResponseEntity<List<UserAccountDTO>>(UserAccountMapper.doToDTOList(userAccounts), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get User by id", response = UserAccountDTO.class)
	public ResponseEntity getUser(@PathVariable("id") Long id) {
		UserAccount userAccount;
		try {
			userAccount = userAccountService.userAccountByPK(id);
		} catch (UserNotFoundException ex) {
			Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserAccountDTO>(UserAccountMapper.doToDTO(userAccount), HttpStatus.OK);

	}

	@PostMapping
	@ApiOperation(value = "create User", response = UserAccountDTO.class)
	public ResponseEntity createUser(@RequestBody UserAccountDTO userAccountDTO) {
		UserAccount saved;
		try {
			saved = userAccountService.save(UserAccountMapper.dtoToDO(userAccountDTO));
		} catch (Exception ex) {
			Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UserAccountDTO>(UserAccountMapper.doToDTO(saved), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "update User by id", response = UserAccountDTO.class)
	public ResponseEntity updateUser(@PathVariable("id") Long userAccountId,
			@RequestBody UserAccountDTO userAccountDTO) {
		UserAccount saved;
		try {
			saved = userAccountService.update(UserAccountMapper.dtoToDO(userAccountDTO), userAccountId);
		} catch (Exception ex) {
			Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UserAccountDTO>(UserAccountMapper.doToDTO(saved), HttpStatus.OK);
	}

	@GetMapping("/{userAccountId}/passbook")
	@ApiOperation(value = "get PassBook by UserId", response = List.class, tags = "getPassBook")
	public ResponseEntity getUserPassbook(@PathVariable("userAccountId") Long id) {
		List<Transaction> passbook;
		try {
			passbook = transactionService.transactionsByUserAccountID(id);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<TransactionDTO>>(TransactionMapper.doToDTOList(passbook), HttpStatus.OK);
	}

	@GetMapping("/{transactionRefId}/transaction")
	@ApiOperation(value = "get transaction by transactionId", response = List.class, tags = "getPassBook")
	public ResponseEntity getTransactionReference(@PathVariable("transactionRefId") Long id) {
		List<Transaction> transactionList = new ArrayList<>();
		try {
			transactionList = transactionService.transactionByRef(id);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<TransactionDTO>>(TransactionMapper.doToDTOList(transactionList), HttpStatus.OK);
	}
}