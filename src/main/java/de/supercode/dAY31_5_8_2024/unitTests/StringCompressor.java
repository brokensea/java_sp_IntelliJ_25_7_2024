package de.supercode.dAY31_5_8_2024.unitTests;

public class StringCompressor {
    public static String compress(String input) {
        if (input == null || input.length() == 0) {
            throw new IllegalArgumentException("input soll nicht null sein");
        }
        StringBuilder compressed = new StringBuilder();
        int count = 1;
        char prevChar = input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            char currChar = input.charAt(i);
            if (currChar == prevChar) {
                count++;
            } else {
                compressed.append(prevChar);
                if (count > 1) {
                    compressed.append(count);
                }
                prevChar = currChar;
                count = 1;
            }
        }
        //Füge das letzte Zeichen und seine Anzahl hinzu
        compressed.append(prevChar);
        if (count > 1) {
            compressed.append(count);
        }
        String compressedString = compressed.toString();

        // Die komprimierte Zeichenfolge nur zurückgeben, wenn die Länge der komprimierten Zeichenfolge kleiner als die Länge der Originalzeichenfolge ist, andernfalls die Originalzeichenfolge zurückgeben
        return compressedString.length() > input.length() ? input : compressedString;

    }
}
