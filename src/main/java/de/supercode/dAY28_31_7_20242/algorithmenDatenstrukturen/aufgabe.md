1. Entfernen von Duplikaten aus einer Liste

# Aufgabenstellung

Schreibe ein Programm, das eine Liste von Zahlen einliest und alle Duplikate entfernt, indem es ein Set verwendet.

2. Set

# Aufgabenstellung

Nimm dir Zeit und schaue dir folgende Informationen über die Datenstruktur Set an. Probiere daraufhin selber alle
Funktionen aus.

- **Set = Menge**
- Abstrakte Datenstruktur, die keine doppelten Elemente erlaubt
- Typische Implementierungen sind `HashSet`, `LinkedHashSet`, und `TreeSet`
  import java.util.*;

public class Main {
public static void main(String[] args) {

        // Erstellen eines neuen HashSets
        Set<Integer> set = new HashSet<>();

        // Elemente zum Set hinzufügen
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(5); // Duplikate werden nicht hinzugefügt

        // Überprüfen, ob das Set leer ist
        System.out.println("Ist das Set leer? " + set.isEmpty());

        // Anzeigen der Elemente des Sets
        System.out.println("Elemente im Set: " + set);

        // Überprüfen, ob ein bestimmtes Element im Set enthalten ist
        System.out.println("Enthält das Set die Zahl 10? " + set.contains(10));

        // Entfernen eines Elements aus dem Set
        set.remove(10);
        System.out.println("Enthält das Set die Zahl 10 nach dem Entfernen? " + set.contains(10));

        // Anzeigen der Größe des Sets
        System.out.println("Größe des Sets: " + set.size());

        // Iterieren über die Elemente des Sets
        System.out.println("Iterieren über die Elemente des Sets:");
        for (Integer num : set) {
            System.out.println(num);
        }

        // Löschen des Sets
        set.clear();
        System.out.println("Ist das Set leer nach dem Löschen? " + set.isEmpty());
        
        
        // Arbeiten mit einem zweiten Set und der retainAll-Methode
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
        
        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);
        
        // Behalte nur die gemeinsamen Elemente in set1
        set1.retainAll(set2);
    }

}

3. Überprüfen, ob eine Liste eindeutige Elemente enthält

# Aufgabenstellung:

Schreibe ein Programm, das überprüft, ob eine Liste nur eindeutige Elemente enthält.

- **Kleiner Tipp:**

  Set

4. TreeMap

# Aufgabenstellung

Nimm dir Zeit und schaue dir folgende Informationen über die Datenstruktur TreeMap an. Probiere daraufhin selber alle
Funktionen aus.
TreeMap = sortierte Map
Eine Map, die ihre Schlüssel in natürlicher Reihenfolge (oder durch einen Comparator) sortiert
import java.util.*;

public class TreeMapExample {
public static void main(String[] args) {

        // Erstellen einer TreeMap mit natürlicher Ordnung
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("Alice", "Info1");
        treeMap.put("Bob", "Info2");
        treeMap.put("Charlie", "Info3");
        
        // Überprüfen, ob die TreeMap leer ist
        System.out.println("Ist die TreeMap leer? " + treeMap.isEmpty());
        
        // Anzeigen der Elemente der TreeMap
        System.out.println("Elemente der TreeMap: " + treeMap);
        
        // Überprüfen, ob ein bestimmter Schlüssel in der TreeMap enthalten ist
        System.out.println("Enthält die TreeMap den Schlüssel 'Bob'? " + treeMap.containsKey("Bob"));
        
        // Entfernen eines Schlüssel-Wert-Paares aus der TreeMap
        treeMap.remove("Bob");
        System.out.println("Elemente der TreeMap nach dem Entfernen von 'Bob': " + treeMap);
        
        // Anzeigen der Größe der TreeMap
        System.out.println("Größe der TreeMap: " + treeMap.size());
        
        // Iterieren über die Einträge der TreeMap
        System.out.println("Iterieren über die Einträge der TreeMap:");
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        // Löschen der TreeMap
        treeMap.clear();
        System.out.println("Ist die TreeMap leer nach dem Löschen? " + treeMap.isEmpty());
        
        // Erstellen einer zweiten TreeMap mit weiteren Schlüssel-Wert-Paaren
        TreeMap<String, String> treeMap2 = new TreeMap<>();
        treeMap2.put("David", "Info4");
        treeMap2.put("Eve", "Info5");
        treeMap2.put("Charlie", "Info6");
        
        // Anzeigen der Elemente der zweiten TreeMap
        System.out.println("Elemente der zweiten TreeMap: " + treeMap2);
        
        // Behalten nur die gemeinsamen Schlüssel zwischen den beiden TreeMaps
        treeMap.put("Alice", "Info1");
        treeMap.put("Charlie", "Info3");
        
        System.out.println("Elemente der ersten TreeMap vor retainAll: " + treeMap);
        
        // Erstellen einer Kopie der ersten TreeMap, um die gemeinsamen Schlüssel zu behalten
        TreeMap<String, String> commonKeysTreeMap = new TreeMap<>(treeMap);
        commonKeysTreeMap.keySet().retainAll(treeMap2.keySet());
        
        // Anzeigen der gemeinsamen Schlüssel und deren Werte
        System.out.println("Gemeinsame Schlüssel in beiden TreeMaps: " + commonKeysTreeMap);
    }

}

