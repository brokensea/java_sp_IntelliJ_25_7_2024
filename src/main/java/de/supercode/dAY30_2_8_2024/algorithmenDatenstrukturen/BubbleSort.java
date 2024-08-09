package de.supercode.dAY30_2_8_2024.algorithmenDatenstrukturen;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] array = hugeArray(5);
        Arrays.stream(array).forEach(System.out::println);
        System.out.println();
        bubbleSortGrossZuKlein(array);
        Arrays.stream(array).forEach(System.out::println);
    }

    public static void bubbleSortGrossZuKlein(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] < array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }


    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] hugeArray(int count) {
        int[] numbers = new int[count];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 65472);
        }
        return numbers;
    }
}
