package com.revature.repository;

import com.revature.entity.Account;

import java.util.List;

public interface AccountDao {

    Account createAccount(Account accountCredentials);
    Account getDetails();
    List<Account> getUserAccounts(int userId);
    List<Account> getUserAccounts(int userId, String type);
    Account withdraw(Account account);
    void deleteAccount(Account account);
}
