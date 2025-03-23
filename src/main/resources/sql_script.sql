CREATE DATABASE my_batis_db;

CREATE TABLE instructors (
                             instructor_id SERIAL PRIMARY KEY ,
                             instructor_name VARCHAR(50) NOT NULL ,
                             email VARCHAR(250) NOT NULL
);

CREATE TABLE courses (
                         course_id SERIAL PRIMARY KEY ,
                         course_name VARCHAR(250) NOT NULL ,
                         description TEXT,
                         instructor_id INT REFERENCES instructors(instructor_id) ON DELETE CASCADE NOT NULL
);

CREATE TABLE students (
                          student_id SERIAL PRIMARY KEY ,
                          student_name VARCHAR(50) NOT NULL ,
                          email VARCHAR(250) NOT NULL ,
                          phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE student_course (
                                id SERIAL PRIMARY KEY ,
                                student_id INT REFERENCES students(student_id) ON DELETE CASCADE NOT NULL ,
                                course_id INT REFERENCES courses(course_id) ON DELETE CASCADE NOT NULL
);

INSERT INTO students (student_name, email, phone_number) VALUES
                                                             ('John Doe', 'john.doe@example.com', '123-456-7890'),
                                                             ('Jane Smith', 'jane.smith@example.com', '123-456-7891'),
                                                             ('Michael Johnson', 'michael.johnson@example.com', '123-456-7892'),
                                                             ('Emily Davis', 'emily.davis@example.com', '123-456-7893'),
                                                             ('Chris Brown', 'chris.brown@example.com', '123-456-7894'),
                                                             ('Anna Williams', 'anna.williams@example.com', '123-456-7895'),
                                                             ('James Wilson', 'james.wilson@example.com', '123-456-7896'),
                                                             ('Patricia Martinez', 'patricia.martinez@example.com', '123-456-7897'),
                                                             ('David Taylor', 'david.taylor@example.com', '123-456-7898'),
                                                             ('Mary Anderson', 'mary.anderson@example.com', '123-456-7899');


-- Instructors data
INSERT INTO instructors (instructor_name, email) VALUES
                                                     ('Dr. Michael Brown', 'michael.brown@example.com'),
                                                     ('Prof. Sarah Lee', 'sarah.lee@example.com'),
                                                     ('Dr. James Wilson', 'james.wilson@example.com'),
                                                     ('Prof. Emma Clark', 'emma.clark@example.com'),
                                                     ('Dr. Robert Martinez', 'robert.martinez@example.com');

-- Courses data
INSERT INTO courses (course_name, description, instructor_id) VALUES
                                                                  ('Introduction to Programming', 'A basic course on programming principles using Java.', 1),
                                                                  ('Data Structures and Algorithms', 'An intermediate course on data structures and algorithms in C++.', 2),
                                                                  ('Web Development', 'A comprehensive course on building websites using HTML, CSS, and JavaScript.', 3),
                                                                  ('Database Management Systems', 'Learn the fundamentals of relational databases and SQL.', 4),
                                                                  ('Software Engineering', 'A course on software development methodologies and practices.', 5);

-- Student-Course mapping data (You can modify student IDs as needed)
INSERT INTO student_course (student_id, course_id) VALUES
                                                       (1, 1), -- John Doe enrolled in Introduction to Programming
                                                       (2, 2), -- Jane Smith enrolled in Data Structures and Algorithms
                                                       (3, 3), -- Michael Johnson enrolled in Web Development
                                                       (4, 4), -- Emily Davis enrolled in Database Management Systems
                                                       (5, 5), -- Chris Brown enrolled in Software Engineering
                                                       (6, 1), -- Anna Williams enrolled in Introduction to Programming
                                                       (7, 2), -- James Wilson enrolled in Data Structures and Algorithms
                                                       (8, 3), -- Patricia Martinez enrolled in Web Development
                                                       (9, 4), -- David Taylor enrolled in Database Management Systems
                                                       (10, 5); -- Mary Anderson enrolled in Software Engineering



INSERT INTO student_course (student_id, course_id) VALUES
                                                       (1, 1), -- John Doe enrolled in Introduction to Programming
                                                       (2, 2), -- Jane Smith enrolled in Data Structures and Algorithms
                                                       (3, 3), -- Michael Johnson enrolled in Web Development
                                                       (4, 4), -- Emily Davis enrolled in Database Management Systems
                                                       (5, 5), -- Chris Brown enrolled in Software Engineering
                                                       (6, 1), -- Anna Williams enrolled in Introduction to Programming
                                                       (7, 2), -- James Wilson enrolled in Data Structures and Algorithms
                                                       (8, 3), -- Patricia Martinez enrolled in Web Development
                                                       (9, 4), -- David Taylor enrolled in Database Management Systems
                                                       (10, 5); -- Mary Anderson enrolled in Software Engineering