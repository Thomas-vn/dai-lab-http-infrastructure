-- SQL Script with Insert, Update, and Select Queries for Each Table

-- Table: lieu
-- Insert query for a new lieu
INSERT INTO lieu (rue, numero, npa, ville) 
VALUES ('rue_example', 123, 1000, 'ville_example');

-- Update query for lieu
UPDATE lieu 
SET rue = 'nouvelle_rue', numero = 456, npa = 2000, ville = 'nouvelle_ville' 
WHERE id = 1;

-- Select query for a lieu
SELECT * FROM lieu WHERE id = 1;

-- Table: personne
-- Insert query for insert a personne
INSERT INTO personne (nom, prenom, datenaissance, sexe, lieuid, noavs)
VALUES ('Dupont', 'Jean', '1990-01-01', 'M', 1, 75646000000191);

-- Update query for personne
UPDATE personne 
SET nom = 'Durand', prenom = 'Marie', sexe = 'F' 
WHERE noavs = 123456789;

-- Select query for personne
SELECT * FROM personne WHERE noavs = 123456789;

-- Table: voiture
-- Insert query for voiture
INSERT INTO voiture (marque, typecarrosserie, couleur, datefabrication, nombreplaces, prix, nombreportes, puissance, descriptionoptions, dateexpertise, typecombustible, nombrekm, typeboitevitesse, consommation, neuf, garantie, datefingarantie, envente, proprietaire, numerochassis)
VALUES ('Toyota', 'SUV', 'Noir', '2020-01-01', 5, 30000.00, 4, 150, 'Option 1, Option 2', '2023-01-01', 'Essence', 20000, 'Automatique', 8.5, true, true, '2025-01-01', true, 756000000010, 'VF1AAAAA11111112');

-- Update query for voiture
UPDATE voiture 
SET marque = 'Honda', prix = 28000.00, envente = false 
WHERE numerochassis = 'CHASSIS123456';

-- Select query for voiture
SELECT * FROM voiture WHERE numerochassis = 'CHASSIS123456';

-- Table: personnel
-- Insert query for personnel
INSERT INTO personnel (salaire, poste, datedebutcontrat, datefincontrat, supervisor, noavs) 
VALUES (5000.00, 'Manager', '2023-01-01', '2025-01-01', 756000000002, 75646000000191);

-- Update query for personnel
UPDATE personnel 
SET salaire = 5500.00, poste = 'Senior Manager' 
WHERE noavs = 987654321;

-- Select query for personnel
SELECT * FROM personnel WHERE noavs = 987654321;

-- Table: type_service
-- Insert query for type_service
INSERT INTO type_service (prix, description) 
VALUES (200.00, 'Changement d’huile');

-- Update query for type_service
UPDATE type_service 
SET prix = 220.00, description = 'Révision complète' 
WHERE typeserviceid = 1;

-- Select query for type_service
SELECT * FROM type_service WHERE typeserviceid = 1;

-- Table: reparation
-- Insert query for reparation
INSERT INTO reparation (prix, datedebut, datefin, numerochassis, lieuid)
VALUES (1500.00, '2023-05-01', '2023-05-10', 'VF1AAAAA1111111', 1);

-- Update query for reparation
UPDATE reparation 
SET prix = 1600.00, datefin = '2023-05-15' 
WHERE reparationid = 1;

-- Select query for reparation
SELECT * FROM reparation WHERE reparationid = 1;

-- Table: service
-- Insert query for service
INSERT INTO service (commentaire, heuresdetravail, reparationid, noavsmecanicien, typeserviceid) 
VALUES ('Nettoyage inclus', 4, 1, 756000000003, 1);

-- Update query for service
UPDATE service 
SET commentaire = 'Nettoyage approfondi', heuresdetravail = 5 
WHERE serviceid = 1;

-- Select query for service
SELECT * FROM service WHERE serviceid = 1;

-- Table: vendeur
-- Insert query for vendeur
INSERT INTO vendeur (noavs) 
VALUES (75646000000191);

-- Update query for vendeur
UPDATE vendeur 
SET noavs = 987654321 
WHERE noavs = 123456789;

-- Select query for vendeur
SELECT * FROM vendeur WHERE noavs = 123456789;

-- Table: mecanicien
-- Insert query for mecanicien
INSERT INTO personne (nom, prenom, datenaissance, sexe, lieuid, noavs)
VALUES ('Dupont', 'Jean', '1990-01-01', 'M', 1, 75646000000192);
INSERT INTO personnel (salaire, poste, datedebutcontrat, datefincontrat, supervisor, noavs)
VALUES (5000.00, 'Manager', '2023-01-01', '2025-01-01', 756000000002, 75646000000192);
INSERT INTO mecanicien (noavs) 
VALUES (75646000000192);

-- Update query for mecanicien
UPDATE mecanicien 
SET noavs = 987654321 
WHERE noavs = 123456789;

-- Select query for mecanicien
SELECT * FROM mecanicien WHERE noavs = 123456789;

-- Table: client
-- Insert query for client
INSERT INTO client (dateajout, noavs) 
VALUES ('2023-06-01', 75646000000192);

-- Update query for client
UPDATE client 
SET dateajout = '2023-06-15' 
WHERE noavs = 123456789;

-- Select query for client
SELECT * FROM client WHERE noavs = 123456789;

-- Table: vente
-- Insert query for vente
INSERT INTO vente (datevente, rabais, prixreel, noavsclient, numerochassis, noavsvendeur) 
VALUES ('2023-07-01', 5.00, 29500.00, 75646000000192, 'VF1AAAAA11111112', 75646000000191);

-- Update query for vente
UPDATE vente 
SET rabais = 10.00
WHERE venteid = 1;

-- Select query for vente
SELECT * FROM vente WHERE venteid = 1;

