package com.revature.repository;

import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.exception.UserSQLException;
import com.revature.utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserDao implements UserDao{
    @Override
    public User createUser(User newUserCredentials) {

        String sql = "insert into user (username, password )values (?, ?)";
        try(Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newUserCredentials.getUsername());
            preparedStatement.setString(2, newUserCredentials.getPassword());
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return newUserCredentials;
            }
            throw new UserSQLException("User could not be created: please try again");
        } catch (SQLException exception){
            throw new UserSQLException(exception.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {

        String sql = "select * from user";
        try(Connection connection = DatabaseConnector.createConnection()){

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            List<User> users = new ArrayList<>();
            while(resultSet.next()){

                User userRecord = new User();

                userRecord.setUsername(resultSet.getString("username"));
                users.add(userRecord);
            }
            return users;
        } catch (SQLException exception){
            throw new UserSQLException(exception.getMessage());
        }
    }

    @Override
    public User getUser(User newUser){
        String sql = "select * from user where username = ? ";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newUser.getUsername());

            ResultSet resultSet = ps.executeQuery();

            User user = new User();

            if(resultSet.next()){

                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            else{
                return null;
            }

            return user;
        } catch (SQLException exception){
            throw new UserSQLException(exception.getMessage());
        }


    }
}
