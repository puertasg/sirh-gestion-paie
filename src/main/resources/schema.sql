DROP TABLE IF EXISTS grade;
DROP TABLE IF EXISTS Cotisation;
CREATE TABLE grade (
  id INT(11) NOT NULL,
  CODE VARCHAR(255) DEFAULT NULL,
  NB_HEURES_BASE VARCHAR(255) DEFAULT NULL,
  TAUX_BASE VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
);