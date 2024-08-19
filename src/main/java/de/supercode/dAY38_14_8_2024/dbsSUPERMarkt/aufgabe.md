# ✅ Aufgabenstellung

### 1. **Tabellen erstellen**

- Erstelle eine Tabelle `Products`, wobei ein Produkt eine eindeutige ID, einen Namen, eine Kategorie (z.B. Obst,
  Gemüse, Getränke), einen Preis und ein Verfallsdatum hat.
- Erstelle eine Tabelle `Categories`, wobei jede Kategorie eine eindeutige ID und einen Namen (z.B. Obst, Gemüse,
  Getränke) hat.

### 2. **Daten einfügen**

- Füge einige Produkte und Kategorien hinzu, z.B. Äpfel, Tomaten, Cola, etc.

### 3. **Daten abfragen**

1. **Einfache Abfragen:**
    - Zeige alle Produkte an
    - Zeige alle Produkte einer bestimmten Kategorie an (z.B. Obst)
    - Zeige alle Produkte an, die teurer als 5 Euro sind
    - Zeige alle Produkte an, deren Verfallsdatum abgelaufen ist

   **Info:**

    ```java
    // Beispiele:
    
    ... WHERE Verfallsdatum < CURDATE() ...
    
    // Differenz zwischen zwei Daten
    DATEDIFF(Verfallsdatum, CURDATE())
    ```

   → `CURDATE()` ist eine SQL-Funktion, die das aktuelle Datum zurückgibt, ohne die Uhrzeit

2. **Erweiterte Abfragen:**
    - **Produkte gruppieren nach Kategorie**
    - **Durchschnittlicher Preis pro Kategorie**
    - **Top 3 Kategorien nach Anzahl der Produkte**
    - **Kategorie mit dem höchsten durchschnittlichen Preis**
    - **Produkte, die teurer als der Durchschnitt ihrer Kategorie sind**
    - **Kategorien mit den meisten abgelaufenen Produkten**
    - **Produkte mit der höchsten Haltbarkeit in ihrer Kategorie**
    - **Durchschnittliche Haltbarkeit (Tage) pro Kategorie**
    - **Durchschnittlicher Preis für Produkte, die nach 2023 ablaufen**
    - **Produkte mit Preis über dem Durchschnitt, sortiert nach Haltbarkeit**
    - **Teuerstes Produkt in jeder Kategorie finden**
    - Produkte anzeigen, deren Preis über dem Durchschnittspreis aller Produkte liegt

**Learn to Learn:**

- **Produkte, die innerhalb der nächsten 7 Tage ablaufen**