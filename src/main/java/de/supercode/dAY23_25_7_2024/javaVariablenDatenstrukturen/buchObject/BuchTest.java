package de.supercode.dAY23_25_7_2024.javaVariablenDatenstrukturen.buchObject;


import java.util.HashMap;
import java.util.Map;

public class BuchTest {
    public static void main(String[] args) {
        // Buch Object
        Buch harryPotter1a = new Buch("Harry Potter und der Stein der Weisen", "J.K. Rowling", "978-3-551-55656-6");
        Buch harryPotter1b = new Buch("Harry Potter und der Stein der Weisen", "J.K. Rowling", "978-3-551-55657-3");
        Buch harryPotter2 = new Buch("Harry Potter und die Kammer des Schreckens", "J.K. Rowling", "978-3-551-55658-0");
        Buch harryPotter1c = new Buch("Harry Potter und der Stein der Weisen", "J.K. Rowling", "978-3-551-55656-6");
        Buch harryPotter3 = new Buch("Harry Potter und der Gefangene von Askaban", "J.K. Rowling", "978-3-551-55659-7");


        // Erstellen einer HashMap zur Speicherung der Buchobjekte und ihrer Anzahl
        Map<Buch, Integer> buchMap = new HashMap<>();


        // Methode zum Hinzufügen oder Erhöhen der Anzahl eines Buches
        addBuch(buchMap, harryPotter1a);
        addBuch(buchMap, harryPotter1b);
        addBuch(buchMap, harryPotter2);
        addBuch(buchMap, harryPotter1c); // Erhöht die Anzahl, weil Titel und Autor gleich sind
        addBuch(buchMap, harryPotter3);

        // Abrufen der Anzahl eines bestimmten Buches
        System.out.println("Anzahl von 'Harry Potter und der Stein der Weisen': " + getAnzahl(buchMap, harryPotter1a));

        // Überprüfen der Existenz eines Buches
        System.out.println("Existiert 'Harry Potter und die Kammer des Schreckens'? " + buchMap.containsKey(harryPotter2));

        // Entfernen eines Buches aus der HashMap
        removeBuch(buchMap, harryPotter1a); // Entfernt das Buch unabhängig von der Anzahl

        // Bonus: Entfernen eines Buches, indem die Anzahl reduziert wird
        reduceOrRemoveBuch(buchMap, harryPotter1b);

        // Iterieren über die HashMap und Ausgabe der Schlüssel-Wert-Paare
        System.out.println("Aktueller Bestand:");
        for (Map.Entry<Buch, Integer> entry : buchMap.entrySet()) {
            System.out.println(entry.getKey() + " - Anzahl: " + entry.getValue());
        }
    }


    // Methode zum Hinzufügen oder Erhöhen der Anzahl eines Buches in der HashMap
    private static void addBuch(Map<Buch, Integer> map, Buch buch) {
        map.put(buch, map.getOrDefault(buch, 0) + 1);
    }

    // Methode zum Abrufen der Anzahl eines bestimmten Buches
    private static int getAnzahl(Map<Buch, Integer> map, Buch buch) {
        return map.getOrDefault(buch, 0);
    }

    // Methode zum Entfernen eines Buches aus der HashMap
    private static void removeBuch(Map<Buch, Integer> map, Buch buch) {
        map.remove(buch);
    }

    // Bonus: Methode zum Reduzieren oder Entfernen eines Buches
    private static void reduceOrRemoveBuch(Map<Buch, Integer> map, Buch buch) {
        if (map.containsKey(buch)) {
            int count = map.get(buch);
            if (count > 1) {
                map.put(buch, count - 1); // Reduziere die Anzahl
            } else {
                map.remove(buch); // Entferne das Buch, wenn keine Exemplare mehr vorhanden sind
            }
        }
    }

   /* // Methode zum Hinzufügen oder Erhöhen der Anzahl eines Buches in der HashMap
    private static void addBuch(Map<Buch, Integer> map, Buch buch) {
        if (map.containsKey(buch)) {
            map.put(buch, map.get(buch) + 1); // Erhöhe die Anzahl des Buches
        } else {
            map.put(buch, 1); // Füge das Buch neu hinzu, Anzahl ist 1
        }
    }*/

}
