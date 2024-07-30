package de.supercode.dAY23_25_7_2024.javaVariablenDatenstrukturen;

import java.util.HashMap;
import java.util.Map;

public class WortBuchstabenfrequenzZaehler {

    public static void main(String[] args) {

        zaehlenHaeufigkeitBuchstabensTest();
        wortHaeufigkeitTest();

    }

    public static void zaehlenHaeufigkeitBuchstabensTest() {
        String text = "Hallo Welt";
        Map<Character, Integer> haeufigkeitMap = zaehlenHaeufigkeitBuchstabens(text);

        // Ausgabe der Häufigkeit jedes Buchstabens
        for (Map.Entry<Character, Integer> eintrag : haeufigkeitMap.entrySet()) {
            System.out.println("Buchstabe: " + eintrag.getKey() + " - Häufigkeit: " + eintrag.getValue());
        }
    }

    public static void wortHaeufigkeitTest() {
        String text = "Hallo Welt! Hallo Welt. Dies ist eine Test Nachricht. Nachricht, die Test enthält.";
        Map<String, Integer> haeufigkeitMap = wortHaeufigkeit(text);

        // Ausgabe der Häufigkeit jedes Wortes
        for (Map.Entry<String, Integer> eintrag : haeufigkeitMap.entrySet()) {
            System.out.println("Wort: " + eintrag.getKey() + " - Häufigkeit: " + eintrag.getValue());
        }

    }

    public static Map<Character, Integer> zaehlenHaeufigkeitBuchstabens(String text) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char buchstabe = text.charAt(i);
            // Wenn der Buchstabe bereits in der Map ist, erhöhe die Häufigkeit
            if (map.containsKey(buchstabe)) {
                map.put(buchstabe, map.get(buchstabe) + 1);
            } else {
                // Ansonsten füge den Buchstaben mit Häufigkeit 1 hinzu
                map.put(buchstabe, 1);
            }
        }
        return map;
    }

    public static Map<String, Integer> wortHaeufigkeit(String text) {
        // HashMap zum Speichern der Wörter und deren Häufigkeit
        HashMap<String, Integer> map = new HashMap<>();
        // Text in Wörter aufteilen
        String[] worte = text.split("\\s+");  // Teilt den Text bei Leerzeichen
        // Durchlaufen jedes Wortes im Array
        for (String wort : worte) {// Entfernen von Satzzeichen am Anfang und Ende des Wortes
            wort = wort.replaceAll("^[^a-zA-Z0-9]+", "");
            wort = wort.replaceAll("[^a-zA-Z0-9]+$", "");

            // Wenn das Wort bereits in der Map ist, erhöhe die Häufigkeit
            if (map.containsKey(wort)) {
                map.put(wort, map.get(wort) + 1);
            } else {
                // Ansonsten füge das Wort mit Häufigkeit 1 hinzu
                map.put(wort, 1);
            }
        }
        return map;
    }
}





