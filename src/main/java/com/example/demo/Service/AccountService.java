package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.UserMessages;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.entity.AccountEntity;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountrepo;

	public List<AccountEntity> getAllAccountDetails() throws CustomException {
		List<AccountEntity> accountEntityList = accountrepo.findAll();
		if (accountEntityList.isEmpty()) {
			throw new CustomException(UserMessages.NODATAFOUND);
		}
		return accountEntityList;
	}

	public AccountEntity getAccountDetails(Long accountNumber) throws CustomException {

		if (!(accountrepo.existsById(accountNumber))) {
			throw new CustomException(UserMessages.ACCOUNTNOTFOUND);
		}
		AccountEntity account = accountrepo.findById(accountNumber).get();
		return account;
	}
}
