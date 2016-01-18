# Users schema
 
# --- !Ups
 
CREATE TABLE `user` (
	`userID` VARCHAR(100) NOT NULL PRIMARY KEY,
	`firstName` VARCHAR(100),
	`lastName` VARCHAR(100),
	`fullName` VARCHAR(100),
	`email` VARCHAR(100),
	`avatarURL` VARCHAR(100)
	);
 
# --- !Downs
 
DROP TABLE User;