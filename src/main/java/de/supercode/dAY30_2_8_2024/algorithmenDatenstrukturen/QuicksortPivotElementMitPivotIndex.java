package de.supercode.dAY30_2_8_2024.algorithmenDatenstrukturen;

import java.util.Arrays;
import java.util.Random;

import static de.supercode.dAY30_2_8_2024.algorithmenDatenstrukturen.BubbleSort.hugeArray;
import static de.supercode.dAY30_2_8_2024.algorithmenDatenstrukturen.BubbleSort.swap;

public class QuicksortPivotElementMitPivotIndex {

    public static void main(String[] args) {
        int[] array = hugeArray(5);
        Arrays.stream(array).forEach(System.out::println);
        System.out.println();
        quickSort(array, 0, array.length - 1);
        Arrays.stream(array).forEach(System.out::println);
    }


    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
//            int pivotIndex = partitionMittlere(array, low, high);
//            int pivotIndex = partitionRandom(array, low, high);
            int pivotIndex = partitionMedianDrei(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Mittlere Element (Median)
    public static int partitionMittlere(int[] array, int low, int high) {
        int pivotIndex = low + (high - low) / 2;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, high);
        return quickSortBody(array, low, high, pivot);
    }

    // Random
    public static int partitionRandom(int[] array, int low, int high) {
        Random rand = new Random();
        int pivotIndex = low + rand.nextInt(high - low + 1);
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, high);
        swap(array, pivotIndex, high);
        return quickSortBody(array, low, high, pivot);
    }

    //Median aus drei (Median of Three)
    public static int partitionMedianDrei(int[] array, int low, int high) {
        int mid = low + (high - low) / 2;
        int lowVonArray = array[low];
        int midVonArray = array[mid];
        int highVonArray = array[high];

        int pivot;
        if (lowVonArray < midVonArray) {
            if (midVonArray < highVonArray) {
                pivot = midVonArray; // lowVonArray < midVonArray < highVonArray
            } else if (lowVonArray < highVonArray) {
                pivot = highVonArray; // lowVonArray < highVonArray < midVonArray
            } else {
                pivot = lowVonArray; // highVonArray < lowVonArray < midVonArray
            }
        } else {
            if (lowVonArray < highVonArray) {
                pivot = lowVonArray; // highVonArray < lowVonArray < midVonArray
            } else if (midVonArray < highVonArray) {
                pivot = highVonArray; // lowVonArray < highVonArray < midVonArray
            } else {
                pivot = midVonArray; // lowVonArray < midVonArray < highVonArray
            }
        }
        // Finden Sie den Pivot-Index
        int pivotIndex = (pivot == array[low]) ? low : (pivot == array[mid]) ? mid : high;
        swap(array, pivotIndex, high); // Verschieben Sie das pivotIndex an das Ende
        return quickSortBody(array, low, high, pivot);
    }

    private static int quickSortBody(int[] array, int low, int high, int pivot) {
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }
}
