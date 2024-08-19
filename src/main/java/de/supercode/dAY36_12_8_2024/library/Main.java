package de.supercode.dAY36_12_8_2024.library;

import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Library library = new Library();

        // Autoren erstellen
        Author author1 = new Author("J.K. Rowling", 1965);
        Author author2 = new Author("George R.R. Martin", 1948);
        Author author3 = new Author("J.R.R. Tolkien", 1892);
        Author author4 = new Author("Agatha Christie", 1890);
        Author author5 = new Author("Stephen King", 1947);

        // Bücher erstellen
        Book book1 = new Book("Harry Potter and the Philosopher's Stone", 1997, 223, List.of(author1));
        Book book2 = new Book("A Game of Thrones", 1996, 835, List.of(author2));
        Book book3 = new Book("The Lord of the Rings", 1954, 1200, List.of(author3));
        Book book4 = new Book("Murder on the Orient Express", 1934, 256, List.of(author4));
        Book book5 = new Book("The Shining", 1977, 447, List.of(author5));
        Book book6 = new Book("Harry Potter and the Chamber of Secrets", 1998, 251, List.of(author1));
        Book book7 = new Book("The Hobbit", 1937, 310, List.of(author3));
        Book book8 = new Book("The Winds of Winter", 2024, 900, List.of(author2)); // Zukunftsbeispiel

        // Bücher zur Bibliothek hinzufügen
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);
        library.addBook(book7);
        library.addBook(book8);

        // Datumformatierer
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        // Ausleihen erstellen
        library.loanBook(book1, dateFormatter.parse("01.06.2023"), dateFormatter.parse("15.06.2023"));
        library.loanBook(book2, dateFormatter.parse("05.07.2023"), dateFormatter.parse("20.07.2023"));
        library.loanBook(book3, dateFormatter.parse("10.08.2023"), dateFormatter.parse("01.09.2023"));
        library.loanBook(book1, dateFormatter.parse("20.08.2023"), dateFormatter.parse("05.09.2023"));
        library.loanBook(book4, dateFormatter.parse("01.09.2023"), dateFormatter.parse("10.09.2023"));
        library.loanBook(book5, dateFormatter.parse("15.09.2023"), dateFormatter.parse("30.09.2023"));
        library.loanBook(book6, dateFormatter.parse("01.10.2023"), dateFormatter.parse("15.10.2023"));
        library.loanBook(book7, dateFormatter.parse("10.10.2023"), dateFormatter.parse("25.10.2023"));
        library.loanBook(book8, dateFormatter.parse("01.11.2023"), dateFormatter.parse("15.11.2023"));
        library.loanBook(book3, dateFormatter.parse("20.11.2023"), dateFormatter.parse("05.12.2023"));


        // 1. Übung: Bücher nach Veröffentlichungsjahr sortiert
        System.out.println("Books sorted by publication year:");
        System.out.println(library.getBooksSortedByPublicationYear());

        // 2. Übung: Autoren nach Geburtsjahr sortiert
        System.out.println("Authors sorted by birth year:");
        System.out.println(library.getAuthorsSortedByBirthYear());

        // 3. Übung: Bücher nach Seitenanzahl filtern
        System.out.println("Books with page count between 200 and 500:");
        System.out.println(library.filterBooksByPageCount(200, 500));

        // 4. Übung: Bücher nach Autor gruppieren
        System.out.println("Books grouped by author:");
        System.out.println(library.groupBooksByAuthor());

        // 5. Übung: Ältesten Autor finden
        System.out.println("Oldest author:");
        System.out.println(library.getOldestAuthor().orElse(null));

        // 6. Übung: Bücher nach Titel-Keyword filtern
        System.out.println("Books containing 'Harry' in the title:");
        System.out.println(library.getBooksByTitleKeyword("Harry"));

        // 7. Übung: Am meisten ausgeliehene Bücher finden
        System.out.println("Most loaned books:");
        System.out.println(library.getMostLoanedBooks());

        // 8. Übung: Anzahl der Ausleihen pro Buch
        System.out.println("Loan count per book:");
        System.out.println(library.getLoanCountPerBook());

        // 9. Übung: Bücher, die von einem bestimmten Autor ausgeliehen wurden
        System.out.println("Books loaned by J.K. Rowling:");
        System.out.println(library.getBooksLoanedByAuthor(author1));

        // 10. Übung: Durchschnittliche Ausleihdauer berechnen
        System.out.println("Average loan duration (in days):");
        System.out.println(library.getAverageLoanDuration());

        // Bonus: 11. Übung: Beliebtesten Autor nach ausgeliehenen Büchern finden
        System.out.println("Most popular author by borrowed books count:");
        System.out.println(library.getMostPopularAuthorByBorrowedBooksCount());
    }
}
