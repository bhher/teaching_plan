package a0212.hotel;

import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<Integer> reservationNumbers;
    // 예약번호 저장
    private ArrayList<String> reservedHotels;
    // 예약한 호텔 이름 저장
    private ArrayList<Integer> reservedRooms;
    // 예약한 방 번호 저장
    private static int reservationCounter = 1;
    // 모든 사용자가 공유하는 예약번호
    private int totalPaid = 0;
    // 누적 결제 금액
    
    public User(String name) {
        this.name = name;
        this.reservationNumbers = new ArrayList<>();
        this.reservedHotels = new ArrayList<>();
        this.reservedRooms = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<Integer> getReservationNumbers() {
        return reservationNumbers;
    }
    
    public ArrayList<String> getReservedHotels() {
        return reservedHotels;
    }
    
    public ArrayList<Integer> getReservedRooms() {
        return reservedRooms;
    }
    
    public static int getReservationCounter() {
        return reservationCounter;
    }
    
    public int getTotalPaid() {
        return totalPaid;
    }
    
    public void addReservation(String hotelName, int roomNumber) {
        reservedHotels.add(hotelName);
        // 예약한 호텔 이름 저장
        reservedRooms.add(roomNumber);
        // 예약한 방 번호 저장
        reservationNumbers.add(reservationCounter++);
        // 예약번호 부여 후 증가
    }
    
    public void addTotalPaid(int amount) {
        totalPaid += amount;
    }
    
    public void showReservations() {
        System.out.println("\n예약 내역");
        for (int i = 0; i < reservedHotels.size(); i++) {
            System.out.println("예약번호: " + reservationNumbers.get(i) +
                             " | 호텔: " + reservedHotels.get(i) +
                             " | 방: " + reservedRooms.get(i));
        }
    }
    
    public void cancelReservation(String hotelName, int roomNumber) {
        int index = reservedHotels.indexOf(hotelName);
        // 호텔 이름으로 인덱스 찾기
        if(index != -1 && reservedRooms.get(index) == roomNumber) {
            // 예약한 호텔 이름과 방 번호가 일치하는 경우
            reservedHotels.remove(index);
            reservedRooms.remove(index);
            reservationNumbers.remove(index);
        }
    }
    
    public void clearReservations() {
        // 예약 내역 초기화
        reservedHotels.clear(); // list 안에 있는 내용 전부 삭제
        // 예약한 호텔 이름 초기화
        reservedRooms.clear();
        // 예약한 방 번호 초기화
        reservationNumbers.clear();
        // 예약번호 초기화
    }
    
    public void removeReservationsByHotel(String hotelName) {
        for(int i = reservedHotels.size() - 1; i >= 0; i--) {
            if(reservedHotels.get(i).equals(hotelName)) {
                reservedHotels.remove(i);
                reservedRooms.remove(i);
                reservationNumbers.remove(i);
            }
        }
    }
    // List 삭제시 i = 0부터 증가하면서 하면 문제 발생 가능
    // 뒤에서부터 size-1 -> 0 도는게 안전합니다.
    // reservedHotels.removeIf(hotel -> hotel.equals(hotelName)); 가장 깔끔
}
