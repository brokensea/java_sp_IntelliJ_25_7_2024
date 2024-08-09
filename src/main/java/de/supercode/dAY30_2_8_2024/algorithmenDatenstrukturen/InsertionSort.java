package de.supercode.dAY30_2_8_2024.algorithmenDatenstrukturen;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] array = BubbleSort.hugeArray(5);
        Arrays.stream(array).forEach(System.out::println);
        System.out.println();
        insertionSort(array);
        Arrays.stream(array).forEach(System.out::println);
    }

    public static void insertionSort(int[] array) {
        // Durchlaufe das Array ab dem zweiten Element
        for (int indexI = 1; indexI < array.length; indexI++) {
            int key = array[indexI];  // Speichere das aktuelle zu sortierende Element
            int indexJ = indexI - 1;
            // Verschiebe die Elemente im sortierten Teil des Arrays nach rechts,
            // die größer als das aktuelle Element sind
            while (indexJ >= 0 && array[indexJ] > key) {
                array[indexJ + 1] = array[indexJ];
                indexJ = indexJ - 1;
            }
            // Füge das aktuelle Element an der richtigen Stelle ein
            array[indexJ + 1] = key;
        }
    }
}
