CREATE SCHEMA IF NOT EXISTS oblig3;

-- 1. Opprett avdeling før man oppretter ansatt, unngå kolisjon
CREATE TABLE IF NOT EXISTS oblig3.avdeling (
    id SERIAL PRIMARY KEY,
    navn VARCHAR(30) NOT NULL,
    sjef INTEGER NOT NULL UNIQUE,
    FOREIGN KEY (sjef) REFERENCES oblig3.ansatt(id)
);

-- 2. Opprett ansatt
CREATE TABLE IF NOT EXISTS oblig3.ansatt (
    id SERIAL PRIMARY KEY,  
    navn VARCHAR(255) NOT NULL,  
    brukernavn VARCHAR(255) NOT NULL,  
    etternavn VARCHAR(255) NOT NULL,  
    ansettelse DATE,  
    stilling VARCHAR(255),  
    monedslonn DOUBLE PRECISION,  
    avdeling_id INTEGER NOT NULL,  
    FOREIGN KEY (avdeling_id) REFERENCES oblig3.avdeling(id) ON DELETE CASCADE
);

-- 3. Sett inn data i avdeling
INSERT INTO oblig3.avdeling(navn) 
VALUES 
  ('Helse'),
  ('IT'),
  ('Vaske');

-- 4. Sett inn ansatte med riktig avdeling_id/riktig avdeling
INSERT INTO oblig3.ansatt (navn, brukernavn, etternavn, ansettelse, stilling, monedslonn, avdeling_id) 
VALUES 
  ('Kari', 'kjo', 'Johansen', '2022-05-01', 'Sykepleier', 45000, 1),  
  ('Ola', 'ons', 'Nilsen', '2023-06-15', 'IT-konsulent', 55000, 2),   
  ('Lise', 'lpe', 'Pettersen', '2021-08-20', 'Renholdsarbeider', 35000, 3), 
  ('Erik', 'era', 'Rasmussen', '2020-12-10', 'Lege', 75000, 1), 
  ('Hanne', 'hsv', 'Olsen', '2024-01-05', 'Systemutvikler', 60000, 2), 
  ('Per', 'po', 'Olsen', '2019-04-30', 'Rengjøringssjef', 40000, 3);
  
--Legge inn sjef etterpå for å unngå kræsj da sjef må være en ansatt(Høna og egget slår ikke oss)

ALTER TABLE oblig3.avdeling 
ADD COLUMN sjef INTEGER UNIQUE,
ADD CONSTRAINT fk_sjef FOREIGN KEY (sjef) REFERENCES oblig3.ansatt(id);

UPDATE oblig3.avdeling SET sjef = 4 WHERE id = 1; -- Erik som sjef for Helse
UPDATE oblig3.avdeling SET sjef = 5 WHERE id = 2; -- Hanne som sjef for IT
UPDATE oblig3.avdeling SET sjef = 6 WHERE id = 3; -- Per som sjef for Vaske


  
