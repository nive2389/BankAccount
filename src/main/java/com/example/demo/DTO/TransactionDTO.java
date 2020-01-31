package com.example.demo.DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.TransactionEntity;

public class TransactionDTO {
	
	private Long id;
	@Override
	public String toString() {
		return "TransactionDTO [Transaction ID=" + id + ", Account Number = " + account.getAccountNumber() + ", account Name=" + accountName + ", value Date=" + valueDate + ", currency=" + currency
				+ ", credit Amount=" + creditAmt + ", transaction Type=" + transactiontype + ", Transaction Summary=" + transactionsummary + "]";
	}

	private AccountEntity account;
	

	private String valueDate;
	private String currency;
	private Double creditAmt;
	private Double debitAmt;
	private String transactiontype;
	private String transactionsummary;
	private String accountName;
	
	
	
	public TransactionDTO()
	{
		
	}
	public TransactionDTO(Long id, String valueDate, String accountName, String currency, Double creditAmt, String accountType,
			String accSummary) {
		
		this.id = id;
		this.account = account;
		this.valueDate = valueDate;
		this.currency = currency;
		this.creditAmt = creditAmt;
		this.transactiontype = accountType;
		this.transactionsummary = accSummary;
		this.accountName = accountName;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*public AccountDTO getAccount() {
		return account;
	}*/
	public void setAccount(AccountEntity account) {
		this.account = account;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getCreditAmt() {
		return creditAmt;
	}
	public void setCreditAmt(Double creditAmt) {
		this.creditAmt = creditAmt;
	}
	
	public Double getDebitAmt() {
		return debitAmt;
	}
	public void setDebitAmt(Double debitAmt) {
		this.debitAmt = debitAmt;
	}
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public String getTransactionsummary() {
		return transactionsummary;
	}
	public void setTransactionsummary(String transactionsummary) {
		this.transactionsummary = transactionsummary;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public AccountEntity getAccount() {
		return account;
	}
	

		public static TransactionDTO valueOf(TransactionEntity transaction) throws ParseException {
			TransactionDTO transationdto=new TransactionDTO();
			transationdto.setId(transaction.getId());
			transationdto.setValueDate(new SimpleDateFormat("MMM dd, yyyy").format(transaction.getValuedate()));
			AccountEntity ac=transaction.getAccount();
			transationdto.setAccountName(ac.getAccountName());
			transationdto.setTransactiontype(transaction.getTransactiontype());
			transationdto.setCreditAmt(transaction.getCreditamt());
			transationdto.setCurrency(transaction.getCurrency());
			transationdto.setTransactionsummary(transaction.getTransactionsummary());
		return transationdto;
		}
		
		public static TransactionEntity valueOf(TransactionDTO transaction) throws ParseException {
			TransactionEntity transationdto=new TransactionEntity();
			transationdto.setId(transaction.getId());
		//	transationdto.setAccount(transaction.getAccount());
			transationdto.setAccoutName(transaction.getAccountName());
			transaction.setCreditAmt(transaction.getCreditAmt());
			transaction.setCurrency(transaction.getCurrency());
			transaction.setDebitAmt(transaction.getDebitAmt());
			transaction.setTransactiontype(transaction.getTransactiontype());
			transaction.setTransactionsummary(transaction.getTransactionsummary());
			transaction.setValueDate(transaction.getValueDate());
		return transationdto;
		}
		
	}
	
	

