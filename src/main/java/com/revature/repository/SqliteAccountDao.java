package com.revature.repository;

import com.revature.entity.Account;
import com.revature.entity.User;
import com.revature.exception.UserSQLException;
import com.revature.utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteAccountDao implements AccountDao  {


    @Override
    public Account createAccount(Account accountCredentials) {
        String sql = "insert into account ( type, user_id, name )values (?,?, ?)";
        try(Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, accountCredentials.getType());
            preparedStatement.setInt(2,accountCredentials.getUser_id());
            preparedStatement.setString(3, accountCredentials.getName());
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

    @Override
    public List<Account> getUserAccounts(int userId) {
        String sql = "select * from account where user_id = ? ";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet resultSet = ps.executeQuery();

            List<Account> accounts = new ArrayList<>();
            while(resultSet.next()){

                Account ac = new Account();

                ac.setType(resultSet.getString("type"));
                ac.setAmount(resultSet.getFloat("amount"));
                ac.setName(resultSet.getString("name"));
                accounts.add(ac);
            }
            return accounts;
        } catch (SQLException exception){
            throw new UserSQLException(exception.getMessage());
        }
    }

    @Override
    public List<Account> getUserAccounts(int userId,String type) {
        String sql = "select * from account where user_id = ? and type = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2,type);
            ResultSet resultSet = ps.executeQuery();

            List<Account> accounts = new ArrayList<>();
            while(resultSet.next()){

                Account ac = new Account();

                ac.setType(resultSet.getString("type"));
                ac.setAmount(resultSet.getFloat("amount"));
                ac.setName(resultSet.getString("name"));
                accounts.add(ac);
            }
            return accounts;
        } catch (SQLException exception){
            throw new UserSQLException(exception.getMessage());
        }
    }

    @Override
    public Account withdraw(Account newCredentials) {
        String sql = "update account set amount = ? where name = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setFloat(1,newCredentials.getAmount());
            ps.setString(2,newCredentials.getName());

            int result = ps.executeUpdate();


            if (result == 1){
                return newCredentials;
            }
            throw new UserSQLException("Account could not be created: please try again");
        } catch (SQLException exception){
            throw new UserSQLException(exception.getMessage());
        }

    }

    @Override
    public void deleteAccount(Account account) {

        String sql = "DELETE from account where name = ?";
        ;
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1,account.getName());
            System.out.println(ps);
            int res = ps.executeUpdate();
           if(res == 1 )return ;
           throw new UserSQLException("Account could not be Deleted: please try again");


        } catch (SQLException exception){
            throw new UserSQLException(exception.getMessage());
        }

    }


}
