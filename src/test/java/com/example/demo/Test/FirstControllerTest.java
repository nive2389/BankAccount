package com.example.demo.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Controller.FirstController;
import com.example.demo.DTO.TransactionDTO;
import com.example.demo.Exception.CustomException;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.TransactionRepository;
import com.example.demo.Service.AccountService;
import com.example.demo.Service.TransactionService;
import com.example.demo.entity.AccountEntity;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FirstController.class, secure = false)
public class FirstControllerTest {

	@MockBean
	AccountService accServiceMock;
	@MockBean
	TransactionService transServiceMock;
	@Mock
	AccountRepository accRepoMock;
	@Mock
	TransactionRepository transRepoMock;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	@InjectMocks
	FirstController firstControllerMock;
	@Autowired
	private MockMvc mockMvc;
	private TransactionDTO td;
	private AccountEntity account;

	@Before
	public void initMocks() throws Exception {
		MockitoAnnotations.initMocks(this);
		acc();
	}

	@Test
	public void getAllAccount() throws CustomException {
		List<AccountEntity> accountList = new ArrayList<AccountEntity>();
		AccountEntity account1 = new AccountEntity(1001L, "User1", "Savings", new Date(2019 - 20 - 8), "SGD", 2000.05);
		AccountEntity account2 = new AccountEntity(1002L, "User2", "Savings", new Date(2019 - 20 - 8), "SGD", 2000.05);
		AccountEntity account3 = new AccountEntity(1003L, "User3", "Savings", new Date(2019 - 20 - 8), "SGD", 2000.05);
		accountList.add(account1);
		accountList.add(account2);
		accountList.add(account3);
		when(accServiceMock.getAllAccountDetails()).thenReturn(accountList);
		List<AccountEntity> list = firstControllerMock.getAllAccountDetails();
		assertEquals(3, list.size());
	}

	@Test(expected = CustomException.class)
	public void getAllAccountFail() throws CustomException {
//		 List<AccountEntity> accountList = new ArrayList<AccountEntity>();
//			when(accServiceMock.getAllAccountDetails()).thenReturn(accountList);
//			List<AccountEntity> list = firstControllerMock.getAllAccountDetails();
//		assertTrue(list.isEmpty());

		when(accServiceMock.getAllAccountDetails()).thenThrow(CustomException.class);
		firstControllerMock.getAllAccountDetails();
		expectedException.expect(CustomException.class);
		expectedException.expectMessage("ACCOUNT DETAILS NOT FOUND");
	}

	@Test
	public void testTransaction() throws Exception {
		List<TransactionDTO> tdList = new ArrayList();
		tdList.add(td);
		when(transServiceMock.getTransactionDetails(1010L)).thenReturn(tdList);
		List<TransactionDTO> responseList = firstControllerMock.getTransactionDetails(1010L);
		assertThat(tdList).containsAnyOf(td);

	}

	@Test
	public void testTransactionFail() throws Exception {

		try {
			when(transServiceMock.getTransactionDetails(100L)).thenThrow(CustomException.class);
			firstControllerMock.getTransactionDetails(100L);
			expectedException.expect(CustomException.class);
			expectedException.expectMessage("NO TRANSACTION DONE");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Test
	public void testgetAccount() throws Exception {
		when(accServiceMock.getAccountDetails(1010L)).thenReturn(account);
		AccountEntity response = firstControllerMock.getAccountDetails(1010L);
		assertThat(response.getAccountName()).isEqualTo(account.getAccountName());
	}

	@Test
	public void testgetAccountFail() throws Exception {
		try {
			when(accServiceMock.getAccountDetails(100L)).thenThrow(CustomException.class);
			firstControllerMock.getAccountDetails(100L);
			expectedException.expect(CustomException.class);
			expectedException.expectMessage("INVALID ACCOUNT NUMBER");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void acc() throws CustomException {
		account = new AccountEntity();
		account.setAccountNumber(1010L);
		account.setAccountName("EGT-DT");
		account.setAccountType("Savings");
		account.setBalanceDate(new Date(2019 - 9 - 6));
		account.setCurrency("INR");
		account.setOpAvailBalance(7891.8);

		td = new TransactionDTO();
		td.setAccount(account);
		td.setId(1L);
		td.setAccountName(account.getAccountName());
		td.setTransactiontype("Savings");
		td.setTransactionsummary("Summary");
		td.setCurrency("INR");
		td.setCreditAmt(100.0);
		td.setValueDate(new Date(2019 - 9 - 6).toString());

	}
}
