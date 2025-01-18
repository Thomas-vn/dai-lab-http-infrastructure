drop schema garage cascade; -- aide au travail

commit; -- pour s'assurer que les changements sont bien pris en compte

set search_path = garage;

--/// Partie Humain

-- Creation du schema
create schema garage;

-- Table Lieu
CREATE TABLE Lieu (
    ID SERIAL PRIMARY KEY,
    Rue VARCHAR(100) NOT NULL ,
    Numero INT NOT NULL ,
    NPA INT NOT NULL ,
    Ville VARCHAR(100) NOT NULL
);

-- Table Personne avec le lien vers le Lieu
CREATE TABLE Personne (
    NoAVS BIGINT PRIMARY KEY,
    Nom VARCHAR(50) NOT NULL,
    Prenom VARCHAR(50) NOT NULL,
    DateNaissance DATE NOT NULL,
    Sexe CHAR(1) CHECK (Sexe IN ('M', 'F')),
    LieuID INT REFERENCES Lieu(ID)  -- Foreign key reference to Lieu
);

CREATE TABLE Personnel (
    NoAVS BIGINT PRIMARY KEY REFERENCES Personne(NoAVS),
    Salaire NUMERIC(10, 2) NOT NULL,
    Poste VARCHAR(50) NOT NULL,
    DateDebutContrat DATE NOT NULL,
    DateFinContrat DATE,
    Supervisor BIGINT REFERENCES Personnel(NoAVS)
);

-- Table Client, herite de Personne
CREATE TABLE Client (
    NoAVS BIGINT PRIMARY KEY REFERENCES Personne(NoAVS),
    DateAjout DATE NOT NULL
);

-- Table Mecanicien, herite de personnel (disjoint et total)
CREATE TABLE Mecanicien (
    NoAVS BIGINT PRIMARY KEY REFERENCES Personnel(NoAVS)
);

-- Table for Vendeur, herite de personnel (disjoint et total)
CREATE TABLE Vendeur (
    NoAVS BIGINT PRIMARY KEY REFERENCES Personnel(NoAVS)
);

--/// Partie Auto, Service et Autre

CREATE TYPE TypeCarrosserie AS ENUM ('Break', 'Cabriolet','Pick-up', 'SUV', 'Coupe', 'Berline', 'Compacte');
CREATE TYPE TypeCouleurs AS ENUM ('Rouge', 'Bleu','Vert','Jaune','Noir','Blanc','Gris','Marron','Orange','Violet');
CREATE TYPE TypeCombustible AS ENUM ('Essence', 'Diesel', 'Électrique', 'Hybride', 'GPL', 'E85', 'Hydrogène');
CREATE TYPE TypeBoiteVitesse AS ENUM ('Manuelle', 'Automatique');

-- Table Voiture
CREATE TABLE Voiture (
    NumeroChassis VARCHAR(17) PRIMARY KEY, --17 chiffre/lettre pour un chassis
    Marque VARCHAR(50) NOT NULL ,
    TypeCarrosserie TypeCarrosserie NOT NULL ,
    Couleur TypeCouleurs NOT NULL,
    DateFabrication DATE NOT NULL ,
    NombrePlaces INT NOT NULL,
    Prix NUMERIC(10, 2), --Peut etre null, car la voiture peut ne pas etre ne vente
    NombrePortes INT NOT NULL ,
    Puissance INT NOT NULL ,
    DescriptionOptions TEXT,
    DateExpertise DATE NOT NULL ,
    TypeCombustible TypeCombustible NOT NULL ,
    NombreKm INT NOT NULL ,
    TypeBoiteVitesse TypeBoiteVitesse NOT NULL ,
    Consommation NUMERIC(5, 2), -- pas tres important
    Neuf BOOLEAN NOT NULL,
    Garantie BOOLEAN NOT NULL ,
    DateFinGarantie DATE,
    EnVente BOOLEAN NOT NULL ,
    Proprietaire BIGINT REFERENCES Personne(NoAVS) -- le lien vers le Proprietaire de la voiture
);

-- Table Vente
CREATE TABLE Vente (
    VenteID SERIAL PRIMARY KEY,
    DateVente DATE NOT NULL,
    Rabais NUMERIC(5, 2), -- Null veut dire pas de rabais
    PrixReel NUMERIC(10, 2) NOT NULL,
    NoAVSClient BIGINT NOT NULL REFERENCES Client(NoAVS),
    NumeroChassis VARCHAR(17) NOT NULL REFERENCES Voiture(NumeroChassis),
    NoAVSVendeur BIGINT NOT NULL REFERENCES Vendeur(NoAVS)
);

-- Table Type_Service
CREATE TABLE Type_Service (
    TypeServiceID SERIAL PRIMARY KEY,
    Prix NUMERIC(10, 2) NOT NULL,
    Description TEXT NOT NULL
);

-- Table Reparation
CREATE TABLE Reparation (
    ReparationID SERIAL PRIMARY KEY,
    Prix NUMERIC(10, 2) NOT NULL,
    DateDebut DATE NOT NULL,
    DateFin DATE,
    NumeroChassis VARCHAR(17) NOT NULL REFERENCES Voiture(NumeroChassis),
    LieuID INT NOT NULL REFERENCES Lieu(ID)
);

-- Table Service
CREATE TABLE Service (
    ServiceID SERIAL PRIMARY KEY,
    Commentaire TEXT,
    HeuresDeTravail INT NOT NULL ,
    ReparationID INT REFERENCES Reparation(ReparationID) NOT NULL , -- TODO not null
    NoAVSMecanicien BIGINT REFERENCES Mecanicien(NoAVS) NOT NULL , -- TODO not null
    TypeServiceID INT REFERENCES Type_Service(TypeServiceID) NOT NULL -- TODO not null
);



--/// Contraintes d'integritée

--Pas de surpervision de soi-meme
ALTER TABLE Personnel
ADD CONSTRAINT check_supervisor_not_self CHECK ( NoAVS <> Supervisor);

--Contract fin IS null or DateDebut<=DateFin
ALTER TABLE Personnel
ADD CONSTRAINT check_dates_contrat_valides CHECK (DateFinContrat IS NULL OR DateDebutContrat <= DateFinContrat);


-- Les donnes de voitures sont logiques
ALTER TABLE Voiture
ADD CONSTRAINT check_prix_non_negative CHECK (Prix >= 0),
ADD CONSTRAINT check_nombre_km_non_negative CHECK (NombreKm >= 0),
ADD CONSTRAINT check_puissance_non_negative CHECK (Puissance >= 0);

ALTER TABLE Vente
ADD CONSTRAINT check_rabais_valide CHECK (Rabais >= 0 AND Rabais <= 100);

-- Les prix ne doivent pas etre negatif
ALTER TABLE Reparation
ADD CONSTRAINT check_prix_non_negative CHECK (Prix >= 0),
ADD CONSTRAINT check_dates_reparation CHECK (DateDebut <= DateFin);

ALTER TABLE Type_Service
ADD CONSTRAINT check_prix_non_negative CHECK (Prix >= 0);

--Creation d'index pour la performance
CREATE INDEX idx_personne_lieu ON Personne(LieuID);
CREATE INDEX idx_voiture_proprietaire ON Voiture(Proprietaire);
CREATE INDEX idx_reparation_lieu ON Reparation(LieuID);
CREATE INDEX idx_service_reparation ON Service(ReparationID);
CREATE INDEX idx_vente_client ON Vente(NoAVSClient);