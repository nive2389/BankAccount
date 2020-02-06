package com.example.demo.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.TransactionDTO;
import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.UserMessages;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.TransactionRepository;
import com.example.demo.entity.TransactionEntity;

@Service
public class TransactionService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	TransactionRepository transrepo;
	@Autowired
	AccountRepository accountrepo;

	public List<TransactionDTO> getTransactionDetails(Long accountNumber) throws CustomException, ParseException {
		if (accountrepo.findById(accountNumber).isPresent()) {
			List<TransactionEntity> transDetailslist = transrepo.findAllByAccountNumber(accountNumber);
			if (transDetailslist.isEmpty()) {
				logger.error(UserMessages.NOTRANSACTIONDONE + "for given "+ accountNumber);
				throw new CustomException(UserMessages.NOTRANSACTIONDONE);
			}
			List<TransactionDTO> transactiondtoList = new ArrayList<TransactionDTO>();
			for (TransactionEntity trans : transDetailslist) {
				transactiondtoList.add(TransactionDTO.valueOf(trans));
			}
			logger.info(transactiondtoList.size() + "transactions done in this account");
			return transactiondtoList;
		} else {
			throw new CustomException(UserMessages.ACCOUNTNOTFOUND);
		}
	}
}
