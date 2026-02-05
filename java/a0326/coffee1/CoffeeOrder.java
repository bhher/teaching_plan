package a0326.coffee1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeOrder {
    public static void main(String[] args) {
        Map<String, Integer>menu = new HashMap<>();
        menu.put("Americano", 3000);
        menu.put("Latte", 4000);
        menu.put("Mocha", 4500);
        menu.put("Espresso", 2500);

        Map<String,Integer> order = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n메뉴");
            /*
            for(Map.Entry<String,Integer> entry : menu.entrySet()){
                System.out.println(entry.getKey()+ " - "+entry.getValue()+"원");
            }
            */
            //menu.entrySet() 커피이름과 가격을 저장
            //entry.getKey() 커피 이름, entry.getValue() 커피 가격을 불러옴
            for(String coffee:menu.keySet()){
                System.out.println(coffee+" - "+menu.get(coffee));
            }
            //이 방법도 있다.

            System.out.println("주문할 커피 이름(종료:exit)");
            String coffee = sc.nextLine();

            if(coffee.equalsIgnoreCase("exit")){
                break;
            }
            if(!menu.containsKey(coffee)){//menu에 포함되지 않은 키값을 부르면
                System.out.println("해당 커피는 메뉴에 없습니다.");
                continue;
            }
            System.out.print("수량 : ");
            int quantity;
            while (true) {

                try {
                    quantity = Integer.parseInt(sc.nextLine());
                    if(quantity <= 0){
                        System.out.println("1이상의 숫자를 입력하세요");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    // TODO: handle exception
                    System.out.println("유효한 숫자를 입력하세요");
                    // continue;
                }
            }
            // order.put(coffee, quantity); 이것도 되는데
            order.put(coffee, order.getOrDefault(coffee, 0)+quantity);
            //가져오려는 수량이 없으면 0 있다면 0+수량
            //getOrDefault()는 Map에서 키가 존재 하지 않을 경우 기본값을 반환하는 메서드

            //null 값을 방지하고 기본값을 처리
            // if(menu.containsKey(coffee)){
            //     order.put(coffee, quantity);
            // }

            //containskey를 사용할 필요 없이 작성됨
            System.out.println(coffee+ " "+quantity+ "개 추가 되었습니다.");
        }
        sc.close();
        //order("Americano",3)이렇게 들어오면
        //menu.get("Americano") = 3000
        //entry.getValue() = 3
        //price = 3*3000
        System.out.println("\n주문 내역");
        int total = 0;
        for(Map.Entry<String,Integer> entry : order.entrySet()){
            int price = menu.get(entry.getKey())*entry.getValue();
            System.out.println(entry.getKey()+ " X "+entry.getValue()+" = "+price+"원");
            total+=price;
        }
        System.out.println("총 금액 : "+total+"원");
    }
}
