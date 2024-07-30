package de.supercode.dAY26_29_7_2024.algorithmenDatenstrukturen;

import java.util.Iterator;
import java.util.LinkedList;

public class KollabierteListenOperationen {


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(1);
        System.out.println("Original List: " + list);
        collapseDuplicates(list);
        System.out.println("Collapsed List: " + list);
    }

    public static void collapseDuplicates(LinkedList<Integer> list) {
        // Überprüfen, ob die Liste null ist oder nur ein Element enthält
        if (list == null || list.size() <= 1) {
            return;// Wenn ja, keine weiteren Schritte erforderlich
        }
        Iterator<Integer> iterator = list.iterator();
        // Den ersten Wert der Liste speichern
        Integer previous = iterator.next();
        while (iterator.hasNext()) {
            // Den aktuellen Wert des Iterators holen
            Integer current = iterator.next();
            // Überprüfen, ob der aktuelle Wert gleich dem vorherigen Wert ist
            if (current.equals(previous)) {
                // Wenn ja, aktuellen Wert entfernen (Doppelgänger)
                iterator.remove();
            } else {
                previous = current;
            }
        }

    }
}
