package de.supercode.dAY29_1_8_2024.eventManagementSystemDatenstrukturen;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Teilnehmer {
    private String name;
    private LocalDate geburtsdatum;
    private Set<Rolle> rolles;

    public Teilnehmer(String name, LocalDate geburtsdatum) {
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        rolles = new HashSet<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Set<Rolle> getRolles() {
        return rolles;
    }

    public void setRolles(Set<Rolle> rolles) {
        this.rolles = rolles;
    }

    public void addRolles(Rolle rolle) {
        this.rolles.add(rolle);
    }

    @Override
    public String toString() {
        return "Teilnehmer{" +
                "name='" + name + '\'' +
                ", geburtsdatum=" + geburtsdatum +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Teilnehmer that = (Teilnehmer) object;
        return Objects.equals(name, that.name) && Objects.equals(geburtsdatum, that.geburtsdatum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, geburtsdatum);
    }

}
