package com.cg.service;

import com.cg.model.AccountDao;

public interface IWalletService {
	public AccountDao createAccount(String mobile, String name, float amt);
	public AccountDao showBalance(String mobile);
	public AccountDao deposit(String mobile, float amt);
	public AccountDao withdraw(String mobile, float amt);
}
