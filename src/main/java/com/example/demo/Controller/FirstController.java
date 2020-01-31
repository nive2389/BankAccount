package com.example.demo.Controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.CustomException;
import com.example.demo.Service.AccountService;
import com.example.demo.Service.TransactionService;
import com.example.demo.entity.AccountEntity;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FirstController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AccountService accservice;

	@Autowired
	TransactionService transervice;

	@GetMapping("/getallaccountdetails")
	public List getAllAccountDetails() throws CustomException {
		logger.info("Get All Account Details Called");
		return accservice.getAllAccountDetails();
	}

	@GetMapping("/accountdetails")
	public AccountEntity getAccountDetails(@RequestParam("accountNumber") Long accountNumber) throws CustomException {
		logger.info("Get Account Details : " + accountNumber);
		if(accountNumber == null) {
			throw new IllegalArgumentException("Account Number must not be empty");
		}
		return accservice.getAccountDetails(accountNumber);
	}

	@GetMapping("/transactiondetails")
	public List getTransactionDetails(@RequestParam("accountNumber") @NotNull Long accountNumber) throws CustomException, ParseException {
		logger.info("Get transaction Details Called for: " + accountNumber);
		return transervice.getTransactionDetails(accountNumber);
	}

}