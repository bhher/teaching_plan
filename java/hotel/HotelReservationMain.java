package a0212.hotel;

import java.util.Scanner;

// 프로그램 진입점
public class HotelReservationMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReservationManager manager = new ReservationManager();
        ReservationTicket ticket = new ReservationTicket(manager); // 티켓 객체 생성

        // 호텔 추가
        manager.addHotel(new Hotel("그랜드 호텔", "서울 강남구", 150000, 50));
        manager.addHotel(new Hotel("리조트 파라다이스", "제주 서귀포시", 200000, 30));

        while(true) {
            System.out.println("\n=== 호텔 예약 시스템 ===");
            System.out.println("1. 사용자 로그인");
            System.out.println("2. 운영자 로그인");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    userMenu(manager, sc);
                    break;
                case "2":
                    adminLogin(manager, ticket, sc);
                    break;
                case "0":
                    System.out.println("시스템을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void adminLogin(ReservationManager reservationManager, ReservationTicket ticket, Scanner sc) {
        System.out.print("운영자 비밀번호를 입력하세요: ");
        String password = sc.nextLine();
        // 예시
        if(!password.equals("admin123")) {
            System.out.println("비밀번호가 틀렸습니다.");
            return;
        }

        while (true) {
            System.out.println("\n=== 운영자 메뉴 ===");
            System.out.println("1. 호텔 삭제");
            System.out.println("2. 호텔 목록 갱신");
            System.out.println("3. 호텔 정보 수정");
            System.out.println("4. 호텔 추가");
            System.out.println("5. 할인율 설정");
            System.out.println("0. 뒤로가기");
            System.out.print("선택: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    reservationManager.showHotels();
                    reservationManager.deleteHotel(sc);
                    break;
                case "2":
                    ticket.updateHotelList();
                    System.out.println("호텔 목록이 갱신되었습니다.");
                    break;
                case "3":
                    reservationManager.showHotels();
                    reservationManager.modifyHotelInfo(sc);
                    break;
                case "4":
                    addHotel(reservationManager, sc);
                    break;
                case "5":
                    reservationManager.setDiscountRate(sc);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void addHotel(ReservationManager manager, Scanner sc) {
        System.out.println("\n=== 호텔 추가 ===");
        System.out.print("호텔 이름: ");
        String name = sc.nextLine();
        
        System.out.print("위치: ");
        String location = sc.nextLine();
        
        System.out.print("가격: ");
        int price = 0;
        try {
            price = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 가격은 숫자여야 합니다.");
            return;
        }
        
        System.out.print("방 개수: ");
        int roomCount = 0;
        try {
            roomCount = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 방 개수는 숫자여야 합니다.");
            return;
        }
        
        // 중복 확인
        if (manager.getHotel(name) != null) {
            System.out.println("이미 존재하는 호텔입니다.");
            return;
        }
        
        Hotel hotel = new Hotel(name, location, price, roomCount);
        manager.addHotel(hotel);
        System.out.println("호텔이 추가되었습니다: " + name);
    }

    private static void userMenu(ReservationManager manager, Scanner sc) {
        End:while (true) {
            System.out.println("\n=== 호텔 예약 시스템 ===");
            System.out.println("1. 예약 가능한 호텔 정보");
            System.out.println("2. 호텔 예약");
            System.out.println("3. 예약 조회");
            System.out.println("4. 예약 취소");
            System.out.println("5. 예약 일괄 취소");
            System.out.println("6. 예약 티켓 출력");
            System.out.println("0. 뒤로가기");
            System.out.print("선택: ");
            int choice = sc.nextInt();
            sc.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1:
                    manager.showHotels();
                    break;
                case 2:
                    manager.showHotels();
                    manager.hotelReservation();
                    break;
                case 3:
                    manager.checkReservation();
                    break;
                case 4:
                    manager.cancelReservation();
                    break;
                case 5:
                    manager.cancelAllReservation();
                    break;
                case 6:
                    manager.printTicket();
                    break;
                case 0:
                    break End;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }
}
