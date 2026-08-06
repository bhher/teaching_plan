package a0108.book;

public class BookMain {
    public static void main(String[] args) {
        // 할인 없음
        Book book1 = new Book("자바의 정석", "남궁성", 30000, 2);
        book1.printBookInfo();

        System.out.println();

        // 할인 + 무료배송
        Book book2 = new Book("이것이 자바다", "신용권", 35000, 1);
        book2.setDiscountRate(0.1);
        book2.printOrderSheet();

        System.out.println();

        // 할인 + 배송비 발생 + 유효성 검사
        Book book3 = new Book("혼자 공부하는 자바", "신용권", 28000, 1);
        book3.setDiscountRate(0.2);
        book3.setDiscountRate(1.5);
        book3.printOrderSheet();
    }
}
