# ✅ Aufgabenstellung

Erweitere das bestehende Quiz-System, um die `Quiz`-Klasse und die `User`-Klasse zu integrieren. Das System soll es
Benutzern ermöglichen, ein Quiz zu machen, bei dem sie Punkte sammeln können. Die `Quiz`-Klasse speichert eine Liste von
Fragen, und die `User`-Klasse verwaltet die Punktzahl eines Benutzers.

- **Erstelle die `User`Klasse, die folgende Attribute und Methoden enthalten soll:**
    - Ein `username` (String) zur Identifikation des Benutzers.
    - Ein `score` (int) zur Speicherung der Punktzahl des Benutzers.
    - Einen Konstruktor, der den Benutzernamen initialisiert und die Punktzahl auf 0 setzt.
    - Eine Methode `getUsername()`, um den Benutzernamen abzurufen.
    - Eine Methode `getScore()`, um die aktuelle Punktzahl abzurufen.
    - Eine Methode `incrementScore()`, um die Punktzahl um 1 zu erhöhen.

- **Erstelle die `Quiz`Klasse, die folgende Attribute und Methoden enthalten:**
    - Eine private `List<Question>` zur Speicherung der Fragen des Quiz.
    - Eine Methode `addQuestion(Question question)`, um eine Frage zum Quiz hinzuzufügen.
    - Eine Methode `askQuestions(User user)`, die die Fragen nacheinander stellt und die vom Benutzer gegebenen
      Antworten überprüft. Die Methode soll die Punktzahl des Benutzers basierend auf den korrekten Antworten
      aktualisieren und die Gesamtanzahl der richtigen Antworten zurückgeben.

    ```java
    public class Main {
        public static void main(String[] args) {
            // Erstellen eines Quiz
            Quiz quiz = new Quiz();
    
            // Erstellen von Fragen
            String description1 = "Was ist die Hauptstadt von Luxemburg?";
            String[] choices1 = {"Luxemburg", "Angola", "Berlin", "NRW"};
            String[] correctChoices1 = {"Luxemburg"};
            Question question1 = new Question(description1, choices1, correctChoices1);
    
            String description2 = "Welches Land hat die größte Bevölkerung?";
            String[] choices2 = {"China", "Indien", "USA", "Brasilien"};
            String[] correctChoices2 = {"China"};
            Question question2 = new Question(description2, choices2, correctChoices2);
            
            String description3 = "Welches dieser Länder liegen in Asien?";
            String[] choices3 = {"China", "Indien", "USA", "Brasilien"};
            String[] correctChoices3 = {"China", "Indien"};
            Question question3 = new Question(description3, choices3, correctChoices3);
    
            // Hinzufügen von Fragen zum Quiz
            quiz.addQuestion(question1);
            quiz.addQuestion(question2);
            quiz.addQuestion(question3);
    
            // Erstellen eines Benutzers
            User user = new User("Alice");
    
            // Durchführen des Quiz
            int correctAnswers = quiz.askQuestions(user);
    
            // Ausgabe der Ergebnisse
            System.out.println("Quiz abgeschlossen!");
            System.out.println("Benutzer: " + user.getUsername());
            System.out.println("Erzielte Punkte: " + user.getScore());
            System.out.println("Anzahl der richtigen Antworten: " + correctAnswers);
        }
    }
    ```

    ```
    Was ist die Hauptstadt von Luxemburg?
    1: Luxemburg
    2: Angola
    3: Berlin
    4: NRW
    Bitte wählen Sie Ihre Antworten (geben Sie die Nummern durch Kommas getrennt ein):
    1
    Ihre Antworten: Luxemburg
    Correct!
    
    Welches Land hat die größte Bevölkerung?
    1: China
    2: Indien
    3: USA
    4: Brasilien
    Bitte wählen Sie Ihre Antworten (geben Sie die Nummern durch Kommas getrennt ein):
    2
    Ihre Antworten: China
    False!
    
    Welches dieser Länder liegen in Asien?
    1: China
    2: Indien
    3: USA
    4: Brasilien
    Bitte wählen Sie Ihre Antworten (geben Sie die Nummern durch Kommas getrennt ein):
    1, 2
    Ihre Antworten: China, Indien
    Correct!
    
    Quiz abgeschlossen!
    Benutzer: Alice
    Erzielte Punkte: 2
    Anzahl der richtigen Antworten: 2 von 3
    ```

- **Teste die `Quiz`Klasse und die `User`Klasse**:
    - Fragen korrekt zu einem Quiz hinzugefügt werden.
    - Der Benutzer die Fragen beantworten kann und seine Punktzahl entsprechend aktualisiert wird.
    - Die Methoden `getUsername()`, `getScore()` und `incrementScore()` korrekt funktionieren.
    - Die `askQuestions()`Methode die Antworten des Benutzers korrekt bewertet und die Punktzahl entsprechend
      aktualisiert.