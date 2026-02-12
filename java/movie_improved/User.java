package movie_improved;

import java.util.ArrayList;

/**
 * 사용자 정보 및 예약 정보 관리 클래스
 */
public class User {
    private String name;
    private String password; // 비밀번호 추가
    private ArrayList<Integer> reservationNumbers;
    private ArrayList<String> reservedMovies;
    private ArrayList<Integer> reservedSeats;
    private static int reservationCounter = 1;
    private int totalPaid = 0;

    /**
     * 생성자 (비밀번호 없이)
     */
    public User(String name) {
        this(name, "");
    }

    /**
     * 생성자 (비밀번호 포함)
     */
    public User(String name, String password) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("사용자 이름은 필수입니다.");
        }
        this.name = name.trim();
        this.password = password != null ? password : "";
        this.reservationNumbers = new ArrayList<>();
        this.reservedMovies = new ArrayList<>();
        this.reservedSeats = new ArrayList<>();
    }
    
    /**
     * 비밀번호 확인
     */
    public boolean checkPassword(String password) {
        if (this.password.isEmpty()) {
            return true; // 비밀번호가 설정되지 않은 경우
        }
        return this.password.equals(password);
    }

    /**
     * 비밀번호 설정
     */
    public void setPassword(String password) {
        this.password = password != null ? password : "";
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

    /**
     * 예약 정보 추가
     */
    public void addReservation(String movieTitle, int seatNumber) {
        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("영화 제목은 필수입니다.");
        }
        if (seatNumber <= 0) {
            throw new IllegalArgumentException("좌석 번호는 1 이상이어야 합니다.");
        }
        reservedMovies.add(movieTitle);
        reservedSeats.add(seatNumber);
        reservationNumbers.add(reservationCounter++);
    }
    
    /**
     * 예약 취소
     */
    public void cancelReservation(String movieTitle, int seatNumber) {
        int index = reservedMovies.indexOf(movieTitle);
        if (index != -1 && reservedSeats.get(index) == seatNumber) {
            reservedMovies.remove(index);
            reservedSeats.remove(index);
            reservationNumbers.remove(index);
        }
    }
    
    /**
     * 예약 내역 출력
     */
    public void showReservations() {
        System.out.println("\n예약 내역:");
        if (reservedMovies.isEmpty()) {
            System.out.println("예약 내역이 없습니다.");
            return;
        }
        for (int i = 0; i < reservedMovies.size(); i++) {
            System.out.println("예매번호: " + reservationNumbers.get(i) +
                               " | 영화: " + reservedMovies.get(i) +
                               " | 좌석: " + reservedSeats.get(i));
        }
    }

    /**
     * 모든 예약 정보 초기화
     */
    public void clearReservations() {
        reservedMovies.clear();
        reservedSeats.clear();
        reservationNumbers.clear();
    }

    /**
     * 특정 영화의 모든 예약 삭제
     */
    public void removeReservationsByMovie(String title) {
        for (int i = reservedMovies.size() - 1; i >= 0; i--) {
            if (reservedMovies.get(i).equals(title)) {
                reservedMovies.remove(i);
                reservedSeats.remove(i);
                reservationNumbers.remove(i);
            }
        }
    }

    /**
     * 결제 금액 추가
     */
    public void addToTotalPaid(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("결제 금액은 0 이상이어야 합니다.");
        }
        totalPaid += amount;
    }

    /**
     * 누적 결제 금액 반환
     */
    public int getTotalPaid() {
        return totalPaid;
    }

    /**
     * 예매번호 카운터 설정 (파일 로드 시 사용)
     */
    public static void setReservationCounter(int counter) {
        reservationCounter = counter;
    }

    /**
     * 예매번호 카운터 반환
     */
    public static int getReservationCounter() {
        return reservationCounter;
    }

    /**
     * 사용자 정보를 파일 저장용 문자열로 변환
     * 형식: 이름|비밀번호|총결제금액|예매번호카운터
     */
    public String toFileString() {
        return name + "|" + password + "|" + totalPaid + "|" + reservationCounter;
    }

    /**
     * 예약 정보를 파일 저장용 문자열로 변환
     * 형식: 예매번호1,예매번호2,...|영화1,영화2,...|좌석1,좌석2,...
     */
    public String reservationsToFileString() {
        StringBuilder numbers = new StringBuilder();
        StringBuilder movies = new StringBuilder();
        StringBuilder seats = new StringBuilder();
        
        for (int i = 0; i < reservationNumbers.size(); i++) {
            if (i > 0) {
                numbers.append(",");
                movies.append(",");
                seats.append(",");
            }
            numbers.append(reservationNumbers.get(i));
            movies.append(reservedMovies.get(i));
            seats.append(reservedSeats.get(i));
        }
        
        return numbers.toString() + "|" + movies.toString() + "|" + seats.toString();
    }
}
