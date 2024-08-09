Unit Test - Min/Max

# Aufgabenstellung

Hier siehst du zwei Methoden zum Berechnen des Maximums und Minimums.

Schreibe Unit-Tests für beide Methoden.

```java
public class MathUtils {
    public int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public int min(int a, int b) {
        return (a < b) ? a : b;
    }
}
```

Und im gleichen Schwung lernen wir noch etwas neues dazu:

**Ternäre Operator**

- wird verwendet, um eine einfache if-else-Bedingung in einer einzigen Zeile zu schreiben
- hat die folgende allgemeine Form:
-

(condition) ? valueIfTrue : valueIfFalse;

Konto Klasse Testing

# Aufgabenstellung

Du erinnerst dich bestimmt an deine Konto-Klasse, die du vor einiger Zeit erstellt hast. Wenn nicht, auch nicht schlimm
🧠 🤭

### **Zur Erinnerung**

Wir haben eine Java Klasse Konto, die die grundlegenden Funktionen eines Bankkontos simuliert. Ein Konto kann
Geldbeträge einzahlen, abheben und den aktuellen Kontostand abfragen.

**Konto-Klasse**

Erstelle eine Klasse `Konto` mit den folgenden Anforderungen:

- **Attribute**:
    - `kontonummer`: Ein `String`, der die Kontonummer des Kontos speichert.
    - `saldo`: Ein `double`, der den aktuellen Kontostand speichert.
- **Konstruktor**:
    - `Konto(String kontonummer, double anfangssaldo)`: Initialisiert die Kontonummer und den Anfangssaldo.
- **Methoden**:
    - `public void einzahlen(double betrag)`: Erhöht den Saldo um den Betrag. Der Betrag muss positiv sein, andernfalls
      wird eine `IllegalArgumentException` geworfen.
    - `public void abheben(double betrag)`: Verringert den Saldo um den Betrag. Der Betrag muss positiv sein und darf
      den aktuellen Saldo nicht überschreiten, andernfalls wird eine `IllegalArgumentException` geworfen.
    - `public double getSaldo()`: Gibt den aktuellen Saldo zurück.
    - `public String getKontonummer()`: Gibt die Kontonummer zurück.
    - `public void ueberweisen(Konto zielKonto, double betrag)`: Diese Methode zieht den angegebenen Betrag vom
      aktuellen Konto ab und fügt ihn dem Zielkonto hinzu. Wenn das aktuelle Konto nicht genügend Saldo hat, soll
      eine `IllegalArgumentException` geworfen werden.

**Testklasse**

Erstelle eine Testklasse `KontoTest` mit den folgenden Anforderungen:

- Teste die Methode `einzahlen`:
    - Teste, ob der Saldo nach dem Einzahlen korrekt ist.
    - Teste, ob eine `IllegalArgumentException` geworfen wird, wenn ein negativer Betrag eingezahlt wird.
- Teste die Methode `abheben`:
    - Teste, ob der Saldo nach dem Abheben korrekt ist.
    - Teste, ob eine `IllegalArgumentException` geworfen wird, wenn ein Betrag abgehoben wird, der den aktuellen Saldo
      überschreitet.
    - Teste, ob eine `IllegalArgumentException` geworfen wird, wenn ein negativer Betrag abgehoben wird.
- Teste die Methode `ueberweisen`:
    - Teste, ob der Saldo des Quellkontos und Zielkontos nach der Überweisung korrekt ist.
    - Teste, ob eine `IllegalArgumentException` geworfen wird, wenn ein Betrag überwiesen wird, der den aktuellen Saldo
      des Quellkontos überschreitet.
    - Teste, ob eine `IllegalArgumentException` geworfen wird, wenn ein negativer Betrag überwiesen wird.
    - Teste, ob eine `IllegalArgumentException` geworfen wird, wenn das Zielkonto null ist.
- Teste die Getter-Methoden `getSaldo` und `getKontonummer`.

String Compressor

# Aufgabenstellung

In dieser Aufgabe sollst du eine Methode implementieren, die einen String nach dem Prinzip der Lauflängenkodierung (
Run-Length Encoding) komprimiert. Diese Methode ersetzt wiederholte Zeichenfolgen durch das Zeichen und die Anzahl der
Wiederholungen:

**Beispiel:**

- Input: `"aaabbbcccaaa"`
- Output: `"a3b3c3a3"`

- Input: `"aabcccccaaa"`
- Output: `"a2bc5a3"`

**StringCompressor-Klasse:**

Erstelle eine Klasse `StringCompressor` mit der folgender Methode:

- `public static String compress(String input)`: Diese Methode nimmt einen String als Eingabe und gibt die komprimierte
  Version des Strings zurück
- wenn die komprimierte Version nicht kürzer ist als der Originalstring, gebe den Originalstring zurück

**Testklasse:**

Erstelle eine Testklasse `StringCompressorTest`, um die Methode `compress` zu testen.


