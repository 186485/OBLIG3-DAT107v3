CREATE SCHEMA IF NOT EXISTS oblig3;

-- Lager tabellen ansatt med de nødvendige feltene
CREATE TABLE IF NOT EXISTS oblig3.ansatt (
    id SERIAL PRIMARY KEY,  -- automatisk unikt id for hver ny ansatt
    navn VARCHAR(255) NOT NULL,  -- fornavn
    brukernavn VARCHAR(255) NOT NULL,  -- Brukernavn initialer
    etternavn VARCHAR(255) NOT NULL,  -- Etternavn
    ansettelse DATE,  -- Ansettelsesdato (Dagens dato)
    stilling VARCHAR(255),  -- Stilling 
    monedslonn DOUBLE PRECISION  -- Månedslønn
);

--Lage avdeling sql
CREATE TABLE oblig3.avdeling(
  id SERIAL PRIMARY KEY,
  navn VARCHAR(30)
);

CREATE TABLE oblig3.ansattdeltagelse
(
  ansattid INTEGER,
  avdelingid INTEGER,
  CONSTRAINT deltagelsePK PRIMARY KEY (ansattid,avdelingid),
  CONSTRAINT ansattFK FOREIGN KEY (ansattid) REFERENCES oblig3.ansatt(id),
  CONSTRAINT avdelingFK FOREIGN KEY (avdelingid) REFERENCES oblig3.avdeling(id)  
);

--DATA inputs
INSERT INTO oblig3.avdeling(navn)
VALUES
  ('Helse'),
  ('IT'),
  ('Vaske');


--Husk å legg til standardverdier for ansattt tabell slik at ansattdeltagelse vil fungere

INSERT INTO oblig3.ansatt (navn, brukernavn, etternavn, ansettelse, stilling, monedslonn) 
VALUES 
  ('Kari', 'kjo', 'Johansen', '2022-05-01', 'Sykepleier', 45000),  -- Helse
  ('Ola', 'ons', 'Nilsen', '2023-06-15', 'IT-konsulent', 55000),   -- IT
  ('Lise', 'lpe', 'Pettersen', '2021-08-20', 'Renholdsarbeider', 35000), -- Vaske
  ('Erik', 'era', 'Rasmussen', '2020-12-10', 'Lege', 75000), -- Helse
  ('Hanne', 'hsv', 'Svensen', '2024-01-05', 'Systemutvikler', 60000), -- IT
  ('Per', 'ppp', 'Persen', '2019-04-30', 'Rengjøringssjef', 40000); -- Vaske
  
  INSERT INTO oblig3.ansattdeltagelse(ansattid, avdelingid)
VALUES
  (1, 1),  -- Kari i Helse
  (2, 2),  -- Ola i IT
  (3, 3),  -- Lise i Vaske
  (4, 1),  -- Erik i Helse
  (5, 2),  -- Hanne i IT
  (6, 3);  -- Per i Vaske
  
  
  