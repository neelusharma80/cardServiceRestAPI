DROP TABLE IF EXISTS Creditcard;

CREATE TABLE Creditcard (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  cardnumber VARCHAR(250) NOT NULL,
  cardname VARCHAR(250) NOT NULL,
  cardlimit Decimal(6,2)
);