package movie_improved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 티켓 출력 및 파일 입출력 처리 클래스
 * 개선 사항:
 * 1. 예외 처리 강화
 * 2. 파일 경로 상수화
 */
public class Ticket {
    private ReservationManager reservationManager;
    private static final String TICKET_DIR = "d:\\movieTicket";
    private static final String MOVIE_LIST_FILE = "d:\\movieTicket\\movieList.txt";

    public Ticket(ReservationManager reservationManager) {
        if (reservationManager == null) {
            throw new IllegalArgumentException("ReservationManager는 null일 수 없습니다.");
        }
        this.reservationManager = reservationManager;
    }

    /**
     * 티켓 파일 출력
     */
    public void printTicket(int reservationNumber) {
        try {
            File dir = new File(TICKET_DIR);
            if (!dir.exists()) {
                boolean created = dir.mkdirs();
                if (!created) {
                    System.out.println("티켓 디렉토리 생성 실패");
                    return;
                }
            }

            File file = new File(dir, "ticket_" + reservationNumber + ".txt");
            boolean isNewFile = file.createNewFile();

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                if (!isNewFile) {
                    bufferedWriter.newLine();
                }

                String ticketInfo = reservationManager.getReservationDetails(reservationNumber);
                if (ticketInfo == null) {
                    System.out.println("예매 정보를 찾을 수 없습니다.");
                    return;
                }

                bufferedWriter.write("=== 영화 티켓 ===");
                bufferedWriter.newLine();
                bufferedWriter.write(ticketInfo);
                bufferedWriter.newLine();
                bufferedWriter.write("발행일시: " + java.time.LocalDateTime.now());
                bufferedWriter.flush();
                System.out.println("티켓 출력 성공: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("티켓 출력 실패: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("티켓 출력 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 파일에서 영화 목록 읽어오기
     */
    public void updateMovieList() {
        File file = new File(MOVIE_LIST_FILE);
    
        if (!file.exists()) {
            System.out.println("movieList.txt 파일이 존재하지 않습니다.");
            System.out.println("파일 경로: " + file.getAbsolutePath());
            return;
        }
    
        int successCount = 0;
        int failCount = 0;
        
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            
            String line;
            System.out.println("======================================");
            System.out.println("영화 목록 파일 읽기 시작");
            System.out.println("======================================");
    
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // 빈 줄이나 주석 건너뛰기
                }
    
                String[] parts = line.split("/");
    
                if (parts.length != 4) {
                    System.out.println("⚠ 잘못된 형식의 영화 정보: " + line);
                    failCount++;
                    continue;
                }
    
                try {
                    String title = parts[0].trim();
                    String showtime = parts[1].trim();
                    int price = Integer.parseInt(parts[2].trim());
                    int seatCount = Integer.parseInt(parts[3].trim());
    
                    // 입력 검증
                    if (title.isEmpty()) {
                        System.out.println("⚠ 영화 제목이 비어있습니다: " + line);
                        failCount++;
                        continue;
                    }
                    if (price < 0) {
                        System.out.println("⚠ 가격이 음수입니다: " + line);
                        failCount++;
                        continue;
                    }
                    if (seatCount <= 0) {
                        System.out.println("⚠ 좌석 수가 0 이하입니다: " + line);
                        failCount++;
                        continue;
                    }
    
                    // 중복 영화 확인
                    if (reservationManager.getMovie(title) != null) {
                        System.out.println("⚠ 중복된 영화 [" + title + "] 은(는) 추가되지 않습니다.");
                        failCount++;
                        continue;
                    }
    
                    Movie movie = new Movie(title, showtime, price, seatCount);
                    reservationManager.addMovie(movie);
                    System.out.println("✅ 영화 추가됨: " + movie.getTitle());
                    successCount++;
    
                } catch (NumberFormatException e) {
                    System.out.println("⚠ 좌석 수 또는 가격이 잘못된 숫자입니다: " + line);
                    failCount++;
                } catch (IllegalArgumentException e) {
                    System.out.println("⚠ " + e.getMessage() + ": " + line);
                    failCount++;
                }
            }
            
            System.out.println("======================================");
            System.out.println("영화 목록 읽기 완료");
            System.out.println("성공: " + successCount + "개, 실패: " + failCount + "개");
            System.out.println("======================================");
    
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("예상치 못한 오류 발생: " + e.getMessage());
        }
    }
}
