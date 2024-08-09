package de.supercode.dAY29_1_8_2024.eventManagementSystemDatenstrukturen;

import java.time.LocalDate;
import java.util.*;

public class Veranstaltung {
    private String name;
    private LocalDate datum;
    private int altersfreigabe;
    private Map<String, Teilnehmer> teilnehmers;

    public Veranstaltung(String name, LocalDate datum, int altersfreigabe) {
        this.name = name;
        this.datum = datum;
        this.altersfreigabe = altersfreigabe;
        this.teilnehmers = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getAltersfreigabe() {
        return altersfreigabe;
    }

    public void setAltersfreigabe(int altersfreigabe) {
        this.altersfreigabe = altersfreigabe;
    }

    public Map<String, Teilnehmer> getTeilnehmers() {
        return teilnehmers;
    }

    public void setTeilnehmers(Map<String, Teilnehmer> teilnehmers) {
        this.teilnehmers = teilnehmers;
    }

    public void addTeilnehmer(Teilnehmer teilnehmer) {
        teilnehmers.put(teilnehmer.getName(), teilnehmer);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Veranstaltung that = (Veranstaltung) object;
        return altersfreigabe == that.altersfreigabe && Objects.equals(name, that.name) && Objects.equals(datum, that.datum) && Objects.equals(teilnehmers, that.teilnehmers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, datum, altersfreigabe, teilnehmers);
    }

    @Override
    public String toString() {
        return "Veranstaltung{" +
                "name='" + name + '\'' +
                ", datum=" + datum +
                ", altersfreigabe=" + altersfreigabe +
                ", teilnehmers=" + teilnehmers +
                '}';
    }
}
