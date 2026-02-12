package movie;

import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<Integer> reservationNumbers;
    // 예매번호 저장
    private ArrayList<String> reservedMovies;
    // 예매한 영화 제목 저장
    private ArrayList<Integer> reservedSeats;
    // 예매한 좌석 번호 저장
    private static int reservationCounter = 1;
    // 모든 사용자가 공유하는 예매번호
    private int totalPaid = 0;
    // 누적 결제 금액

    public User(String name) {
        this.name = name;
        this.reservationNumbers = new ArrayList<>();
        this.reservedMovies = new ArrayList<>();
        this.reservedSeats = new ArrayList<>();
    }
    
    public void addToTotalPaid(int amount) {
        totalPaid += amount;
    }

    public int getTotalPaid() {
        return totalPaid;
    }

    public String getName() {
        return name;
    }
    public ArrayList<Integer> getReservationNumbers() {
        return reservationNumbers;
    }
    public ArrayList<String> getReservedMovies() {
        return reservedMovies;
    }
    public ArrayList<Integer> getReservedSeats() {
        return reservedSeats;
    }

    public void addReservation(String movieTitle, int seatNumber) {
        reservedMovies.add(movieTitle);
        // 예매한 영화 제목 저장
        reservedSeats.add(seatNumber);
        // 예매한 좌석 번호 저장
        reservationNumbers.add(reservationCounter++);
        // 예매번호 부여 후 증가
    }
    
    public void cancelReservation(String movieTitle, int seatNumber) {
        int index = reservedMovies.indexOf(movieTitle);
        // 영화 제목으로 인덱스 찾기
        if (index != -1 && reservedSeats.get(index) == seatNumber) {
            // 예매한 영화 제목과 좌석 번호가 일치하는 경우
            reservedMovies.remove(index);
            reservedSeats.remove(index);
            reservationNumbers.remove(index);
        }
    }
    
    public void showReservations() {
        System.out.println("\n예약 내역:");
        for (int i = 0; i < reservedMovies.size(); i++) {
            System.out.println("예매번호: " + reservationNumbers.get(i) +
                               " | 영화: " + reservedMovies.get(i) +
                               " | 좌석: " + reservedSeats.get(i));
        }
    }

    public void clearReservations() {
        // 예약 내역 초기화
        reservedMovies.clear();
        // 예매한 영화 제목 초기화
        reservedSeats.clear();
        // 예매한 좌석 번호 초기화
        reservationNumbers.clear();
        // 예매번호 초기화
    }

    public void removeReservationsByMovie(String title) {
        for (int i = reservedMovies.size() - 1; i >= 0; i--) {
            if (reservedMovies.get(i).equals(title)) {
                reservedMovies.remove(i);
                reservedSeats.remove(i);
                reservationNumbers.remove(i);
            }
        }
    }
}
