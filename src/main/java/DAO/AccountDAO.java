package DAO;

import Util.ConnectionUtil;
import Model.Account;

import java.sql.*;
import java.util.*;

/* 
*  GET localhost:8080/accounts/{account_id}/messages : list all posts from a particular user
*  POST localhost:8080/register : create a new account
*  POST localhost:8080/login : verify login
*/

public class AccountDAO {

    //Retrieve all posts by User

    public List<Account> getAllMessagesFromUser() {
        Connection connection = ConnectionUtil.getConnection();
        List<Account> accounts = new ArrayList<>();

        try {
            String sql = "SELECT * FROM account WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                accounts.add(account);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //is this supposed to be message? 
        }
        return null;

    }

    //Create a new account
    
    public Account postAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO account (username, password) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword()); 

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()) {
                int generated_account_key = (int) pkeyResultSet.getInt(1);
                return Account(generated_account_key, account.getUsername());
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null; 
    }

    private Account Account(int generated_account_key, String username) {
        return null; 
    } //is this method neccessary? It made line 55 happy again. 


    //Verify login

    private Account postLogin(String username, String password) {
        Connection connection = ConnectionUtil.getConnection(); 
        Account account = null;

        try {
            String sql = "SELECT account_id, username FROM account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next()) {
                int accountId = rs.getInt("account_id");
                String fetchedUsername = rs.getString("username");
                account = new Account(accountId, fetchedUsername); //need to figure this one out. 
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return account;
    }

}
