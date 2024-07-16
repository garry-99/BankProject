package com.revature.controller;
import com.revature.entity.Account;
import com.revature.exception.LoginFail;
import com.revature.exception.ValidUserException;
import com.revature.service.AccountService;

import java.nio.channels.AcceptPendingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AccountController {
    AccountService accountService ;
    private Scanner sc ;

    public AccountController( Scanner sc, AccountService accountService) {
        this.accountService = accountService;
        this.sc = sc;
    }
    public void promptUserAccount(HashMap<String,String> controlMap){

        System.out.println("Lets do the banking");
        System.out.println("1. Create an account");
        System.out.println("2. Details of current bank accounts");
        System.out.println("3. Select checking account for transfers");
        System.out.println("q. Log out");

        try{
            String userActionIndicated = sc.nextLine();
            switch (userActionIndicated) {
                case "1":
                    createAccount(controlMap);
                    System.out.println("New Account Successfully Created");
                    break;
                case "2":
                    selectAccount(controlMap);
                    break;
                case "3":
                    transerAccount(controlMap);
                    break;
                case "q":
                    System.out.println("Logged Out");
                    controlMap.remove("User");
                    controlMap.remove("UserId");
                    break;
                default:
                    System.out.println("Enter the valid choice");
            }

        } catch(LoginFail | ValidUserException exception){
            System.out.println(exception.getMessage());
        }
    }
    public void createAccount(HashMap<String, String> m1){
        System.out.println("Name ur Account");
        String accountName = sc.nextLine();

        System.out.println("What type of account you want");
        System.out.println("1: Checkings");
        System.out.println("2: Savings");
        int typeAccount = sc.nextInt();
        sc.nextLine();
        Account ac = new Account();
        ac.setUser_id(Integer.parseInt(m1.get("UserId")));
        ac.setName(accountName);
        if(typeAccount == 1 ){

            ac.setType("Checkings");
            accountService.createAccount(ac);
        }
        else if(typeAccount == 2){
            ac.setType("Savings");
            accountService.createAccount(ac);
        }
    }
    public List<Account> selectAccount(HashMap<String,String> m1){

        List<Account> accounts =  accountService.getUserAccounts(Integer.parseInt(m1.get("UserId")));
        int cnt = 1 ;
        for(Account ac : accounts){
            System.out.printf("Account No: %s \n", cnt++);
            System.out.printf("Account Name: %s \n",ac.getName() );
            System.out.printf("Account Type : %s \n", ac.getType());
            System.out.printf("Amount in your acccount: %s \n", ac.getAmount());
            System.out.println("\n");
        }
        return accounts;
    }

    public List<Account> selectAccount(HashMap<String,String> m1, String type){

        List<Account> accounts =  accountService.getUserAccounts(Integer.parseInt(m1.get("UserId")), type);
        int cnt = 1 ;
        for(Account ac : accounts){
            System.out.printf("Account No: %s \n", cnt++);
            System.out.printf("Account Name: %s \n",ac.getName() );
            System.out.printf("Account Type : %s \n", ac.getType());
            System.out.printf("Amount in your acccount: %s \n", ac.getAmount());
            System.out.println("\n");
        }
        return accounts;
    }
    public void transerAccount(HashMap<String, String > m1){
        List<Account> accounts = selectAccount(m1,"Checkings");
        System.out.println("Give Account Number You Transer from");
        int ncnt = sc.nextInt();
        sc.nextLine();
        Account account =  accounts.get(ncnt-1);

        while(true){
            System.out.printf("You have %s in your account \n",account.getAmount());
            System.out.println("Select what you want to do");
            System.out.println();

            System.out.println("1: Withdraw ");
            System.out.println("2: Deposit");
            System.out.println("3: Close the account");
            System.out.println("g: Go Back");
            String val = sc.nextLine();
            switch (val) {
                case "1":
                    System.out.println("Enter amount to withdraw: ");
                    float withdrawAmount = sc.nextFloat();
                    sc.nextLine();
                    if(account.getAmount() - withdrawAmount < 0 ){
                        System.out.println("Can't withdraw more than Balance" );
                        continue;
                    }
                    account.setAmount(account.getAmount() - withdrawAmount);
                    accountService.withdraw(account);
                    break;
                case "2":
                    System.out.println("Enter amount to deposit: ");
                    float depositAmount = sc.nextFloat();
                    sc.nextLine();
                    account.setAmount(account.getAmount() + depositAmount);
                     accountService.withdraw(account);
                    break;
                case "3":
                    if(account.getAmount() > 0 ){
                        System.out.println("Withdraw all the money from the account before closing");
                        continue;
                    }
                    else{
                        System.out.println("Press C to confirm or anything else to go Back");

                        String confirm = sc.nextLine();
                        if(confirm .equals("C")){
                            accountService.deleteAccount(account);
                            return;
                        }
                        else{
                            System.out.println("Thank u for your trust in our Bank");
                            continue;
                        }
                    }
                case "g":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }



}
