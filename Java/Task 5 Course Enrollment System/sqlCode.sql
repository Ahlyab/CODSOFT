-- Create the Course Database
CREATE DATABASE IF NOT EXISTS course_management;
USE course_management;

-- Create the Course Table
CREATE TABLE IF NOT EXISTS courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_code VARCHAR(10) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    capacity INT NOT NULL,
    schedule VARCHAR(255) NOT NULL
);

-- Create the Student Table
CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create the Student-Course Registration Table (Many-to-Many Relationship)
CREATE TABLE IF NOT EXISTS student_course (
    registration_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

-- Course Listing: Display available courses with details and available slots
-- SELECT c.course_code, c.title, c.description, c.capacity, c.schedule, 
--        c.capacity - IFNULL(SUM(s.course_id = c.course_id), 0) AS available_slots
-- FROM courses c
-- LEFT JOIN student_course s ON c.course_id = s.course_id
-- GROUP BY c.course_id;

-- Student Registration: Allow students to register for courses from the available options
-- INSERT INTO student_course (student_id, course_id)
-- VALUES (/* student_id */, /* course_id */);

-- Course Removal: Enable students to drop courses they have registered for
-- DELETE FROM student_course
-- WHERE student_id = /* student_id */ AND course_id = /* course_id */;

DELIMITER //
CREATE PROCEDURE RegisterStudentForCourse(IN studentID INT, IN courseID INT)
BEGIN
    DECLARE current_capacity INT;
    
    -- Get the current capacity of the course
    SELECT COUNT(*) INTO current_capacity
    FROM student_course
    WHERE course_id = courseID;

    -- Check if the course is already full
    IF current_capacity >= 100 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Course is already full. Cannot register.';
    ELSE
        INSERT INTO student_course (student_id, course_id)
        VALUES (studentID, courseID);
    END IF;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE RemoveCourseRegistration(IN studentID INT, IN courseID INT)
BEGIN
    DELETE FROM student_course
    WHERE student_id = studentID AND course_id = courseID;
END;
//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE ListAvailableCourses()
BEGIN
    SELECT c.course_code, c.title, c.description, c.capacity, c.schedule, 
           c.capacity - IFNULL(SUM(s.course_id = c.course_id), 0) AS available_slots
    FROM courses c
    LEFT JOIN student_course s ON c.course_id = s.course_id
    GROUP BY c.course_id;
END;
//
DELIMITER ;


DELIMITER //

CREATE PROCEDURE ListStudentsEnrolledInCourse(IN courseID INT)
BEGIN
    SELECT s.student_id, s.name
    FROM students s
    INNER JOIN student_course sc ON s.student_id = sc.student_id
    WHERE sc.course_id = courseID;
END;
//

DELIMITER ;


DELIMITER //

CREATE PROCEDURE ListCoursesEnrolledByStudent(IN studentID INT)
BEGIN
    SELECT c.course_id, c.course_code, c.title, c.description, c.schedule
    FROM courses c
    INNER JOIN student_course sc ON c.course_id = sc.course_id
    WHERE sc.student_id = studentID;
END;
//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE AddStudent(IN studentName VARCHAR(255))
BEGIN
    INSERT INTO students (name)
    VALUES (studentName);
END;
//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE AddCourse(
    IN courseCode VARCHAR(10),
    IN courseTitle VARCHAR(255),
    IN courseDescription TEXT,
    IN courseCapacity INT,
    IN courseSchedule VARCHAR(255)
)
BEGIN
    INSERT INTO courses (course_code, title, description, capacity, schedule)
    VALUES (courseCode, courseTitle, courseDescription, courseCapacity, courseSchedule);
END;
//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE GetStudentIDByName(IN studentName VARCHAR(255), OUT studentID INT)
BEGIN
    SELECT student_id INTO studentID
    FROM students
    WHERE name = studentName;
END;
//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE GetCourseIDByCode(IN courseCode VARCHAR(10), OUT courseID INT)
BEGIN
    SELECT course_id INTO courseID
    FROM courses
    WHERE course_code = courseCode;
END;
//

DELIMITER ;


DELIMITER //

CREATE PROCEDURE GetCourseNames()
BEGIN
    SELECT course_code 
    FROM courses;
END;
//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE GetStudentNames()
BEGIN
    SELECT * 
    FROM students;
END;
//

DELIMITER ;



-- Insert 10 entries into the courses table
INSERT INTO courses (course_code, title, description, capacity, schedule)
VALUES
    ('COURSE101', 'Introduction to Programming', 'Basic programming concepts', 100, 'Mon/Wed 9:00 AM - 10:30 AM'),
    ('COURSE102', 'Database Management', 'Intro to database systems', 80, 'Tue/Thu 11:00 AM - 12:30 PM'),
    ('COURSE103', 'Web Development', 'HTML, CSS, and JavaScript', 90, 'Tue/Thu 2:00 PM - 3:30 PM'),
    ('COURSE104', 'Data Structures', 'Algorithms and data structures', 70, 'Mon/Wed 2:00 PM - 3:30 PM'),
    ('COURSE105', 'Artificial Intelligence', 'AI and machine learning', 60, 'Mon/Wed 4:00 PM - 5:30 PM'),
    ('COURSE106', 'Computer Networks', 'Networking fundamentals', 85, 'Tue/Thu 4:00 PM - 5:30 PM'),
    ('COURSE107', 'Software Engineering', 'Software development methodologies', 75, 'Tue/Thu 9:00 AM - 10:30 AM'),
    ('COURSE108', 'Databases and SQL', 'Database querying', 95, 'Mon/Wed 11:00 AM - 12:30 PM'),
    ('COURSE109', 'Cybersecurity', 'Security concepts', 80, 'Tue/Thu 2:00 PM - 3:30 PM'),
    ('COURSE110', 'Mobile App Development', 'Creating mobile apps', 70, 'Mon/Wed 9:00 AM - 10:30 AM');

-- Insert 10 entries into the students table
INSERT INTO students (name)
VALUES
    ('Alice Johnson'),
    ('Bob Smith'),
    ('Charlie Brown'),
    ('David Lee'),
    ('Ella Davis'),
    ('Frank Miller'),
    ('Grace Wilson'),
    ('Hannah Clark'),
    ('Ivy White'),
    ('Jack Taylor');


-- Enroll Student 1 in Multiple Courses
CALL RegisterStudentForCourse(1, 1); -- Enroll student 1 in course 1
CALL RegisterStudentForCourse(1, 2); -- Enroll student 1 in course 2

-- Enroll Student 2 in Multiple Courses
CALL RegisterStudentForCourse(2, 3); -- Enroll student 2 in course 3
CALL RegisterStudentForCourse(2, 4); -- Enroll student 2 in course 4

-- Enroll Student 3 in One Course
CALL RegisterStudentForCourse(3, 5); -- Enroll student 3 in course 5

-- Enroll Student 4 in One Course
CALL RegisterStudentForCourse(4, 6); -- Enroll student 4 in course 6

-- Enroll Student 5 in One Course
CALL RegisterStudentForCourse(5, 7); -- Enroll student 5 in course 7


select * from courses;
select * from student_course;
select * from students;

CALL ListCoursesEnrolledByStudent(3);