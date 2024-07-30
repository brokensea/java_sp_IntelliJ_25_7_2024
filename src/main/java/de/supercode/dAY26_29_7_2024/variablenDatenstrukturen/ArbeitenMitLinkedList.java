package de.supercode.dAY26_29_7_2024.variablenDatenstrukturen;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ArbeitenMitLinkedList {


    public static void main(String[] args) {
        List<String> immutableList = List.of("Alice", "Bob", "Charlie", "Diana", "Eve");
        List<String> students = new LinkedList<>(immutableList);

        students.addFirst("Zara");
        students.addLast("Fred");
        System.out.println("erste: " + students.getFirst() + " letzte: " + students.getLast());
        System.out.println("er dritten Position: " + students.get(2));

        students.removeFirst();
//        students.remove(students.size() - 1);
        students.removeLast();
        System.out.println(students);
        students.remove(1);
        System.out.println(students);
//        - **Überprüfen und Suchen**:
//    - Überprüfe, ob der Name "Alice" in der Liste enthalten ist
//    - Überprüfe, ob der Name "Eve" in der Liste enthalten ist
//    - Gib die Position (Index) des Namens "Charlie" in der Liste aus
        System.out.println(students.contains("Alice"));
        System.out.println(students.contains("Eve"));
        System.out.println(students.indexOf("Charlie"));

//        - **Iterieren und Anzeigen**:
//    - Iteriere über alle Elemente der Liste und gebe sie aus
        Iterator<String> iterator = students.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

//        - **Listenoperationen**:
//    - Erstelle eine zweite `LinkedList` namens `newStudents` mit den Namen: "George", "Hannah"
//    - Fügen Sie alle Elemente von `newStudents` zur Liste `students` hinzu
//    - Sortieren Sie die Liste `students` alphabetisch und geben Sie sie aus
        List<String> immutableList2 = List.of("George", "Hannah");
        List<String> newStudents = new LinkedList<>(immutableList2);
        students.addAll(newStudents);
        students.sort(Comparator.naturalOrder());
        System.out.println(students);


        System.out.println(kteElementVonHintenAusgibt(1, students));
    }

    //    - Implementiere eine Methode, die in einer LinkedList das k-te Element von hinten ausgibt.
//    Überprüfe deine Ausgabe, indem du in der Main-Methode einen Beispiel-Aufruf erstellst.
    public static String kteElementVonHintenAusgibt(int k, List<String> list) {
        if (k < 1 || k > list.size()) {
            throw new IndexOutOfBoundsException("OutOfBoundsException");
        }
        return list.get(list.size() - k);
    }
}
