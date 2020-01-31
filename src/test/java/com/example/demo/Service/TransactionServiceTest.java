package com.example.demo.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.DTO.TransactionDTO;
import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.UserMessages;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.TransactionRepository;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.TransactionEntity;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class TransactionServiceTest {
	@Mock
	TransactionRepository transrepo;
	@Mock
	AccountRepository accountrepo;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	@InjectMocks
	TransactionService transactionServiceMock;
	TransactionEntity transaction;
	AccountEntity account;
	TransactionDTO td;
	String dummyData = "TestUser";
	final static Long accountId = 1001L;

	@Before
	public void Setup() {
		MockitoAnnotations.initMocks(this);
		Transaction();
	}

	private void Transaction() {
		account = new AccountEntity();
		account.setAccountNumber(1001L);
		account.setAccountName("TestUser");
		account.setAccountType("Savings");
		account.setBalanceDate(new Date(2019 - 20 - 8));
		account.setCurrency("INR");
		account.setOpAvailBalance(20000.50);

		// TODO Auto-generated method stub
		transaction = new TransactionEntity();
		transaction.setAccount(account);
		transaction.setId(1001L);
		transaction.setTransactiontype("Savings");
		transaction.setTransactionsummary("Summary");
		transaction.setCurrency("INR");
		transaction.setCreditamt(100.0);
		transaction.setDebitamt(0.0);
		transaction.setValuedate(new Date(2019 - 9 - 6));

	}

	@Test
	public void testgetTransactionService() throws Exception {

		List<TransactionEntity> list = new ArrayList<TransactionEntity>();
		list.add(transaction);

		when(transrepo.findAllByAccountNumber(accountId)).thenReturn(list);

		List<TransactionDTO> transactionDTO = transactionServiceMock.getTransactionDetails(1001L);

		List<TransactionEntity> transactionList = new ArrayList<TransactionEntity>();
		for (TransactionDTO transdto : transactionDTO) {
			TransactionEntity transEntity = TransactionDTO.valueOf(transdto);
			transactionList.add(transEntity);
		}
		assertEquals(list.size(), transactionList.size());
	}

	@Test(expected = CustomException.class)
	public void testgetTransactionServiceFail() throws Exception {
		when(accountrepo.findById(accountId).isPresent()).thenReturn(true);
		List<TransactionEntity> list = new ArrayList<TransactionEntity>();
		when(transrepo.findAllByAccountNumber(accountId)).thenReturn(list);
		transactionServiceMock.getTransactionDetails(accountId);
		expectedException.expect(CustomException.class);
		expectedException.expectMessage(UserMessages.NOTRANSACTIONDONE);
	}
	
}
