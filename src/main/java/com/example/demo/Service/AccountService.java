package com.example.demo.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.UserMessages;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.entity.AccountEntity;

@Service
public class AccountService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	AccountRepository accountrepo;

	public List<AccountEntity> getAllAccountDetails() throws CustomException {
		List<AccountEntity> accountEntityList = accountrepo.findAll();
		if (accountEntityList.isEmpty()) {
			logger.error(UserMessages.NODATAFOUND + "in account entity");
			throw new CustomException(UserMessages.NODATAFOUND);
		}
		logger.info("No of accounts in the account entity list: " + accountEntityList.size());
		return accountEntityList;
	}

	public AccountEntity getAccountDetails(Long accountNumber) throws CustomException {

		if (!(accountrepo.existsById(accountNumber))) {
			logger.error("Given account number: " + accountNumber + "is " + UserMessages.ACCOUNTNOTFOUND);
			throw new CustomException(UserMessages.ACCOUNTNOTFOUND);
		}
		AccountEntity account = accountrepo.findById(accountNumber).get();
		return account;
	}
}
