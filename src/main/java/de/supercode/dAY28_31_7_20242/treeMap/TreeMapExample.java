package de.supercode.dAY28_31_7_20242.treeMap;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {

        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("Alice", "Info1");
        treeMap.put("Bob", "Info2");
        treeMap.put("Charlie", "Info3");

        // Überprüfen, ob die TreeMap leer ist
        System.out.println("Ist die TreeMap leer? " + treeMap.isEmpty());
        // Anzeigen der Elemente der TreeMap
        System.out.println("Elemente der TreeMap: " + treeMap);
        // Überprüfen, ob ein bestimmter Schlüssel in der TreeMap enthalten ist
        System.out.println(treeMap.containsKey("Bob"));
        // Entfernen eines Schlüssel-Wert-Paares aus der TreeMap
        treeMap.remove("Bob");
        System.out.println("Elemente der TreeMap nach dem Entfernen von 'Bob': " + treeMap);
        // Anzeigen der Größe der TreeMap
        System.out.println("Größe der TreeMap: " + treeMap.size());
        // Iterieren über die Einträge der TreeMap
        System.out.println("Iterieren über die Einträge der TreeMap:");
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
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

//        红黑树
        // Erstellen einer Kopie der ersten TreeMap, um die gemeinsamen Schlüssel zu behalten
        TreeMap<String, String> commonKeysTreeMap = new TreeMap<>(treeMap);
        commonKeysTreeMap.keySet().retainAll(treeMap2.keySet());
        // Anzeigen der gemeinsamen Schlüssel und deren Werte
        System.out.println("Gemeinsame Schlüssel in beiden TreeMaps: " + commonKeysTreeMap);
    }
}
