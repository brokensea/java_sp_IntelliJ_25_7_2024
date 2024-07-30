package de.supercode.dAY23_25_7_2024.javaVariablenDatenstrukturen;

import java.util.HashMap;
import java.util.Map;

public class AnagrammHashMap {
    public static void main(String[] args) {
        // Testbeispiele
        String s1 = "listen";
        String s2 = "silent";

        boolean result = sindAnagramme(s1, s2);
        System.out.println("Sind die Strings Anagramme? " + result);
    }

    // Methode zum Überprüfen, ob zwei Strings Anagramme sind
    public static boolean sindAnagramme(String str1, String str2) {
        // Wenn die Längen der Strings unterschiedlich sind, können sie keine Anagramme sein
        if (str1.length() != str2.length()) {
            return false;
        }
        // Erstellen einer HashMap zum Zählen der Zeichen
        Map<Character, Integer> charCountMap = new HashMap<>();
        // Zählen der Zeichen im ersten String
        for (char c : str1.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        // Überprüfen der Zeichen im zweiten String
        for (char c : str2.toCharArray()) {
            if (!charCountMap.containsKey(c)) {
                return false; // Zeichen kommt im ersten String nicht vor
            }
            int count = charCountMap.get(c);
            if (count == 1) {
                charCountMap.remove(c); // Entfernen des Eintrags, wenn der Zähler auf 0 fällt
            } else {
                charCountMap.put(c, count - 1);
            }
        }
        // Wenn die HashMap leer ist, sind die Strings Anagramme
        return charCountMap.isEmpty();
    }
}

/*
Schritt-für-Schritt-Erklärung
Initialisierung der HashMap:

Erstellen Sie eine HashMap, um die Häufigkeit jedes Zeichens im ersten String zu zählen.
Zählen der Zeichen im ersten String:

Durchlaufen Sie den ersten String und aktualisieren Sie die HashMap mit der Häufigkeit jedes Zeichens.
Abgleichen mit dem zweiten String:

Durchlaufen Sie den zweiten String und reduzieren Sie die Häufigkeit der Zeichen in der HashMap.
Wenn ein Zeichen im zweiten String nicht in der HashMap vorhanden ist oder dessen Häufigkeit bereits auf 0 gefallen ist, sind die Strings keine Anagramme.
Überprüfen, ob alle Zeichen vollständig verarbeitet wurden:

Am Ende sollte die HashMap leer sein (alle Werte sollten 0 sein), was bedeutet, dass alle Zeichen des ersten Strings durch die Zeichen des zweiten Strings ausgeglichen wurden.*/
