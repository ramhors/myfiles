DROP TABLE If exists students;

CREATE TABLE students(
	
	student_id Integer PRIMARY KEY AUTO_INCREMENT,
	student_first_name VARCHAR(255) NOT NULL,
	student_last_name VARCHAR(255) NOT NULL,
	student_classification VARCHAR(20) NOT NULL,
	student_age INTEGER NOT NULL

);

INSERT INTO students
	(student_first_name,student_last_name,student_classification,student_age)values
	('Maurice','Smith','Freshman',20),('Patrick','Pat','Freshman',19);

INSERT INTO students (student_first_name,student_last_name,student_classification,student_age)
VALUES('Pit','Brad','Freshman',23),('Bob','Kirk','Freshman',25);

select * from students;

select * from grades;

select student_last_name, student_age from students;

UPDATE students SET student_last_name='Rakotoarimanana' where students.student_id= 1;

DELETE FROM students WHERE student_id = 2;

DELETE FROM students;

UPDATE students 
SET student_first_name = ?,
	student_last_name  = ?,
	student_classification  = ?,
	student_age  = ?
	where student_id = ?;

drop table Animal;
	
CREATE TABLE Animal (
	animal_id SERIAL PRIMARY KEY,
	animal_name VARCHAR(50) NOT NULL,
	animal_age INTEGER NOT NULL,
	animal_breed VARCHAR(20) NOT NULL,
	animal_weight INTEGER NOT NULL
);

INSERT INTO animal (animal_name,animal_age,animal_breed,animal_weight)
VALUES('Elephant',8,'African',350),('Zebra',5,'Genus-equus',80),('Dog',10,'Beagle',60);

SELECT * FROM animal;


CREATE TABLE grades (
	grade_id SERIAL PRIMARY KEY,
	grade INTEGER NOT NULL,
	assignment_name VARCHAR(100) NOT NULL,
	student_id INTEGER NOT NULL,
	
	CONSTRAINT fk_student FOREIGN KEY(student_id) REFERENCES students(student_id)
	
);

SELECT s.student_first_name AS Name,g.grade 
FROM students s
INNER JOIN grades g
ON s.student_id = g.student_id;

SELECT assignment_name, AVG(grade)
from grades 
GROUP BY assignment_name 
having AVG(grade) > 80;

select * from grades;

SELECT student_first_name,student_last_name from students 
INNER JOIN
grade from grades;

INSERT INTO grades(grade,assignment_name,student_id)VALUES(70,'Homework',9),(80,'Homeworkd',10),(95,'Homework',12),(85,'Homework',13);

