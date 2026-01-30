package a0128.bookEx;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookManager manager = new BookManager();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n===== 도서 관리 시스템 =====");
            System.out.println("1. 도서 추가");
            System.out.println("2. 도서 삭제");
            System.out.println("3. 도서 검색");
            System.out.println("4. 도서 수정");
            System.out.println("5. 도서 목록 보기");
            System.out.println("6. 파일로 저장");
            System.out.println("7. 파일에서 불러오기");
            System.out.println("0. 종료");
            System.out.print(">>");
            
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    manager.addBook();
                    break;
                case 2:
                    manager.deleteBook();
                    break;
                case 3:
                    manager.searchBook();
                    break;
                case 4:
                    manager.updateBook();
                    break;
                case 5:
                    manager.showBooks();
                    break;
                case 6:
                    manager.saveToFile();
                    break;
                case 7:
                    manager.loadFromFile();
                    break;
                case 0:
                    System.out.println("종료합니다.");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못 입력했습니다.");
                    break;
            }
        }
    }
}
