package de.supercode.dAY29_1_8_2024.eventManagementSystemDatenstrukturen;

import java.util.Comparator;
import java.util.Set;

public class RoleComparator implements Comparator<Teilnehmer> {

    @Override
    public int compare(Teilnehmer t1, Teilnehmer t2) {
        // Hole die Rollen der beiden Teilnehmer
        Set<Rolle> roles1 = t1.getRolles(); // Methode, die die Rollen des ersten Teilnehmers zurückgibt
        Set<Rolle> roles2 = t2.getRolles(); // Methode, die die Rollen des zweiten Teilnehmers zurückgibt

        // Bestimme die höchste Rolle für beide Teilnehmer
        Rolle highestRole1 = getHighestRole(roles1);
        Rolle highestRole2 = getHighestRole(roles2);

        // Vergleiche die höchsten Rollen der beiden Teilnehmer
        return Integer.compare(highestRole1.getRank(), highestRole2.getRank());
    }

    // Hilfsmethode, um die höchste Rolle aus einer Rolle-Sammlung zu ermitteln
    public Rolle getHighestRole(Set<Rolle> roles) {
        // Suche die Rolle mit dem höchsten Rang (Ranking)
        return roles.stream()
                .min(Comparator.comparingInt(Rolle::getRank)) // Vergleiche Rollen basierend auf ihrem Rang
                .orElse(Rolle.VOLUNTEER); // Wenn keine Rollen vorhanden sind, gib die niedrigste Rolle als Standardwert zurück
    }
}
