package de.supercode.dAY23_25_7_2024.javaVariablenDatenstrukturen.buchObject;

import java.util.Objects;

public class Buch {
    private final String titel;
    private final String autor;
    private final String isbn;

    // Konstruktor
    public Buch(String titel, String autor, String isbn) {
        this.titel = titel;
        this.autor = autor;
        this.isbn = isbn;
    }

    public String getTitel() {
        return titel;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Buch{" +
                "titel='" + titel + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Buch buch = (Buch) object;
        return Objects.equals(titel, buch.titel) && Objects.equals(autor, buch.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titel, autor);
    }
}
