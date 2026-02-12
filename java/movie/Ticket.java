package movie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ticket {
    private ReservationManager reservationManager; // 예약 관리자

    // 기존 예약 관리자를 받아서 사용하도록 변경
    public Ticket(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    public void printTicket(int reservationNumber) {
        try {
            File dir = new File("d:\\movieTicket");
            if (!dir.exists()) {
                dir.mkdir(); // 폴더가 없으면 생성
            }

            File file = new File(dir, "ticket_"+reservationNumber+".txt");
            boolean isNewFile = file.createNewFile(); // 파일이 없으면 생성

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                if (file.canWrite()) {
                    if (!isNewFile) {
                        bufferedWriter.newLine(); // 기존 파일이면 개행 추가
                    }

                    // 예매 정보를 가져와서 저장
                    String ticketInfo = reservationManager.getReservationDetails(reservationNumber);
                    if (ticketInfo == null) {
                        System.out.println("예매 정보를 찾을 수 없습니다.");
                        return;
                    }

                    bufferedWriter.write(ticketInfo);
                    bufferedWriter.flush();
                    System.out.println("티겟 출력 성공");
                } else {
                    System.out.println("티겟 출력 실패: 권한 문제");
                }
            }
        } catch (Exception e) {
            System.out.println("티겟 출력 실패: " + e.getMessage());
        }
    }

    public void updateMovieList() {
        File file = new File("d:\\movieTicket\\movieList.txt");
    
        if (!file.exists()) {
            System.out.println("movieList.txt 파일이 존재하지 않습니다.");
            return;
        }
    
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            System.out.println("======================================");
    
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
    
                String[] movies = line.split("/");
    
                if (movies.length != 4) {
                    System.out.println("잘못된 형식의 영화 정보: " + line);
                    continue;
                }
    
                try {
                    String title = movies[0];
                    String genre = movies[1];
                    int totalSeats = Integer.parseInt(movies[2]);
                    int rows = Integer.parseInt(movies[3]);
    
                    // 중복 영화 확인
                    if (reservationManager.getMovie(title) != null) {
                        System.out.println("중복된 영화 [" + title + "] 은(는) 추가되지 않습니다.");
                        continue;
                    }
    
                    Movie movie = new Movie(title, genre, totalSeats, rows);
                    reservationManager.addMovie(movie);
    
                    System.out.println("영화 추가됨: " + movie.getTitle());
    
                } catch (NumberFormatException e) {
                    System.out.println("좌석 수 또는 행 수가 잘못된 숫자입니다: " + line);
                }
            }
    
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }
    
}
