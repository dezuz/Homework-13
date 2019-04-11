package com.mateacademy.sql;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;

public class ConnectorUtil {
    private static final Logger LOGGER = Logger.getLogger(ConnectorUtil.class);
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Properties properties;
    private String project = "INSERT INTO projects (name, developers_number, customer, price, cost, creation_date) " +
            "VALUES (?,?,?,?,?,?)";
    private String customer = "INSERT INTO customers (name, company, budget) " +
            "VALUES (?,?,?)";
    private String developer = "INSERT INTO developers (name, age, sex, salary) " +
            "VALUES (?,?,?,?)";

    public void getConnection() throws SQLException {
        createProperties();
        try(InputStream in = new FileInputStream("/database.properties")){
            properties.load(in);
        } catch (IOException e ) {
            LOGGER.error("Error" + e);
        }
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        connection = DriverManager.getConnection(url, username, password);
    }

    public void createMethod(String createRequest) {
        try {
            statement = connection.createStatement();
            statement.execute(createRequest);
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        }
    }

    public void readMethod(String createRequest, Integer count) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(createRequest);
            while (resultSet.next()) {
                for (int i = 1; i <= count; i++) {
                    System.out.print(resultSet.getObject(i) + "  ");
                }
                System.out.println();
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        }
    }

    public void updateOrDeleteMethod(String createRequest) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(createRequest);
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        }
    }

    public void createNewProject(String name, Integer developersNumber,
                                 String customer, Integer price, Integer cost, String creationDate) {
        try {
            PreparedStatement firstPreparedStatement = connection.prepareStatement(project);
            firstPreparedStatement.setString(1, name);
            firstPreparedStatement.setInt(2, developersNumber);
            firstPreparedStatement.setString(3, customer);
            firstPreparedStatement.setInt(4, price);
            firstPreparedStatement.setInt(5, cost);
            firstPreparedStatement.setString(6, creationDate);
            firstPreparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        }

    }

    public void createNewDeveloper(String name, Integer age, String sex, Integer salary) {
        try {
            PreparedStatement secondPreparedStatement = connection.prepareStatement(developer);
            secondPreparedStatement.setString(1, name);
            secondPreparedStatement.setInt(2, age);
            secondPreparedStatement.setString(3, sex);
            secondPreparedStatement.setInt(4, salary);
            secondPreparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        }
    }

    public void createNewCustomer(String name, String company, Integer budget) {
        try {
            PreparedStatement thirdPreparedStatement = connection.prepareStatement(customer);
            thirdPreparedStatement.setString(1, name);
            thirdPreparedStatement.setString(2, company);
            thirdPreparedStatement.setInt(3, budget);
            thirdPreparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        }
    }

    private void createProperties() {
        properties = new Properties();
        properties.setProperty("url","jdbc:mysql://localhost:3306/hw13?serverTimezone=Europe/Kiev&useSSL=false");
        properties.setProperty("username", "root");
        properties.setProperty("password", "root");
    }
}
