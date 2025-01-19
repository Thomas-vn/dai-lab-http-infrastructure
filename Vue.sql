-- Vue des voitures en vente
CREATE OR REPLACE VIEW vue_voitures_en_vente AS
SELECT
    numerochassis,
    marque,
    typecarrosserie,
    couleur,
    prix,
    nombreportes,
    puissance,
    consommation,
    neuf,
    garantie,
    datefingarantie
FROM voiture
WHERE envente = TRUE;

-- Vue des ventes détaillées
-- Affiche les informations complètes sur les ventes, incluant le client et le vendeur.
CREATE OR REPLACE VIEW vue_ventes AS
SELECT
    v.datevente,
    v.prixreel AS prix_vente,
    v.rabais,
    vo.marque,
    vo.typecarrosserie,
    cl.nom AS nom_client,
    cl.prenom AS prenom_client,
    vend.nom AS nom_vendeur,
    vend.prenom AS prenom_vendeur
FROM vente v
JOIN voiture vo ON v.numerochassis = vo.numerochassis
JOIN client c ON v.noavsclient = c.noavs
JOIN personne cl ON c.noavs = cl.noavs
JOIN personnel pe ON v.noavsvendeur = pe.noavs
JOIN personne vend ON pe.noavs = vend.noavs;

-- Vue de l’historique des réparations
--
-- Affiche l’historique complet des réparations, y compris le mécanicien responsable et les détails de la voiture.
CREATE OR REPLACE VIEW vue_reparations AS
SELECT
    r.datedebut,
    r.datefin,
    r.prix,
    ts.description AS type_service,
    m.nom AS nom_mecanicien,
    m.prenom AS prenom_mecanicien,
    vo.marque,
    vo.typecarrosserie,
    vo.numerochassis
FROM reparation r
JOIN type_service ts ON r.ReparationID = ts.typeserviceid
JOIN voiture vo ON r.numerochassis = vo.numerochassis
JOIN service s ON r.reparationid = s.reparationid
JOIN personnel pe ON s.noavsmecanicien = pe.noavs
JOIN personne m ON pe.noavs = m.noavs;

-- Vue des revenus totaux par type d’activité, utile pour la compta et analyse de notre garage
CREATE OR REPLACE VIEW vue_revenus AS
SELECT
    'Ventes' AS type_activite,
    SUM(v.prixreel) AS total_revenu
FROM vente v
UNION
SELECT
    'Reparations',
    SUM(r.prix) AS total_revenu
FROM reparation r;



--Vue des activités d’un employé (ventes et réparations)
--Affiche l’ensemble des activités d’un employé, qu’il s’agisse de ventes ou de réparations. (ressortire nos meilleurs employée), peut-être pour les bonus de fin d'année
CREATE OR REPLACE VIEW vue_activites_employe AS
SELECT
    p.nom AS nom_employe,
    p.prenom AS prenom_employe,
    'Vente' AS type_activite,
    v.datevente AS date_activite,
    vo.marque,
    vo.typecarrosserie
FROM personnel pe
JOIN vente v ON pe.noavs = v.noavsvendeur
JOIN voiture vo ON v.numerochassis = vo.numerochassis
JOIN personne p ON pe.noavs = p.noavs
UNION
SELECT
    p.nom,
    p.prenom,
    'Reparation',
    r.datefin,
    vo.marque,
    vo.typecarrosserie
FROM personnel pe
JOIN service s ON pe.noavs = s.noavsmecanicien
JOIN reparation r ON s.reparationid = r.reparationid
JOIN voiture vo ON r.numerochassis = vo.numerochassis
JOIN personne p ON pe.noavs = p.noavs;

