-- CREATE USER 'hector'@'localhost' IDENTIFIED BY 'asdf';

CREATE DATABASE `oauth2-provider` CHARACTER SET utf8 COLLATE utf8_bin;
GRANT ALL ON `oauth2-provider`.* TO 'hector'@'localhost';
FLUSH PRIVILEGES;