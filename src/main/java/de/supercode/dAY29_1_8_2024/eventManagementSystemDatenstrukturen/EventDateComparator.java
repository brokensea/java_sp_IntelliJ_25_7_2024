package de.supercode.dAY29_1_8_2024.eventManagementSystemDatenstrukturen;

import java.util.Comparator;

public class EventDateComparator implements Comparator<Veranstaltung> {
    @Override
    public int compare(Veranstaltung o1, Veranstaltung o2) {
        return o1.getDatum().compareTo(o2.getDatum());
    }
}
