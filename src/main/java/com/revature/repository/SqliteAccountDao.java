package com.revature.repository;

import com.revature.entity.Account;
import com.revature.exception.UserSQLException;
import com.revature.utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqliteAccountDao implements AccountDao  {


    @Override
    public Account createAccount(Account accountCredentials) {
        String sql = "insert into account (name, type )values (?, ?)";
        try(Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, accountCredentials.getName());
            preparedStatement.setString(2, accountCredentials.getType());
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return accountCredentials;
            }
            throw new UserSQLException("Account could not be created: please try again");
        } catch (SQLException exception){
            throw new UserSQLException(exception.getMessage());
        }
    }

    @Override
    public Account getDetails() {
        return null;
    }
}
