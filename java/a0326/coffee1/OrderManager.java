package a0326.coffee1;

import java.util.HashMap;
import java.util.Map;

public class OrderManager {
    private Map<String, Integer> orders;
    private Menu menu;
    
    public OrderManager(Menu menu) {
        this.orders = new HashMap<>();
        this.menu = menu;
    }
    
    public void addOrder(String coffeeName, int quantity) {
        orders.put(coffeeName, orders.getOrDefault(coffeeName, 0) + quantity);
        System.out.println(coffeeName + " " + quantity + "개 추가 되었습니다.");
    }
    
    public void displayOrderSummary() {
        System.out.println("\n주문 내역");
        int total = 0;
        
        for(Map.Entry<String, Integer> entry : orders.entrySet()) {
            String coffeeName = entry.getKey();
            int quantity = entry.getValue();
            int price = menu.getPrice(coffeeName);
            int itemTotal = price * quantity;
            
            System.out.println(coffeeName + " X " + quantity + " = " + itemTotal + "원");
            total += itemTotal;
        }
        
        System.out.println("총 금액 : " + total + "원");
    }
    
    public boolean isEmpty() {
        return orders.isEmpty();
    }
}
