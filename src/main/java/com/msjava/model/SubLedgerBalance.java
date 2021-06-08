package com.msjava.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class SubLedgerBalance {
	
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "GL_ACCT_ID")
	private int acctId;
	
	@Column(name = "GL_TRAN_AMOUNT")
	private double amount;
	
	@Column(name = "GL_TRAN_CURRENCY")
	private String currency;
	
	@Column(name = "GL_TRAN_DATE")
	private Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAcctId() {
		return acctId;
	}
	public void setAcctId(int acctId) {
		this.acctId = acctId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
