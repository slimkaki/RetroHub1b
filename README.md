# RetroHub1b
Projeto 1b da disciplina Tecnologias Web da engenharia da computação do insper

## Setting up MySQL

    CREATE DATABASE RetroHub1b;

    USE DATABASE RetroHub1b;

    CREATE TABLE users(userId INT(32) AUTO_INCREMENT NOT NULL PRIMARY KEY, username VARCHAR(50) NOT NULL, password VARCHAR(255) NOT NULL, foto MEDIUMBLOB);

    CREATE TABLE subjects(id INT(32) NOT NULL AUTO_INCREMENT PRIMARY KEY, subject VARCHAR(255) NOT NULL, url VARCHAR(255) NOT NULL);