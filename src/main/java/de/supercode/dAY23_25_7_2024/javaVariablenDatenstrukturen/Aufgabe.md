Zwei Arrays auf Gleichheit prüfen

JavaVariablen und Datenstrukturen

# Aufgabenstellung

Überprüfe, ob zwei Arrays von Ganzzahlen die gleichen Elemente in derselben Häufigkeit enthalten, unabhängig von der
Reihenfolge.

Wort- und Buchstabenfrequenz Zähler

# Aufgabenstellung

Erstelle zwei Methoden:

- a) eine, die die Häufigkeit jedes Buchstabens in einem String zählt
- b) eine, die die Häufigkeit jedes Wortes in einem gegebenen Text zählt

# Equals - HashCode - HashMap

# ✅ Aufgabenstellung:

1. **Klasse Buch**

- Erstelle eine Klasse `Buch` mit den folgenden Attributen: `titel` (String), `autor` (String) und `isbn` (String). Die
  ISBN-Nummer des Buches wird zur eindeutigen Identifikation eines Buchexemplars verwendet
- Überschreibe die `toString`Methode, um eine lesbare Darstellung des Buches zu geben
- Überschreibe die `equals`Methode, um zwei `Buch`Objekte als gleich zu betrachten, wenn sie den gleichen Titel und den
  gleichen Autor haben
- Überschreibe die `hashCode`Methode, um einen konsistenten Hashcode für `Buch`Objekte zu erzeugen

1. **Erstellen und Verwenden der Buch-Objekte**

- Erstelle mehrere `Buch`Objekte, einige davon mit unterschiedlichen ISBNs, aber gleichen Titeln und Autoren, um
  unterschiedliche Ausgaben zu simulieren
- Verwende die `equals`Methode, um die Gleichheit von Büchern zu testen
- *fühlt euch frei, diesen Code zu übernehmen oder denkt euch selber coole Bücher aus (@Andreas → “Wann ist
  Wochenende ?”, “Wann Wie Wo werde ich Millionär ?”* 😄*)*

    ```java
    Buch harryPotter1a = new Buch("Harry Potter und der Stein der Weisen", "J.K. Rowling", "978-3-551-55656-6");
    Buch harryPotter1b = new Buch("Harry Potter und der Stein der Weisen", "J.K. Rowling", "978-3-551-55657-3");
    Buch harryPotter2 = new Buch("Harry Potter und die Kammer des Schreckens", "J.K. Rowling", "978-3-551-55658-0");
    Buch harryPotter1c = new Buch("Harry Potter und der Stein der Weisen", "J.K. Rowling", "978-3-551-55656-6");
    Buch harryPotter3 = new Buch("Harry Potter und der Gefangene von Askaban", "J.K. Rowling", "978-3-551-55659-7");
    ```


1. **Verwenden einer HashMap:**

- **Erstelle eine HashMap**: die `Buch`Objekte als Schlüssel und `Integer` als Werte speichert. Der Wert in der HashMap
  stellt die Anzahl der Exemplare des jeweiligen Buches dar.
- **Einfügen von Büchern**: Füge `Buch`Objekte in die HashMap ein.

  **Bonus:** wenn ein doppeltes Buch (mit gleicher Titel und Autor, aber unterschiedlicher ISBN) hinzugefügt wird, soll
  der Wert (Anzahl der Exemplare) automatisch erhöht werden.

- **Abrufen der Anzahl eines bestimmten Buches**: Rufe die Anzahl der Exemplare eines bestimmten Buches ab.
- **Überprüfen der Existenz eines Buches**: Überprüfe, ob ein bestimmtes Buch in der HashMap vorhanden ist.
- **Entfernen eines Buches**: Entferne ein Buch aus der HashMap unabhängig davon, wieviele Exemplare von dem Buch
  existieren

  **Bonus:** entferne ein Buch aus der HashMap, verringere dabei die Anzahl an Exemplaren, wenn es kein Exemplar mehr
  gibt, soll das gesamte Buch aus der HashMap verschwinden

- **Iterieren über die HashMap**: Iteriere über die HashMap und gebe alle Schlüssel-Wert-Paare aus.

# Bonus: Anagramm Prüfer - HashMap

# ✅ Aufgabenstellung

Überprüfe, ob zwei gegebene Strings Anagramme voneinander sind (d.h., sie enthalten dieselben Zeichen in derselben
Häufigkeit, aber möglicherweise in unterschiedlicher Reihenfolge). Benutzte zur Überprüfung diesmal eine HashMap.

