package a0128.bookEx;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookDAO books = new BookDAO();
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
            } catch (Exception e) {
                // TODO: handle exception
                choice =-1;
            }
            switch (choice) {
                case 1:
                    books.addBook();
                    break;
                case 2:
                    books.deletBook();
                    break;
                case 3:
                    books.searchBook();
                    break;
                case 4:
                    books.updateBook();
                    break;
                case 5:
                    books.showBooks();
                    break;
                case 6:
                    try {
                        books.dataSave();
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    books.dataLoad();
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
