package com.example.ewallet.walletapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ewallet.datatransferobject.TransactionDTO;
import com.example.ewallet.datatransferobject.mapper.TransactionMapper;
import com.example.ewallet.exceptions.BalanceLowException;
import com.example.ewallet.exceptions.UserNotFoundException;
import com.example.ewallet.models.Transaction;
import com.example.ewallet.service.TransactionService;
import com.example.ewallet.service.UserAccountService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/transferTo")
public class TransactionController {

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private TransactionService transactionService;

	@ApiOperation(value = "Add Money in Wallet ", response = TransactionDTO.class, tags = "transact")
	@PostMapping("/{id}")
	public ResponseEntity addMoney(@PathVariable("id") Long userAccountId, @RequestBody TransactionDTO walletDTO) {
		Transaction saved;
		try {
			walletDTO.setUserAccountId(userAccountId);
			saved = transactionService.createTransaction(TransactionMapper.dtoToDO(walletDTO));
		} catch (BalanceLowException ex) {
			Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<TransactionDTO>(TransactionMapper.doToDTO(saved), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Pay from wallet ", response = List.class, tags = "transact")
	@PostMapping("/{toUser}/from/{fromUser}")
	public ResponseEntity transferMoney(@PathVariable("toUser") Long toUserAccountId,
			@PathVariable("fromUser") Long fromUserAccountId, @RequestBody TransactionDTO walletDTO) {
		List<Transaction> both;

		try {
			both = transactionService.transfer(walletDTO, toUserAccountId, fromUserAccountId);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (BalanceLowException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<TransactionDTO>>(TransactionMapper.doToDTOList(both), HttpStatus.OK);
	}

	@GetMapping("/{transactionRefId}/reverseTransaction")
	@ApiOperation(value = "reverse transaction by transactionId", response = List.class, tags = "reverseTransaction")
	public ResponseEntity reverseTransactionReference(@PathVariable("transactionRefId") Long id) {
		Transaction transaction;
		List<Transaction> transactionList = new ArrayList<>();
		try {
			transaction = transactionService.reverseTransactionByRef(id);
			transactionList.add(transaction);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<TransactionDTO>>(TransactionMapper.doToDTOList(transactionList), HttpStatus.OK);
	}
}
