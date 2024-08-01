package de.supercode.dAY28_31_7_20242.treeMap;

import java.util.TreeMap;

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
