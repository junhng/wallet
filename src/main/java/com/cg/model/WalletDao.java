package com.cg.model;

public class WalletDao {
	private Wallet wallet;
	public WalletDao(Wallet wallet) {
		this.wallet = wallet;
	}
	public int getId() { return this.wallet.getId(); }
	public float getBalance() { return this.wallet.getBalance(); }
}
