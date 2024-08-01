package de.supercode.dAY28_31_7_20242.treeMap;

import java.util.Comparator;
import java.util.TreeMap;

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
