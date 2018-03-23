package com.cg.model;

import java.util.List;

public class AccountDao {
	private Account account;
	public AccountDao(Account account) {
		this.account = account;
	}
	public int getId() { return this.account.getId(); }
	public String getMobile() { return this.account.getMobile(); }
	public String getName() { return this.account.getName(); }
	public WalletDao getWallet() { return new WalletDao(this.account.getWallet()); }
	public List<Transaction> getTransactions() { return this.account.getTransactions(); }
}
