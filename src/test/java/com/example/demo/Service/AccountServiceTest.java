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
	String dummyData ="TestUser";
	Long id=1001L;
	@Before
	public void Setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test 
	public void testgetAllAccount() throws Exception
	{
		List<AccountEntity> accountList = new ArrayList<AccountEntity>();
		AccountEntity account1 = new AccountEntity(1001L, "User1", "Savings", new Date(2019-20-8), "SGD", 2000.05);
		AccountEntity account2 = new AccountEntity(1002L, "User2", "Savings", new Date(2019-20-8), "SGD", 2000.05);
		accountList.add(account1);
		accountList.add(account2);
		when(accountrepo.findAll()).thenReturn(accountList);
		assertEquals(2, accountList.size());
	}
	
	@Test(expected = CustomException.class)
	 public void testgetAllAccountFalse() throws CustomException {
				
		 List<AccountEntity> accountList = new ArrayList<AccountEntity>();
			when(accountrepo.findAll()).thenReturn(accountList);
			accountServiceMock.getAllAccountDetails();
			expectedException.expect(CustomException.class);
			expectedException.expectMessage(UserMessages.NODATAFOUND);
	 }
	
//	@Test
//	public void testgetAccountService() throws CustomException
//	{	
//		AccountEntity account = new AccountEntity();
//		account = new AccountEntity();
//		account.setAccountNumber(1010L);
//		account.setAccountName("EGT-DT");
//		account.setAccountType("Savings");
//		account.setBalanceDate(new Date(2019 - 9 - 6));
//		account.setCurrency("INR");
//		account.setOpAvailBalance(7891.8);
//		
//		when(accountrepo.findById((long) 1001).get()).thenReturn(account);
//		AccountEntity accdto = accountServiceMock.getAccountDetails(1001L);
//		assertEquals(dummyData, accdto.getAccountName());
//		assertThat(accdto.getBalanceDate()).hasSameTimeAs(new Date(2019-20-8));
//	}
//	
//	@Test
//	public void testgetAccountServiceFalse() throws Exception
//	{	
//		try { 
//		
//		when(accountrepo.findById(1001L)).thenThrow(CustomException.class);
//		AccountEntity account = accountServiceMock.getAccountDetails(1001L);
//		expectedException.expect(CustomException.class);
//        expectedException.expectMessage(UserMessages.NODATAFOUND);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	
	
	
	//}
	
}
