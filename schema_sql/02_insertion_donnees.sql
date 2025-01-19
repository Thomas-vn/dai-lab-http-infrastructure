set search_path to garage;

--// Fichie d'insertion de donnlées

-- Insertion des données dans la table Lieu
INSERT INTO Lieu (Rue, Numero, NPA, Ville) VALUES
('Rue de Lausanne', 1, 1800, 'Vevey'),
('Rue de Genève', 2, 1003, 'Lausanne'),
('Rue de Berne', 3, 1201, 'Genève'),
('Rue de Zurich', 4, 8001, 'Zurich'),
('Rue de Bâle', 5, 4001, 'Bâle'),
('Rue de Lucerne', 6, 6002, 'Lucerne'),
('Rue de Lugano', 7, 6900, 'Lugano'),
('Rue de Neuchâtel', 8, 2000, 'Neuchâtel'),
('Rue de Fribourg', 9, 1700, 'Fribourg'),
('Rue de Sion', 10, 1950, 'Sion'),
('Rue de La Chaux-de-Fonds', 11, 2300, 'La Chaux-de-Fonds'),
('Rue de Montreux', 12, 1820, 'Montreux'),
('Rue de Yverdon-les-Bains', 13, 1400, 'Yverdon-les-Bains'),
('Route de Cheseaux', 1, 1400, 'Yverdon-les-Bains');



-- Insertion des données dans la table Personne
INSERT INTO Personne (NoAVS, Nom, Prenom, DateNaissance, Sexe, LieuID) VALUES
(756000000001, 'Martin', 'Pierre', '1980-05-10', 'M', 1),
(756000000002, 'Bernard', 'Marie', '1975-08-22', 'F', 14),
(756000000003, 'Dubois', 'Jacques', '1990-12-15', 'M', 13),
(756000000004, 'Thomas', 'Anne', '1985-07-04', 'F', 4),
(756000000005, 'Robert', 'Jean', '1995-09-30', 'M', 5),
(756000000006, 'Petit', 'Sophie', '1992-11-18', 'F', 6),
(756000000007, 'Durand', 'Philippe', '1988-03-27', 'M', 7),
(756000000008, 'Leroy', 'Camille', '1991-06-02', 'F', 1),
(756000000009, 'Moreau', 'Julien', '1978-04-14', 'M', 2),
(756000000010, 'Simon', 'Nathalie', '1982-10-08', 'F', 3),
(756000000011, 'Girard', 'Luc', '1983-02-12', 'M', 8),
(756000000012, 'Francois', 'Isabelle', '1990-05-25', 'F', 9),
(756000000013, 'Muller', 'Alain', '1979-07-30', 'M', 10),
(756000000014, 'Lambert', 'Catherine', '1987-11-05', 'F', 11),
(756000000015, 'Fontaine', 'Emmanuel', '1993-03-18', 'M', 12),
(756000000016, 'Roux', 'Laura', '1995-06-27', 'F', 13),
(756000000017, 'Blanc', 'Denis', '1986-09-14', 'M', 14),
(756000000018, 'Legrand', 'Chloé', '1991-12-22', 'F', 8),
(756000000019, 'Garcia', 'Sébastien', '1984-08-09', 'M', 9),
(756000000020, 'Masson', 'Élise', '1989-01-17', 'F', 10);

-- Insertion des données dans la table Personnel
INSERT INTO Personnel (NoAVS, Salaire, Poste, DateDebutContrat, DateFinContrat, Supervisor) VALUES
(756000000001, 8000.00, 'Directeur', '2010-01-01', NULL, NULL),
(756000000002, 6000.00, 'Chef Mécanicien', '2012-03-15', NULL, 756000000001),
(756000000003, 4000.00, 'Mécanicien', '2015-07-20', NULL, 756000000002),
(756000000004, 4500.00, 'Vendeur', '2017-09-01', NULL, 756000000001),
(756000000005, 4500.00, 'Vendeur', '2018-11-10', NULL, 756000000001),
(756000000011, 5000.00, 'Mécanicien', '2016-04-01', NULL, 756000000002),
(756000000012, 5500.00, 'Vendeur', '2017-07-15', NULL, 756000000001),
(756000000013, 4200.00, 'Mécanicien', '2018-10-20', NULL, 756000000002),
(756000000014, 4700.00, 'Vendeur', '2019-01-05', NULL, 756000000001),
(756000000015, 6000.00, 'Chef Mécanicien', '2020-03-18', NULL, 756000000001);

