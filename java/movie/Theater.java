package movie;

import java.util.ArrayList;

public class Theater {
    private ArrayList<String> seats;

    public Theater(int seatCount) {
        //초기 좌석 수에 따라 좌석 목록 생성
        seats = new ArrayList<>();
        for (int i = 0; i < seatCount; i++) {
            seats.add((i + 1) + "");
            //초기 좌석 번호 저장
        }
    }

    public boolean reserveSeat(int seatNumber) {
        if (seatNumber > 0 && seatNumber <= seats.size() && !seats.get(seatNumber - 1).equals("X")) {
            seats.set(seatNumber - 1, "X");
            //좌석 예약 시 "X"로 변경
            return true;
        }
        return false;
    }

    public void cancelSeat(int seatNumber) {
        if (seatNumber > 0 && seatNumber <= seats.size() && seats.get(seatNumber - 1).equals("X")) {
            seats.set(seatNumber - 1, String.valueOf(seatNumber));
            //예약 취소 시 원래 좌석 번호로 변경
        }
    }

    public void displaySeats() {
        System.out.println("\n좌석 배치 (예약된 좌석: X)");
        for (int i = 0; i < seats.size(); i++) {
            System.out.printf("%2s ", seats.get(i));
            // 좌석 번호 또는 "X" 출력
            if ((i + 1) % 10 == 0) System.out.println();
        }
    }

    public int getAvailableSeats() {
        int count = 0;
        for (String seat : seats) {
            if (!seat.equals("X")) count++;
            //예약되지 않은 좌석 수 카운트
        }
        return count;
    }
}
