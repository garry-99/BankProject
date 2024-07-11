package com.revature.repository;

import com.revature.entity.Account;

public interface AccountDao {

    Account createAccount(Account accountCredentials);
    Account getDetails();
}
