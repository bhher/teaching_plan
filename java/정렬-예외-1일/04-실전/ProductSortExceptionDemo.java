import java.util.ArrayList;
import java.util.Collections;

/**
 * 실전: 정렬(Comparable/Comparator) + 사용자 정의 예외
 */
public class ProductSortExceptionDemo {
    public static void main(String[] args) {
        ArrayList<Product> list = new ArrayList<>();

        System.out.println("=== 상품 등록 ===");
        addSafe(list, "마우스", 15000);
        addSafe(list, "키보드", 45000);
        addSafe(list, "모니터", 200000);
        addSafe(list, "잘못된상품", -100);  // 예외 → 등록 안 됨
        addSafe(list, "USB", 8000);

        System.out.println("\n등록된 상품: " + list.size() + "개");

        System.out.println("\n=== 가격 오름차순 (Comparable) ===");
        Collections.sort(list);
        print(list);

        System.out.println("\n=== 이름순 (Comparator 람다) ===");
        Collections.sort(list, (a, b) -> a.name.compareTo(b.name));
        print(list);

        System.out.println("\n=== 가격 내림차순 ===");
        Collections.sort(list, (a, b) -> b.price - a.price);
        print(list);
    }

    static void addSafe(ArrayList<Product> list, String name, int price) {
        try {
            list.add(new Product(name, price));
            System.out.println("등록: " + name);
        } catch (InvalidPriceException e) {
            System.out.println("등록 실패 → " + e.getMessage());
        }
    }

    static void print(ArrayList<Product> list) {
        for (Product p : list) {
            System.out.println(p);
        }
    }
}
