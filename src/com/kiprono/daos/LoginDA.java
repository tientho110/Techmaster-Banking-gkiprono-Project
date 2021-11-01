package com.kiprono.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kiprono.models.Customers;
import com.kiprono.utils.DatabaseConnection;

public class LoginDA {

    private PreparedStatement statement;
    private Connection connection;
    ResultSet resultSet;

    // query database for customer with given username and password
    // get username annd password from database
    // if username and password match, return customer object
    // else return null
    public Customers finCustomer(String uname){
        Customers customer = new Customers();
        String query = "SELECT * FROM customers WHERE username = ?";

        connection = DatabaseConnection.getConnection();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, uname);
            resultSet = statement.executeQuery();
            resultSet.next();
            customer.setUserId(resultSet.getInt("userid"));
            customer.setUserName(resultSet.getString("username"));
            customer.setPasswd(resultSet.getString("passwd"));
            customer.setFirstName(resultSet.getString("firstname"));
            customer.setMiddleInitial(resultSet.getString("middleinitial"));
            customer.setLastName(resultSet.getString("lastname"));
            customer.setPhoneNumber(resultSet.getString("phonenumber"));
            customer.setAddress(resultSet.getString("address"));
            customer.setCity(resultSet.getString("city"));
            customer.setState(resultSet.getString("state"));
            customer.setZipCode(resultSet.getInt("zipcode"));
            customer.setAccountNumber(resultSet.getInt("accountnumber"));
            
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Wrong User id");
        } finally{
            closeResources();
        }
        
        return customer;        
    }

    private void closeResources() {
        try {
            if (connection != null && !statement.isClosed()) {
                connection.close();
                statement.close();
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // create main method to test the class
    public static void main(String[] args) {
        LoginDA loginDA = new LoginDA();
        Customers customer = loginDA.finCustomer("JohnAForsy");
        System.out.println(customer.getUserName());
    }

}