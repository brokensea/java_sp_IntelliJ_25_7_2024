-- ### 1. **Tabellen erstellen**

-- - Erstelle eine Tabelle `Products`, wobei ein Produkt eine eindeutige ID, einen Namen, eine Kategorie (z.B. Obst, Gemüse, Getränke), einen Preis und ein Verfallsdatum hat.
-- - Erstelle eine Tabelle `Categories`, wobei jede Kategorie eine eindeutige ID und einen Namen (z.B. Obst, Gemüse, Getränke) hat.

CREATE TABLE Categories (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL
);

CREATE TABLE Products(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    CategoryID INT,
    Price DECIMAL(10,2) NOT NULL,
    ExpirationDate DATE,
    FOREIGN KEY (CategoryID) REFERENCES
    Categories(ID)
);

-- ### 2. **Daten einfügen**
-- - Füge einige Produkte und Kategorien hinzu, z.B. Äpfel, Tomaten, Cola, etc.
INSERT INTO Categories(Name)
VALUES
    ('Obst'),
    ('Gemüse'),
    ('Getränke');

INSERT INTO Products (Name, CategoryID, Price, ExpirationDate)
VALUES
    ('Apfel', 1, 0.99, '2024-09-01'),
    ('Tomate', 2, 1.49, '2024-08-15'),
    ('Cola', 3, 1.99, '2025-01-01'),
    ('Orange', 1, 1.20, '2024-08-30'),
    ('Karotte', 2, 0.79, '2024-08-10'),
    ('Wasser', 3, 0.50, '2026-05-10');

SELECT * FROM Categories;
SELECT * FROM Products;


-- ### 3. **Daten abfragen**

-- 1. **Einfache Abfragen:**
--     - Zeige alle Produkte an
SELECT * FROM Products;


--     - Zeige alle Produkte einer bestimmten Kategorie an (z.B. Obst)
SELECT *
FROM Products
WHERE CategoryID = 1;

--     - Zeige alle Produkte an, die teurer als 5 Euro sind
INSERT INTO Products (Name, CategoryID, Price, ExpirationDate)
VALUES
    ('Wein', 3, 12.99, '2026-12-01'),
    ('Rindersteak', 2, 15.49, '2024-08-20'),
    ('Premium Schokolade', 1, 6.75, '2025-03-01'),
    ('Champagner', 3, 25.99, '2026-10-15');
    SELECT *
    FROM Products
    WHERE Price > 5;


--     - Zeige alle Produkte an, deren Verfallsdatum abgelaufen ist
-- SELECT *, DATEDIFF('DAY', ExpirationDate, CURRENT_DATE) AS
SELECT *, DATEDIFF('DAY', ExpirationDate, CURDATE()) AS
DaysExpired
FROM Products
WHERE ExpirationDate < CURDATE();


--     **Info:**

--     ```java
--     // Beispiele:

--     ... WHERE Verfallsdatum < CURDATE() ...

--     // Differenz zwischen zwei Daten
--     DATEDIFF(Verfallsdatum, CURDATE())
--     ```

--     → `CURDATE()` ist eine SQL-Funktion, die das aktuelle Datum zurückgibt, ohne die Uhrzeit

-- 2. **Erweiterte Abfragen:**
--     - **Produkte gruppieren nach Kategorie**
SELECT *, COUNT(CategoryID)
FROM Products
GROUP BY CategoryID;

SELECT CategoryID, *
FROM Products
ORDER BY CategoryID;


SELECT *, CategoryID
FROM Products
ORDER BY CategoryID;


--     - **Durchschnittlicher Preis pro Kategorie**
SELECT CategoryID, AVG(Price) AS DurchschnittlicherPreis
FROM Products
GROUP BY CategoryID
ORDER BY DurchschnittlicherPreis;

-- Oder
SELECT *,(
    SELECT AVG(Price)
    FROM Products p2
    WHERE p2.CategoryID = p1.CategoryID) AS DurchschnittlicherPreis
    FROM Products p1
    WHERE CategoryID IN(SELECT DISTINCT CategoryID
    FROM Products
    );

-- vereinfacht
    SELECT *,
       (
           SELECT AVG(Price)
           FROM Products p2
           WHERE p2.CategoryID = p1.CategoryID
       ) AS DurchschnittlicherPreis
       FROM Products p1;



--     - **Top 3 Kategorien nach Anzahl der Produkte**
SELECT *
FROM Products
WHERE CategoryID IN (
    SELECT CategoryID
    FROM Products
    GROUP BY CategoryID
    ORDER BY COUNT(ID) DESC
    LIMIT 3
);

