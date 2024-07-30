package de.supercode.dAY27_30_7_2024.oop;

public class GenerischeMethodeErstellen {

    public static <T> void swap(T[] array, int index_1, int index_2) {
        if (array == null || index_1 < 0 || index_2 < 0 || index_1 >= array.length || index_2 >= array.length) {
            throw new IllegalArgumentException("Invalid index or null array.");
        }
        T temp = array[index_1];
        array[index_1] = array[index_2];
        array[index_2] = temp;
    }
}
