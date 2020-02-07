package com.example.demo.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.UserMessages;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.entity.AccountEntity;


@RunWith(SpringRunner.class)
@ContextConfiguration
public class AccountServiceTest {

	@Mock
	AccountRepository accountrepo;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	@InjectMocks
	AccountService accountServiceMock;
	AccountEntity account;

	@Before
	public void Setup() {
		MockitoAnnotations.initMocks(this);
		account = new AccountEntity();
		account.setAccountNumber(1001L);
		account.setAccountName("TestUser");
		account.setAccountType("Savings");
		account.setBalanceDate(new Date(2019 - 20 - 8));
		account.setCurrency("INR");
		account.setOpAvailBalance(20000.50);
	}

	@Test
	public void testgetAllAccount() throws Exception {
		List<AccountEntity> accountList = new ArrayList<AccountEntity>();
		AccountEntity account1 = new AccountEntity(1001L, "User1", "Savings", new Date(2019 - 20 - 8), "SGD", 2000.05);
		AccountEntity account2 = new AccountEntity(1002L, "User2", "Savings", new Date(2019 - 20 - 8), "SGD", 2000.05);
		accountList.add(account1);
		accountList.add(account2);
		when(accountrepo.findAll()).thenReturn(accountList);
		List<AccountEntity> accountEntityList = accountServiceMock.getAllAccountDetails();
		assertEquals(2, accountList.size());
	}

	@Test(expected = CustomException.class)
	public void testgetAllAccountFail() throws CustomException {
		List<AccountEntity> accountList = new ArrayList<AccountEntity>();
		when(accountrepo.findAll()).thenReturn(accountList);
		accountServiceMock.getAllAccountDetails();
		expectedException.expect(CustomException.class);
		expectedException.expectMessage("NO ACCOUNT DETAILS FOUND");
	}

	@Test
	public void testgetAccountDetails() throws CustomException {
		final Long accountId=1001L;
		when(accountrepo.existsById(accountId)).thenReturn(true);
		when(accountrepo.findById(accountId)).thenReturn(Optional.of(account));
		AccountEntity acc = accountServiceMock.getAccountDetails(1001L);
		System.out.println(acc.getAccountName());
		assertEquals("Equal","TestUser", acc.getAccountName());
	}
	
	@Test(expected = CustomException.class)
	public void testgetAccountDetailsFail() throws CustomException {
		final Long accountId=1001L;
		when(accountrepo.existsById(accountId)).thenReturn(false);
		accountServiceMock.getAccountDetails(1001L);
		expectedException.expect(CustomException.class);
		expectedException.expectMessage("INVALID ACCOUNT NUMBER");
		
	}

}
