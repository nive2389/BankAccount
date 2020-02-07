package com.example.demo.Controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.TransactionDTO;
import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.UserMessages;
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

	@GetMapping("/viewaccountdetails")
	public List<AccountEntity> getAllAccountDetails() throws CustomException {
		logger.info("List of all account details");
		return accservice.getAllAccountDetails();
	}

	@GetMapping("/getaccountdetails")
	public AccountEntity getAccountDetails(@RequestParam("accountNumber") @NotBlank @NotNull Long accountNumber) throws CustomException {
		logger.info("Get Account Details of : " + accountNumber);
		if(accountNumber == null) {
			throw new IllegalArgumentException(UserMessages.ACCOUNTNONOTEMPTY);
		}
		return accservice.getAccountDetails(accountNumber);
	}

	@GetMapping("/transactiondetails")
	public List<TransactionDTO> getTransactionDetails(@RequestParam("accountNumber") @NotBlank @NotNull Long accountNumber) throws CustomException, ParseException {
		logger.info("Get transaction Details for: " + accountNumber);
		if(accountNumber == null) {
			throw new IllegalArgumentException(UserMessages.ACCOUNTNONOTEMPTY);
		}
		return transervice.getTransactionDetails(accountNumber);
	}

}