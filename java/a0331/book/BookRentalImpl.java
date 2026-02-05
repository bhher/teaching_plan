package a0331.book;

public class BookRentalImpl {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        bookService.start();
    }
}
