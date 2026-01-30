package a0128.bookEx;

public class Book {
    private int id;           // 도서 ID
    private String title;     // 도서 제목
    private String author;    // 저자명
    private String isbn;      // ISBN
    private double price;     // 가격
    
    // 기본 생성자
    public Book() {
    }
    
    // 전체 필드 생성자
    public Book(int id, String title, String author, String isbn, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }
    
    // Getter 메서드
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public double getPrice() {
        return price;
    }
    
    // Setter 메서드
    public void setId(int id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    // 파일 저장용 문자열
    public String toFileString() {
        return id + "|" + title + "|" + author + "|" + isbn + "|" + price;
    }
    
    // 파일에서 읽은 문자열로 객체 생성
    public static Book fromFileString(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length == 5) {
                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String author = parts[2].trim();
                String isbn = parts[3].trim();
                double price = Double.parseDouble(parts[4].trim());
                return new Book(id, title, author, isbn, price);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "[제목: " + title + ", 저자: " + author + ", ISBN: " + isbn + ", 가격: " + price + "]";
    }
}