-- Insertion des données dans la table Client
INSERT INTO Client (NoAVS, DateAjout) VALUES
(756000000006, '2019-02-20'),
(756000000007, '2020-05-30'),
(756000000008, '2021-08-15'),
(756000000009, '2019-12-01'),
(756000000010, '2020-04-25'),
(756000000016, '2020-06-20'),
(756000000017, '2021-09-30'),
(756000000018, '2021-12-15'),
(756000000019, '2022-02-01'),
(756000000020, '2022-04-25');

-- Insertion des données dans la table Mecanicien
INSERT INTO Mecanicien (NoAVS) VALUES
(756000000002),
(756000000003),
(756000000011),
(756000000013),
(756000000015);

-- Insertion des données dans la table Vendeur
INSERT INTO Vendeur (NoAVS) VALUES
(756000000004),
(756000000005),
(756000000012),
(756000000014);

-- Insertion des données dans la table Voiture
INSERT INTO Voiture (NumeroChassis, Marque, TypeCarrosserie, Couleur, DateFabrication, NombrePlaces, Prix, NombrePortes, Puissance, DescriptionOptions, DateExpertise, TypeCombustible, NombreKm, TypeBoiteVitesse, Consommation, Neuf, Garantie, DateFinGarantie, EnVente, proprietaire) VALUES
('VF1AAAAA1111111', 'Renault', 'Berline', 'Bleu', '2018-05-10', 5, 15000.00, 4, 110, 'GPS, Climatisation', '2020-05-10', 'Essence', 25000, 'Manuelle', 5.5, FALSE, FALSE, NULL, TRUE, 756000000006),
('VF1BBBBB2222222', 'Peugeot', 'SUV', 'Noir', '2019-06-15', 5, 20000.00, 5, 130, 'Toit ouvrant', '2021-06-15', 'Diesel', 15000, 'Automatique', 6.0, FALSE, FALSE, NULL, TRUE, 756000000007),
('VF1CCCCC3333333', 'Citroën', 'Cabriolet', 'Rouge', '2020-07-20', 4, 25000.00, 2, 150, 'Intérieur cuir', '2021-07-20', 'Essence', 5000, 'Manuelle', 7.0, TRUE, TRUE, '2023-07-20', TRUE, 756000000008),
('VF1DDDDD4444444', 'Tesla', 'Berline', 'Blanc', '2021-08-25', 5, 50000.00, 4, 350, 'Autopilote', '2022-08-25', 'Électrique', 10000, 'Automatique', 0.0, TRUE, TRUE, '2024-08-25', TRUE, 756000000009),
('VF1EEEEE5555555', 'BMW', 'SUV', 'Gris', '2017-09-30', 5, 30000.00, 5, 200, 'GPS, Intérieur cuir', '2019-09-30', 'Diesel', 40000, 'Automatique', 6.5, FALSE, FALSE, NULL, TRUE, 756000000010),
('VF1FFFFF6666666', 'Audi', 'Berline', 'Noir', '2021-01-10', 5, 40000.00, 4, 250, 'GPS, Climatisation, Intérieur cuir', '2021-01-10', 'Essence', 0, 'Automatique', 5.0, TRUE, TRUE, '2024-01-10', TRUE, NULL),
('VF1GGGGG7777777', 'Mercedes', 'Berline', 'Gris', '2021-02-20', 5, 45000.00, 4, 280, 'GPS, Climatisation, Intérieur cuir, Toit ouvrant', '2021-02-20', 'Essence', 0, 'Automatique', 5.0, TRUE, TRUE, '2024-02-20', TRUE, NULL),
('VF1HHHHH8888888', 'Volkswagen', 'Compacte', 'Blanc', '2018-03-15', 5, 18000.00, 5, 120, 'Climatisation, GPS', '2020-03-15', 'Essence', 30000, 'Manuelle', 5.8, FALSE, FALSE, NULL, TRUE, 756000000016),
('VF1IIIII9999999', 'Ford', 'Break', 'Bleu', '2019-04-20', 5, 22000.00, 5, 140, 'Barres de toit', '2021-04-20', 'Diesel', 20000, 'Manuelle', 6.2, FALSE, FALSE, NULL, TRUE, 756000000017),
('VF1JJJJJ1010101', 'Opel', 'Berline', 'Gris', '2020-05-25', 5, 24000.00, 4, 160, 'Sièges chauffants', '2021-05-25', 'Essence', 10000, 'Automatique', 6.0, TRUE, TRUE, '2023-05-25', TRUE, 756000000018),
('VF1KKKKK1111111', 'Nissan', 'SUV', 'Rouge', '2021-06-30', 5, 28000.00, 5, 180, 'Caméra de recul', '2022-06-30', 'Hybride', 5000, 'Automatique', 4.5, TRUE, TRUE, '2024-06-30', TRUE, 756000000019),
('VF1LLLLL1212121', 'Honda', 'Coupe', 'Noir', '2017-07-05', 4, 26000.00, 2, 200, 'Toit ouvrant', '2019-07-05', 'Essence', 35000, 'Manuelle', 7.0, FALSE, FALSE, NULL, TRUE, 756000000020),
('VF1MMMMM1313131', 'Toyota', 'Berline', 'Blanc', '2022-01-15', 5, 30000.00, 4, 150, 'GPS, Climatisation', '2022-01-15', 'Hybride', 0, 'Automatique', 3.5, TRUE, TRUE, '2025-01-15', TRUE, NULL),
('VF1NNNNN1414141', 'Kia', 'SUV', 'Vert', '2022-02-20', 5, 32000.00, 5, 170, 'Climatisation, Intérieur cuir', '2022-02-20', 'Électrique', 0, 'Automatique', 0.0, TRUE, TRUE, '2025-02-20', TRUE, NULL);
-- Insertion des données dans la table Type_Service
INSERT INTO Type_Service (Prix, Description) VALUES
(100.00, 'Vidange huile moteur'),
(400.00, 'Remplacement des pneus'),
(300.00, 'Entretien des freins'),
(1000.00, 'Réparation moteur'),
(150.00, 'Diagnostic électrique'),
(200.00, 'Changement de courroie de distribution'),
(250.00, 'Révision générale'),
(800.00, 'Remplacement de embrayage'),
(1200.00, 'Réparation de la boîte de vitesses'),
(90.00, 'Contrôle technique');

