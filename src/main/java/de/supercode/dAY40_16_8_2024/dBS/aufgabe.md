# Gartenverwaltung - SQL Queries - Join

# ✅ Aufgabenstellung

Nachdem wir bereits die Deutsche Bahn, Thalia und Warner Bros. Entertainment in unserem Kundenportfolio begrüßen
durften, freuen wir uns heute, einen weiteren Großkunden hinzuzufügen: OBI.

**Erstelle die folgenden beschriebenen Tabellen in SQL:**

- **Tabelle: Garten**
    - **GartenID** (Primary Key)
    - **Name** (Name des Gartens)
    - **Größe** (Fläche des Gartens in Quadratmetern)


- **Tabelle: Pflanze**
    - **PflanzenID** (Primary Key)
    - **Name** (Name der Pflanze)
    - **Essbar** (Boolescher Wert, ob die Pflanze essbar ist)
    - **WasserbedarfProPflanze** (Wasserbedarf in Litern pro Pflanze)

- **Tabelle: GartenPflanzen**
    - **GartenID** (Foreign Key zu Garten)
    - **PflanzenID** (Foreign Key zu Pflanze)
    - **Anzahl** (Anzahl der Pflanzen im Garten)

```sql
INSERT INTO Garten (GartenID, Name, Größe)
VALUES
(1, 'Blumenparadies', 500),
(2, 'GemüseObstGarten', 300),
(3, 'Kräutergarten', 150);
```

```sql
INSERT INTO Pflanze (PflanzenID, Name, Essbar, WasserbedarfProPflanze)
VALUES
(1, 'Rose', FALSE, 2),
(2, 'Tomate', TRUE, 3),
(3, 'Basilikum', TRUE, 1),
(4, 'Sonnenblume', FALSE, 5),
(5, 'Karotte', TRUE, 2),
(6, 'Apfelbaum', TRUE, 10),
(7, 'Lavendel', FALSE, 1),
(8, 'Erdbeere', TRUE, 2),
(9, 'Kartoffel', TRUE, 4),
(10, 'Kürbis', TRUE, 3),
(11, 'Dill', TRUE, 1);
```

```sql
INSERT INTO GartenPflanzen (GartenID, PflanzenID, Anzahl)
VALUES
(1, 1, 50),    -- 50 Rosen im Blumenparadies
(1, 4, 20),    -- 20 Sonnenblumen im Blumenparadies
(1, 7, 100),   -- 100 Lavendelpflanzen im Blumenparadies

(2, 2,  30),    -- 30 Tomaten 
(2, 5,  40),    -- 40 Karotten
(2, 9,  20),    -- 20 Karoffelpflanzen
(2, 10, 10),    -- 10 Kürbispflanzen 
(2, 6,  5),     -- 5 Apfelbäume
(2, 8,  25),    -- 25 Erdbeerpflanzen

(3, 3, 100),   -- 100 Basilikumpflanzen
(3, 2, 15),    -- 15 Tomaten
(3, 7, 50),    -- 50 Lavendelpflanzen
(3, 11, 100);  -- 100 Dill
```

1. **Berechne die gesamte Anzahl von Pflanzen in jedem Garten**
2. **Finde die Namen der Pflanzen und die Anzahl der Pflanzen im „Blumenparadies“**
3. **Liste Gärten mit weniger als 200 Pflanzen auf**
4. **Finde den Namen der Pflanze und den Wasserbedarf für alle essbaren Pflanzen im „Kräutergarten“**
5. **Berechne die durchschnittliche Anzahl an Pflanzen pro Garten**
6. **Finde alle essbaren Pflanzen im Kräutergarten**

2 JOINS notwendig

1. **Liste alle Gärten auf, die mindestens eine essbare Pflanze enthalten**
2. **Finde die Gärten, in denen mindestens eine Pflanze mehr als 10 Liter Wasser pro Tag benötigt**
3. **Berechne den gesamten Wasserbedarf für einen bestimmten Garten**
4. **Berechne den Garten mit dem höchsten Wasserbedarf**
5. **Finde den Garten mit der größten Anzahl an essbaren Pflanzen**
6. **Finde die Gärten, die keine essbaren Pflanzen enthalten**