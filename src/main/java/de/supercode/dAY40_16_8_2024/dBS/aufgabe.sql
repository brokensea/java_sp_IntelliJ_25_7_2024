-- **Erstelle die folgenden beschriebenen Tabellen in SQL:**

-- - **Tabelle: Garten**
--     - **GartenID** (Primary Key)
--     - **Name** (Name des Gartens)
--     - **Größe** (Fläche des Gartens in Quadratmetern)

    CREATE TABLE Garten (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255),
    Groesse INT
);

-- - **Tabelle: Pflanze**
--     - **PflanzenID** (Primary Key)
--     - **Name** (Name der Pflanze)
--     - **Essbar** (Boolescher Wert, ob die Pflanze essbar ist)
--     - **WasserbedarfProPflanze** (Wasserbedarf in Litern pro Pflanze)

CREATE TABLE Pflanze
(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255),
    Essbar BOOLEAN,
    WasserbedarfProPflanze INT
);


-- - **Tabelle: GartenPflanzen**
--     - **GartenID** (Foreign Key zu Garten)
--     - **PflanzenID** (Foreign Key zu Pflanze)
--     - **Anzahl** (Anzahl der Pflanzen im Garten)

CREATE TABLE GartenPflanzen(
    GartenID INT,
    PflanzenID INT,
    Anzahl INT,
    FOREIGN KEY (GartenID) REFERENCES Garten(ID),
    FOREIGN KEY (PflanzenID) REFERENCES Pflanze(ID)
);

-- ```sql
-- INSERT INTO Garten (GartenID, Name, Größe)
-- VALUES
-- (1, 'Blumenparadies', 500),
-- (2, 'GemüseObstGarten', 300),
-- (3, 'Kräutergarten', 150);

INSERT INTO Garten (Name, Groesse)
VALUES
('Blumenparadies', 500),
( 'GemüseObstGarten', 300),
('Kräutergarten', 150);


-- ```sql
-- INSERT INTO Pflanze (PflanzenID, Name, Essbar, WasserbedarfProPflanze)
-- VALUES
-- (1, 'Rose', FALSE, 2),
-- (2, 'Tomate', TRUE, 3),
-- (3, 'Basilikum', TRUE, 1),
-- (4, 'Sonnenblume', FALSE, 5),
-- (5, 'Karotte', TRUE, 2),
-- (6, 'Apfelbaum', TRUE, 10),
-- (7, 'Lavendel', FALSE, 1),
-- (8, 'Erdbeere', TRUE, 2),
-- (9, 'Kartoffel', TRUE, 4),
-- (10, 'Kürbis', TRUE, 3),
-- (11, 'Dill', TRUE, 1);
-- ```
INSERT INTO Pflanze (Name, Essbar, WasserbedarfProPflanze)
VALUES
('Rose', FALSE, 2),
('Tomate', TRUE, 3),
('Basilikum', TRUE, 1),
('Sonnenblume', FALSE, 5),
('Karotte', TRUE, 2),
('Apfelbaum', TRUE, 10),
('Lavendel', FALSE, 1),
('Erdbeere', TRUE, 2),
('Kartoffel', TRUE, 4),
('Kürbis', TRUE, 3),
('Dill', TRUE, 1);


-- ```sql
-- INSERT INTO GartenPflanzen (GartenID, PflanzenID, Anzahl)
-- VALUES
-- (1, 1, 50),    -- 50 Rosen im Blumenparadies
-- (1, 4, 20),    -- 20 Sonnenblumen im Blumenparadies
-- (1, 7, 100),   -- 100 Lavendelpflanzen im Blumenparadies

-- (2, 2,  30),    -- 30 Tomaten
-- (2, 5,  40),    -- 40 Karotten
-- (2, 9,  20),    -- 20 Karoffelpflanzen
-- (2, 10, 10),    -- 10 Kürbispflanzen
-- (2, 6,  5),     -- 5 Apfelbäume
-- (2, 8,  25),    -- 25 Erdbeerpflanzen

