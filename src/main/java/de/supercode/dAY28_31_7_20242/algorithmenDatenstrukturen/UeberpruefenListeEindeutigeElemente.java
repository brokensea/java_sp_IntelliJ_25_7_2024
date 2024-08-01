package de.supercode.dAY28_31_7_20242.algorithmenDatenstrukturen;

import java.util.*;

public class UeberpruefenListeEindeutigeElemente {

    public static void main(String[] args) {
        // test
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 3);

        System.out.println("List 1 hat nur eindeutige Elemente: " + ueberpruefenListeEindeutigeElemente(list1));
        System.out.println("List 2 hat nur eindeutige Elemente: " + ueberpruefenListeEindeutigeElemente(list2));
    }

    public static <T> boolean ueberpruefenListeEindeutigeElemente(List<T> list) {
        Set<T> set = new HashSet<>(list);
        if (set.size() == list.size()) {
            return true;
        } else {
            return false;
        }
    }
}
