CREATE TABLE developer_to_projects (
id INT NOT NULL AUTO_INCREMENT,
id_developer INT NOT NULL,
id_project INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (id_developer) REFERENCES developers(id),
FOREIGN KEY (id_project) REFERENCES projects(id)
);

INSERT INTO developer_to_projects (id_developers, id_project) VALUES (1,2),(1,3),(2,1),(2,2),(2,3),(3,3),(3,5),(4,2),(4,4),(4,5);

SELECT * FROM developer_to_projects;

CREATE TABLE companies_to_projects (
id INT NOT NULL AUTO_INCREMENT,
id_company INT NOT NULL,
id_project INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (id_company)REFERENCES companies(id),
FOREIGN KEY (id_project) REFERENCES projects(id)
);

INSERT INTO companies_to_projects (id_company,id_project) VALUES (1,3),(1,4),(1,5),(2,1),(2,2),(2,3),(2,4),(2,5),(3,1),(3,2),(3,5);

SELECT * FROM companies_to_projects;

CREATE TABLE developer_to_skill (
id INT NOT NULL AUTO_INCREMENT,
id_developer INT NOT NULL,
id_skill INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (id_developer) REFERENCES developers(id),
FOREIGN KEY (id_skill) REFERENCES skills(id)
);

INSERT INTO developer_to_skill (id_developer, id_skill) VALUES (1,1),(1,2),(2,2),(2,3),(2,3),(3,1),(3,2),(3,3),(4,1),(4,2),(4,3),(4,4);

SELECT * FROM developer_to_skill;

CREATE TABLE customers_to_projects (
id INT NOT NULL AUTO_INCREMENT,
id_customer INT NOT NULL,
id_project INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (id_customer) REFERENCES customers(id),
FOREIGN KEY (id_project) REFERENCES projects(id)
);

INSERT INTO customers_to_projects (id_customer,id_project) VALUES (1,1),(1,2),(2,3),(2,4),(3,1),(3,2),(3,5);

SELECT * FROM customers_to_projects;

ALTER TABLE developers
ADD salary INT NOT NULL;

SELECT * FROM developers;

UPDATE developers
SET salary = 1200
WHERE id = 1;

UPDATE developers
SET salary = 600
WHERE id = 2;

UPDATE developers
SET salary = 700
WHERE id = 3;

UPDATE developers
SET salary = 2500
WHERE id = 4;

ALTER TABLE projects
ADD price INT NOT NULL;

UPDATE projects
SET price = 600
WHERE id = 1;

UPDATE projects
SET price = 4300
WHERE id = 2;

UPDATE projects
SET price = 2500
WHERE id = 3;

UPDATE projects
SET price = 2500
WHERE id = 7;

UPDATE projects
SET price = 3200
WHERE id = 8;

SELECT MAX(price)
FROM projects;

ALTER TABLE projects
ADD cost INT NOT NULL;

UPDATE projects
SET cost = 40000
WHERE id = 1;

UPDATE projects
SET cost = 120000
WHERE id = 2;

UPDATE projects
SET cost = 15000
WHERE id = 3;

UPDATE projects
SET cost = 4000
WHERE id = 7;

UPDATE projects
SET cost = 50000
WHERE id = 8;

SELECT MIN(cost)
FROM projects;

SELECT SUM(developers.salary)
FROM developers
INNER JOIN developer_to_skill ON
developers.id = developer_to_skill.id_developer
WHERE id_skill = 1;

SELECT AVG(developers.salary)
FROM developers
INNER JOIN developer_to_projects ON
developers.id = developer_to_projects.id_developers
WHERE id_project = 4;