​

1. Natürliche Ordnung
   In diesem Beispiel wird eine TreeMap von Integer zu String erstellt, die die Schlüssel automatisch in aufsteigender
   Reihenfolge sortiert.
   TreeMap sortiert die Schlüssel in natürlicher Ordnung, da Integer die ComparableSchnittstelle implementiert.
   import java.util.*;

public class TreeMapNaturalOrder {
public static void main(String[] args) {

        // Erstellen einer TreeMap mit natürlicher Ordnung
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(15, "Fünfzehn");
        treeMap.put(10, "Zehn");
        treeMap.put(20, "Zwanzig");
        treeMap.put(5, "Fünf");
        
        // Anzeigen der Elemente der TreeMap in natürlicher Ordnung
        System.out.println("TreeMap in natürlicher Ordnung: " + treeMap);
    }

}

​

2. Benutzerdefinierte Sortierung mit Comparator
   Hier wird eine TreeMap mit einem Comparator erstellt, der die Reihenfolge der Schlüssel umkehrt.
   Der Comparator.reverseOrder() wird verwendet, um die natürliche Reihenfolge umzukehren.
   import java.util.*;

public class TreeMapCustomOrder {
public static void main(String[] args) {

        // Erstellen einer TreeMap mit benutzerdefiniertem Comparator (umgekehrte Reihenfolge der Schlüssel)
        TreeMap<Integer, String> treeMap = new TreeMap<>(Comparator.reverseOrder());
        treeMap.put(15, "Fünfzehn");
        treeMap.put(10, "Zehn");
        treeMap.put(20, "Zwanzig");
        treeMap.put(5, "Fünf");
        
        // Anzeigen der Elemente der TreeMap in benutzerdefinierter Reihenfolge
        System.out.println("TreeMap in benutzerdefinierter Reihenfolge: " + treeMap);
    }

}

​

3. Sortierung von benutzerdefinierten Objekten als Schlüssel
   Eine TreeMap von PersonObjekten wird erstellt, wobei die natürliche Ordnung durch Implementierung der
   ComparableSchnittstelle definiert wird.
   Ein weiteres TreeMap wird mit einem Comparator erstellt, der die PersonObjekte nach ihrem Namen sortiert.
   Das Comparator.comparing()Lambda wird verwendet, um die Objekte nach ihrem Namen zu sortieren.
   Hier ein Beispiel, wie man eine TreeMap mit benutzerdefinierten Objekten als Schlüssel sortiert, indem man die
   Comparable-Schnittstelle implementiert und/oder einen Comparator verwendet:
   import java.util.*;

// Benutzerdefinierte Klasse, die Comparable implementiert
class Person implements Comparable<Person> {
String name;
int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int compareTo(Person other) {
        // Sortieren nach Alter
        return Integer.compare(this.age, other.age);
    }
    
    @Override
    public String toString() {
        return name + " (" + age + ")";
    }

}

public class TreeMapCustomObjects {
public static void main(String[] args) {

        // Erstellen einer TreeMap mit natürlicher Ordnung der Person-Objekte (nach Alter)
        TreeMap<Person, String> treeMap = new TreeMap<>();
        treeMap.put(new Person("Alice", 30), "Alice's Info");
        treeMap.put(new Person("Bob", 25), "Bob's Info");
        treeMap.put(new Person("Charlie", 35), "Charlie's Info");
        
        // Anzeigen der Elemente der TreeMap in natürlicher Ordnung
        System.out.println("TreeMap nach Alter sortiert: " + treeMap);
        
        // Erstellen einer TreeMap mit benutzerdefiniertem Comparator (nach Name)
        TreeMap<Person, String> treeMapByName = new TreeMap<>(Comparator.comparing(p -> p.name));
        // TreeMap<Person, String> peopleByAgeDesc = new TreeMap<>(Comparator.comparingInt(Person::getAge).reversed());
        
        treeMapByName.put(new Person("Alice", 30), "Alice's Info");
        treeMapByName.put(new Person("Bob", 25), "Bob's Info");
        treeMapByName.put(new Person("Charlie", 35), "Charlie's Info");
        
        // Anzeigen der Elemente der TreeMap nach Namen sortiert
        System.out.println("TreeMap nach Namen sortiert: " + treeMapByName);
    }

}
