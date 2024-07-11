package com.revature.service;

import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.exception.ValidUserException;
import com.revature.repository.UserDao;

import java.util.List;


public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao){

        this.userDao = userDao;
    }


    public User validateNewCredentials(User newUserCredentials){

        if (checkUsernamePasswordLength(newUserCredentials)){

            if(checkUsernameIsUnique(newUserCredentials)){

                return userDao.createUser(newUserCredentials);

            }
        }

        throw new ValidUserException("User may already exist Or New User credentials are not valid ");
    }

    public User checkLoginCredentials(User credentials){


        User user = userDao.getUser(credentials);
        boolean usernameMatches = user.getUsername().equals(credentials.getUsername());
        boolean passwordMatches = user.getPassword().equals(credentials.getPassword());
        if (usernameMatches && passwordMatches){
            return credentials;
        }
//        for (User user : userDao.getUser(credentials)){
//            // if username and password match return the credentials
//
//        }
        // this exception holds our failure message for the user if their credentials are invalid
        throw new LoginFail("Credentials are invalid: please try again");
    }


    // we will use this method to check the length of the credentials
    private boolean checkUsernamePasswordLength(User newUserCredentials){
        boolean usernameIsValid = newUserCredentials.getUsername().length() <= 30;
        boolean passwordIsValid = newUserCredentials.getPassword().length() <= 30;
        return usernameIsValid && passwordIsValid;
    }

    // we will use this method to make sure the username is unique
    private boolean checkUsernameIsUnique(User newUserCredentials){
        boolean usernameIsUnique = true;
        User user = userDao.getUser(newUserCredentials);
        System.out.println(user);
        if(user != null)usernameIsUnique = false;
        return usernameIsUnique;
    }

}
