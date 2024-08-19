### 1. Tabellen erstellen:

**`Kategorie:`**

- **KategorieID**: Primärschlüssel
- **Name**: Der Name der Zugkategorie, z.B. ICE, Regional

CREATE TABLE Kategorie (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL
);




**`Zug:`**

- **ZugID**: Primärschlüssel
- **KategorieID**: Fremdschlüssel
- **Sitzplaetze**: Die Anzahl der Sitzplätze im Zug
- **Stehplaetze**: Die Anzahl der Stehplätze im Zug

CREATE TABLE Zug (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    KategorieID INT,
    Sitzplaetze INT,
    Stehplaetze INT,
    FOREIGN KEY (KategorieID) REFERENCES Kategorie(ID)
);



**`Zugverbindung:`**

Diese Tabelle speichert Informationen über die Zugverbindungen zwischen verschiedenen Städten. Jede Verbindung wird durch einen Zug realisiert.

- **VerbindungsID**: Primärschlüssel
- **ZugID**: Fremdschlüssel, der auf den Zug verweist, der die Verbindung durchführt
- **Startort**: Die Stadt, von der die Zugverbindung startet
- **Ankunftsort**: Die Stadt, in der die Zugverbindung endet
- **Datum**: Das Datum, an dem die Zugverbindung stattfindet
- **Dauer**: Die Dauer der Zugverbindung in Stunden und Minuten → (Datentyp: TIME)

CREATE TABLE Zugverbindung (
    VerbindungsID INT PRIMARY KEY AUTO_INCREMENT,
    ZugID INT,
    Startort VARCHAR(255) NOT NULL,
    Ankunftsort VARCHAR(255) NOT NULL,
    Datum DATE,
    Dauer TIME,
    FOREIGN KEY (ZugID) REFERENCES Zug(ID)
);



SELECT * FROM Kategorie;
SELECT * FROM Zug;
SELECT * FROM Zugverbindung;


// Einfügen von Kategorien
INSERT INTO Kategorie (KategorieID, Name) VALUES (1, 'ICE');
INSERT INTO Kategorie (KategorieID, Name) VALUES (2, 'Regional');
INSERT INTO Kategorie (KategorieID, Name) VALUES (3, 'Intercity');
INSERT INTO Kategorie (KategorieID, Name) VALUES (4, 'S-Bahn');

// Einfügen von Zügen
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (1, 500, 200, 1);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (2, 300, 100, 2);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (3, 400, 150, 3);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (4, 200, 50, 4);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (5, 600, 250, 1);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (6, 350, 120, 2);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (7, 450, 170, 3);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (8, 250, 80, 4);

