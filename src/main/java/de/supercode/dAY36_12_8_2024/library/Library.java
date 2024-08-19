package de.supercode.dAY36_12_8_2024.library;


import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

class Library {
    private List<Book> books;
    private List<Loan> loans;

    public Library() {
        this.books = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void loanBook(Book book, Date startDate, Date endDate) {
        loans.add(new Loan(book, startDate, endDate));
    }

    // 1. Übung
    public List<Book> getBooksSortedByPublicationYear() {
        return books.stream() //Stream<Book>
                .sorted(Comparator
                        .comparing(Book::getPublicationYear)//Stream<Book>
                )
                .collect(Collectors.toList()); //List<Book>
    }

    // 2. Übung
    public List<Author> getAuthorsSortedByBirthYear() {
        return books.stream()
                .flatMap(book -> book.getAuthors().stream())
                .collect(Collectors.toSet())
                .stream().sorted(Comparator.comparing(Author::getBirthYear))
                .collect(Collectors.toList());
    }

    // 3. Übung
    public List<Book> filterBooksByPageCount(int minPages, int maxPages) {
        return books.stream()
                .filter(book -> book.getPages() >= minPages && book.getPages() <= maxPages)
                .toList();
    }


    // 4. Übung

    public Map<Author, List<Book>> groupBooksByAuthor() {
        return books.stream()
                .flatMap(book -> book.getAuthors()
                        .stream()
                        .map(author -> new AbstractMap.SimpleEntry<>(author, book)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(
                                Map.Entry::getValue,
                                Collectors.toList())
                ));
    }


    // 5. Übung
    public Optional<Author> getOldestAuthor() {
        return books.stream()
                .flatMap(book -> book.getAuthors().stream())
                .min(Comparator
                        .comparingInt(Author::getBirthYear));
    }


    // 6. Übung
    public List<Book> getBooksByTitleKeyword(String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword))
                .toList();
    }


    // 7. Übung
    public List<Book> getMostLoanedBooks() {
        // 计算每本书的借阅次数
        Map<Book, Long> bookLoanCounts
                = loans.stream()
                .map(Loan::getBook)
                .collect(Collectors.groupingBy(book -> book,
                        Collectors.counting()));
        // 找到最高的借阅次数
        long maxLoanCount = bookLoanCounts.values()
                .stream().max(Comparator.comparingLong(value -> value)).
                orElse(0L);
        // 找到所有借阅次数等于最高次数的书籍
        return bookLoanCounts.entrySet().stream()
                .filter(bookLongEntry -> bookLongEntry.getValue() == maxLoanCount)
                .map(Map.Entry::getKey)
                .toList();
    }


    // 8. Übung
    public Map<Book, Long> getLoanCountPerBook() {
        return loans.stream()
                .map(Loan::getBook)
                .collect(Collectors.groupingBy(book -> book,
                        Collectors.counting()));
    }


    // 9. Übung
    public List<Book> getBooksLoanedByAuthor(Author author) {
        return loans.stream()
                .map(Loan::getBook)
                .filter(book -> book.getAuthors().contains(author))
                .toList();
    }


    // 10. Übung
    public double getAverageLoanDuration() {
        return loans.stream()
                .filter(loan -> loan.getStartDate() != null && loan.getEndDate() != null)
                .mapToDouble(loan -> {
                    LocalDate start = loan.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate end = loan.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    return ChronoUnit.DAYS.between(start, end);
                })
                .average()
                .orElse(0.0);
    }


    // Bonus: 11. Übung
    public String getMostPopularAuthorByBorrowedBooksCount() {
        Map<Author, Integer> bookBorrowByAuthor =
                loans.stream()
                        .flatMap(loan -> loan.getBook().getAuthors()
                                .stream()
                                .map(author -> new AbstractMap.SimpleEntry<>(
                                        author, loan.getBook()
                                )))
                        .collect(Collectors.groupingBy(Map.Entry::getKey,
                                Collectors.summingInt(number -> 1)
                        ));
        return bookBorrowByAuthor.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .map(Author::getName)
                .orElse("No authors found");
    }


}

/*
为什么不能直接用.collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                                Collectors.toList())))而一定要用 Collectors.mapping作downstream

 Java 流的 Collectors.groupingBy 的使用以及 Collectors.mapping 的作用。在你提到的代码中，Collectors.mapping 的使用确实是为了确保正确地将每本书收集到对应的作者列表中。让我们详细解释为什么在这种情况下需要使用 Collectors.mapping 而不能直接使用 Collectors.toList()。

为什么不能直接用 Collectors.toList()？
分组的键值问题：

在 groupingBy 中，分组的键是作者（Map.Entry::getKey），而值是书籍（Map.Entry::getValue）。Collectors.toList() 直接用于 groupingBy 不能正确地处理这种情况，因为它试图直接将整个 Map.Entry 对象收集到列表中。
不正确的数据结构：

如果你直接使用 Collectors.toList()，你会得到一个包含 Map.Entry 对象的列表，而不是你期望的书籍列表。这不是你想要的结果，因为你需要按作者将书籍分组，而不是按 Map.Entry 对象。
使用 Collectors.mapping 的原因
Collectors.mapping 是一个中间收集器，可以在 groupingBy 的下游操作中转换流中的元素。在你的情况中，Collectors.mapping(Map.Entry::getValue, Collectors.toList()) 用于从 Map.Entry 对象中提取书籍，并将这些书籍收集到一个 List 中。
代码解释
以下是详细的代码解释：

java
Code kopieren
import java.util.List;
import java.util.Map;
import java.util.AbstractMap;
import java.util.stream.Collectors;

public Map<Author, List<Book>> groupBooksByAuthor() {
    return books.stream()
            .flatMap(book -> book.getAuthors()
                    .stream()
                    .map(author -> new AbstractMap.SimpleEntry<>(author, book)))
            .collect(Collectors.groupingBy(
                    Map.Entry::getKey, // 按作者（Map.Entry 的键）分组
                    Collectors.mapping(
                            Map.Entry::getValue, // 提取 Map.Entry 的值（书籍）
                            Collectors.toList()) // 将书籍收集到列表中
            ));
}
flatMap(book -> book.getAuthors().stream().map(author -> new AbstractMap.SimpleEntry<>(author, book)))：

将每本书的作者列表展平为流，并为每个作者和书籍创建一个 Map.Entry 对象。
Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList()))：

按作者分组（Map.Entry::getKey）。
使用 Collectors.mapping(Map.Entry::getValue, Collectors.toList()) 从每个 Map.Entry 中提取书籍，并将这些书籍收集到一个 List 中。
总结
Collectors.mapping 用于从 Map.Entry 对象中提取需要的数据（书籍），然后使用 Collectors.toList() 将这些数据收集到列表中。
Collectors.toList() 不能直接用于 groupingBy 的下游，因为它不会正确处理 Map.Entry 对象，结果会是包含 Map.Entry 对象的列表，而不是你想要的书籍列表。
使用 Collectors.mapping 是为了确保在分组后，正确地将每本书籍按作者分组到相应的列表中。*/
