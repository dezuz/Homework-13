package com.mateacademy.sql;

import java.sql.SQLException;

public class SqlTest {
    private static String skillsInsert = "INSERT INTO skills (area, skills_level) " +
            "VALUES (\"Java\", \"Junior\")";
    private static String updateSkills = "UPDATE skills SET area = \"C++\" WHERE id = 6";
    private static String deleteSkills = "DELETE FROM skills WHERE id = 6";
    private static String selectSumOfSalary = "SELECT SUM(developers.salary)\n" +
            "FROM developers\n" +
            "INNER JOIN developer_to_projects ON\n" +
            "developers.id = developer_to_projects.id_developers\n" +
            "WHERE id_project = 4;";
    private static String selectDevelopersNameOnProject = "SELECT developers.name\n" +
            "FROM developers\n" +
            "INNER JOIN developer_to_projects ON\n" +
            "developers.id = developer_to_projects.id_developers\n" +
            "WHERE id_project = 1;";
    private static String selectAllJavaDevelopers = "SELECT developers.name\n" +
            "FROM developers\n" +
            "INNER JOIN developer_to_skill ON\n" +
            "developers.id = developer_to_skill.id_developer\n" +
            "WHERE id_skill = 2;";
    private static String selectMiddleDevelopers = "SELECT developers.name\n" +
            "FROM developers\n" +
            "INNER JOIN skills ON\n" +
            "developers.id = skills.id\n" +
            "WHERE skills.skills_level = \"middle\";";
    private static String selectProjects = "SELECT creation_date,name,developers_number\n" +
            "FROM projects;";

    public static void main(String[] args) {
        ConnectorUtil connectorUtil = new ConnectorUtil();
        try {
            connectorUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectorUtil.createMethod(skillsInsert);
        connectorUtil.updateOrDeleteMethod(updateSkills);
        connectorUtil.updateOrDeleteMethod(deleteSkills);
                System.out.println("Salaries of all developers on one project:\n" +
                "------------------------------------------");
        connectorUtil.readMethod(selectSumOfSalary, 1);
        System.out.println("Developers name on project:\n" +
                "------------------------------------------");
        connectorUtil.readMethod(selectDevelopersNameOnProject, 1);
        System.out.println("All Java developers:\n" +
                "------------------------------------------");
        connectorUtil.readMethod(selectAllJavaDevelopers, 1);
        System.out.println("All middle developers:\n" +
                "------------------------------------------");
        connectorUtil.readMethod(selectMiddleDevelopers, 1);
        System.out.println("All projects (creation date : project name : developer numbers):\n" +
                "------------------------------------------");
        connectorUtil.readMethod(selectProjects, 3);
        ProjectTable projectTable = new ProjectTable("Project2", 4, "Project company", 5000,
                1_000_000, "09.04.2019");
        connectorUtil.createNewProject(projectTable);
        connectorUtil.createNewDeveloper("Andrew", 19, "male", 550);
        connectorUtil.createNewCustomer("Max", "NAU company", 40_000);
    }
}
