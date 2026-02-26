package a0212.hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class ReservationManager {
    private ArrayList<Hotel> hotels; // 호텔 목록
    private ArrayList<User> users;  // 사용자 목록
    private Scanner sc; // 사용자 입력을 위한 스캐너
    private static ReservationTicket ticket; // 티켓 객체
    private int discountRate = 0;
    
    public ReservationManager() {
        hotels = new ArrayList<>();
        // 호텔 목록 초기화
        users = new ArrayList<>();
        // 사용자 목록 초기화
        sc = new Scanner(System.in);
        // 스캐너 초기화
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
        // 호텔 추가
    }

    public void showHotels() {
        System.out.println("\n 현재 예약 가능한 호텔 목록");
        for(Hotel hotel : hotels) {
            System.out.println(hotel);
            // hotel 클래스에 toString() 출력
        }
    }

    // 호텔 예약
    public void hotelReservation() {
        System.out.print("호텔 이름 입력: ");
        String hotelName = sc.nextLine();
        System.out.print("사용자 이름 입력: ");
        String userName = sc.nextLine();
        Hotel hotel = getHotel(hotelName);
        // 호텔 객체 가져오기

        if (hotel == null) {
            System.out.println("해당 호텔이 없습니다.");
            return;
        }
        
        hotel.getRoom().displayRooms();
        System.out.print("방 번호 선택: ");
        int roomNumber = Integer.parseInt(sc.nextLine());

        if(bookRoom(userName, hotelName, roomNumber)) {
            int price = hotel.getPrice();
            int discount = (price * discountRate) / 100;
            int finalPrice = price - discount;
            System.out.println("예약이 완료되었습니다.");
            System.out.println("원가: " + price + "원");
            System.out.println("할인율: " + discountRate + "%");
            System.out.println("할인된 금액: " + discount + "원");
            System.out.println("결제 금액: " + finalPrice + "원");

            // 사용자에게 결제 금액 누적
            User user = getUser(userName);
            if(user != null) {
                user.addTotalPaid(finalPrice);
            }
        } else {
            System.out.println("이미 예약된 방입니다.");
        }
    }

    private boolean bookRoom(String userName, String hotelName, int roomNumber) {
        Hotel hotel = getHotel(hotelName);
        if(hotel == null) {
            System.out.println("해당 호텔이 없어요");
            return false;
        }
        if(!hotel.getRoom().reserveRoom(roomNumber)) {
            // 방 예약을 시도했는데 실패했다면 안내 메시지 띄우고 작업 중단
            System.out.println("이미 예약된 방이거나 잘못된 방 번호입니다.");
            return false;
        }
        User user = getUser(userName);
        // 유저가 존재하는지 확인
        if(user == null) {
            // 유저가 없으면 새로 생성
            user = new User(userName);
            users.add(user);
        }
        user.addReservation(hotelName, roomNumber);
        // 유저 예약 추가(호텔 이름과 방 번호)
        return true;
    }

    public User getUser(String userName) {
        for(User user : users) {
            if(user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public Hotel getHotel(String hotelName) {
        for(Hotel hotel : hotels) {
            if(hotel.getName().equals(hotelName)) {
                // 호텔 이름이 같으면 해당 호텔 객체를 반환
                return hotel;
            }
        }
        return null;
    }

    // 예약 조회
    public void checkReservation() {
        System.out.print("사용자 이름 입력: ");
        String userName = sc.nextLine();
        User user = getUser(userName);
        if(user != null && !user.getReservedHotels().isEmpty()) {
            // 유저 정보가 존재하고 예약 내역이 있는 경우
            for (int i = 0; i < user.getReservedHotels().size(); i++) {
                System.out.println("예약번호: " + user.getReservationNumbers().get(i) +
                                 " | 호텔: " + user.getReservedHotels().get(i) +
                                 " | 방: " + user.getReservedRooms().get(i));
            }
            System.out.println("총 결제 금액: " + user.getTotalPaid() + "원");
        } else {
            System.out.println("예약된 내역이 없습니다.");
        }
    }

    public void cancelReservation() {
        System.out.print("사용자 이름 입력: ");
        String userName = sc.nextLine();
        User user = getUser(userName);

        if (user == null || user.getReservedHotels().isEmpty()) {
            System.out.println(userName + "님은 예약된 내역이 없습니다.");
            return;
        }
        user.showReservations();
        // 예약 목록 출력
        System.out.print("취소할 예약 번호를 입력하세요: ");
        int reservationNumber = sc.nextInt();
        sc.nextLine();
        int index = user.getReservationNumbers().indexOf(reservationNumber); // 내용이 같으면 인덱스 번호 다르면 -1
        if(index == -1) {
            System.out.println("해당 예약번호의 예약이 없습니다.");
            return;
        }
        String hotelName = user.getReservedHotels().get(index);
        // 호텔 이름 가져오기
        int roomNumber = user.getReservedRooms().get(index);
        // 방 번호 가져오기

        Hotel hotel = getHotel(hotelName);
        if(hotel != null) {
            hotel.getRoom().cancelRoom(roomNumber);
            // 방 취소
            System.out.println("호텔 [" + hotelName + "] 방 [" + roomNumber + "] 예약이 취소되었습니다.");
        }

        user.cancelReservation(hotelName, roomNumber);
        // 유저 예약 정보 삭제
    }

    public void cancelAllReservation() {
        System.out.print("사용자 이름 입력: ");
        String userName = sc.nextLine();
        User user = getUser(userName);

        if (user == null || user.getReservedHotels().isEmpty()) {
            System.out.println(userName + "님은 예약된 내역이 없습니다.");
            return;
        }
        // 모든 예약 정보 가져오기
        ArrayList<String> hotels = new ArrayList<>(user.getReservedHotels());
        ArrayList<Integer> rooms = new ArrayList<>(user.getReservedRooms());
        ArrayList<Integer> numbers = new ArrayList<>(user.getReservationNumbers());

        for(int i = 0; i < hotels.size(); i++) {
            String hotelName = hotels.get(i);
            int roomNumber = rooms.get(i);
            int reservationNumber = numbers.get(i);
            Hotel hotel = getHotel(hotelName);
            if(hotel != null) {
                hotel.getRoom().cancelRoom(roomNumber);
                System.out.println("[" + reservationNumber + "] 호텔 [" + hotelName + "] 방 [" + roomNumber + "] 취소됨.");
            }
        }
        // 사용자 예약 전체 삭제
        user.clearReservations();
        System.out.println("\n" + userName + "님의 모든 예약이 취소되었습니다.");
    }

    public void printTicket() {
        System.out.println("\n=== 예약 티켓 출력 ===");
        System.out.print("예약 번호를 입력하세요: ");
        int reservationNum = -1;

        try {
            reservationNum = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("숫자를 입력하세요.");
        }
        if(reservationNum != -1) {
            // ticket 객체가 null이면 생성
            if(ticket == null) {
                ticket = new ReservationTicket(this);
                // this는 현재 ReservationManager 객체
            }
            ticket.printTicket(reservationNum);
        }
    }

    public String getReservationDetails(int reservationNum) {
        for(User user : users) {
            if(user.getReservationNumbers().contains(reservationNum)) {
                int index = user.getReservationNumbers().indexOf(reservationNum);
                return "예약번호: " + user.getReservationNumbers().get(index) +
                       " | 호텔: " + user.getReservedHotels().get(index) +
                       " | 방: " + user.getReservedRooms().get(index);
            }
        }
        return null;
        // 예약 번호 존재하지 않음
    }

    public void deleteHotel(Scanner sc) {
        System.out.print("삭제할 호텔 이름을 입력하세요: ");
        String hotelName = sc.nextLine();
        Hotel hotel = getHotel(hotelName);
        if(hotel != null) {
            // 먼저 사용자들의 해당 호텔 예약을 모두 취소
            for(User user : users) {
                ArrayList<String> reservedHotels = user.getReservedHotels();
                if(reservedHotels.contains(hotelName)) {
                    // 예약 정보 인덱스 확보
                    ArrayList<Integer> reservationNumbers = new ArrayList<>(user.getReservationNumbers());
                    for(int i = 0; i < reservationNumbers.size(); i++) {
                        if(user.getReservedHotels().get(i).equals(hotelName)) {
                            int roomNumber = user.getReservedRooms().get(i);
                            hotel.getRoom().cancelRoom(roomNumber);
                            // 방 취소
                            System.out.println("[" + reservationNumbers.get(i) + "] 예약도 함께 취소되었습니다.");
                        }
                    }
                    // 사용자 측 예약 정보에서 삭제
                    user.removeReservationsByHotel(hotelName);
                }
            }
            hotels.remove(hotel);
            System.out.println("[" + hotelName + "] 호텔이 삭제되었습니다.");
        } else {
            System.out.println("해당 호텔이 존재하지 않습니다.");
        }
    }

    public void modifyHotelInfo(Scanner sc) {
        System.out.print("수정할 호텔 이름을 입력하세요: ");
        String hotelName = sc.nextLine();
        Hotel hotel = getHotel(hotelName);
        if (hotel == null) {
            System.out.println("해당 호텔이 없습니다.");
            return;
        }

        System.out.println("현재 정보: " + hotel.getName() + ", " + hotel.getLocation() + ", " + hotel.getPrice() + "원");
        System.out.println("수정할 정보를 입력하세요. 미 입력 시 기존 정보 유지됩니다. 또한 모든 예약은 취소됩니다.");
        
        for(User user : users) {
            ArrayList<String> reservedHotels = user.getReservedHotels();
            if(reservedHotels.contains(hotelName)) {
                // 예약 정보 인덱스 확보
                ArrayList<Integer> reservationNumbers = new ArrayList<>(user.getReservationNumbers());
                for(int i = 0; i < reservationNumbers.size(); i++) {
                    if(user.getReservedHotels().get(i).equals(hotelName)) {
                        int roomNumber = user.getReservedRooms().get(i);
                        hotel.getRoom().cancelRoom(roomNumber);
                        // 방 취소
                        System.out.println("[" + reservationNumbers.get(i) + "] 예약도 함께 취소되었습니다.");
                    }
                }
                // 사용자 측 예약 정보에서 삭제
                user.removeReservationsByHotel(hotelName);
            }
        }

        System.out.print("새 호텔 이름: ");
        String newName = sc.nextLine();
        if(newName.isEmpty()) {
            newName = hotel.getName();
            // 미 입력시 원상태로
        }
        System.out.print("새 위치: ");
        String newLocation = sc.nextLine();
        if(newLocation.isEmpty()) {
            newLocation = hotel.getLocation();
            // 미 입력시 원상태로
        }
        System.out.print("새 가격: ");
        String priceInput = sc.nextLine();
        int newPrice = 0;
        try {
            if(priceInput.trim().isEmpty()) {
                // 공란을 입력시 기존 가격 유지
                newPrice = hotel.getPrice();
            } else {
                newPrice = Integer.parseInt(priceInput);
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 가격은 숫자여야 합니다.");
            return;
        }

        System.out.print("새 방 개수: ");
        String roomInput = sc.nextLine();
        int newRooms = 0;
        try {
            if (roomInput.trim().isEmpty()) {
                // 공란 입력 시 기존 방 개수 유지
                newRooms = hotel.getRoom().getAvailableRooms();
            } else {
                newRooms = Integer.parseInt(roomInput);
            }

            if(newRooms == 0) {
                newRooms = hotel.getRoom().getAvailableRooms();
                // 미 입력시 원상태로
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 방 개수는 숫자여야 합니다.");
            return;
        }

        // 새로운 Hotel 객체로 대체
        Hotel updateHotel = new Hotel(newName, newLocation, newPrice, newRooms);
        hotels.remove(hotel);
        hotels.add(updateHotel);
        System.out.println("호텔 정보가 수정되었습니다.");
    }

    public void setDiscountRate(Scanner sc) {
        System.out.println("현재 할인율: " + discountRate + "%");
        System.out.print("할인율(%)을 입력하세요: ");
        try {
            discountRate = Integer.parseInt(sc.nextLine());
            System.out.println("할인율이 " + discountRate + "%로 설정되었습니다.");
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다.");
        }
    }
    
    public int getDiscountRate() {
        return discountRate;
    }
}
