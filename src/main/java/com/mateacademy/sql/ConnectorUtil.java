package com.mateacademy.sql;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ConnectorUtil {
    private static final Logger logger = Logger.getLogger(ConnectorUtil.class);
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/hw13?serverTimezone=Europe/Kiev&useSSL=false";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String project = "INSERT INTO projects (name, developers_number, customer, price, cost, creation_date) " +
            "VALUES (?,?,?,?,?,?)";
    private String customer = "INSERT INTO customers (name, company, budget) " +
            "VALUES (?,?,?)";
    private String developer = "INSERT INTO developers (name, age, sex, salary) " +
            "VALUES (?,?,?,?)";

    public void getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            logger.error("Error" + e);
        }
    }

    public void createMethod(String createRequest) {
        try {
            statement = connection.createStatement();
            statement.execute(createRequest);
            statement.close();
        } catch (SQLException e) {
            logger.error("Error" + e);
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
            logger.error("Error" + e);
        }
    }

    public void updateOrDeleteMethod(String createRequest) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(createRequest);
            statement.close();
        } catch (SQLException e) {
            logger.error("Error" + e);
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
            logger.error("Error" + e);
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
            logger.error("Error" + e);
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
            logger.error("Error" + e);
        }
    }


    public static void main(String[] args) {
        ConnectorUtil connectorUtil = new ConnectorUtil();
        connectorUtil.getConnection();
        connectorUtil.createMethod("INSERT INTO skills (area, skills_level) " +
                "VALUES (\"Java\", \"Junior\")");
        connectorUtil.updateOrDeleteMethod("UPDATE skills SET area = \"C++\" WHERE id = 6");
        connectorUtil.updateOrDeleteMethod("DELETE FROM skills WHERE id = 6");
        System.out.println("Salaries of all developers on one project:\n" +
                "------------------------------------------");
        connectorUtil.readMethod("SELECT SUM(developers.salary)\n" +
                "FROM developers\n" +
                "INNER JOIN developer_to_projects ON\n" +
                "developers.id = developer_to_projects.id_developers\n" +
                "WHERE id_project = 4;", 1);
        System.out.println("Developers name on project:\n" +
                "------------------------------------------");
        connectorUtil.readMethod("SELECT developers.name\n" +
                "FROM developers\n" +
                "INNER JOIN developer_to_projects ON\n" +
                "developers.id = developer_to_projects.id_developers\n" +
                "WHERE id_project = 1;", 1);
        System.out.println("All Java developers:\n" +
                "------------------------------------------");
        connectorUtil.readMethod("SELECT developers.name\n" +
                "FROM developers\n" +
                "INNER JOIN developer_to_skill ON\n" +
                "developers.id = developer_to_skill.id_developer\n" +
                "WHERE id_skill = 2;", 1);
        System.out.println("All middle developers:\n" +
                "------------------------------------------");
        connectorUtil.readMethod("SELECT developers.name\n" +
                "FROM developers\n" +
                "INNER JOIN skills ON\n" +
                "developers.id = skills.id\n" +
                "WHERE skills.skills_level = \"middle\";", 1);
        System.out.println("All projects (creation date : project name : developer numbers):\n" +
                "------------------------------------------");
        connectorUtil.readMethod("SELECT creation_date,name,developers_number\n" +
                "FROM projects;", 3);
        connectorUtil.createNewProject("Project1", 4, "Project company", 5000,
                1_000_000, "09.04.2019");
        connectorUtil.createNewDeveloper("Andrew", 19, "male", 550);
        connectorUtil.createNewCustomer("Max", "NAU company", 40_000);
    }
}
