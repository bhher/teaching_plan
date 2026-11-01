package a0813.product;

import java.util.Scanner;

/**
 * 상품 관리 시스템 메인
 * ArrayList CRUD + File 입출력
 */
public class ProductApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager manager = new ProductManager();
        boolean run = true;

        while (run) {
            System.out.println();
            System.out.println("=== 상품 관리 시스템 ===");
            System.out.println("1. 상품 등록 (Create)");
            System.out.println("2. 상품 조회 (Read)");
            System.out.println("3. 상품 수정 (Update)");
            System.out.println("4. 상품 삭제 (Delete)");
            System.out.println("5. 전체 조회");
            System.out.println("6. 파일 저장");
            System.out.println("7. 파일 불러오기");
            System.out.println("8. 종료");
            System.out.print("선택 > ");

            int menu;
            try {
                menu = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요.");
                continue;
            }

            switch (menu) {
                case 1: // Create
                    System.out.print("상품 코드: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("상품명: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("가격: ");
                    int price = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("재고: ");
                    int stock = Integer.parseInt(scanner.nextLine().trim());
                    manager.addProduct(id, name, price, stock);
                    break;

                case 2: // Read
                    System.out.print("조회할 상품 코드: ");
                    String findId = scanner.nextLine().trim();
                    Product found = manager.findProduct(findId);
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("해당 상품이 없습니다.");
                    }
                    break;

                case 3: // Update
                    System.out.print("수정할 상품 코드: ");
                    String updateId = scanner.nextLine().trim();
                    System.out.print("새 상품명: ");
                    String newName = scanner.nextLine().trim();
                    System.out.print("새 가격: ");
                    int newPrice = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("새 재고: ");
                    int newStock = Integer.parseInt(scanner.nextLine().trim());

                    if (manager.updateProduct(updateId, newName, newPrice, newStock)) {
                        System.out.println("수정 완료");
                    } else {
                        System.out.println("해당 상품이 없습니다.");
                    }
                    break;

                case 4: // Delete
                    System.out.print("삭제할 상품 코드: ");
                    String deleteId = scanner.nextLine().trim();
                    if (manager.deleteProduct(deleteId)) {
                        System.out.println("삭제 완료");
                    } else {
                        System.out.println("해당 상품이 없습니다.");
                    }
                    break;

                case 5:
                    manager.printAllProducts();
                    break;

                case 6:
                    manager.saveToFile();
                    break;

                case 7:
                    manager.loadFromFile();
                    break;

                case 8:
                    run = false;
                    System.out.println("프로그램을 종료합니다.");
                    break;

                default:
                    System.out.println("1~8 중에서 선택하세요.");
            }
        }
        scanner.close();
    }
}
