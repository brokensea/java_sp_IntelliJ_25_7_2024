package de.supercode.dAY33_7_8_2024.stream.bibliotheksverwaltung;

import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addmember(Member member) {
        members.add(member);
    }


    // finden Member mit ID
    public Member findenMemberMitID(int memberId) {
        return members.stream()
                .filter(m -> m.getMemberId() == memberId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }

    // Ein Buch an ein Mitglied ausleihen.
    public void borrowBook(int memberId, Book book) {
        Member member = this.findenMemberMitID(memberId);
        member.borrowBook(book);
        book.incrementBorrowCount();
    }

    //- **Ein Buch von einem Mitglied zurückgeben.**
    public void giveBackBook(int memberId, Book book) {
        Member member = this.findenMemberMitID(memberId);
        member.giveBackBook(book);
    }

    //- **Liste aller Bücher eines bestimmten Genres**
    public List<Book> findenBuecherMitGenre(String genre) {
        return this.books.stream()
//                .filter(book -> book.getGenre() == genre)
                .filter(book -> Objects.equals(book.getGenre(), genre))
                .toList();
    }

    //- **Liste aller Autoren ohne Duplikate**
    public List<String> ListeAutorenOhneDuplikate() {
        return books.stream().map(Book::getAuthor)
                .collect(Collectors.toSet())
                .stream()
                .toList();
//        Collectors.toSet() 是一种方便的方式来收集流中的元素到一个Set中，默认使用HashSet。
//        如果想使用特定类型的Set（例如，LinkedHashSet或TreeSet），
//        可以使用Collectors.toCollection()并提供一个集合工厂。
//         Set<String> set = items.stream()
//                               .collect(Collectors.toCollection(LinkedHashSet::new));

    }

    //- **Liste aller Bücher, die von einem bestimmten Mitglied ausgeliehen wurden**
    public List<Book> listeBuechervonEinemMitglied(Member member) {
        return members.stream()
                .filter(m -> m.getMemberId() == member.getMemberId())
                .findFirst()
                .map(Member::getBorrowedBooks)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }
   /* public List<Book> listeBuechervonEinemMitglied(Member member) {
        return members.stream().filter(m -> m.getMemberId() == member.getMemberId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Member not found"))
                .getBorrowedBooks();
    }*/

    //- **Top 5 der meistgeliehenen Bücher**
    public List<Book> top5MeistgelieheneBuecher() {
        return books.stream()
                .sorted(Comparator.comparingInt(Book::getBorrowCount).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }
    //wenn ein Book kann gleizetig von Viel Member ausleihen:
   /* public void printMostFiveBorrowedBooks() {
        System.out.println("Top 5 Most Borrowed Books:");
        members.stream()
                .map(Member::getBorrowedBooks)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(book -> book, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Book, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(b -> System.out.println("\t" + b.getKey() + ", Number Times Borrowed: " + b.getValue()));
        System.out.println("*************************************************************");

    }*/

  /*  public void topFiveBorrowedBooks() {
        members.stream()
                .flatMap(member -> member.getBorrowedBooks().stream())
                .collect(Collectors.toMap(b -> b, e -> 1, Integer::sum))
                .entrySet().stream()
                .sorted(Map.Entry.<Book, Integer>comparingByValue().reversed())
                .limit(5)
                .forEach(System.out::println);
    }*/

    //- **Durchschnittliches Veröffentlichungsjahr der Bücher**
    public int durchschnittlichesVeroeffentlichungsjahr() {
        return (int) books.stream()
                .mapToInt(Book::getPublicationYear)
                .average()
                .orElse(0.0);
    }

    // - **Gruppierung der Bücher nach Genre.**
    public Map<String, List<Book>> gruppierungDerBuecherNachGenre() {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getGenre));
    }


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
