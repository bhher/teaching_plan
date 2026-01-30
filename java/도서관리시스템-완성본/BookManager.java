package a0128.bookEx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookManager {
    private ArrayList<Book> bookList;
    private String filename;
    private Scanner sc;
    
    public BookManager() {
        this.bookList = new ArrayList<>();
        this.filename = "c:/Users/TJ/memo/book.txt";
        this.sc = new Scanner(System.in);
    }
    
    // 디렉토리 확인 및 생성
    private void ensureDirectory() {
        File file = new File(filename);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
            System.out.println("디렉토리가 생성되었습니다: " + parentDir.getPath());
        }
    }
    
    // 도서 찾기 (제목으로)
    private Book findBookByTitle(String title) {
        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }
    
    // 도서 인덱스 찾기 (제목으로)
    private int findBookIndex(String title) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getTitle().equals(title)) {
                return i;
            }
        }
        return -1;
    }
    
    // 도서 추가
    public void addBook() {
        Book book = new Book();
        book.setId(bookList.size()); // ID 자동 부여
        
        System.out.print("책 제목: ");
        book.setTitle(sc.nextLine());
        
        System.out.print("저자: ");
        book.setAuthor(sc.nextLine());
        
        System.out.print("ISBN: ");
        book.setIsbn(sc.nextLine());
        
        System.out.print("가격: ");
        while (true) {
            try {
                double price = Double.parseDouble(sc.nextLine());
                book.setPrice(price);
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요");
                System.out.print("가격: ");
            }
        }
        
        bookList.add(book);
        System.out.println("도서가 추가되었습니다.");
    }
    
    // 도서 삭제
    public void deleteBook() {
        System.out.println("도서 제거");
        System.out.print("책 이름을 입력: ");
        String title = sc.nextLine();
        
        int index = findBookIndex(title);
        if (index == -1) {
            System.out.println("찾는 도서가 없습니다.");
        } else {
            String bookTitle = bookList.get(index).getTitle();
            bookList.remove(index);
            System.out.println(bookTitle + "를 제거 하였습니다.");
        }
    }
    
    // 도서 검색
    public void searchBook() {
        System.out.println("도서 검색");
        System.out.print("책 이름을 입력: ");
        String title = sc.nextLine();
        
        Book book = findBookByTitle(title);
        if (book == null) {
            System.out.println("찾는 도서가 없습니다.");
        } else {
            System.out.println(book);
        }
    }
    
    // 도서 수정
    public void updateBook() {
        System.out.println("도서 정보 수정");
        System.out.print("책 이름을 입력: ");
        String title = sc.nextLine();
        
        int index = findBookIndex(title);
        if (index == -1) {
            System.out.println("찾는 도서가 없습니다.");
        } else {
            Book book = bookList.get(index);
            
            System.out.println("책 내용 수정");
            System.out.print("책 제목: ");
            book.setTitle(sc.nextLine());
            
            System.out.print("저자: ");
            book.setAuthor(sc.nextLine());
            
            System.out.print("ISBN: ");
            book.setIsbn(sc.nextLine());
            
            System.out.print("가격: ");
            while (true) {
                try {
                    double price = Double.parseDouble(sc.nextLine());
                    book.setPrice(price);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력하세요");
                    System.out.print("가격: ");
                }
            }
            
            bookList.set(index, book);
            System.out.println("도서 정보가 수정되었습니다.");
        }
    }
    
    // 도서 목록 보기
    public void showBooks() {
        if (bookList.isEmpty()) {
            System.out.println("등록된 도서가 없습니다.");
            return;
        }
        
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println((i + 1) + ". " + bookList.get(i));
        }
    }
    
    // 파일 저장
    public void saveToFile() {
        ensureDirectory();
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Book book : bookList) {
                bw.write(book.toFileString());
                bw.newLine();
            }
            System.out.println("파일 저장 완료: " + filename);
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage());
        }
    }
    
    // 파일 불러오기
    public void loadFromFile() {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("파일이 없습니다. 새로 시작합니다.");
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            bookList.clear(); // 기존 목록 초기화
            
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Book book = Book.fromFileString(line);
                    if (book != null) {
                        bookList.add(book);
                    }
                }
            }
            System.out.println("파일 불러오기 완료! (" + bookList.size() + "권)");
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }
}