// Einfügen von Zugverbindungen
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (1, 1, 'Berlin', 'München', '2024-08-15', '04:30:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (2, 2, 'Hamburg', 'Köln', '2024-08-15', '03:00:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (3, 3, 'Frankfurt', 'Stuttgart', '2024-08-15', '02:15:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (4, 4, 'Düsseldorf', 'Dortmund', '2024-08-15', '00:45:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (5, 1, 'München', 'Berlin', '2024-08-16', '04:30:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (6, 2, 'Köln', 'Hamburg', '2024-08-16', '03:00:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (7, 3, 'Stuttgart', 'Frankfurt', '2024-08-16', '02:15:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (8, 4, 'Dortmund', 'Düsseldorf', '2024-08-16', '00:45:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (9, 5, 'Berlin', 'München', '2024-08-17', '04:30:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (10, 6, 'Hamburg', 'Köln', '2024-08-17', '03:00:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (11, 7, 'Frankfurt', 'Stuttgart', '2024-08-17', '02:15:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (12, 8, 'Düsseldorf', 'Dortmund', '2024-08-17', '00:45:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (13, 1, 'München', 'Berlin', '2024-08-18', '04:30:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (14, 2, 'Köln', 'Hamburg', '2024-08-18', '03:00:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (15, 3, 'Stuttgart', 'Frankfurt', '2024-08-18', '02:15:00');
INSERT INTO Zugverbindung (VerbindungID, ZugID, Startort, Ankunftsort, Datum, Dauer) VALUES (16, 4, 'Dortmund', 'Düsseldorf', '2024-08-18', '00:45:00');



### 3. SQL Queries:

- **1.1:** Alle Züge auflisten
SELECT * FROM Zug;

- **1.2:** Alle Zugverbindungen, die in Berlin starten
SELECT *
FROM Zugverbindung
WHERE STARTORT = 'Berlin';


- **1.3:** Alle Zugverbindungen, die länger als 3 Stunden dauern
SELECT *
FROM Zugverbindung
WHERE 	DAUER  > '03:00:00';

--oder
SELECT *
FROM Zugverbindung
WHERE TIMEDIFF(DAUER, '00:00:00') > '03:00:00';

SELECT *
FROM Zugverbindung
WHERE EXTRACT(HOUR FROM DAUER) > 3
OR (EXTRACT(HOUR FROM DAUER) = 3 AND EXTRACT(MINUTE FROM DAUER) > 0);

SELECT *
FROM Zugverbindung
WHERE CAST(DAUER AS TIME) > '03:00:00';



- **1.4:** Alle Verbindungen für den 15. August 2024

SELECT *
FROM Zugverbindung
WHERE DATUM  = '2024-8-15';

-- Oder
SELECT *
FROM Zugverbindung
WHERE DATE(DATUM) = '2024-08-15';

SELECT *
FROM Zugverbindung
WHERE DATUM BETWEEN '2024-08-15' AND '2024-08-15 23:59:59';

SELECT *
FROM Zugverbindung
WHERE CAST(DATUM AS DATE) = '2024-08-15';

SELECT *
FROM Zugverbindung
WHERE CONVERT(DATE, DATUM) = '2024-08-15';




- **1.5:** Alle Verbindungen von München nach Berlin

SELECT *
FROM Zugverbindung
WHERE ANKUNFTSORT = 'München' AND STARTORT = 'Berlin';




- **2.1:** Die Anzahl der Sitzplätze und Stehplätze für Züge, die in Frankfurt ankommen

SELECT SITZPLAETZE, STEHPLAETZE
FROM ZUG
WHERE ID IN (
    SELECT ZUGID
    FROM ZUGVERBINDUNG
    WHERE ANKUNFTSORT = 'Frankfurt'
);

--Oder
SELECT SITZPLAETZE, STEHPLAETZE
FROM ZUG
WHERE ID = (
    SELECT ZUGID
    FROM ZUGVERBINDUNG
    WHERE ANKUNFTSORT = 'Frankfurt'
    LIMIT 1
);

--oder
SELECT SITZPLAETZE, STEHPLAETZE
FROM ZUG z
WHERE EXISTS (
    SELECT 1
    FROM ZUGVERBINDUNG zv
    WHERE zv.ZUGID = z.ID
      AND zv.ANKUNFTSORT = 'Frankfurt'
);

SELECT z.SITZPLAETZE, z.STEHPLAETZE
FROM ZUG z
JOIN ZUGVERBINDUNG zv ON z.ID = zv.ZUGID
WHERE zv.ANKUNFTSORT = 'Frankfurt';



- **2.2:** Finde alle Verbindungen der Kategorie 'ICE'
SELECT *
FROM ZUGVERBINDUNG
WHERE ZUGID IN (
    SELECT ZUGID
    FROM ZUG
    WHERE KATEGORIEID = (
        SELECT ID
        FROM KATEGORIE
        WHERE NAME = 'ICE'
    )
);



- **2.3:** Die Gesamtzahl der Sitzplätze für alle Züge, die am 16. August 2024 verkehren

SELECT SUM(SITZPLAETZE) AS GesamtzahlDerSitzplaetze
FROM ZUG
WHERE ID IN (
    SELECT ZUGID
    FROM ZUGVERBINDUNG
    WHERE DATUM = '2024-08-16'
);


- **2.4:** Liste die Anzahl der Verbindungen auf, die pro Tag stattfinden

SELECT DATUM, COUNT(*)
FROM ZUGVERBINDUNG
GROUP BY DATUM;


- **2.5:** Finde die Startorte, von denen die meisten Verbindungen ausgehen, und sortiere sie nach der Anzahl der Verbindungen

SELECT STARTORT, COUNT(*) AS AnzahlVerbindungen
FROM ZUGVERBINDUNG
GROUP BY STARTORT
ORDER BY AnzahlVerbindungen DESC;


**Bonus:**

- **3.1:** Finde alle Züge, die an mindestens zwei verschiedenen Tagen im Einsatz sind
SELECT ZUGID
FROM ZUGVERBINDUNG
GROUP BY ZUGID
HAVING COUNT(DISTINCT DATUM) >= 2;


- **3.2:** Ermittle die durchschnittliche Dauer aller Verbindungen der Kategorie 'Regional'

SELECT AVG(EXTRACT(EPOCH FROM DAUER)) AS DurchschnittDauer
FROM ZUGVERBINDUNG
WHERE ZUGID IN (
    SELECT ZUGID
    FROM ZUG
    WHERE KATEGORIEID = (
        SELECT ID
        FROM KATEGORIE
        WHERE NAME = 'Regional'
    )
);



- **3.3:** Identifiziere Züge, die in zwei aufeinanderfolgenden Tagen fahren und dabei in den gleichen Städten starten und ankommen

SELECT DISTINCT z1.ZUGID
FROM ZUGVERBINDUNG z1
WHERE z1.ZUGID IN (
    SELECT z2.ZUGID
    FROM ZUGVERBINDUNG z2
    WHERE z1.STARTORT = z2.STARTORT
    AND z1.ANKUNFTSORT = z2.ANKUNFTSORT
    AND z1.DATUM = DATEADD(day,-1,z2.DATUM)
);


- **3.4:** Ermittle die Anzahl der Zugverbindungen, die im Jahr 2024 stattfinden, und gruppiere sie nach Monaten.

SELECT
    EXTRACT(MONTH FROM DATUM) AS Monat,
    COUNT(*) AS AnzahlDerVerbindungen
FROM
    ZUGVERBINDUNG
WHERE
    EXTRACT(YEAR FROM DATUM) = 2024
GROUP BY
    EXTRACT(MONTH FROM DATUM)
ORDER BY
    Monat;


- **3.5:** Finde den Tag mit den meisten Verbindungen in einem bestimmten Monat, zum Beispiel August 2024.

SELECT DATUM AS TAG,
COUNT(*) AS AnzahlDerVerbindungen
FROM ZUGVERBINDUNG
WHERE EXTRACT(YEAR FROM DATUM)= 2024
AND EXTRACT(MONTH FROM DATUM) = 8
GROUP BY
DATUM
ORDER BY
AnzahlDerVerbindungen DESC
LIMIT 1;