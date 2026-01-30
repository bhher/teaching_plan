package a0128.bookEx;

import java.util.ArrayList;
import java.util.Scanner;

public class BookDAO_bak {
    private ArrayList<BookDTO> bookList;
    Scanner sc = new Scanner(System.in);
    BookFile file = new BookFile("book","books");

    public BookDAO_bak(){
        bookList = new ArrayList<>();

        bookList.add(new BookDTO(0,"자바의 정석", "남궁성", "12345", 32000));
        bookList.add(new BookDTO(1,"Effective Java", "Joshua Bloch", "54321", 45000));
        bookList.add(new BookDTO(2,"Clean Code", "Robert C. Martin", "67890", 38000));
        bookList.add(new BookDTO(3,"스프링 부트와 AWS", "이동욱", "98765", 28000));
        bookList.add(new BookDTO(4,"코틀린 인 액션", "Dmitry Jemerov", "13579", 40000));
    }

    public void addBook() {
        BookDTO b = new BookDTO();
        b.setId(bookList.size());
        System.out.print("책 제목: ");
        b.setbName(sc.next());
        System.out.print("저자: ");
        b.setName(sc.next());
        System.out.print("ISBN: ");
        b.setIsbn(sc.next());
        System.out.print("가격: ");
        while (true) {
            try {
                b.setPrice(sc.nextDouble());
                break;
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("숫자를 입력하세요");
                sc.next();
            }
        }
        bookList.add(b);
    }

	public void deletBook() {
        System.out.println("도서 제거");
        int index = searchIndex();
        if(index == -1){
            System.out.println("찾는 도서가 없습니다");
        } else {
            String bookName = bookList.get(index).getbName();
            bookList.remove(index);
            System.out.println(bookName+"를 제거 하였습니다");
        }
	}

    private int searchIndex() {
        int index = -1;
        System.out.println("책 이름을 입력");
        System.out.print(">>");
        String name = sc.next();
        for(int i = 0; i < bookList.size(); i++){
            if(bookList.get(i).getbName().equals(name)){
                index = i;
                break;
            }
        }
        return index;
    }

    public void searchBook() {
        System.out.println("도서 검색");
        int index = searchIndex();
        if(index == -1){
            System.out.println("찾는 도서가 없습니다");
        } else {
            BookDTO searchB = bookList.get(index);
            System.out.println(searchB);
        }
    }

    public void updateBook() {
        System.out.println("도서 정보 수정");
        int index = searchIndex();
        if(index == -1){
            System.out.println("찾는 도서가 없습니다");
        } else {
            BookDTO updateB = new BookDTO();

            updateB.setId(bookList.get(index).getId());
            System.out.println("책 내용 수정");
            System.out.print("책 제목: ");
            updateB.setbName(sc.next());
            System.out.print("저자: ");
            updateB.setName(sc.next());
            System.out.print("ISBN: ");
            updateB.setIsbn(sc.next());
            System.out.print("가격: ");
            while (true) {
                try {
                    updateB.setPrice(sc.nextDouble());
                    break;
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("숫자를 입력하세요");
                    sc.next();
                }
            }
            bookList.set(index, updateB);
        }
    }

    public void showBooks() {
        for(int i = 0; i < bookList.size(); i++){
            System.out.println(bookList.get(i).toString());
        }
    }

    public void dataSave() throws Exception {
        file.create();
        String str = "";
        for(int j = 0; j < bookList.size(); j ++){
            str += bookList.get(j).toString()+"\n";
        }
        file.write(str);
    }

	public void dataLoad() {
        try {
            file.read();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("읽을파일이 없습니다.");
        }
	}
}
