CREATE TABLE oblig3.ansatt (
    ansatt_id BIGINT PRIMARY KEY,  -- Vi setter ansatt_id som en vanlig primærnøkkel (ikke auto-generert)
    brukernavn VARCHAR(50) UNIQUE NOT NULL,
    fornavn VARCHAR(100) NOT NULL,
    etternavn VARCHAR(100) NOT NULL,
    dato_for_ansettelse DATE NOT NULL,
    stilling VARCHAR(50),
    manedslonn DECIMAL(10,2)
);

//Legge til eksempel 

INSERT INTO oblig3.ansatt (ansatt_id, brukernavn, fornavn, etternavn, dato_for_ansettelse, stilling, manedslonn) 
VALUES 
(1, 'johndoe', 'John', 'Doe', '2020-01-15', 'Utvikler', 60000.00),
(2, 'janedoe', 'Jane', 'Doe', '2021-03-10', 'Leder', 80000.00),
(3, 'alicesmith', 'Alice', 'Smith', '2019-06-20', 'Designer', 55000.00);