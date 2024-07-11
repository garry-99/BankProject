package com.revature.controller;
import com.revature.entity.Account;
import com.revature.exception.LoginFail;
import com.revature.exception.ValidUserException;
import com.revature.service.AccountService;

import java.nio.channels.AcceptPendingException;
import java.util.Map;
import java.util.Scanner;

public class AccountController {
    AccountService accountService ;
    private Scanner sc ;

    public AccountController( Scanner sc, AccountService accountService) {
        this.accountService = accountService;
        this.sc = sc;
    }
    public void promptUserAccount(){

        System.out.println("Lets do the banking");
        System.out.println("1. Create an account");
        System.out.println("2. Details of current accounts");
        System.out.println("q. Log out");

        try{
            String userActionIndicated = sc.nextLine();
            switch (userActionIndicated) {
                case "1":
                    createAccount();

                    break;
////                case "2":
////
////                    controlMap.put("User", login().getUsername());
//                    break;
                case "q":
                    System.out.println("Goodbye!");
//                    controlMap.put("Continue Loop", "false");
            }

        } catch(LoginFail | ValidUserException exception){
            System.out.println(exception.getMessage());
        }
    }
    public void createAccount(){
        System.out.println("What type of account you want");
        System.out.println("1: Checkings");
        System.out.println("2: Savings");
        int typeAccount = sc.nextInt();
        if(typeAccount == 1 ){
            Account ac = new Account();
            ac.setName("ac1");
            ac.setType("Checkings");
            Account account = accountService.createAccount(ac);
        }
        else if(typeAccount == 2){
            Account ac = new Account();
            ac.setName("ac1");
            ac.setType("Savings");
            Account account = accountService.createAccount(ac);
        }
    }


}
