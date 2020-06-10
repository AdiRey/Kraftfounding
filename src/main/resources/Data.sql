INSERT INTO ability (ability)
SELECT 'C#'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 1);

INSERT INTO ability (ability)
SELECT 'Java Spring'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 2);

INSERT INTO ability (ability)
SELECT 'React.js'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 3);

INSERT INTO ability (ability)
SELECT 'C++'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 4);

INSERT INTO ability (ability)
SELECT 'Taniec'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 5);

INSERT INTO ability (ability)
SELECT 'Zwijanie parówek'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 6);

INSERT INTO ability (ability)
SELECT 'autocad'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 7);

INSERT INTO ability (ability)
SELECT 'Zarządzanie projektem'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 8);

INSERT INTO ability (ability)
SELECT 'czytanie ze zrozumieniem'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 9);

INSERT INTO ability (ability)
SELECT '120 na klate'
WHERE NOT EXISTS (SELECT * FROM ability WHERE id_ability = 10);

INSERT INTO subject (name, term)
SELECT 'Informatyka', 1
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Informatyka' AND term = 1);

INSERT INTO subject (name, term)
SELECT 'Informatyka', 2
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Informatyka' AND term = 2);

INSERT INTO subject (name, term)
SELECT 'Informatyka', 3
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Informatyka' AND term = 3);

INSERT INTO subject (name, term)
SELECT 'Informatyka', 4
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Informatyka' AND term = 4);

INSERT INTO subject (name, term)
SELECT 'Informatyka', 5
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Informatyka' AND term = 5);

INSERT INTO subject (name, term)
SELECT 'Informatyka', 6
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Informatyka' AND term = 6);

INSERT INTO subject (name, term)
SELECT 'Informatyka', 7
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Informatyka' AND term = 7);

INSERT INTO subject (name, term)
SELECT 'Biotechnologia', 1
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Biotechnologia' AND term = 1);

INSERT INTO subject (name, term)
SELECT 'Biotechnologia', 2
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Biotechnologia' AND term = 2);

INSERT INTO subject (name, term)
SELECT 'Biotechnologia', 3
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Biotechnologia' AND term = 3);

INSERT INTO subject (name, term)
SELECT 'Biotechnologia', 4
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Biotechnologia' AND term = 4);

INSERT INTO subject (name, term)
SELECT 'Biotechnologia', 5
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Biotechnologia' AND term = 5);

INSERT INTO subject (name, term)
SELECT 'Biotechnologia', 6
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Biotechnologia' AND term = 6);

INSERT INTO subject (name, term)
SELECT 'Biotechnologia', 7
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Biotechnologia' AND term = 7);

SELECT 'Zarządzanie', 1
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Zarządzanie' AND term = 1);

INSERT INTO subject (name, term)
SELECT 'Zarządzanie', 2
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Zarządzanie' AND term = 2);

INSERT INTO subject (name, term)
SELECT 'Zarządzanie', 3
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Zarządzanie' AND term = 3);

INSERT INTO subject (name, term)
SELECT 'Zarządzanie', 4
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Zarządzanie' AND term = 4);

INSERT INTO subject (name, term)
SELECT 'Zarządzanie', 5
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Zarządzanie' AND term = 5);

INSERT INTO subject (name, term)
SELECT 'Zarządzanie', 6
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Zarządzanie' AND term = 6);

INSERT INTO subject (name, term)
SELECT 'Zarządzanie', 7
WHERE NOT EXISTS (SELECT * FROM subject WHERE name = 'Zarządzanie' AND term = 7);

INSERT INTO academic_worker(email, image_url, interests, login, name, password, surname)
SELECT 'mpenar@kia.prz.edu.pl', 'tecza.jpg','Bazy danych, czyli triggerki te sprawy.', 'maciejox16','Maciej','$2a$10$h4CrhYBIqUP9GHK7F.nJTOVpBCEEHlKCI9fMgRQrd5sYjyWA15YeO','Penar'
WHERE NOT EXISTS(SELECT * FROM academic_worker WHERE id_worker = 1);

INSERT INTO student (email, login ,password, name, surname, availability, interests, image_url, id_subject)
SELECT 'kadrian13@o2.pl', 'adekos', '$2a$10$h4CrhYBIqUP9GHK7F.nJTOVpBCEEHlKCI9fMgRQrd5sYjyWA15YeO',  'Adrian', 'Kowal', 10,
       'Programista Java - framework Spring, Liga Legend, netflix', 'icon.png', 1
WHERE NOT EXISTS (SELECT * FROM student WHERE id_student = 1); -- password: Mariuszek12

INSERT INTO student (email, login, password, name, surname, availability, interests, image_url, id_subject)
SELECT 'kadrian14@o2.pl', 'mariuszkos' ,'$2a$10$h4CrhYBIqUP9GHK7F.nJTOVpBCEEHlKCI9fMgRQrd5sYjyWA15YeO',  'Łukasz', 'Koperwas', 5,
       'Programista C#, netflix', 'icon.png', 2
