INSERT INTO subject (name, term)
SELECT 'Informatyka', 4
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Informatyka' AND term = 4);

INSERT INTO subject (name, term)
SELECT 'Informatyka', 5
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Informatyka' AND term = 5);

INSERT INTO subject (name, term)
SELECT 'Biotechnologia', 1
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Biotechnologia' AND term = 1);

INSERT INTO subject (name, term)
SELECT 'Biotechnologia', 2
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Biotechnologia' AND term = 2);

INSERT INTO ability (ability)
SELECT 'C#'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 1);

INSERT INTO ability (ability)
SELECT 'Java Spring'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 2);

INSERT INTO ability (ability)
SELECT 'React.js'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 3);

INSERT INTO student (email, login ,password, name, surname, availability, interests, image_url, id_subject)
SELECT 'kadrian13@o2.pl', 'adekos', '$2a$10$h4CrhYBIqUP9GHK7F.nJTOVpBCEEHlKCI9fMgRQrd5sYjyWA15YeO',  'Adrian', 'Kowal', 10,
       'Programista Java - framework Spring, Liga Legend, netflix', 'adi_photo', 1
WHERE NOT EXISTS (SELECT * FROM student WHERE id_student = 1); -- password: Mariuszek12

INSERT INTO student (email, login, password, name, surname, availability, interests, image_url, id_subject)
SELECT 'kadrian14@o2.pl', 'mariuszkos' ,'$2a$10$h4CrhYBIqUP9GHK7F.nJTOVpBCEEHlKCI9fMgRQrd5sYjyWA15YeO',  'Adrsas', 'Koxal', 5,
       'Programista Java, netflix', 'adi_photo4213123.jpg', 2
WHERE NOT EXISTS (SELECT * FROM student WHERE id_student = 2); -- password: Mariuszek12

INSERT INTO student_ability (id_ability_st, id_student_ab)
VALUES (1,1),(2,1),(3,1),(1,2),(2,2);

INSERT INTO project(completed, date, description, limits, title, student_id_student)
SELECT false, '2020-05-16 18:12:47', 'test1', 80, 'testujemy nr 1', 1
WHERE NOT EXISTS (SELECT * FROM project WHERE id_project = 1);

INSERT INTO project(completed, date, description, limits, title, student_id_student)
SELECT false, '2020-05-17 19:12:47', 'test2', 70, 'testujemy nr 2', 1
WHERE NOT EXISTS (SELECT * FROM project WHERE id_project = 2);

INSERT INTO project(completed, date, description, limits, title, student_id_student)
SELECT false, '2020-05-18 20:12:47', 'test3', 10, 'testujemy nr 3', 2
WHERE NOT EXISTS (SELECT * FROM project WHERE id_project = 3);

INSERT INTO project(completed, date, description, limits, title, student_id_student)
SELECT true, '2020-05-19 21:12:47', 'test4', 20, 'testujemy nr 4', 2
WHERE NOT EXISTS (SELECT * FROM project WHERE id_project = 4);

INSERT INTO project_abilities(project_id_project, abilities_id_ability)
VALUES (1,1),(2,1),(3,1),(4,2),(1,2),(1,3),(4,1),(3,3);

INSERT INTO project_student(id_project_st, id_student_pr, id_ability_proj, id_project_ab)
VALUES (1,1,1,1),(1,2,2,2),(2,1,2,2)