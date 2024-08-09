# Algorithmus : Bubble Sort

# ✅ Aufgabenstellung

- Wir haben uns zusammen den Code von Bubble Sort erarbeitet
- Schaue dir den Code nochmal genau an und probiere ihn dann, ohne zu schauen, nachzuprogrammieren
- Verändere im Nachhinein den Code so, dass der Algorithmus die Zahlen von Groß zu Klein sortiert

# Algorithmus : Insertion Sort

# ✅ Aufgabenstellung

**Insertion-Sort-Algorithmus:**

- ist ein einfacher Sortieralgorithmus
- durchläuft das Eingabearray schrittweise und fügt Elemente in ihre korrekte Position innerhalb eines sortierten Teils
  des Arrays ein
- im Wesentlichen nimmt der Algorithmus jedes Element nacheinander und fügt es an der richtigen Stelle im bereits
  sortierten Teil des Arrays ein

Schau dir die genaue Funktionsweise von Insertion-Sort und implementiere dann den Algorithmus:

- **Initiales Array**:

    ```java
    [4][7][5][3][9][1][2]
    ```

- **Erster Durchgang (7 einfügen)**:
    - Sortierter Bereich: `[4]`
    - Nächste Zahl: `7`
    - 7 ist größer als 4, daher bleibt sie an ihrer Stelle.

    ```java
    [4][7][5][3][9][1][2]
    ```

- **Zweiter Durchgang (5 einfügen)**:
    - Sortierter Bereich: `[4][7]`
    - Nächste Zahl: `5`
    - 5 ist kleiner als 7 und größer als 4, daher zwischen 4 und 7 einfügen.

    ```java
    [4][5][7][3][9][1][2]
    ```

- **Dritter Durchgang (3 einfügen)**:
    - Sortierter Bereich: `[4][5][7]`
    - Nächste Zahl: `3`
    - 3 ist kleiner als 7, 5 und 4, daher an die erste Stelle setzen.

    ```java
    [3][4][5][7][9][1][2]
    ```

- **Vierter Durchgang (9 einfügen)**:
    - Sortierter Bereich: `[3][4][5][7]`
    - Nächste Zahl: `9`
    - 9 ist größer als alle, daher bleibt sie an ihrer Stelle.

    ```java
    [3][4][5][7][9][1][2]
    ```

- **Fünfter Durchgang (1 einfügen)**:
    - Sortierter Bereich: `[3][4][5][7][9]`
    - Nächste Zahl: `1`
    - 1 ist kleiner als alle, daher an die erste Stelle setzen.

    ```java
    [1][3][4][5][7][9][2]
    ```

- **Sechster Durchgang (2 einfügen)**:
    - Sortierter Bereich: `[1][3][4][5][7][9]`
    - Nächste Zahl: `2`
    - 2 ist kleiner als 3, daher zwischen 1 und 3 einfügen.

    ```java
    [1][2][3][4][5][7][9]
    ```

- **Endgültig sortiertes Array**:

    ```java
    [1][2][3][4][5][7][9]
    ```

# Algorithmus : Quicksort Pivot-Element

# ✅ Aufgabenstellung

Im Unterricht haben wir als Pivot-Element das das letzte bzw. letzte Element des Arrays gewählt. Vorteil davon ist ein
vereinfachter Algorithmus. Was man jedoch auch machen kann, ist die Wahl des Pivot Elements zu optimieren.

**Alternative Strategien für die Auswahl des Pivot-Elements sind z. B.:**

- das mittlere Element
- ein zufälliges Element
- der Median aus drei, fünf oder mehr Elementen

Implementiere alle drei Auswahlmöglichkeiten 

