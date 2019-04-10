CREATE TABLE developers (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
age INT NOT NULL,
sex VARCHAR(255) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE companies (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
developers_number INT NOT NULL,
budget INT NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE customers (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
company VARCHAR(255),
budget INT NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE projects (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
developers_number INT NOT NULL,
customer VARCHAR(255) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE skills (
id INT NOT NULL AUTO_INCREMENT,
area VARCHAR(255) NOT NULL,
skills_level VARCHAR(255),
work_experience INT,
PRIMARY KEY(id)
);

INSERT INTO developers (name, age, sex) VALUES ("Alex", 23, "male"),("Serhij", 18, "male"),
("Vasilios", 19, "male"),("Lesya", 19, "female");

SELECT * FROM developers;

INSERT INTO skills (area, skills_level, work_experience) VALUES ("Java", "Senior", 48),
("JavaScript", "Junior", 2),("C#", "Junior", 3),("C++","Middle",12);

SELECT * FROM skills;

INSERT INTO projects (name, developers_number, customer) VALUES ("Monobank", 2, "Monobank company"),
("NAU secret project", 1, "NAU headquarter"),("Mate Academy designer course", 4, "Mate Academy");

INSERT INTO projects (name, developers_number, customer) VALUES ("English course", 3, "Mate Academy"),("Amazon website",4,"Amazon");

SELECT * FROM projects;

INSERT INTO companies (name, developers_number, budget) VALUES ("Mate Academy", 30, 50000),("CatsTown", 500, 1000000),("Monobank", 200, 300000);

SELECT * FROM companies;

INSERT INTO customers (name, company, budget) VALUES ("Andrew", "CatsTown", 20000),("Lesya","Mate Academy", 7000),("Max","Monobank", 14000);

SELECT * FROM customers;

DELETE FROM projects WHERE id = 6;
