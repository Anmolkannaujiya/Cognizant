-- ============================================================
-- payroll.sql - updated to match the schema diagram
-- Hands-on 3, 4, 5, 6 (2. spring-data-jpa-handson)
-- Run in MySQL before starting the app:
--   mysql> source <path>\sql\payroll.sql
-- ============================================================

USE ormlearn;

DROP TABLE IF EXISTS employee_skill;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS department;

CREATE TABLE department (
    dp_id   INT          NOT NULL AUTO_INCREMENT,
    dp_name VARCHAR(45),
    PRIMARY KEY (dp_id)
);

-- em_salary is DECIMAL(10,2) as shown in the schema diagram
CREATE TABLE employee (
    em_id            INT           NOT NULL AUTO_INCREMENT,
    em_name          VARCHAR(45),
    em_salary        DECIMAL(10,2),
    em_permanent     BOOLEAN,
    em_date_of_birth DATE,
    em_dp_id         INT,
    PRIMARY KEY (em_id),
    FOREIGN KEY (em_dp_id) REFERENCES department(dp_id)
);

CREATE TABLE skill (
    sk_id   INT         NOT NULL AUTO_INCREMENT,
    sk_name VARCHAR(45),
    PRIMARY KEY (sk_id)
);

-- employee_skill has its own es_id PK as shown in the schema diagram
-- es_em_id and es_sk_id are the FK columns JPA uses via @JoinTable
CREATE TABLE employee_skill (
    es_id    INT NOT NULL AUTO_INCREMENT,
    es_em_id INT NOT NULL,
    es_sk_id INT NOT NULL,
    PRIMARY KEY (es_id),
    FOREIGN KEY (es_em_id) REFERENCES employee(em_id),
    FOREIGN KEY (es_sk_id) REFERENCES skill(sk_id)
);

-- ---- sample data ----

INSERT INTO department (dp_name) VALUES ('Engineering');
INSERT INTO department (dp_name) VALUES ('Marketing');
INSERT INTO department (dp_name) VALUES ('Finance');
INSERT INTO department (dp_name) VALUES ('Human Resources');

INSERT INTO skill (sk_name) VALUES ('Java');
INSERT INTO skill (sk_name) VALUES ('Python');
INSERT INTO skill (sk_name) VALUES ('SQL');
INSERT INTO skill (sk_name) VALUES ('JavaScript');
INSERT INTO skill (sk_name) VALUES ('Spring Boot');
INSERT INTO skill (sk_name) VALUES ('React');

INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
    VALUES ('Alice Johnson', 85000.00, true, '1990-03-15', 1);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
    VALUES ('Bob Smith', 72000.00, true, '1988-07-22', 1);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
    VALUES ('Carol White', 65000.00, false, '1995-11-05', 2);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
    VALUES ('David Brown', 90000.00, true, '1985-01-30', 3);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
    VALUES ('Eva Green', 68000.00, true, '1993-06-18', 2);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
    VALUES ('Frank Lee', 78000.00, false, '1991-09-11', 1);

INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 5);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 3);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 2);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (3, 4);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (3, 6);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (4, 3);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (5, 4);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (6, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (6, 5);
