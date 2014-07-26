DROP DATABASE IF EXISTS interview;


CREATE DATABASE interview CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER 'manager' IDENTIFIED BY 'javamanager';
GRANT ALL ON interview.* TO 'manager'@'%' IDENTIFIED BY 'javamanager';
GRANT ALL ON interview.* TO 'manager'@'localhost' IDENTIFIED BY 'javamanager';
FLUSH PRIVILEGES;

