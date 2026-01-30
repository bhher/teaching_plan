package a0128.bookEx;

public class BookDTO {
    private String bName;
    private String name;
    private String isbn;
    private double price;
    private int id;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getbName() {
        return bName;
    }
    public void setbName(String bName) {
        this.bName = bName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public BookDTO(){}

    public BookDTO(int id,String bName, String name, String isbn, double price) {
        this.id = id;
        this.bName = bName;
        this.name = name;
        this.isbn = isbn;
        this.price = price;
    }
    @Override
    public String toString() {
        return "[제목: " + bName + ", 저자: " + name + ", ISBN: " + isbn + ", 가격: " + price + "]";
    }
}
