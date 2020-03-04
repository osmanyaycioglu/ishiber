package com.isbank.rest.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accId;
	
	private double balance;
	
	@Enumerated(EnumType.STRING)
	private EAccountType accountType;
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public EAccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(EAccountType accountType) {
		this.accountType = accountType;
	}
	public long getAccId() {
		return accId;
	}
	public void setAccId(long accId) {
		this.accId = accId;
	}
	
	
}
