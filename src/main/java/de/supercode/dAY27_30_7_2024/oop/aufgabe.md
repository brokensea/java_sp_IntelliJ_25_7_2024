Generische Klasse erstellen

# Aufgabenstellung

Erstelle eine generische Klasse `Box`, die ein einzelnes Objekt eines beliebigen Typs enthält.

Generische Methode erstellen

# Aufgabenstellung

Erstelle eine generische Methode `swap`, die zwei Elemente eines Arrays vertauscht.

Stack
Aufgabenstellung

Selbststudium: nimm dir Zeit und schau dir folgende Informationen über den Stack an. Probiere daraufhin selber alle
Funktionen aus.
Stack = Stapel
abstrakte Datenstruktur nach dem Prinzip Last In, First Out (LIFO)
d.h. das zuletzt hinzugefügte Element wird als erstes entfernt
public class Main {
public static void main(String[] args) {

        // Erstellen eines neuen Stacks
        Stack<Integer> stack = new Stack<>();

        // Elemente zum Stack hinzufügen
        stack.push(5);
        stack.push(10);
        stack.push(15);

        // Überprüfen, ob der Stack leer ist
        System.out.println("Ist der Stack leer? " + stack.isEmpty());

        // Anzeigen des obersten Elements des Stacks, ohne es zu entfernen
        System.out.println("Oberstes Element des Stacks: " + stack.peek());

        // Entfernen und Rückgabe des obersten Elements des Stacks
        int poppedElement = stack.pop();
        System.out.println("Entferntes Element: " + poppedElement);

        // Anzeigen der Größe des Stacks
        System.out.println("Größe des Stacks: " + stack.size());

        // Löschen des Stacks
        stack.clear();
        System.out.println("Ist der Stack leer nach dem Löschen? " + stack.isEmpty());
    }

}

Generischen Stack implementieren

✅ Aufgabenstellung

Erstelle eine generische Klasse MyOwnStack<T>, die die grundlegenden Stack-Operationen (Push, Pop, Peek, isEmpty, Size)
unterstützt. Implementier diese Klasse und teste ihre Funktionalität mit verschiedenen Datentypen.
Achtung: Du kannst für die Aufgabe gerne folgenden Code ergänzen oder dir erstmal komplett alleine Gedanken dazu machen.
Der Code ist nur eine Leitlinie, selber programmieren wirst du so oder so 😉 also keine Scheu davor, dir ein :
Code:
public class MyOwnStack<T> {
private List<T> elements = new ArrayList<>();

    public void push(T item) {
        // ...
    }

    public T pop() {
        // ...
    }

    public T peek() {
        // ...
    }

    public boolean isEmpty() {
        // ...
    }

    public int size() {
        // ...
    }

    public void clear() {
        // ...
    }

}



