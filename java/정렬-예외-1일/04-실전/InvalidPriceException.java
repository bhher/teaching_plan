/** 가격이 0 이하일 때 */
public class InvalidPriceException extends RuntimeException {
    public InvalidPriceException(int price) {
        super("잘못된 가격: " + price + "원 (1원 이상이어야 함)");
    }
}
