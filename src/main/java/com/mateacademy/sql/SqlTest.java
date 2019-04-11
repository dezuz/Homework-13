package com.mateacademy.sql;

import java.sql.SQLException;

public class SqlTest {
    public static void main(String[] args) {
        ConnectorUtil connectorUtil = new ConnectorUtil();
        try {
            connectorUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
