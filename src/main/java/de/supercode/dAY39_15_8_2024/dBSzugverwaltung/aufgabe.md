# Zugverwaltung - SQL Queries

# ✅ Aufgabenstellung

Du bist beauftragt, eine Datenbank für ein Zugverwaltungssystem zu erstellen. Die Datenbank soll drei Tabellen
enthalten: **Zug**, **Zugverbindung**, und **Kategorie**. Diese Tabellen sollen die Zugverbindungen zwischen
verschiedenen Städten verwalten und die verschiedenen Zugkategorien berücksichtigen.

### 1. Tabellen erstellen:

**`Kategorie:`**

- **KategorieID**: Primärschlüssel
- **Name**: Der Name der Zugkategorie, z.B. ICE, Regional

**`Zug:`**

- **ZugID**: Primärschlüssel
- **KategorieID**: Fremdschlüssel
- **Sitzplaetze**: Die Anzahl der Sitzplätze im Zug
- **Stehplaetze**: Die Anzahl der Stehplätze im Zug

**`Zugverbindung:`**

Diese Tabelle speichert Informationen über die Zugverbindungen zwischen verschiedenen Städten. Jede Verbindung wird
durch einen Zug realisiert.

- **VerbindungsID**: Primärschlüssel
- **ZugID**: Fremdschlüssel, der auf den Zug verweist, der die Verbindung durchführt
- **Startort**: Die Stadt, von der die Zugverbindung startet
- **Ankunftsort**: Die Stadt, in der die Zugverbindung endet
- **Datum**: Das Datum, an dem die Zugverbindung stattfindet
- **Dauer**: Die Dauer der Zugverbindung in Stunden und Minuten → (Datentyp: TIME)

### 2. Datenbank mit Datensätze füttern:

```sql
**// Einfügen von Kategorien**
INSERT INTO Kategorie (KategorieID, Name) VALUES (1, 'ICE');
INSERT INTO Kategorie (KategorieID, Name) VALUES (2, 'Regional');
INSERT INTO Kategorie (KategorieID, Name) VALUES (3, 'Intercity');
INSERT INTO Kategorie (KategorieID, Name) VALUES (4, 'S-Bahn');

**// Einfügen von Zügen**
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (1, 500, 200, 1);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (2, 300, 100, 2);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (3, 400, 150, 3);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (4, 200, 50, 4);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (5, 600, 250, 1);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (6, 350, 120, 2);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (7, 450, 170, 3);
INSERT INTO Zug (ZugID, Sitzplaetze, Stehplaetze, KategorieID) VALUES (8, 250, 80, 4);

**// Einfügen von Zugverbindungen**
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
```

### 3. SQL Queries:

- **1.1:** Alle Züge auflisten
- **1.2:** Alle Zugverbindungen, die in Berlin starten
- **1.3:** Alle Zugverbindungen, die länger als 3 Stunden dauern
- **1.4:** Alle Verbindungen für den 15. August 2024
- **1.5:** Alle Verbindungen von München nach Berlin

- **2.1:** Die Anzahl der Sitzplätze und Stehplätze für Züge, die in Frankfurt ankommen
- **2.2:** Finde alle Verbindungen der Kategorie 'ICE’
- **2.3:** Die Gesamtzahl der Sitzplätze für alle Züge, die am 16. August 2024 verkehren
- **2.4:** Liste die Anzahl der Verbindungen auf, die pro Tag stattfinden
- **2.5:** Finde die Startorte, von denen die meisten Verbindungen ausgehen, und sortiere sie nach der Anzahl der
  Verbindungen

**Bonus:**

- **3.1:** Finde alle Züge, die an mindestens zwei verschiedenen Tagen im Einsatz sind
- **3.2:** Ermittle die durchschnittliche Dauer aller Verbindungen der Kategorie 'Regional’
- **3.3:** Identifiziere Züge, die in zwei aufeinanderfolgenden Tagen fahren und dabei in den gleichen Städten starten
  und ankommen
- **3.4:** Ermittle die Anzahl der Zugverbindungen, die im Jahr 2024 stattfinden, und gruppiere sie nach Monaten.
- **3.5:** Finde den Tag mit den meisten Verbindungen in einem bestimmten Monat, zum Beispiel August 2024.