WHERE NOT EXISTS (SELECT * FROM student WHERE id_student = 2); -- password: Mariuszek12

INSERT INTO student (email, login, password, name, surname, availability, interests, image_url, id_subject)
SELECT 'mariuszek@o2.pl', 'mariuszkosXxx' ,'$2a$10$h4CrhYBIqUP9GHK7F.nJTOVpBCEEHlKCI9fMgRQrd5sYjyWA15YeO',  'Mateusz', 'Kolak', 10,
       'Programista', 'icon.png', 2
WHERE NOT EXISTS (SELECT * FROM student WHERE id_student = 3); -- password: Mariuszek12

INSERT INTO student_ability (id_ability_st, id_student_ab)
VALUES (1,1),(2,1),(3,1),(1,2),(2,2),(7,2),(8,1),(6,1),(9,1);

INSERT INTO project(completed, date, description, limits, title, student_id_student)
SELECT false, '2020-05-16 18:12:47',
       'Jesteśmy grupą młodych i mądrych studentów politechniki, którzy poszukują ludzi z pasją do programowania i chęcią rozwoju.', 20,
       'Tworzenie strony kina', 1
WHERE NOT EXISTS (SELECT * FROM project WHERE id_project = 1);

INSERT INTO project(completed, date, description, limits, title, student_id_student)
SELECT false, '2020-05-17 19:12:47', 'Po więcej informacji zapraszamy do kontaktu pod numer telefonu: +48 743 234 234', 70,
       'Projektowanie w autocadach', 1
WHERE NOT EXISTS (SELECT * FROM project WHERE id_project = 2);

INSERT INTO project(completed, date, description, limits, title, student_id_student)
SELECT false, '2020-05-18 20:12:47', 'Poszukujemy ludzi chcących rozwijać swoją osobowość i pasje taneczne w rzeszowskiej grupie ludowej.', 10,
       'Taneczne podkarpacie', 3
WHERE NOT EXISTS (SELECT * FROM project WHERE id_project = 3);

INSERT INTO project(completed, date, description, limits, title, student_id_student)
SELECT true, '2020-05-19 21:12:47', 'Masz drukarke i nie wiesz co drukować? Zapraszamy do nas do grupy, nie rozczarujemy Cię ;)', 20,
       'Drukarki 3D', 2
WHERE NOT EXISTS (SELECT * FROM project WHERE id_project = 4);

INSERT INTO project(completed, date, description, limits, title, student_id_student)
SELECT false, '2020-05-21 21:12:47',
       'Interesujesz się kosmosem? Znasz dobrze angielski? Interesują Cię tytuły i osiągnięcia? Jeśli tak - ten projekt jest idealny dla Ciebie :)', 20,
       'Łazik marsjański', 1
WHERE NOT EXISTS (SELECT * FROM project WHERE id_project = 5);

INSERT INTO project_abilities(project_id_project, abilities_id_ability)
VALUES (1,1),(2,7),(3,5),(4,2),(1,2),(1,3),(4,1),(3,3);

INSERT INTO project_student(id_project_st, id_student_pr, id_ability_proj, id_project_ab)
VALUES (1,1,1,1),(1,2,2,2),(2,1,2,2);

INSERT INTO file(id_file, name, project_id_project, student_id_student) SELECT
'1', 'edycja.html', 1, 1
WHERE NOT EXISTS (SELECT * FROM file WHERE id_file = 1);

INSERT INTO file(name, project_id_project, student_id_student) SELECT
                                                                            'edytuj.js', 1, 2
WHERE NOT EXISTS (SELECT * FROM file WHERE id_file = 2);

INSERT INTO file(name, project_id_project, student_id_student) SELECT
                                                                   'League of Legends.lnk', 1, 1
WHERE NOT EXISTS (SELECT * FROM file WHERE id_file = 3);

INSERT INTO file(name, project_id_project, student_id_student) SELECT
                                                                   'my-profile.html', 3, 2
WHERE NOT EXISTS (SELECT * FROM file WHERE id_file = 4);

INSERT INTO file(name, project_id_project, student_id_student) SELECT
                                                                   'World of Tanks EU.lnk', 2, 2
WHERE NOT EXISTS (SELECT * FROM file WHERE id_file = 5);

INSERT INTO file(name, project_id_project, student_id_student) SELECT
                                                                   'Kowal_Adrian_2FDI_L03.pdf', 2, 1
WHERE NOT EXISTS (SELECT * FROM file WHERE id_file = 6);

INSERT INTO file(name, project_id_project, student_id_student) SELECT
                                                                   'zak.png', 3, 1
WHERE NOT EXISTS (SELECT * FROM file WHERE id_file = 7);