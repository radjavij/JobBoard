
DROP DATABASE IF EXISTS JobBoard;
CREATE DATABASE IF NOT EXISTS JobBoard;
use JobBoard;


CREATE TABLE Seekers(
  username VARCHAR(30) PRIMARY KEY, 
  fullName VARCHAR(60), 
  password VARCHAR(30), 
  resume MEDIUMTEXT);

CREATE TABLE Jobs(
  title VARCHAR(60), 
  companyName VARCHAR(80), 
  education VARCHAR(30), 
  department VARCHAR(30), 
  numAvailable INT, 
  wage DECIMAL(65, 2), 
  description LONGTEXT, 
  postDate DATE,
  PRIMARY KEY(title, companyName));

CREATE TABLE Companies(
  companyName VARCHAR(80) PRIMARY KEY, 
  manager VARCHAR(60), 
  pointOfContact VARCHAR(100), 
  description LONGTEXT);

CREATE TABLE JobApplications(
  jobTitle VARCHAR(60), 
  company VARCHAR(80), 
  applicantUsername VARCHAR(30), 
  appDate DATE, 
  PRIMARY KEY(jobTitle, company, applicantUsername));

INSERT INTO Seekers VALUES ('paigej', 'Joseph Paige', 'Password', 'Example Resume'), ('georgep', 'Paul George', 'Password123', 'Example Resume'), ('jamesl', 'Lenny James', 'Password!', 'Example Resume'), ('hectork', 'Kyle Hector', 'Pa$$word', 'Example Resume'), ('omara', 'Allison Omar', 'pasSwoRd', 'Example Resume');

INSERT INTO Jobs VALUES ('Software Engineer', 'T-Mobile', 'B.S. Computer Science', 'Technology', 3, 30.00, 'Example Description', '2020-09-30'), ('Sales Representative', 'Coca-Cola', 'B.S. Marketing', 'Sales', 6, 25.50, 'Example Description', '2021-11-26'), ('Customer Service Representative', 'Best-Buy', 'High School Diploma/GED', 'Service', 1, 19.80, 'Example Description', '2022-03-06'), ('Food Service Worker', 'Chipotle', 'High School Diploma/GED', 'Food', 3, 10.00, 'Example Description', '2023-06-11'), ('Software Engineer', 'Garmin', 'B.S. Computer Science', 'Technology', 8, 30.00, 'Example Description', '2023-05-21');

INSERT INTO Companies VALUES ('T-Mobile', 'John Smith', 'Jane Doe', 'Example Description'), ('Coca-Cola', 'Jacob Mason', 'Nathan Paul', 'Example Description'), ('Best-Buy', 'Michael George', 'Julia Welsh', 'Example Description'), ('Chipotle', 'Frank Trap', 'Ernie Allison', 'Example Description'), ('Garmin', 'Timothy Brian', 'Liam Dream', 'Example Description');

INSERT INTO JobApplications VALUES ('Software Engineer', 'T-Mobile', 'paigej', '2024-01-23'), ('Software Engineer', 'Garmin', 'paigej', '2024-01-23'), ('Software Engineer', 'Garmin', 'jamesl', '2024-03-29'), ('Food Service Worker', 'Chipotle', 'omara', '2023-08-14');
