/** 상품 — 기본 정렬: 가격 오름차순 (Comparable) */
public class Product implements Comparable<Product> {
    String name;
    int price;

    public Product(String name, int price) {
        if (price <= 0) {
            throw new InvalidPriceException(price);
        }
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Product o) {
        return this.price - o.price;
    }

    @Override
    public String toString() {
        return name + " (" + price + "원)";
    }
}
