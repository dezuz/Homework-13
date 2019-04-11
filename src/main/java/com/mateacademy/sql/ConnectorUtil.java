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
    private String url;
    private String username;
    private String password;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Properties properties;
    private String path = "C:\\Users\\dezuz\\IdeaProjects\\MateAcademyProject13\\src\\main\\resources\\database.properties";
    private String project = "INSERT INTO projects (name, developers_number, customer, price, cost, creation_date) " +
            "VALUES (?,?,?,?,?,?)";
    private String customer = "INSERT INTO customers (name, company, budget) " +
            "VALUES (?,?,?)";
    private String developer = "INSERT INTO developers (name, age, sex, salary) " +
            "VALUES (?,?,?,?)";

    public void getConnection() throws SQLException {
        properties = new Properties();
        try(InputStream in = new FileInputStream(path)){
            properties.load(in);
        } catch (IOException e ) {
            LOGGER.error("Error" + e);
        }
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");

        connection = DriverManager.getConnection(url, username, password);
        connection.close();
    }

    public void createMethod(String createRequest) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            statement.execute(createRequest);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        }
    }

    public void readMethod(String createRequest, Integer count) {
        try {
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
        }
    }

    public void updateOrDeleteMethod(String createRequest) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            statement.executeUpdate(createRequest);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        }
    }

    public void createNewProject(ProjectTable projectTable) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement firstPreparedStatement = connection.prepareStatement(project);
            firstPreparedStatement.setString(1, projectTable.getName());
            firstPreparedStatement.setInt(2, projectTable.getDevelopersNumber());
            firstPreparedStatement.setString(3, projectTable.getCustomer());
            firstPreparedStatement.setInt(4, projectTable.getPrice());
            firstPreparedStatement.setInt(5, projectTable.getCost());
            firstPreparedStatement.setString(6, projectTable.getCreationDate());
            firstPreparedStatement.execute();
            firstPreparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        }
    }

    public void createNewDeveloper(String name, Integer age, String sex, Integer salary) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement secondPreparedStatement = connection.prepareStatement(developer);
            secondPreparedStatement.setString(1, name);
            secondPreparedStatement.setInt(2, age);
            secondPreparedStatement.setString(3, sex);
            secondPreparedStatement.setInt(4, salary);
            secondPreparedStatement.execute();
            secondPreparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        }
    }

    public void createNewCustomer(String name, String company, Integer budget) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement thirdPreparedStatement = connection.prepareStatement(customer);
            thirdPreparedStatement.setString(1, name);
            thirdPreparedStatement.setString(2, company);
            thirdPreparedStatement.setInt(3, budget);
            thirdPreparedStatement.execute();
            thirdPreparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error" + e);
        }
    }
}
