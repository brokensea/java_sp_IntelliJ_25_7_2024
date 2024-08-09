Streams → Einführung

✅ Aufgabenstellung

Hinweis: Nutze das Internet, um dich über Methoden zu informieren, die wir im Unterricht noch nicht behandelt haben. Die
Fähigkeit zur eigenständigen Recherche ist eine wichtige Kompetenz für Programmierer.

1. Liste von Zahlen filtern
   Gegeben: Eine Liste von ganzen Zahlen.
   Aufgabe: Filter alle geraden Zahlen aus der Liste.

2. Liste von Zeichenketten in Großbuchstaben umwandeln
   Gegeben: Eine Liste von Zeichenketten.
   Aufgabe: Wandele alle Zeichenketten in der Liste in Großbuchstaben um.

3. Summe einer Liste von Zahlen berechnen
   Gegeben: Eine Liste von ganzen Zahlen.
   Aufgabe: Berechne die Summe aller Zahlen in der Liste.

4. Duplikate aus einer Liste entfernen
   Gegeben: Eine Liste von ganzen Zahlen mit Duplikaten.
   Aufgabe: Entferne alle Duplikate aus der Liste.

5. Durchschnitt einer Liste von Zahlen berechnen
   Gegeben: Eine Liste von ganzen Zahlen.
   Aufgabe: Berechne den Durchschnittswert der Zahlen in der Liste.

6. Zeichenketten nach Länge sortieren
   Gegeben: Eine Liste von Zeichenketten.
   Aufgabe: Sortiere die Zeichenketten nach ihrer Länge.

Streams → Flatmap

# ✅  Aufgabenstellung

1. **Liste von Listen zu einer einzigen Liste “flach machen”**

- Gegeben: Eine Liste von Listen von ganzen Zahlen.
- Aufgabe: Verwende `flatMap`, um eine flache Liste zu erstellen, die alle Zahlen enthält.

1. **Zeichenketten in eine Liste von Wörtern aufteilen**

- Gegeben: Eine Liste von Sätzen (Zeichenketten).
- Aufgabe: Verwende `flatMap`, um eine Liste von einzelnen Wörtern zu erstellen.

1. **Filtern und flach machen einer Liste von Listen**

- Gegeben: Eine Liste von Listen von ganzen Zahlen.
- Aufgabe: Filter alle Zahlen, die größer als 5 sind.

Bibliotheksverwaltung → Streams

# ✅ Aufgabenstellung

**Klassendefinitionen**

- **Book**: Repräsentiert ein Buch in der Bibliothek.

    ```java
    public class Book {
        private String title;
        private String author;
        private String genre;
        private int publicationYear;
    
        // Konstruktoren, Getter und Setter
    }
    ```

- **Member**: Repräsentiert ein Mitglied der Bibliothek.

    ```java
    public class Member {
        private String name;
        private int memberId;
        private List<Book> borrowedBooks;
    
        // Konstruktoren, Getter und Setter
    }
    ```

- **Library**: Repräsentiert die Bibliothek und enthält Methoden zur Verwaltung von Büchern und Mitgliedern.

    ```java
    public class Library {
        private List<Book> books;
        private List<Member> members;
    
        // Konstruktoren, Getter und Setter
        // Methoden zum Hinzufügen von Büchern und Mitgliedern
    
    		// Ein Buch an ein Mitglied ausleihen.
        public void borrowBook(int memberId, Book book) {
            Member member = members.stream()
                                   .filter(m -> m.getMemberId() == memberId)
                                   .findFirst()
                                   .orElseThrow(() -> new IllegalArgumentException("Member not found"));
            member.borrowBook(book);
        }
        
        // ...
    
    }
    
    ```

### Schreibe folgende Methoden und verwende ausschließlich Streams, keine for-Schleifen:

- **Ein Buch von einem Mitglied zurückgeben.**
- **Liste aller Bücher eines bestimmten Genres**
- **Liste aller Autoren ohne Duplikate**
- **Liste aller Bücher, die von einem bestimmten Mitglied ausgeliehen wurden**
- **Top 5 der meistgeliehenen Bücher**
- **Durchschnittliches Veröffentlichungsjahr der Bücher**

Bonus: **Gruppierung der Bücher nach Genre**