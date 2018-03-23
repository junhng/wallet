package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	public Account findByMobile(String mobile);
	@SuppressWarnings("unchecked")
	public Account save(Account account);
}
