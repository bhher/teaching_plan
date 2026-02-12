package movie_improved;

import java.util.ArrayList;

/**
 * 상영관 좌석 관리 클래스
 */
public class Theater {
    private ArrayList<String> seats;

    public Theater(int seatCount) {
        if (seatCount <= 0) {
            throw new IllegalArgumentException("좌석 수는 1 이상이어야 합니다.");
        }
        seats = new ArrayList<>();
        for (int i = 0; i < seatCount; i++) {
            seats.add(String.valueOf(i + 1));
        }
    }

    /**
     * 좌석 예약
     * @param seatNumber 좌석 번호
     * @return 예약 성공 여부
     */
    public boolean reserveSeat(int seatNumber) {
        if (seatNumber <= 0 || seatNumber > seats.size()) {
            return false;
        }
        if (seats.get(seatNumber - 1).equals("X")) {
            return false; // 이미 예약됨
        }
        seats.set(seatNumber - 1, "X");
        return true;
    }

    /**
     * 좌석 예약 취소
     * @param seatNumber 좌석 번호
     */
    public void cancelSeat(int seatNumber) {
        if (seatNumber > 0 && seatNumber <= seats.size() && seats.get(seatNumber - 1).equals("X")) {
            seats.set(seatNumber - 1, String.valueOf(seatNumber));
        }
    }

    /**
     * 좌석 배치 출력
     */
    public void displaySeats() {
        System.out.println("\n좌석 배치 (예약된 좌석: X)");
        for (int i = 0; i < seats.size(); i++) {
            System.out.printf("%2s ", seats.get(i));
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
        if (seats.size() % 10 != 0) {
            System.out.println();
        }
    }

    /**
     * 예약 가능한 좌석 수 반환
     */
    public int getAvailableSeats() {
        int count = 0;
        for (String seat : seats) {
            if (!seat.equals("X")) {
                count++;
            }
        }
        return count;
    }

    /**
     * 총 좌석 수 반환
     */
    public int getTotalSeats() {
        return seats.size();
    }

    /**
     * 좌석 상태를 파일 저장용 문자열로 변환
     * 형식: 좌석1,좌석2,좌석3,... (예약된 좌석은 X)
     */
    public String toFileString() {
        return String.join(",", seats);
    }

    /**
     * 파일에서 좌석 상태 복원
     * @param seatData 파일에서 읽은 좌석 데이터
     */
    public void loadFromFileString(String seatData) {
        if (seatData == null || seatData.isEmpty()) {
            return;
        }
        String[] seatArray = seatData.split(",");
        seats.clear();
        for (String seat : seatArray) {
            seats.add(seat.trim());
        }
    }
}
