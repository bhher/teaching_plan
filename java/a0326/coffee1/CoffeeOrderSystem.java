package a0326.coffee1;

import java.util.Scanner;

public class CoffeeOrderSystem {
    public static void main(String[] args) {
        Menu menu = new Menu();
        OrderManager orderManager = new OrderManager(menu);
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            menu.displayMenu();
            System.out.println("주문할 커피 이름(종료:exit)");
            String coffee = sc.nextLine();
            
            if(coffee.equalsIgnoreCase("exit")) {
                break;
            }
            
            if(!menu.containsCoffee(coffee)) {
                System.out.println("해당 커피는 메뉴에 없습니다.");
                continue;
            }
            
            int quantity = getValidQuantity(sc);
            orderManager.addOrder(coffee, quantity);
        }
        
        sc.close();
        
        if(!orderManager.isEmpty()) {
            orderManager.displayOrderSummary();
        } else {
            System.out.println("주문 내역이 없습니다.");
        }
    }
    
    private static int getValidQuantity(Scanner sc) {
        int quantity;
        while (true) {
            System.out.print("수량 : ");
            try {
                quantity = Integer.parseInt(sc.nextLine());
                if(quantity <= 0) {
                    System.out.println("1이상의 숫자를 입력하세요");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("유효한 숫자를 입력하세요");
            }
        }
        return quantity;
    }
}
