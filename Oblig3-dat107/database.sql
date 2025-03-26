CREATE SCHEMA IF NOT EXISTS oblig3;

-- Lager tabellen ansatt med de nødvendige feltene
CREATE TABLE IF NOT EXISTS oblig3.ansatt (
    id SERIAL PRIMARY KEY,  -- Genererer automatisk et unikt id for hver ansatt
    navn VARCHAR(255) NOT NULL,  -- Navn på den ansatte
    brukernavn VARCHAR(255) NOT NULL,  -- Brukernavn for den ansatte
    etternavn VARCHAR(255) NOT NULL,  -- Etternavn på den ansatte
    ansettelse DATE,  -- Ansettelsesdato
    stilling VARCHAR(255),  -- Stilling for den ansatte
    monedslonn DOUBLE PRECISION  -- Månedslønn for den ansatte
);