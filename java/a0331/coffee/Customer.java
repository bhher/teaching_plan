package a0331.coffee;

import java.util.Map;

public class Customer {
    private int orderNum; //고객의 주문 번호
    private int money; //고객이 가지고 있는 돈을 저장
    private Map<String , Integer> coffeeOrder;//커피주문
    
    public Customer(int orderNum) {
        this.money = 20000;//초기값 설정
        this.orderNum = orderNum;
    }

    public Map<String, Integer> getCoffeeOrder() {
        return coffeeOrder;
    }

    public void setCoffeeOrder(Map<String, Integer> coffeeOrder) {
        this.coffeeOrder = coffeeOrder;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    //고객1 ,고객2... 하게 늘어나는 구조
    public String getOrderName() {
        return "고객 "+orderNum;
    }

    
}
