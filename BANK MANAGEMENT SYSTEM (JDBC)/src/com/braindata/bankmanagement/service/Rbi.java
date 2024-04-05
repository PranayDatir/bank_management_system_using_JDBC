package com.braindata.bankmanagement.service;

public interface Rbi {
	public void createAccount() throws Exception;
	public void insertDataIntoAccount()throws Exception;
	public void displayAllDetails()throws Exception;
	public void displayAccDetails()throws Exception;
	public void depositeMoney()throws Exception;
	public void withdrawal()throws Exception;
	public void balanceCheck()throws Exception;
	public void deleteAccount()throws Exception;
	public void showLastTransactions()throws Exception;
}