--Vue des réparations en cours
--Affiche les réparations qui ne sont pas encore terminées. (utile pour les chef d'atelier ou les managers)
CREATE OR REPLACE VIEW vue_reparations_en_cours AS
SELECT
    r.reparationid,
    vo.numerochassis,
    vo.marque,
    vo.typecarrosserie,
    r.datedebut,
    r.prix,
    m.nom AS nom_mecanicien,
    m.prenom AS prenom_mecanicien
FROM reparation r
JOIN voiture vo ON r.numerochassis = vo.numerochassis
JOIN service s ON r.reparationid = s.reparationid
JOIN personnel pe ON s.noavsmecanicien = pe.noavs
JOIN personne m ON pe.noavs = m.noavs
WHERE r.datefin IS NULL;


--Vue qui indique tout les services effectuées pour une réparation. (par exemple, pour faire les factures ou les montrer au client)
CREATE OR REPLACE VIEW vue_services_reparation AS
SELECT
    r.reparationid,
    vo.numerochassis AS numero_chassis,
    vo.marque,
    vo.typecarrosserie,
    r.datedebut AS date_debut_reparation,
    r.datefin AS date_fin_reparation,
    r.prix AS prix_reparation,
    ts.description AS description_service,
    s.heuresdetravail AS heures_travail,
    m.nom AS nom_mecanicien,
    m.prenom AS prenom_mecanicien
FROM reparation r
JOIN voiture vo ON r.numerochassis = vo.numerochassis
JOIN service s ON r.reparationid = s.reparationid
JOIN personnel pe ON s.noavsmecanicien = pe.noavs
JOIN personne m ON pe.noavs = m.noavs
JOIN type_service ts ON s.typeserviceid = ts.typeserviceid;




-- Vue complète des clients (Client + Personne)
CREATE OR REPLACE VIEW vue_clients_complets AS
SELECT
    p.NoAVS,
    p.Nom,
    p.Prenom,
    p.DateNaissance,
    p.Sexe,
    p.LieuID,
    c.DateAjout
FROM Client c
JOIN Personne p ON c.NoAVS = p.NoAVS;

-- Vue complète du personnel (Personnel + Personne)
CREATE OR REPLACE VIEW vue_personnel_complet AS
SELECT
    p.NoAVS,
    p.Nom,
    p.Prenom,
    p.DateNaissance,
    p.Sexe,
    p.LieuID,
    pe.Salaire,
    pe.Poste,
    pe.DateDebutContrat,
    pe.DateFinContrat,
    pe.Supervisor
FROM Personnel pe
JOIN Personne p ON pe.NoAVS = p.NoAVS;

-- Vue complète des mécaniciens (Mecanicien + Personnel + Personne)
CREATE OR REPLACE VIEW vue_mecaniciens_complets AS
SELECT
    p.NoAVS,
    p.Nom,
    p.Prenom,
    p.DateNaissance,
    p.Sexe,
    p.LieuID,
    pe.Salaire,
    pe.Poste,
    pe.DateDebutContrat,
    pe.DateFinContrat,
    pe.Supervisor
FROM Mecanicien m
JOIN Personnel pe ON m.NoAVS = pe.NoAVS
JOIN Personne p ON pe.NoAVS = p.NoAVS;

-- Vue complète des vendeurs (Vendeur + Personnel + Personne)
CREATE OR REPLACE VIEW vue_vendeurs_complets AS
SELECT
    p.NoAVS,
    p.Nom,
    p.Prenom,
    p.DateNaissance,
    p.Sexe,
    p.LieuID,
    pe.Salaire,
    pe.Poste,
    pe.DateDebutContrat,
    pe.DateFinContrat,
    pe.Supervisor
FROM Vendeur v
JOIN Personnel pe ON v.NoAVS = pe.NoAVS
JOIN Personne p ON pe.NoAVS = p.NoAVS;


-- Nombre total de réparations effectuées par chaque mécanicien
CREATE OR REPLACE VIEW reparation_par_mecanicien AS
SELECT
    p.Nom,
    p.Prenom,
    COUNT(s.ServiceID) AS NombreReparations
FROM Service s
JOIN Personnel pe ON s.NoAVSMecanicien = pe.NoAVS
JOIN Personne p ON pe.NoAVS = p.NoAVS
GROUP BY p.Nom, p.Prenom
HAVING COUNT(s.ServiceID) > 0
ORDER BY NombreReparations DESC;


--Vue qui permet de savoir les clients qui sont aussi membre du personnel
CREATE OR REPLACE VIEW vue_personnel_client AS
SELECT
    p.NoAVS,
    p.Nom,
    p.Prenom,
    p.DateNaissance,
    p.Sexe,
    p.LieuID,
    pe.Salaire,
    pe.Poste,
    pe.DateDebutContrat,
    pe.DateFinContrat
FROM Personnel pe
JOIN Personne p ON pe.NoAVS = p.NoAVS
JOIN Client c ON p.NoAVS = c.NoAVS;