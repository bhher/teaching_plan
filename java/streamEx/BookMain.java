package a0401.streamEx;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookMain {
    public static void main(String[] args) {
        Author jkRowling = new Author("J.K. Rowling", "UK");
        Author georgeOrwell = new Author("George Orwell", "UK");
        Author harukiMurakami = new Author("Haruki Murakami", "Japan");
        Author stephenKing = new Author("Stephen King", "USA");
        Author leoTolstoy = new Author("Leo Tolstoy", "Russia");
        
        List<Book> books = Arrays.asList(
            new Book(jkRowling, 1997, 15000, "Harry Potter"),
            new Book(georgeOrwell, 1949, 12000, "1984"),
            new Book(harukiMurakami, 2002, 18000, "Kafka on the Shore"),
            new Book(stephenKing, 1977, 20000, "The Shining"),
            new Book(jkRowling, 1998, 15000, "Harry Potter 2"),
            new Book(georgeOrwell, 1945, 11000, "Animal Farm"),
            new Book(harukiMurakami, 2013, 19000, "Colorless Tsukuru"),
            new Book(stephenKing, 1986, 22000, "It"),
            new Book(leoTolstoy, 1869, 25000, "War and Peace")
        );
        
        System.out.println("=== 문제 1: 2000년 이후 출판된 도서를 가격 오름차순 정렬 ===");
        practice1(books);
        
        System.out.println("\n=== 문제 2: 모든 국가를 중복 없이 나열 ===");
        practice2(books);
        
        System.out.println("\n=== 문제 3: 영국 출신 저자의 도서를 제목순 정렬 ===");
        practice3(books);
        
        System.out.println("\n=== 문제 4: 일본 출신 저자가 있는지 확인 ===");
        practice4(books);
        
        System.out.println("\n=== 문제 5: 미국 출신 저자의 도서 가격 출력 ===");
        practice5(books);
        
        System.out.println("\n=== 문제 6: 모든 저자 이름을 알파벳 순 정렬 ===");
        practice6(books);
        
        System.out.println("\n=== 문제 7: 가장 비싼 도서 찾기 ===");
        practice7(books);
        
        System.out.println("\n=== 문제 8: 가장 저렴한 도서의 가격 구하기 ===");
        practice8(books);
    }
    
    // 문제 1: 2000년 이후 출판된 모든 도서를 찾아 가격 오름차순으로 정렬
    private static void practice1(List<Book> books) {
        List<Book> result = books.stream()
            .filter(book -> book.getYear() >= 2000)
            .sorted(Comparator.comparing(Book::getPrice))
            .collect(Collectors.toList());
        System.out.println(result);
    }
    
    // 문제 2: 도서가 출판된 모든 국가를 중복 없이 나열
    private static void practice2(List<Book> books) {
        List<String> result = books.stream()
            .map(book -> book.getAuthor().getCountry())
            .distinct()
            .collect(Collectors.toList());
        System.out.println(result);
    }
    
    // 문제 3: 영국(UK) 출신 저자의 모든 도서를 찾아 제목순으로 정렬
    private static void practice3(List<Book> books) {
        List<Book> result = books.stream()
            .filter(book -> "UK".equals(book.getAuthor().getCountry()))
            .sorted(Comparator.comparing(Book::getTitle))
            .collect(Collectors.toList());
        System.out.println(result);
    }
    
    // 문제 4: 일본(Japan) 출신 저자가 있는지 확인
    private static void practice4(List<Book> books) {
        boolean result = books.stream()
            .anyMatch(book -> "Japan".equals(book.getAuthor().getCountry()));
        System.out.println(result);
    }
    
    // 문제 5: 미국(USA) 출신 저자의 모든 도서 가격 출력
    private static void practice5(List<Book> books) {
        List<Integer> result = books.stream()
            .filter(book -> "USA".equals(book.getAuthor().getCountry()))
            .map(Book::getPrice)
            .collect(Collectors.toList());
        System.out.println(result);
    }
    
    // 문제 6: 모든 저자의 이름을 알파벳 순으로 정렬
    private static void practice6(List<Book> books) {
        List<String> result = books.stream()
            .map(book -> book.getAuthor().getName())
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        System.out.println(result);
    }
    
    // 문제 7: 가장 비싼 도서 찾기
    private static void practice7(List<Book> books) {
        Optional<Book> result = books.stream()
            .max(Comparator.comparing(Book::getPrice));
        result.ifPresent(System.out::println);
    }
    
    // 문제 8: 가장 저렴한 도서의 가격 구하기
    private static void practice8(List<Book> books) {
        Optional<Book> minBook = books.stream()
            .min(Comparator.comparing(Book::getPrice));
        minBook.map(Book::getPrice)
            .ifPresent(System.out::println);
    }
}
