package a0813.product;

/**
 * 상품 정보 클래스
 * 파일 저장 형식: 상품코드|상품명|가격|재고
 */
public class Product {
    private String id;
    private String name;
    private int price;
    private int stock;

    public Product(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "상품코드: " + id
                + ", 상품명: " + name
                + ", 가격: " + price + "원"
                + ", 재고: " + stock + "개";
    }

    // 파일 저장용 (파이프 구분)
    public String toFileString() {
        return id + "|" + name + "|" + price + "|" + stock;
    }

    // 파일 한 줄 → Product 객체
    public static Product fromFileString(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length != 4) {
                return null;
            }
            String id = parts[0].trim();
            String name = parts[1].trim();
            int price = Integer.parseInt(parts[2].trim());
            int stock = Integer.parseInt(parts[3].trim());
            return new Product(id, name, price, stock);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
