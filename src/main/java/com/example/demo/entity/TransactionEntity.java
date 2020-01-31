package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity {
	@Id
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "AccountNumber")
	private AccountEntity account;
	//@JoinColumn(name = "accountname")
	private String accoutName;
	private Date valuedate;
	private String currency;
	private Double debitamt;
	private Double creditamt;
	private String transactiontype;
	private String transactionsummary;

	public TransactionEntity() {

	}

	public TransactionEntity(Long id, AccountEntity account, String accoutName, Date valuedate, String currency, Double debitamt,
			Double creditamt, String transactiontype, String transactionsummary) {
		super();
		this.id = id;
		this.account = account;
		this.accoutName = accoutName;
		this.valuedate = valuedate;
		this.currency = currency;
		this.debitamt = debitamt;
		this.creditamt = creditamt;
		this.transactiontype = transactiontype;
		this.transactionsummary = transactionsummary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountEntity getAccount() {
		return account;
	}
	public void setAccount(AccountEntity account) {
		this.account = account;
	}
	
	public String getAccoutName() {
		return accoutName;
	}

	public void setAccoutName(String accoutName) {
		this.accoutName = accoutName;
	}

	public Date getValuedate() {
		return valuedate;
	}

	public void setValuedate(Date valuedate) {
		this.valuedate = valuedate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getDebitamt() {
		return debitamt;
	}

	public void setDebitamt(Double debitamt) {
		this.debitamt = debitamt;
	}

	public Double getCreditamt() {
		return creditamt;
	}

	public void setCreditamt(Double creditamt) {
		this.creditamt = creditamt;
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

}