-- Insertion des données dans la table Reparation
INSERT INTO Reparation (Prix, DateDebut, DateFin, NumeroChassis, LieuID) VALUES
(500.00, '2021-09-01', '2021-09-05', 'VF1BBBBB2222222', 14),
(1500.00, '2021-10-10', NULL, 'VF1AAAAA1111111', 14),
(700.00, '2021-11-01', '2021-11-07', 'VF1IIIII9999999', 14),
(1300.00, '2022-01-10', NULL, 'VF1HHHHH8888888', 14);

-- Insertion des données dans la table Service
INSERT INTO Service (Commentaire, HeuresDeTravail, ReparationID, NoAVSMecanicien, TypeServiceID) VALUES
('Vidange complète', 2, 1, 756000000003, 1),
('Remplacement des pneus hiver', 3, 1, 756000000003, 2),
('Réparation moteur suite à une panne', 10, 2, 756000000002, 4),
('Changement de courroie suite à usure', 4, 3, 756000000011, 6),
('Réparation de la boîte de vitesses défectueuse', 12, 4, 756000000015, 9);

-- Insertion des données dans la table Vente
INSERT INTO Vente (DateVente, Rabais, PrixReel, NoAVSClient, NumeroChassis, NoAVSVendeur) VALUES
('2021-09-15', 5.00, 38000.00, 756000000006, 'VF1FFFFF6666666', 756000000004),
('2021-09-20', 10.00, 40500.00, 756000000007, 'VF1GGGGG7777777', 756000000005),
('2022-03-10', 7.00, 27900.00, 756000000016, 'VF1MMMMM1313131', 756000000012),
('2022-03-20', 5.00, 30400.00, 756000000017, 'VF1NNNNN1414141', 756000000014);

-- Mise à jour de la propriété des voitures après la vente
UPDATE Voiture SET proprietaire = 756000000006 WHERE NumeroChassis = 'VF1FFFFF6666666';
UPDATE Voiture SET proprietaire = 756000000007 WHERE NumeroChassis = 'VF1GGGGG7777777';
UPDATE Voiture SET proprietaire = 756000000016 WHERE NumeroChassis = 'VF1MMMMM1313131';
UPDATE Voiture SET proprietaire = 756000000017 WHERE NumeroChassis = 'VF1NNNNN1414141';