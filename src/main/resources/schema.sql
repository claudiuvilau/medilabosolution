SET NAMES utf8mb4;

CREATE DATABASE IF NOT EXISTS medilabsolution CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE medilabsolution;

CREATE TABLE IF NOT EXISTS patients (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nom VARCHAR(20) NOT NULL,
	prenom VARCHAR(20) NOT NULL,
	date_de naissance DATE NOT NULL,
	genre CHAR(1) NOT NULL,
	adresse VARCHAR(20) NOT NULL,
	telephone VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
)
ENGINE=INNODB;

INSERT INTO patients (nom, prenom, date_de_naissance, genre, adresse, telephone) VALUES 
	("TestNone", "Test", "1966-12-31", "F", "1 Brookside St", "100-222-3333"),
	("TestBorderline", "Test", "1945-06-24", "M", "2 High St", "200-333-4444"),
	("TestInDanger", "Test", "2004-06-18", "M", "3 Club Road", "300-444-5555"),
	("TestEarlyOnset", "Test", "2002-06-28", "F", "4 Valley Dr", "400-555-6666");