-- oder
SELECT CategoryID, COUNT(ID) AS ProductNumber
FROM Products
GROUP BY CategoryID
ORDER BY ProductNumber DESC
LIMIT 3;

--     - **Kategorie mit dem höchsten durchschnittlichen Preis**
SELECT *
FROM Products
WHERE CategoryID IN (
    SELECT CategoryID
    FROM Products
    GROUP BY CategoryID
    ORDER BY AVG(Price) ASC
    LIMIT 3
);
--Oder
-- 获取具有最高平均价格的类别 ID
WITH HighestAvgPriceCategory AS (
    SELECT CategoryID
    FROM Products
    GROUP BY CategoryID
    ORDER BY AVG(Price) DESC
    LIMIT 1
)

-- 显示该类别的所有产品信息
SELECT *
FROM Products
WHERE CategoryID IN (
    SELECT CategoryID
    FROM HighestAvgPriceCategory
);

--     - **Produkte, die teurer als der Durchschnitt ihrer Kategorie sind**
SELECT p.*
FROM Products p
WHERE p.Price > (
    SELECT AVG(p2.Price)
    FROM Products p2
    WHERE p2.CategoryID = p.CategoryID
);


--     - **Kategorien mit den meisten abgelaufenen Produkten**
-- 计算每个类别中过期产品的数量
SELECT CategoryID, COUNT(ID) AS AnzahlAbgelaufeneProdukte
FROM Products
WHERE ExpirationDate < CURDATE()
GROUP BY CategoryID
ORDER BY AnzahlAbgelaufeneProdukte DESC;

--Oder
-- 计算每个类别中过期产品的数量
WITH CategoryExpiredCounts AS (
    SELECT CategoryID, COUNT(ID) AS AnzahlAbgelaufeneProdukte
    FROM Products
    WHERE ExpirationDate < CURDATE()
    GROUP BY CategoryID
)

-- 显示过期产品的详细信息
SELECT *,
    (SELECT COUNT(ID)
     FROM Products p2
     WHERE p2.CategoryID = p1.CategoryID
       AND p2.ExpirationDate < CURDATE()
    ) AS AnzahlAbgelaufeneProdukte
FROM Products p1
WHERE ExpirationDate < CURDATE()
ORDER BY AnzahlAbgelaufeneProdukte DESC;

--     - **Produkte mit der höchsten Haltbarkeit in ihrer Kategorie**
-- 找到每个类别中具有最长保质期的产品
SELECT *,
       (SELECT MAX(ExpirationDate)
        FROM Products p2
        WHERE p2.CategoryID = p1.CategoryID
       ) AS MaxExpirationDate
FROM Products p1
WHERE ExpirationDate = (
    SELECT MAX(ExpirationDate)
    FROM Products p2
    WHERE p2.CategoryID = p1.CategoryID
);

--     - **Durchschnittliche Haltbarkeit (Tage) pro Kategorie**
-- 计算每个类别的平均保质期（以天为单位）
SELECT CategoryID,
       AVG(DATEDIFF('DAY', CURDATE(), ExpirationDate)) AS DurchschnittlicheHaltbarkeit
FROM Products
GROUP BY CategoryID;


--     - **Durchschnittlicher Preis für Produkte, die nach 2023 ablaufen**

-- 计算2023年之后过期的产品的平均价格
SELECT AVG(Price) AS DurchschnittlicherPreis
FROM Products
WHERE ExpirationDate > '2023-12-31';


--     - **Produkte mit Preis über dem Durchschnitt, sortiert nach Haltbarkeit**
-- 计算价格高于平均价格的产品，并按保质期排序
SELECT *
FROM Products
WHERE Price > (
    SELECT AVG(Price)
    FROM Products
)
ORDER BY ExpirationDate;


--     - **Teuerstes Produkt in jeder Kategorie finden**
-- 找出每个类别中最贵的产品
SELECT CategoryID, Name, Price, ExpirationDate
FROM Products p1
WHERE Price = (
    SELECT MAX(Price)
    FROM Products p2
    WHERE p2.CategoryID = p1.CategoryID
);

--     - Produkte anzeigen, deren Preis über dem Durchschnittspreis aller Produkte liegt
-- 显示价格高于所有产品的平均价格的产品
SELECT *
FROM Products
WHERE Price > (
    SELECT AVG(Price)
    FROM Products
);

-- **Learn to Learn:**

-- - **Produkte, die innerhalb der nächsten 7 Tage ablaufen**
-- 显示在接下来的 7 天内过期的产品
SELECT *
FROM Products
WHERE ExpirationDate BETWEEN CURDATE() AND DATEADD('DAY', 7, CURDATE());
