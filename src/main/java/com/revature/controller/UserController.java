package com.revature.controller;

import com.revature.exception.LoginFail;
import com.revature.exception.ValidUserException;
import com.revature.service.UserService;
import com.revature.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserController {

    private Scanner scanner;
    private UserService userService;

    public UserController(Scanner scanner, UserService userService){
        this.scanner = scanner;
        this.userService = userService;
    }

    public void promptUserForService(HashMap<String,String> controlMap){

        System.out.println("What would you like to do?");
        System.out.println("1. register an account");
        System.out.println("2. login");
        System.out.println("q. quit");

        try{
            String userActionIndicated = scanner.nextLine();
            switch (userActionIndicated) {
                case "1":
                    registerNewUser();
                    break;
                case "2":
                    User user = login();


                    controlMap.put("User", user.getUsername());
                    controlMap.put("UserId",String.valueOf(user.getId()));
                    System.out.println(controlMap);


                    break;
                case "q":
                    System.out.println("Goodbye!");
                    controlMap.put("Continue Loop", "false");
            }

        } catch(LoginFail | ValidUserException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void registerNewUser(){

        User newCredentials = getUserCredentials();
        User newUser = userService.validateNewCredentials(newCredentials);
        System.out.printf("New account created: %s", newUser);
    }

    public User login(){

        return userService.checkLoginCredentials(getUserCredentials());
    }

    public User getUserCredentials(){
        String newUsername;
        String newPassword;
        // user needs to provide a username and password
        System.out.print("Please enter a username: ");
        newUsername = scanner.nextLine();
        System.out.print("Please enter a password: ");
        newPassword = scanner.nextLine();
        return new User(newUsername, newPassword);
    }

}
