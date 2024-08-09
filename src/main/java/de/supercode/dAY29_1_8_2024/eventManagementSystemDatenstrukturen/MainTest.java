package de.supercode.dAY29_1_8_2024.eventManagementSystemDatenstrukturen;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MainTest {
    public static void main(String[] args) {
//        testFunktion();
//        testComparator();
        testRoleComparator();

    }

    public static void testFunktion() {
        EventManager manager = new EventManager();

        Veranstaltung techConf = new Veranstaltung("Tech Conference", LocalDate.of(2024, 8, 1), 18);
        Veranstaltung musicFest = new Veranstaltung("Music Festival", LocalDate.of(2024, 8, 1), 16);

        manager.addEvent(techConf);
        manager.addEvent(musicFest);

        Teilnehmer alice = new Teilnehmer("Alice", LocalDate.of(1995, 6, 10));
        Teilnehmer bob = new Teilnehmer("Bob", LocalDate.of(2005, 12, 1));

        Teilnehmer teilnehmer2 = new Teilnehmer("teilnehmer2", LocalDate.of(1990, 6, 10));
        Teilnehmer teilnehmer3 = new Teilnehmer("teilnehmer3", LocalDate.of(1990, 6, 10));

        manager.addEventTeilnehmer("Tech Conference", alice, Rolle.SPEAKER);
        manager.addEventTeilnehmer("Music Festival", bob, Rolle.ZUSCHAUER);

        manager.addEventTeilnehmer("Tech Conference", teilnehmer2, Rolle.SPEAKER);
        manager.addEventTeilnehmer("Tech Conference", teilnehmer3, Rolle.SPEAKER);

        manager.getTeilnehmerByEvent("Tech Conference");

        manager.removeEventTeilnehmer("Tech Conference", "Alice");

        System.out.println("Events on 2024-08-01: " + manager.getEventsByDate(LocalDate.of(2024, 8, 1)));
    }

    public static void testComparator() {
        Veranstaltung event1 = new Veranstaltung("Tech Conference", LocalDate.of(2024, 8, 1), 18);
        Veranstaltung event2 = new Veranstaltung("Music Festival", LocalDate.of(2024, 7, 15), 16);
        Veranstaltung event3 = new Veranstaltung("Art Expo", LocalDate.of(2024, 8, 10), 12);

        List<Veranstaltung> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        events.add(event3);


        Collections.sort(events, new EventDateComparator());

        for (Veranstaltung event : events) {
            System.out.println(event);
        }
    }

    public static void testRoleComparator() {
        Teilnehmer alice = new Teilnehmer("Alice", LocalDate.of(1995, 6, 10));
        Teilnehmer bob = new Teilnehmer("Bob", LocalDate.of(2005, 12, 1));
        Teilnehmer charlie = new Teilnehmer("Charlie", LocalDate.of(1990, 3, 20));

        alice.addRolles(Rolle.SPEAKER);
        bob.addRolles(Rolle.ZUSCHAUER);
        charlie.addRolles(Rolle.ORGANIZER);

        List<Teilnehmer> teilnehmerList = new ArrayList<>();
        teilnehmerList.add(alice);
        teilnehmerList.add(bob);
        teilnehmerList.add(charlie);

        Collections.sort(teilnehmerList, new RoleComparator());

        for (Teilnehmer teilnehmer : teilnehmerList) {
            System.out.println(teilnehmer + " - Höchste Rolle: " + new RoleComparator().getHighestRole(teilnehmer.getRolles()));
        }
    }
}


