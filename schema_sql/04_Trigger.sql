-- Trigger pour mettre à jour automatiquement le statut de garantie (voitures)
-- Quand une voiture atteint la fin de sa garantie, le champ garantie passe à FALSE.
--A lancer perdiodiquement
CREATE OR REPLACE FUNCTION update_warranty_status()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.datefingarantie IS NOT NULL AND NEW.datefingarantie < CURRENT_DATE THEN
        NEW.garantie = FALSE;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_warranty_status
BEFORE INSERT OR UPDATE ON voiture
FOR EACH ROW
EXECUTE FUNCTION update_warranty_status();
-- Trigger pour vérifier qu'une vente est liée à une voiture en stock
-- Une voiture doit être en stock (envente = TRUE) pour pouvoir être vendue.
CREATE OR REPLACE FUNCTION validate_car_sale()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM voiture
        WHERE numerochassis = NEW.numerochassis AND envente = TRUE
    ) THEN
        RAISE EXCEPTION 'La voiture % n''est pas en stock pour être vendue.', NEW.numerochassis;
    END IF;

    -- Marquer la voiture comme vendue
    UPDATE voiture
    SET envente = FALSE
    WHERE numerochassis = NEW.numerochassis;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validate_sale
BEFORE INSERT ON vente
FOR EACH ROW
EXECUTE FUNCTION validate_car_sale();
--Fonction pour calculer les bonus des commerciaux
--Calcule les bonus pour les employés ayant effectué des ventes, en fonction du prix total des ventes.
CREATE OR REPLACE FUNCTION calculate_bonus(salesperson_noavs BIGINT)
RETURNS NUMERIC AS $$
DECLARE
    total_sales NUMERIC := 0;
    bonus NUMERIC := 0;
BEGIN
    -- Calculer le total des ventes du commercial
    SELECT SUM(prixreel) INTO total_sales
    FROM vente
    WHERE noavsvendeur = salesperson_noavs;

    -- Bonus de 5% du total des ventes
    IF total_sales IS NOT NULL THEN
        bonus := total_sales * 0.05;
    END IF;

    RETURN bonus;
END;
$$ LANGUAGE plpgsql;

-- Calcul automatique du prix réel basé sur le rabais en pourcentage
CREATE OR REPLACE FUNCTION calculate_real_sale_price_percentage()
RETURNS TRIGGER AS $$
BEGIN
    -- Récupérer le prix de la voiture et calculer le prix réel
    NEW.prixreel := (SELECT prix FROM voiture WHERE numerochassis = NEW.numerochassis) * (1 - NEW.rabais / 100.0);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER enforce_real_sale_price_percentage
BEFORE INSERT OR UPDATE ON vente
FOR EACH ROW
EXECUTE FUNCTION calculate_real_sale_price_percentage();

--Trigger qui vérifie qu'une personne a bien 18 révolu age pour acheter une voiture. --> check
CREATE OR REPLACE FUNCTION check_minimum_age()
RETURNS TRIGGER AS $$
BEGIN
    -- Calculer l'âge de la personne à partir de sa date de naissance
    IF AGE(NEW.datenaissance) < INTERVAL '18 years' THEN
        RAISE EXCEPTION 'La personne doit avoir au moins 18 ans. Date de naissance fournie : %', NEW.datenaissance;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;



CREATE TRIGGER validate_minimum_age
BEFORE INSERT OR UPDATE ON personne
FOR EACH ROW
EXECUTE FUNCTION check_minimum_age();





--Trigger qui transorme un personnel en client si c'est lui le client
-- Trigger pour appeler la procédure à chaque insertion dans la table Vente
-- Fonction unique pour vérifier et affilier un client lors d'une vente
CREATE OR REPLACE FUNCTION verifier_et_affilier_client_trigger()
RETURNS TRIGGER AS $$
BEGIN
    -- Vérifier si la personne est déjà un client
    IF NOT EXISTS (
        SELECT 1 FROM Client WHERE NoAVS = NEW.NoAVSClient
    ) THEN
        -- Ajouter la personne comme client avec la date actuelle
        INSERT INTO Client (NoAVS, DateAjout)
        VALUES (NEW.NoAVSClient, CURRENT_DATE);

        RAISE NOTICE 'La personne % a été ajoutée comme client.', NEW.NoAVSClient;
    ELSE
        RAISE NOTICE 'La personne % est déjà un client.', NEW.NoAVSClient;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Création du trigger sur la table Vente
CREATE TRIGGER trg_verifier_et_affilier_client
BEFORE INSERT ON Vente
FOR EACH ROW
EXECUTE FUNCTION verifier_et_affilier_client_trigger();




