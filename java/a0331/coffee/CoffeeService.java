package a0331.coffee;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeService {

    private boolean reOrder = false;
    private int orderNum = 1;
    Map<String,Integer>orderList;

    public CoffeeService() {
        orderList = new LinkedHashMap<>();
    }
    //고객 주문 저장
    //new LinkedHashMap<>(); 입력 순서 또는 접근 순서가 보장 되어 있을때 사용하며 속도가 느림
    //용도는 순서가 중요한 경우 사용

    Coffee coffee = Coffee.getInstance();// 커피 메뉴 관리하는 싱글톤 객체 new Coffee()가 아님
    Customer customer;

    Thread t = new Thread();//스레드
    //사용자가 처리할 프로그램 지정
    //여기선 지연 클래스 사용할거임

    Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println("\n 어서오세요 더조은커피숍입니다.");
        customer = new Customer(orderNum); // 주문번호 , 잔액(카드)
        coffee.getMenu();
        order();
        //주문메서드

        totalOrder(customer);
        try{
            System.out.println("기다려 주시면 주문하신 음료가 나옵니다.");
            t.sleep(2000);
            //2초 후에 다음문장이 실행
            end();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void end() {
        int s = 1;
        StringBuffer message = new StringBuffer();
        message.append("\n\n ")
                .append("+----------------------------------------------------+\n ")
                .append("|                                                    | \n ")
                .append("|           " + customer.getOrderName() + " 고객님 주문하신 음료 나왔습니다         | " + "\n");
        System.out.print(message);
        for (Map.Entry<String, Integer> order : customer.getCoffeeOrder().entrySet()) {
            System.out.printf(" | [%d] %-20s : %2d잔  %7s |\n", s, order.getKey(), order.getValue(), "");
            s++;
        }
        System.out.println(" |                                                    |");
        System.out.println(" +----------------------------------------------------+");
    }

    private void totalOrder(Customer customer) {
        int s = 1;
        int totalMoney = 0;
        int coffeePrice = 0;
        DecimalFormat f = new DecimalFormat("###,000원");
        String name = customer.getOrderName()+"번 ";
        StringBuffer message = new StringBuffer();
            message.append("\n\n ")
            .append("+----------------------------------------------------+\n ")
            .append("|                                                    | \n ")
            .append("|             " + name + "고객님 의 주문 내역 입니다         | " + "\n");
        
        for(Map.Entry<String,Integer> order : customer.getCoffeeOrder().entrySet()){
            String coffeeName = order.getKey();
            int orderCount = order.getValue();
            int coffeeUnitPrice = coffee.menu.get(coffeeName);
            coffeePrice = coffeeUnitPrice * orderCount;
            totalMoney = totalMoney + coffeePrice;
            String pay = f.format(coffeePrice); // 가격 포맷팅
            message.append(String.format(" | [%d] %-20s : %2d잔  %7s |\n", s, coffeeName, orderCount, pay));
            s++; // 리스트 인덱스 증가
        }
        message.append(" |                                                    |\n ")
            .append("+----------------------------------------------------+ \n")
            .append(" ============ 총 결제 금액은 " + f.format(totalMoney) + "입니다 ========== \n");
            System.out.println(message);
            payment(totalMoney);
    }

    private void payment(int totalMoney) {
        System.out.println("\n결제를 도와 드리겠습니다 카드 넣어주세요");
        int payResult = customer.getMoney() - totalMoney;
        try {
            System.out.println("결제중입니다.....");
            t.sleep(2500);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        if(payResult < 0){
            System.out.println("잔액이 부족합니다. 주문 다시 해주세요");
        } else {
            customer.setMoney(payResult);
            System.out.println("결제가 완료 되었습니다");
            System.out.println("이용해주셔서 감사합니다");
            orderNum++;//고객번호 증가
        }
    }

    private void order() {
        System.out.println("\n 취소를 원하시면 0번을 눌러주세요");
        end:while (true) {
            try{
                System.out.print("\n원하는 음료의 번호를 선택해주세요 :");
                String choice = sc.next();
                int choiceNum = Integer.parseInt(choice.substring(0,1));
                //한글자만 추출해서 숫자로 변경
                if(choiceNum == 0){
                    System.out.println("주문을 취소합니다.");
                    System.exit(0);
                }
                sc.nextLine();
                String coffeeName = coffee.coffeeList.get(choiceNum-1); //주문번호에서 -1 해야 0~5까지 선택됨 0은 탈출용어로 써서 이럼
                System.out.println("선택 하신 음료는 : "+coffeeName+"입니다. 몆잔 주문?");
                int orderCount = sc.nextInt();
                sc.nextLine();

                //재주문 if문
                if(reOrder){
                    for(String coff : orderList.keySet()){
                        if(coff.equals(coffeeName)){//똑같은 커피를 주문한다면
                            int addCount = orderList.get(coff).intValue()+orderCount;
                            //주문 리스트의 수량을 불러서 현재 수량에 더한다
                            orderList.replace(coffeeName, addCount);
                        } else {
                            orderList.put(coffeeName, orderCount);
                            break;
                        }
                    }
                } else {
                    orderList.put(coffeeName, orderCount);// 커피이름,수량을 주문리스트에 담아서 저장
                }
                customer.setCoffeeOrder(orderList);
                //추가주문
                addOrder();
                break end;
            } catch(Exception e) {
                System.out.println("잘못된 선택입니다");
            }
        }
    }

    private void addOrder() {
        reOrder = false;
        System.out.println("\n주문을 계속 하시겠습니까?");
        System.out.println("예(Y)/아니오(N)");
        String yesOrNo = sc.next();
        if(yesOrNo.equals("예") || yesOrNo.equalsIgnoreCase("y")){
            coffee.getMenu();
            reOrder = true;
            order();
        } else if(yesOrNo.equals("아니오") || yesOrNo.equalsIgnoreCase("n")){
            return;
        }
    }

}