-- Table : reparations
-- Add a new reparation
INSERT INTO reparation (prix, datedebut, datefin, numerochassis, lieuid)
VALUES (300, '2024-05-05', NULL, 'VF1AAAAA11111112', 3);

-- il manque le fait d'associer la réparation au mecanicien 

-- Edit the informations of a reparation
UPDATE reparation
SET datefin = '2024-12-08', prix = 350
WHERE reparationid = 1;

-- Get the reparation informations (for instance where reparationID = 1)
SELECT r.reparationid, r.prix, r.datedebut, r.datefin, s.commentaire, m.noavs, p.nom, p.prenom, ts.description
FROM reparation AS r
JOIN service AS s ON r.reparationid = s.reparationid
JOIN mecanicien AS m ON s.noavsmecanicien = m.noavs
JOIN personne AS p ON m.noavs = p.noavs
JOIN type_service ts ON s.typeserviceid = ts.typeserviceid
WHERE r.reparationid = 1;


-- pour l'ajout d'une reparartion, est ce correcte de faire ça avec une fonction ?????
CREATE OR REPLACE FUNCTION ajouter_reparation(
    p_prix NUMERIC(10, 2),
    p_datedebut DATE,
    p_datefin DATE,
    p_numerochassis VARCHAR(17),
    p_lieuID INT,
    p_commentaire TEXT,
    p_heuresdetravail INT,
    p_noavsmecanicien BIGINT,
    p_typeserviceid INT
) RETURNS TEXT AS $$
DECLARE
    v_reparationID INT; -- Variable pour stocker l'ID de la réparation
BEGIN
    -- Étape 1 : Ajouter la réparation
    INSERT INTO reparation (prix, datedebut, datefin, numerochassis, lieuID)
    VALUES (p_prix, p_datedebut, p_datefin, p_numerochassis, p_lieuID)
    RETURNING reparationid INTO v_reparationID;

    -- Étape 2 : Ajouter l'entrée dans la table service
    INSERT INTO service (commentaire, heuresdetravail, reparationid, noavsmecanicien, typeserviceid)
    VALUES (p_commentaire, p_heuresdetravail, v_reparationID, p_noavsmecanicien, p_typeserviceid);

    -- Retourner un message de succès
    RETURN FORMAT('Réparation ajoutée avec succès avec l''ID : %s', v_reparationID);
END;
$$ LANGUAGE plpgsql;

-- Les 5 meilleures ventes par vendeur (par montant total des ventes)
SELECT
    p.Nom,
    p.Prenom,
    SUM(vt.PrixReel) AS MontantTotalVentes,
    COUNT(vt.VenteID) AS NombreVentes
FROM Vente vt
JOIN Personnel pe ON vt.NoAVSVendeur = pe.NoAVS
JOIN Personne p ON pe.NoAVS = p.NoAVS
GROUP BY p.Nom, p.Prenom
ORDER BY MontantTotalVentes DESC
LIMIT 5;

-- Garages (selon les lieux les plus efficaces (temps moyen de réparation par lieu)
SELECT
    l.Ville,
    l.Rue,
    AVG(r.DateFin - r.DateDebut) AS TempsMoyenReparation
FROM Reparation r
JOIN Lieu l ON r.LieuID = l.ID
WHERE r.DateFin IS NOT NULL
GROUP BY l.Ville, l.Rue
ORDER BY TempsMoyenReparation ASC;


--tester du macanicien qui va devenir clien.
-- Insertion d'une personne qui sera un vendeur
INSERT INTO Personne (NoAVS, Nom, Prenom, DateNaissance, Sexe, LieuID)
VALUES (2001, 'Leclerc', 'Vincent', '1985-02-15', 'M', 1);

-- Insertion d'une personne qui sera un mécanicien
INSERT INTO Personne (NoAVS, Nom, Prenom, DateNaissance, Sexe, LieuID)
VALUES (2002, 'Moreau', 'Julien', '1990-07-10', 'M', 1);

-- Insertion du vendeur dans la table Personnel
INSERT INTO Personnel (NoAVS, Salaire, Poste, DateDebutContrat)
VALUES (2001, 4500, 'Vendeur', '2020-01-01');

-- Insertion du mécanicien dans la table Personnel
INSERT INTO Personnel (NoAVS, Salaire, Poste, DateDebutContrat)
VALUES (2002, 4000, 'Mécanicien', '2021-03-01');

-- Insertion dans la table Vendeur
INSERT INTO Vendeur (NoAVS) VALUES (2001);

-- Insertion dans la table Mecanicien
INSERT INTO Mecanicien (NoAVS) VALUES (2002);
-- Insertion d'une voiture à vendre
INSERT INTO Voiture (NumeroChassis, Marque, TypeCarrosserie, Couleur, DateFabrication, NombrePlaces, Prix, NombrePortes, Puissance, DateExpertise, TypeCombustible, NombreKm, TypeBoiteVitesse, Consommation, Neuf, Garantie, DateFinGarantie, EnVente, Proprietaire)
VALUES ('XYZ987LMN654ABC32', 'Renault', 'Berline', 'Bleu', '2023-05-01', 5, 25000, 4, 130, '2023-10-01', 'Essence', 5000, 'Manuelle', 7.5, TRUE, TRUE, '2026-05-01', TRUE, 2001);
-- Insertion d'une vente où le mécanicien (NoAVS 2002) devient client
INSERT INTO Vente (DateVente, Rabais, PrixReel, NoAVSClient, NumeroChassis, NoAVSVendeur)
VALUES ('2025-05-15', 10, 22500, 2002, 'XYZ987LMN654ABC32', 2001);

-- Vérifier que le mécanicien a bien été ajouté comme client
SELECT * FROM Client WHERE NoAVS = 2002;







