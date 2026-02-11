/**
 * 예제 8: 도서 관리 시스템
 * 업캐스팅과 다형성을 활용한 도서 관리 시스템
 */
import java.util.ArrayList;

// 도서 추상 클래스
abstract class Book {
    protected String title;
    protected String author;
    protected String isbn;
    protected double price;
    
    Book(String title, String author, String isbn, double price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }
    
    // 추상 메서드: 각 도서 타입마다 다른 할인율
    abstract double calculateDiscount();
    
    // 최종 가격 계산
    double getFinalPrice() {
        return price - calculateDiscount();
    }
    
    // 공통 메서드: 도서 정보 출력
    void printInfo() {
        System.out.println("제목: " + title + 
                          ", 저자: " + author + 
                          ", 원가: " + String.format("%,.0f원", price) +
                          ", 할인: " + String.format("%,.0f원", calculateDiscount()) +
                          ", 최종가: " + String.format("%,.0f원", getFinalPrice()));
    }
    
    String getTitle() {
        return title;
    }
    
    String getAuthor() {
        return author;
    }
}

// 일반 도서
class RegularBook extends Book {
    private static final double DISCOUNT_RATE = 0.1; // 10% 할인
    
    RegularBook(String title, String author, String isbn, double price) {
        super(title, author, isbn, price);
    }
    
    @Override
    double calculateDiscount() {
        return price * DISCOUNT_RATE;
    }
    
    void read() {
        System.out.println(title + "을(를) 읽습니다.");
    }
}

// 전자책
class EBook extends Book {
    private static final double DISCOUNT_RATE = 0.2; // 20% 할인
    private String fileFormat;
    
    EBook(String title, String author, String isbn, double price, String fileFormat) {
        super(title, author, isbn, price);
        this.fileFormat = fileFormat;
    }
    
    @Override
    double calculateDiscount() {
        return price * DISCOUNT_RATE;
    }
    
    void download() {
        System.out.println(title + "을(를) " + fileFormat + " 형식으로 다운로드합니다.");
    }
}

// 오디오북
class AudioBook extends Book {
    private static final double DISCOUNT_RATE = 0.15; // 15% 할인
    private int duration; // 분 단위
    
    AudioBook(String title, String author, String isbn, double price, int duration) {
        super(title, author, isbn, price);
        this.duration = duration;
    }
    
    @Override
    double calculateDiscount() {
        return price * DISCOUNT_RATE;
    }
    
    void play() {
        System.out.println(title + " 오디오북을 재생합니다. (재생 시간: " + duration + "분)");
    }
}

// 전집 (세트)
class BookSet extends Book {
    private static final double DISCOUNT_RATE = 0.25; // 25% 할인
    private int volumeCount;
    
    BookSet(String title, String author, String isbn, double price, int volumeCount) {
        super(title, author, isbn, price);
        this.volumeCount = volumeCount;
    }
    
    @Override
    double calculateDiscount() {
        return price * DISCOUNT_RATE;
    }
    
    void showVolumes() {
        System.out.println(title + " 전집은 총 " + volumeCount + "권으로 구성되어 있습니다.");
    }
}

public class Example8_BookManagement {
    public static void main(String[] args) {
        System.out.println("=== 실전 예제: 도서 관리 시스템 ===\n");
        
        // 다양한 타입의 도서들을 부모 타입 배열에 저장 (업캐스팅)
        Book[] books = new Book[6];
        books[0] = new RegularBook("자바의 정석", "남궁성", "ISBN001", 30000);
        books[1] = new EBook("이것이 자바다", "신용권", "ISBN002", 25000, "PDF");
        books[2] = new AudioBook("해리포터", "J.K.롤링", "ISBN003", 35000, 1200);
        books[3] = new BookSet("삼국지", "나관중", "ISBN004", 100000, 10);
        books[4] = new RegularBook("코딩 테스트", "이코테", "ISBN005", 28000);
        books[5] = new EBook("리액트 완전정복", "김민준", "ISBN006", 32000, "EPUB");
        
        System.out.println("=== 모든 도서 정보 출력 ===");
        for (Book book : books) {
            book.printInfo();  // 다형성: 각 도서 타입에 맞는 할인 계산
        }
        
        System.out.println("\n=== 전체 도서 총액 계산 ===");
        double totalOriginalPrice = 0;
        double totalFinalPrice = 0;
        for (Book book : books) {
            totalOriginalPrice += book.price;
            totalFinalPrice += book.getFinalPrice();
        }
        System.out.println("원가 합계: " + String.format("%,.0f원", totalOriginalPrice));
        System.out.println("최종가 합계: " + String.format("%,.0f원", totalFinalPrice));
        System.out.println("총 할인액: " + String.format("%,.0f원", totalOriginalPrice - totalFinalPrice));
        
        System.out.println("\n=== 도서 타입별 처리 ===");
        for (Book book : books) {
            if (book instanceof RegularBook) {
                RegularBook rb = (RegularBook) book;
                rb.read();
            } else if (book instanceof EBook) {
                EBook eb = (EBook) book;
                eb.download();
            } else if (book instanceof AudioBook) {
                AudioBook ab = (AudioBook) book;
                ab.play();
            } else if (book instanceof BookSet) {
                BookSet bs = (BookSet) book;
                bs.showVolumes();
            }
        }
        
        System.out.println("\n=== 특정 도서 검색 ===");
        String searchTitle = "자바의 정석";
        Book found = findBook(books, searchTitle);
        if (found != null) {
            System.out.println("검색 결과:");
            found.printInfo();
        } else {
            System.out.println(searchTitle + "을(를) 찾을 수 없습니다.");
        }
        
        System.out.println("\n=== 저자별 도서 검색 ===");
        String searchAuthor = "남궁성";
        ArrayList<Book> authorBooks = findBooksByAuthor(books, searchAuthor);
        if (!authorBooks.isEmpty()) {
            System.out.println(searchAuthor + "의 도서 목록:");
            for (Book book : authorBooks) {
                book.printInfo();
            }
        } else {
            System.out.println(searchAuthor + "의 도서를 찾을 수 없습니다.");
        }
    }
    
    // 업캐스팅을 활용한 공통 메서드: 제목으로 검색
    static Book findBook(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }
    
    // 업캐스팅을 활용한 공통 메서드: 저자로 검색
    static ArrayList<Book> findBooksByAuthor(Book[] books, String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }
}
