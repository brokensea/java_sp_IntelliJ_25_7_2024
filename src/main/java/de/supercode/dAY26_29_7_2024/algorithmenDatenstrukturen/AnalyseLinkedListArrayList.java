package de.supercode.dAY26_29_7_2024.algorithmenDatenstrukturen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AnalyseLinkedListArrayList {

    private static final int ELEMENT_COUNT = 100000; // Anzahl der zu testenden Elemente
    private static final int MID_INDEX = ELEMENT_COUNT / 2; // Mittelwert der Liste

    public static void main(String[] args) {
        // Leistungstests
        System.out.println("Performance Testing Phase:");

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        // Warm-Up Phase
        System.out.println("Warm-Up Phase:");
        System.out.println("ArrayList:");
        warmUp(arrayList);
        System.out.println("LinkedList:");
        warmUp(linkedList);

        // Testen des Hinzufügens von Elementen
        System.out.println("Hinzufügen von Elementen:");
        testAddingElements(new ArrayList<>(), new LinkedList<>());

        // Testen des Hinzufügens von Elementen am Anfang
        System.out.println("Hinzufügen eines Elements am Anfang:");
        testAddFirstElement(new ArrayList<>(), new LinkedList<>());

        // Testen des Entfernens von Elementen aus der Mitte
        System.out.println("Entfernen eines Elements aus der Mitte:");
        testRemoveMiddleElement(new ArrayList<>(), new LinkedList<>());

        // Testen des Zugriffs auf ein bestimmtes Element
        System.out.println("Zugriff auf ein bestimmtes Element:");
        testAccessElement(new ArrayList<>(), new LinkedList<>());

        // Testen des Iterierens durch die Liste
        System.out.println("Iterieren durch die Liste:");
        testIterateList(new ArrayList<>(), new LinkedList<>());

        // Testen des Entfernens des ersten Elements
        System.out.println("Entfernen des ersten Elements:");
        testRemoveFirstElement(new ArrayList<>(), new LinkedList<>());
    }

    // Warm-Up Methode: Füge eine große Anzahl von Elementen zur Liste hinzu
    public static void warmUp(List<Integer> list) {
        long startTime, endTime;
        startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            list.add(i);
        }
        endTime = System.nanoTime();
        System.out.println("Zeit zum Hinzufügen von Elementen: " + (endTime - startTime) + " ns");
        list.clear(); // Liste leeren
    }

    // Testet das Hinzufügen von Elementen zur Liste
    public static void testAddingElements(List<Integer> arrayList, List<Integer> linkedList) {
        long startTime, endTime;

        // ArrayList Elemente hinzufügen
        startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            arrayList.add(i);
        }
        endTime = System.nanoTime();
        System.out.println("ArrayList - Hinzufügen von " + ELEMENT_COUNT + " Elementen: " + (endTime - startTime) + " ns");

        // LinkedList Elemente hinzufügen
        startTime = System.nanoTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            linkedList.add(i);
        }
        endTime = System.nanoTime();
        System.out.println("LinkedList - Hinzufügen von " + ELEMENT_COUNT + " Elementen: " + (endTime - startTime) + " ns");
    }

    // Testet das Hinzufügen eines Elements am Anfang der Liste
    public static void testAddFirstElement(List<Integer> arrayList, List<Integer> linkedList) {
        long startTime, endTime;

        // ArrayList - Hinzufügen am Anfang
        startTime = System.nanoTime();
        arrayList.add(0, -1);
        endTime = System.nanoTime();
        System.out.println("ArrayList - Hinzufügen am Anfang: " + (endTime - startTime) + " ns");

        // LinkedList - Hinzufügen am Anfang
        startTime = System.nanoTime();
        linkedList.add(0, -1);
        endTime = System.nanoTime();
        System.out.println("LinkedList - Hinzufügen am Anfang: " + (endTime - startTime) + " ns");
    }

    // Testet das Entfernen eines Elements aus der Mitte der Liste
    public static void testRemoveMiddleElement(List<Integer> arrayList, List<Integer> linkedList) {
        populateList(arrayList);
        populateList(linkedList);

        long startTime, endTime;

        // ArrayList - Entfernen aus der Mitte
        startTime = System.nanoTime();
        arrayList.remove(MID_INDEX);
        endTime = System.nanoTime();
        System.out.println("ArrayList - Entfernen aus der Mitte: " + (endTime - startTime) + " ns");

        // LinkedList - Entfernen aus der Mitte
        startTime = System.nanoTime();
        linkedList.remove(MID_INDEX);
        endTime = System.nanoTime();
        System.out.println("LinkedList - Entfernen aus der Mitte: " + (endTime - startTime) + " ns");
    }

    // Testet den Zugriff auf ein bestimmtes Element in der Liste
    public static void testAccessElement(List<Integer> arrayList, List<Integer> linkedList) {
        populateList(arrayList);
        populateList(linkedList);

        long startTime, endTime;

        // ArrayList - Zugriff auf mittleres Element
        startTime = System.nanoTime();
        arrayList.get(MID_INDEX);
        endTime = System.nanoTime();
        System.out.println("ArrayList - Zugriff auf mittleres Element: " + (endTime - startTime) + " ns");

        // LinkedList - Zugriff auf mittleres Element
        startTime = System.nanoTime();
        linkedList.get(MID_INDEX);
        endTime = System.nanoTime();
        System.out.println("LinkedList - Zugriff auf mittleres Element: " + (endTime - startTime) + " ns");
    }

    // Testet das Iterieren durch die Liste
    public static void testIterateList(List<Integer> arrayList, List<Integer> linkedList) {
        populateList(arrayList);
        populateList(linkedList);

        long startTime, endTime;

        // ArrayList - Iteration durch die Liste
        startTime = System.nanoTime();
        for (Integer element : arrayList) {
            // Iterationsprozess
        }
        endTime = System.nanoTime();
        System.out.println("ArrayList - Iteration durch die Liste: " + (endTime - startTime) + " ns");

        // LinkedList - Iteration durch die Liste
        startTime = System.nanoTime();
        for (Integer element : linkedList) {
            // Iterationsprozess
        }
        endTime = System.nanoTime();
        System.out.println("LinkedList - Iteration durch die Liste: " + (endTime - startTime) + " ns");
    }

    // Testet das Entfernen des ersten Elements aus der Liste
    public static void testRemoveFirstElement(List<Integer> arrayList, List<Integer> linkedList) {
        populateList(arrayList);
        populateList(linkedList);

        long startTime, endTime;

        // ArrayList - Entfernen des ersten Elements
        startTime = System.nanoTime();
        arrayList.remove(0);
        endTime = System.nanoTime();
        System.out.println("ArrayList - Entfernen des ersten Elements: " + (endTime - startTime) + " ns");

        // LinkedList - Entfernen des ersten Elements
        startTime = System.nanoTime();
        linkedList.remove(0);
        endTime = System.nanoTime();
        System.out.println("LinkedList - Entfernen des ersten Elements: " + (endTime - startTime) + " ns");
    }

    // Füllt die Liste mit einer großen Anzahl von Elementen
    private static void populateList(List<Integer> list) {
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            list.add(i);
        }
    }
}