package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.model.AccountDao;
import com.cg.service.IWalletService;

@RestController
public class WalletController {
	@Autowired
	private IWalletService service;
	@RequestMapping("/")
	public String hello() {
		return "Hello world";
	}
	@RequestMapping(value = "/createaccount/mobile/{mobile}/name/{name}/amount/{amount}")
	public AccountDao createAccount(@PathVariable String mobile, @PathVariable String name, @PathVariable float amount) {
		return service.createAccount(mobile, name, amount);
	}
	@RequestMapping(value = "/deposit/mobile/{mobile}/amount/{amount}")
	public AccountDao deposit(@PathVariable String mobile, @PathVariable float amount) {
		return service.deposit(mobile, amount);
	}
	@RequestMapping(value = "/withdraw/mobile{mobile}/amount/{amount}")
	public AccountDao withdraw(@PathVariable String mobile, @PathVariable float amount) {
		return service.withdraw(mobile, amount);
	}
	@RequestMapping(value = "/showbalance/mobile/{mobile}")
	public AccountDao showBalance(@PathVariable String mobile) {
		return service.showBalance(mobile);
	}
}
