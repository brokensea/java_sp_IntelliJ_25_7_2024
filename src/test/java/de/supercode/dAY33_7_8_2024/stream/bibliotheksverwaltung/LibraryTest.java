package de.supercode.dAY33_7_8_2024.stream.bibliotheksverwaltung;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private Member member1;
    private Member member2;

    @BeforeEach
    public void setUp() {
        library = new Library();
        book1 = new Book("Title1", "Author1", "Genre1", 2000);
        book2 = new Book("Title2", "Author2", "Genre2", 2010);
        member1 = new Member("Member1", 1);
        member2 = new Member("Member2", 2);

        library.addBook(book1);
        library.addBook(book2);
        library.addmember(member1);
        library.addmember(member2);
    }

    @Test
    public void testAddBook() {
        Book newBook = new Book("Title3", "Author3", "Genre3", 2020);
        library.addBook(newBook);
        assertTrue(library.getBooks().contains(newBook));
    }

    @Test
    public void testAddMember() {
        Member newMember = new Member("Member3", 3);
        library.addmember(newMember);
        assertTrue(library.getMembers().contains(newMember));
    }

    @Test
    public void testFindenMemberMitID() {
        Member foundMember = library.findenMemberMitID(1);
        assertEquals(member1, foundMember);
    }

    @Test
    public void testBorrowBook() {
        library.borrowBook(1, book1);
        assertTrue(member1.getBorrowedBooks().contains(book1));
        assertEquals(1, book1.getBorrowCount());
    }

    @Test
    public void testGiveBackBook() {
        library.borrowBook(1, book1);
        library.giveBackBook(1, book1);
        assertFalse(member1.getBorrowedBooks().contains(book1));
    }

    @Test
    public void testFindenBuecherMitGenre() {
        List<Book> genre1Books = library.findenBuecherMitGenre("Genre1");
        assertTrue(genre1Books.contains(book1));
        assertFalse(genre1Books.contains(book2));
    }

    @Test
    public void testListeAutorenOhneDuplikate() {
        List<String> authors = library.ListeAutorenOhneDuplikate();
        assertTrue(authors.contains("Author1"));
        assertTrue(authors.contains("Author2"));
    }

    @Test
    public void testListeBuechervonEinemMitglied() {
        library.borrowBook(1, book1);
        List<Book> borrowedBooks = library.listeBuechervonEinemMitglied(member1);
        assertTrue(borrowedBooks.contains(book1));
    }

    @Test
    public void testTop5MeistgelieheneBuecher() {
        library.borrowBook(1, book1);
        library.borrowBook(2, book1);
        library.borrowBook(1, book2);
        List<Book> topBooks = library.top5MeistgelieheneBuecher();
        assertEquals(2, topBooks.size());
        assertEquals(book1, topBooks.get(0));
        assertEquals(book2, topBooks.get(1));
    }

    @Test
    public void testDurchschnittlichesVeroeffentlichungsjahr() {
        int averageYear = library.durchschnittlichesVeroeffentlichungsjahr();
        assertEquals((2000 + 2010) / 2, averageYear);
    }

    @Test
    public void testGruppierungDerBuecherNachGenre() {
        library.addBook(new Book("Title3", "Author3", "Genre1", 2020));
        Map<String, List<Book>> groupedBooks = library.gruppierungDerBuecherNachGenre();
        assertTrue(groupedBooks.containsKey("Genre1"));
        assertTrue(groupedBooks.containsKey("Genre2"));
        assertEquals(2, groupedBooks.get("Genre1").size());
        assertEquals(1, groupedBooks.get("Genre2").size());
    }
}