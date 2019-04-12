SELECT SUM(developers.salary)
FROM developers
INNER JOIN developer_to_skill ON
developers.id = developer_to_skill.id_developer
WHERE id_skill = 1;

SELECT SUM(developers.salary)
FROM developers
INNER JOIN developer_to_projects ON
developers.id = developer_to_projects.id_developers
WHERE id_project = 4;

SELECT developers.name
FROM developers
INNER JOIN developer_to_projects ON
developers.id = developer_to_projects.id_developers
WHERE id_project = 1;

SELECT developers.name
FROM developers
INNER JOIN developer_to_skill ON
developers.id = developer_to_skill.id_developer
WHERE id_skill = 2;

SELECT developers.name
FROM developers
INNER JOIN skills ON
developers.id = skills.id
WHERE skills.skills_level = "middle";

ALTER TABLE projects
ADD creation_date VARCHAR(255) NOT NULL;

UPDATE projects
SET creation_date = ("07.07.2003")
WHERE id = 8;

SELECT * FROM projects;

SELECT creation_date,name,developers_number
FROM projects;