-- (3, 3, 100),   -- 100 Basilikumpflanzen
-- (3, 2, 15),    -- 15 Tomaten
-- (3, 7, 50),    -- 50 Lavendelpflanzen
-- (3, 11, 100);  -- 100 Dill
-- ```

INSERT INTO GartenPflanzen (GartenID, PflanzenID, Anzahl)
VALUES
(1, 1, 50),    -- 50 Rosen im Blumenparadies
(1, 4, 20),    -- 20 Sonnenblumen im Blumenparadies
(1, 7, 100),   -- 100 Lavendelpflanzen im Blumenparadies

(2, 2, 30),    -- 30 Tomaten
(2, 5, 40),    -- 40 Karotten
(2, 9, 20),    -- 20 Kartoffelpflanzen
(2, 10, 10),   -- 10 Kürbispflanzen
(2, 6, 5),     -- 5 Apfelbäume
(2, 8, 25),    -- 25 Erdbeerpflanzen

(3, 3, 100),   -- 100 Basilikumpflanzen
(3, 2, 15),    -- 15 Tomaten
(3, 7, 50),    -- 50 Lavendelpflanzen
(3, 11, 100);  -- 100 Dill

SELECT * FROM GARTEN;
SELECT * FROM GARTENPFLANZEN;
SELECT * FROM PFLANZE;


-- 1. **Berechne die gesamte Anzahl von Pflanzen in jedem Garten**
SELECT GARTEN.ID, NAME,SUM(ANZAHL) AS SUM_ANZAHL
FROM GARTEN LEFT JOIN GARTENPFLANZEN
ON GARTEN.ID = GARTENPFLANZEN.GartenID
GROUP BY GARTEN.ID;

--
SELECT GARTEN.ID, GARTEN.NAME, COALESCE(SUM(GARTENPFLANZEN.ANZAHL), 0) AS SUM_ANZAHL
FROM GARTEN
LEFT JOIN GARTENPFLANZEN
ON GARTEN.ID = GARTENPFLANZEN.GartenID
GROUP BY GARTEN.ID, GARTEN.NAME;


-- 2. **Finde die Namen der Pflanzen und die Anzahl der Pflanzen im „Blumenparadies“**
SELECT GARTEN.NAME, PFLANZE.NAME AS PFLANZENNAME, GARTENPFLANZEN.ANZAHL
FROM GARTEN
LEFT JOIN GARTENPFLANZEN
ON GARTEN.ID = GARTENPFLANZEN.GartenID
LEFT JOIN PFLANZE
ON GARTENPFLANZEN.PflanzenID = PFLANZE.ID
WHERE GARTEN.NAME = 'Blumenparadies';


-- 3. **Liste Gärten mit weniger als 200 Pflanzen auf**
SELECT GARTEN.*
FROM GARTEN
LEFT JOIN (
    SELECT GartenID, SUM(ANZAHL) AS GESAMTANZAHL
    FROM GARTENPFLANZEN
    GROUP BY GartenID
) AS Pflanzenanzahl
ON GARTEN.ID = Pflanzenanzahl.GartenID
WHERE COALESCE(Pflanzenanzahl.GESAMTANZAHL, 0) < 200;




-- 4. **Finde den Namen der Pflanze und den Wasserbedarf für alle essbaren Pflanzen im „Kräutergarten“**
SELECT P.NAME, P.WasserbedarfProPflanze
FROM PFLANZE P
JOIN GARTENPFLANZEN GP ON P.ID = GP.PflanzenID
JOIN GARTEN G ON GP.GartenID = G.ID
WHERE G.NAME = 'Kräutergarten' AND P.Essbar = TRUE;

-- 5. **Berechne die durchschnittliche Anzahl an Pflanzen pro Garten**

SELECT G.ID, G.NAME, AVG(COALESCE(GP.ANZAHL, 0)) AS DurchschnittlicheAnzahl
FROM GARTEN G
LEFT JOIN GARTENPFLANZEN GP ON G.ID = GP.GartenID
GROUP BY G.ID;


-- 6. **Finde alle essbaren Pflanzen im Kräutergarten**
SELECT P.ID, P.NAME, P.Essbar
FROM PFLANZE P
JOIN GARTENPFLANZEN GP ON P.ID = GP.PflanzenID
JOIN GARTEN G ON GP.GartenID = G.ID
WHERE G.NAME = 'Kräutergarten' AND P.Essbar = TRUE;

-- 2 JOINS notwendig

-- 1. **Liste alle Gärten auf, die mindestens eine essbare Pflanze enthalten**

--fasle
-- SELECT G.*,ANZAHL.*
-- FROM GARTEN G
-- LEFT JOIN GARTENPFLANZEN GP
-- ON G.ID = GP.GartenID
-- LEFT JOIN (
--     SELECT COUNT(Essbar=TRUE) AS ANZAHL_ESSBAR
--     FROM PFLANZE P
--     ON GP.PflanzenID = P.ID
--     WHERE ANZAHL_ESSBAR>1
--     GROUP BY P.ID
-- ) AS ANZAHL;

SELECT G.*, COALESCE(ANZAHL.ANZAHL_ESSBAR, 0) AS ANZAHL_ESSBAR
FROM GARTEN G
LEFT JOIN (
    SELECT GP.GartenID,
    COUNT(CASE WHEN P.Essbar = TRUE THEN 1 END)
    AS ANZAHL_ESSBAR
    FROM GARTENPFLANZEN GP
    JOIN PFLANZE P ON GP.PflanzenID = P.ID
    WHERE P.Essbar = TRUE
    GROUP BY GP.GartenID
) AS ANZAHL ON G.ID = ANZAHL.GartenID
WHERE ANZAHL.ANZAHL_ESSBAR > 0;


-- SELECT G.*, COALESCE(TEMP.ESSEBAR_ANZHAL, 0) AS ESSEBAR_ANZHAL
-- FROM GARTEN G
-- LEFT JOIN (
--     SELECT GP.GartenID, COUNT(P.ID) AS ESSEBAR_ANZHAL
--     FROM GARTENPFLANZEN GP
--     JOIN PFLANZE P ON GP.PflanzenID = P.ID
--     WHERE P.Essbar = TRUE
--     GROUP BY GP.GartenID
-- ) AS TEMP
-- ON G.ID = TEMP.GartenID
-- WHERE TEMP.ESSEBAR_ANZHAL > 0;



-- 2. **Finde die Gärten, in denen mindestens eine Pflanze mehr als 10 Liter Wasser pro Tag benötigt**


SELECT DISTINCT G.*
FROM GARTEN G
LEFT JOIN GARTENPFLANZEN GP ON G.ID = GP.GartenID
LEFT JOIN PFLANZE P ON GP.PflanzenID = P.ID
WHERE P.WasserbedarfProPflanze > 10;




-- 3. **Berechne den gesamten Wasserbedarf für einen bestimmten Garten**

SELECT G.NAME, SUM(P.WasserbedarfProPflanze * GP.ANZAHL) AS GesamtWasserbedarf
FROM GARTEN G
JOIN GARTENPFLANZEN GP ON G.ID = GP.GartenID
JOIN PFLANZE P ON GP.PflanzenID = P.ID
WHERE G.NAME = 'Blumenparadies'
GROUP BY G.NAME;



-- 4. **Berechne den Garten mit dem höchsten Wasserbedarf**
SELECT G.NAME, SUM(P.WasserbedarfProPflanze * GP.ANZAHL) AS GesamtWasserbedarf
FROM GARTEN G
JOIN GARTENPFLANZEN GP ON G.ID = GP.GartenID
JOIN PFLANZE P ON GP.PflanzenID = P.ID
GROUP BY G.NAME
ORDER BY GesamtWasserbedarf DESC
LIMIT 1;

-- 5. **Finde den Garten mit der größten Anzahl an essbaren Pflanzen**
SELECT G.NAME, COUNT(P.ID) AS AnzahlEssbarePflanzen
FROM GARTEN G
JOIN GARTENPFLANZEN GP ON G.ID = GP.GartenID
JOIN PFLANZE P ON GP.PflanzenID = P.ID
WHERE P.Essbar = TRUE
GROUP BY G.NAME
ORDER BY AnzahlEssbarePflanzen DESC
LIMIT 1;


-- 6. **Finde die Gärten, die keine essbaren Pflanzen enthalten**

SELECT G.ID, G.NAME
FROM GARTEN G
LEFT JOIN GARTENPFLANZEN GP ON G.ID = GP.GartenID
LEFT JOIN PFLANZE P ON GP.PflanzenID = P.ID
AND P.Essbar = TRUE
WHERE P.ID IS NULL;


-- SELECT G.ID, G.NAME, COALESCE(essbarAnzahl, 0) AS essbarAnzahl
-- FROM GARTEN G
-- LEFT JOIN (
--     SELECT GP.GartenID, COUNT(*) AS essbarAnzahl
--     FROM GARTENPFLANZEN GP
--     JOIN PFLANZE P ON GP.PflanzenID = P.ID
--     WHERE P.Essbar = TRUE
--     GROUP BY GP.GartenID
-- ) AS essbar_counts ON G.ID = essbar_counts.GartenID
-- WHERE essbar_counts.essbarAnzahl IS NULL;