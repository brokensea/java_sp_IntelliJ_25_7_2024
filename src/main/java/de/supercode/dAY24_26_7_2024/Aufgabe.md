# Aufgabenstellung

Gegeben ist ein String `s`, finde die Länge des längsten Substrings ohne sich wiederholende Zeichen.

**Beispiel 1:**

Eingabe: `s = "abcabcbb"`

Ausgabe: `3`

Erklärung: Die Antwort ist "abc", mit der Länge von 3.

**Beispiel 2:**

Eingabe: `s = "bbbbb"`

Ausgabe: `1`

Erklärung: Die Antwort ist "b", mit der Länge von 1.

**Beispiel 3:**

Eingabe: `s = "pwwkew"`

Ausgabe: `3`

Erklärung: Die Antwort ist "wke", mit der Länge von 3.

# ✅ Aufgabenstellung

Implementiere die Funktion `myAtoi(String s)`, die einen String in eine 32-Bit vorzeichenbehaftete Ganzzahl konvertiert.

Der Algorithmus für `myAtoi(String s)` ist wie folgt:

1. **Whitespace:** Ignoriere alle führenden Leerzeichen (" ").
2. **Vorzeichen:** Bestimme das Vorzeichen, indem du prüfst, ob das nächste Zeichen '-' oder '+' ist. Wenn kein
   Vorzeichen vorhanden ist, wird davon ausgegangen, dass die Zahl positiv ist.
3. **Konvertierung:** Lese die Ganzzahl, indem du führende Nullen überspringst, bis ein Nicht-Ziffer-Zeichen oder das
   Ende des Strings erreicht wird. Wenn keine Ziffern gelesen wurden, ist das Ergebnis 0.
4. Gib die Ganzzahl als Endergebnis zurück.

# ✅ Aufgabenstellung

Gegeben ist eine vorzeichenbehaftete 32-Bit-Ganzzahl `x`. Schreibe eine Methode, die die Ziffern von `x` umkehrt und das
Ergebnis zurückgibt. Falls das Umkehren der Ziffern den Wertebereich einer vorzeichenbehafteten 32-Bit-Ganzzahl
überschreitet, soll die Methode `0` zurückgeben.

Die vorzeichenbehaftete 32-Bit-Ganzzahl hat den Wertebereich von `-2^31` bis `2^31 - 1` (d.h., von -2.147.483.648 bis
2.147.483.647).

**Beispiele:**

1. **Eingabe:** `x = 123`

   **Ausgabe:** `321`

2. **Eingabe:** `x = -123`

   **Ausgabe:** `-321`

3. **Eingabe:** `x = 120`

   **Ausgabe:** `21`

**Hinweis:**

- Du kannst keine 64-Bit-Ganzzahlen (vorzeichenbehaftet oder vorzeichenlos) verwenden.
- Die Methode sollte die Zahl umkehren, ohne dass es zu einem Überlauf kommt.