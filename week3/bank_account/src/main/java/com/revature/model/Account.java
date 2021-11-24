package com.revature.model;

import java.util.Objects;

public class Account {
	private int accountId;
	private String accountType;
	private double accountBalance;
	private String accountNumber;
	private int clientId;
	private String status;
	
	public Account() {}

	public Account(int accountId, String accountType, double accountBalance, String accountNumber, int clientId,
			String status) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.accountNumber = accountNumber;
		this.clientId = clientId;
		this.status = status;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountBalance, accountId, accountNumber, accountType, clientId, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Double.doubleToLongBits(accountBalance) == Double.doubleToLongBits(other.accountBalance)
				&& accountId == other.accountId && Objects.equals(accountNumber, other.accountNumber)
				&& Objects.equals(accountType, other.accountType) && clientId == other.clientId
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountType=" + accountType + ", accountBalance=" + accountBalance
				+ ", accountNumber=" + accountNumber + ", clientId=" + clientId + ", status=" + status + "]";
	}
	

}
