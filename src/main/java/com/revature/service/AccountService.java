package com.revature.service;

import com.revature.entity.Account;
import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.exception.ValidUserException;
import com.revature.repository.AccountDao;

import java.util.Map;

public class AccountService {
    private AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account createAccount(Account newCredentials){
        return accountDao.createAccount(newCredentials);
    }



}
