package a0331.book;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class BookService {
    private boolean reRental = false;      // 재대여 여부
    private int memberNum = 1;             // 회원 번호
    Map<String, Integer> rentalList;        // 대여 리스트
    
    public BookService() {
        rentalList = new LinkedHashMap<>();
    }
    
    Book book = Book.getInstance();         // 싱글톤 객체
    Member member;                          // 회원 객체
    Thread t = new Thread();                // 스레드
    Scanner sc = new Scanner(System.in);
    
    public void start() {
        System.out.println("\n 어서오세요 더조은도서관입니다.");
        member = new Member(memberNum);
        book.getMenu();
        rental();
        
        totalRental(member);
        try {
            System.out.println("기다려 주시면 대여하신 도서를 준비하겠습니다.");
            t.sleep(2000);
            end();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void rental() {
        System.out.println("\n 취소를 원하시면 0번을 눌러주세요");
        end:while (true) {
            try {
                System.out.print("\n원하는 도서의 번호를 선택해주세요 :");
                String choice = sc.next();
                int choiceNum = Integer.parseInt(choice.substring(0,1));
                
                if(choiceNum == 0){
                    System.out.println("대여를 취소합니다.");
                    System.exit(0);
                }
                
                sc.nextLine();
                String bookName = book.bookList.get(choiceNum-1);
                System.out.println("선택 하신 도서는 : " + bookName + "입니다. 며칠 대여하시겠습니까?");
                int rentalDays = sc.nextInt();
                sc.nextLine();
                
                // 재대여 처리 (개선된 로직)
                if(reRental){
                    if(rentalList.containsKey(bookName)){
                        // 같은 도서 재대여 - 기간 누적
                        int addDays = rentalList.get(bookName) + rentalDays;
                        rentalList.replace(bookName, addDays);
                    } else {
                        // 새로운 도서 추가
                        rentalList.put(bookName, rentalDays);
                    }
                } else {
                    rentalList.put(bookName, rentalDays);
                }
                
                member.setBookRental(rentalList);
                addRental();
                break end;
            } catch(Exception e) {
                System.out.println("잘못된 선택입니다");
            }
        }
    }
    
    private void addRental() {
        reRental = false;
        System.out.println("\n대여를 계속 하시겠습니까?");
        System.out.println("예(Y)/아니오(N)");
        String yesOrNo = sc.next();
        if(yesOrNo.equals("예") || yesOrNo.equalsIgnoreCase("y")){
            book.getMenu();
            reRental = true;
            rental();
        } else if(yesOrNo.equals("아니오") || yesOrNo.equalsIgnoreCase("n")){
            return;
        }
    }
    
    private void totalRental(Member member) {
        int s = 1;
        int totalMoney = 0;
        int bookPrice = 0;
        DecimalFormat f = new DecimalFormat("###,000원");
        String name = member.getMemberName() + "번 ";
        StringBuffer message = new StringBuffer();
        
        message.append("\n\n ")
            .append("+----------------------------------------------------+\n ")
            .append("|                                                    | \n ")
            .append("|             " + name + "회원님 의 대여 내역 입니다         | " + "\n");
        
        for(Map.Entry<String, Integer> rental : member.getBookRental().entrySet()){
            String bookName = rental.getKey();
            int rentalDays = rental.getValue();
            int unitPrice = book.menu.get(bookName);
            bookPrice = unitPrice * rentalDays;
            totalMoney = totalMoney + bookPrice;
            String pay = f.format(bookPrice);
            message.append(String.format(" | [%d] %-20s : %2d일  %7s |\n", 
                s, bookName, rentalDays, pay));
            s++;
        }
        
        message.append(" |                                                    |\n ")
            .append("+----------------------------------------------------+ \n")
            .append(" ============ 총 결제 금액은 " + f.format(totalMoney) + "입니다 ========== \n");
        System.out.println(message);
        payment(totalMoney);
    }
    
    private void payment(int totalMoney) {
        System.out.println("\n결제를 도와 드리겠습니다 카드 넣어주세요");
        int payResult = member.getMoney() - totalMoney;
        try {
            System.out.println("결제중입니다.....");
            t.sleep(2500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(payResult < 0){
            System.out.println("잔액이 부족합니다. 대여 다시 해주세요");
        } else {
            member.setMoney(payResult);
            System.out.println("결제가 완료 되었습니다");
            System.out.println("이용해주셔서 감사합니다");
            memberNum++;
        }
    }
    
    private void end() {
        int s = 1;
        StringBuffer message = new StringBuffer();
        message.append("\n\n ")
            .append("+----------------------------------------------------+\n ")
            .append("|                                                    | \n ")
            .append("|           " + member.getMemberName() + " 회원님 대여하신 도서 준비되었습니다         | " + "\n");
        System.out.print(message);
        for (Map.Entry<String, Integer> rental : member.getBookRental().entrySet()) {
            System.out.printf(" | [%d] %-20s : %2d일  %7s |\n", 
                s, rental.getKey(), rental.getValue(), "");
            s++;
        }
        System.out.println(" |                                                    |");
        System.out.println(" +----------------------------------------------------+");
    }
}
