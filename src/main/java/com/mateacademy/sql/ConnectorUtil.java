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
    private String path = "src/main/resources/database.properties";
    private final String PROJECT = "INSERT INTO projects (name, developers_number, customer, price, cost, creation_date) " +
            "VALUES (?,?,?,?,?,?)";
    private final String CUSTOMER = "INSERT INTO customers (name, company, budget) " +
            "VALUES (?,?,?)";
    private final String DEVELOPER = "INSERT INTO developers (name, age, sex, salary) " +
            "VALUES (?,?,?,?)";

    public void createMethod(String createRequest) throws SQLException {
        properties = new Properties();
        try(InputStream in = new FileInputStream(path)){
            properties.load(in);
        } catch (IOException e ) {
            LOGGER.error("Error" + e);
        }
        try {
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            statement.execute(createRequest);
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        } finally {
            statement.close();
            connection.close();
        }
    }

    public void readMethod(String createRequest, Integer count) throws SQLException {
        properties = new Properties();
        try(InputStream in = new FileInputStream(path)){
            properties.load(in);
        } catch (IOException e ) {
            LOGGER.error("Error" + e);
        }
        try {
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
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
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        } finally {
            statement.close();
            resultSet.close();
            connection.close();
        }
    }

    public void updateOrDeleteMethod(String createRequest) throws SQLException {
        properties = new Properties();
        try(InputStream in = new FileInputStream(path)){
            properties.load(in);
        } catch (IOException e ) {
            LOGGER.error("Error" + e);
        }
        try {
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            statement.executeUpdate(createRequest);
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        } finally {
            statement.close();
            connection.close();
        }
    }

    public void createNewProject(ProjectTable projectTable) throws SQLException {
        properties = new Properties();
        try(InputStream in = new FileInputStream(path)){
            properties.load(in);
        } catch (IOException e ) {
            LOGGER.error("Error" + e);
        }
        try {
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement firstPreparedStatement = connection.prepareStatement(PROJECT);
            firstPreparedStatement.setString(1, projectTable.getName());
            firstPreparedStatement.setInt(2, projectTable.getDevelopersNumber());
            firstPreparedStatement.setString(3, projectTable.getCustomer());
            firstPreparedStatement.setInt(4, projectTable.getPrice());
            firstPreparedStatement.setInt(5, projectTable.getCost());
            firstPreparedStatement.setString(6, projectTable.getCreationDate());
            firstPreparedStatement.execute();
            firstPreparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        } finally {
            connection.close();
        }
    }

    public void createNewDeveloper(String name, Integer age, String sex, Integer salary) throws SQLException {
        properties = new Properties();
        try(InputStream in = new FileInputStream(path)){
            properties.load(in);
        } catch (IOException e ) {
            LOGGER.error("Error" + e);
        }
        try {
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement secondPreparedStatement = connection.prepareStatement(DEVELOPER);
            secondPreparedStatement.setString(1, name);
            secondPreparedStatement.setInt(2, age);
            secondPreparedStatement.setString(3, sex);
            secondPreparedStatement.setInt(4, salary);
            secondPreparedStatement.execute();
            secondPreparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        } finally {
            connection.close();
        }
    }

    public void createNewCustomer(String name, String company, Integer budget) throws SQLException {
        properties = new Properties();
        try(InputStream in = new FileInputStream(path)){
            properties.load(in);
        } catch (IOException e ) {
            LOGGER.error("Error" + e);
        }
        try {
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement thirdPreparedStatement = connection.prepareStatement(CUSTOMER);
            thirdPreparedStatement.setString(1, name);
            thirdPreparedStatement.setString(2, company);
            thirdPreparedStatement.setInt(3, budget);
            thirdPreparedStatement.execute();
            thirdPreparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        } finally {
            connection.close();
        }
    }
}
