package com.cg.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.exceptions.InvalidAccountInformationException;
import com.cg.model.Account;
import com.cg.model.AccountDao;
import com.cg.model.Transaction;
import com.cg.model.Wallet;
import com.cg.repository.AccountRepository;
import com.cg.utils.WalletUtils;

@Service(value="service")
public class WalletServiceImpl implements IWalletService {
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountDao createAccount(String mobile, String name, float amt) {
		if(WalletUtils.validateNull(mobile)) {
			throw new InvalidAccountInformationException("Mobile number cannot be NULL");
		}
		else if(!WalletUtils.validateNull(accountRepository.findByMobile(mobile))) {
			throw new InvalidAccountInformationException("Cannot create account because account with this mobile number already exists");
		}
		Account newAccount = new Account();
		Wallet newWallet = new Wallet();
		if(WalletUtils.validateBalance(newWallet.getBalance(), amt))
			newWallet.setBalance(amt);
		else {
			throw new InvalidAccountInformationException("Deposit is out of limits");
		}
		if(WalletUtils.validateMobile(mobile))
			newAccount.setMobile(mobile);
		else {
			throw new InvalidAccountInformationException("Invalid mobile number");
		}
		if(WalletUtils.validateNull(name)) {
			throw new InvalidAccountInformationException("Name cannot be NULL");
		}
		else if(WalletUtils.validateName(name))
			newAccount.setName(name);
		else if(!WalletUtils.validateEmptyString(name)) {
			throw new InvalidAccountInformationException("Name cannot be empty");
		}
		else {
			throw new InvalidAccountInformationException("Name cannot include special characters");
		}
		newAccount.setWallet(newWallet);
		newAccount.setTransactions(new ArrayList<Transaction>());
		accountRepository.save(newAccount);
		return new AccountDao(newAccount);
	}
	@Override
	public AccountDao showBalance(String mobile) {
		if(WalletUtils.validateNull(mobile)) {
			throw new InvalidAccountInformationException("Mobile number cannot be null");
		}
		else if(WalletUtils.validateMobile(mobile)) {
			Account account = accountRepository.findByMobile(mobile);
			if(!WalletUtils.validateNull(account)) {
				return new AccountDao(account);
			}
			else
				throw new InvalidAccountInformationException("Mobile number does not exist");
		}
		else {
			throw new InvalidAccountInformationException("Invalid mobile number");
		}
	}
	@Override
	public AccountDao deposit(String mobile, float amt) {
		Account account = null;
		if(WalletUtils.validateNull(mobile)) {
			throw new InvalidAccountInformationException("Mobile number cannot be null");
		}
		else if(WalletUtils.validateMobile(mobile)) {
			account = accountRepository.findByMobile(mobile);
			if(WalletUtils.validateNull(account)) 
				throw new InvalidAccountInformationException("Mobile number does not exist");
		}
		else {
			throw new InvalidAccountInformationException("Invalid mobile number");
		}
		if(WalletUtils.isNegative(amt))
			throw new InvalidAccountInformationException("Amount cannot be negative");
		else if(!WalletUtils.validateBalance(account.getWallet().getBalance(), amt))
			throw new InvalidAccountInformationException("Maximum balance can only be 100000");
		
		Transaction transaction = new Transaction();
    	transaction.setAmount(amt);
    	transaction.setBalance(account.getWallet().getBalance() + amt);
    	transaction.setDescription("Deposit amount: " + amt);
    	transaction.setTransactionDate(new GregorianCalendar());
    	transaction.setAccount(account);
    	account.getTransactions().add(transaction);
    	account.getWallet().setBalance(account.getWallet().getBalance() + amt);
		accountRepository.save(account);
		return new AccountDao(account);
	}
	@Override
	public AccountDao withdraw(String mobile, float amt) {
		Account account = null;
		if(WalletUtils.validateNull(mobile)) {
			throw new InvalidAccountInformationException("Mobile number cannot be null");
		}
		else if(WalletUtils.validateMobile(mobile)) {
			account = accountRepository.findByMobile(mobile);
			if(WalletUtils.validateNull(account)) 
				throw new InvalidAccountInformationException("Mobile number does not exist");
		}
		else {
			throw new InvalidAccountInformationException("Invalid mobile number");
		}
		if(WalletUtils.isNegative(amt))
			throw new InvalidAccountInformationException("Amount cannot be negative");
		else if(!WalletUtils.validateBalance(account.getWallet().getBalance(), -1 * amt))
			throw new InvalidAccountInformationException("Insufficient funds for withdrawal");
		
		Transaction transaction = new Transaction();
    	transaction.setAmount(amt);
    	transaction.setBalance(account.getWallet().getBalance() - amt);
    	transaction.setDescription("Withdraw amount: " + amt);
    	transaction.setTransactionDate(new GregorianCalendar());
    	transaction.setAccount(account);
    	account.getTransactions().add(transaction);
		account.getWallet().setBalance(account.getWallet().getBalance() - amt);
		accountRepository.save(account);
		return new AccountDao(account);
	}
}
