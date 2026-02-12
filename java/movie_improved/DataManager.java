package movie_improved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 데이터 영속성 관리 클래스
 * 개선 사항: 파일로 데이터 저장 및 로드
 */
public class DataManager {
    private ReservationManager manager;
    private static final String DATA_DIR = "d:\\movieTicket";
    private static final String MOVIES_FILE = DATA_DIR + "\\movies.txt";
    private static final String USERS_FILE = DATA_DIR + "\\users.txt";
    private static final String RESERVATIONS_FILE = DATA_DIR + "\\reservations.txt";
    private static final String SETTINGS_FILE = DATA_DIR + "\\settings.txt";

    public DataManager(ReservationManager manager) {
        this.manager = manager;
        ensureDataDirectory();
    }

    /**
     * 데이터 디렉토리 생성
     */
    private void ensureDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 모든 데이터 저장
     */
    public void saveAll() {
        saveMovies();
        saveUsers();
        saveReservations();
        saveSettings();
    }

    /**
     * 영화 목록 저장
     * 형식: 제목/상영시간/가격/총좌석수|좌석상태
     */
    public void saveMovies() {
        try {
            File file = new File(MOVIES_FILE);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                for (Movie movie : manager.getMovies()) {
                    writer.write(movie.toFileString());
                    writer.write("|");
                    writer.write(movie.getTheater().toFileString());
                    writer.newLine();
                }
            }
            System.out.println("영화 목록 저장 완료.");
        } catch (IOException e) {
            System.out.println("영화 목록 저장 실패: " + e.getMessage());
        }
    }

    /**
     * 영화 목록 로드
     */
    public void loadMovies() {
        File file = new File(MOVIES_FILE);
        if (!file.exists()) {
            System.out.println("영화 목록 파일이 없습니다. 기본 영화를 추가합니다.");
            // 기본 영화 추가
            manager.addMovie(new Movie("Inception", "18:00", 12000, 50));
            manager.addMovie(new Movie("Interstellar", "20:30", 15000, 50));
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    System.out.println("잘못된 형식의 영화 데이터: " + line);
                    continue;
                }

                String[] movieInfo = parts[0].split("/");
                if (movieInfo.length != 4) {
                    System.out.println("잘못된 형식의 영화 정보: " + parts[0]);
                    continue;
                }

                try {
                    String title = movieInfo[0];
                    String showtime = movieInfo[1];
                    int price = Integer.parseInt(movieInfo[2]);
                    int seatCount = Integer.parseInt(movieInfo[3]);

                    Movie movie = new Movie(title, showtime, price, seatCount);
                    // 좌석 상태 복원
                    movie.getTheater().loadFromFileString(parts[1]);
                    manager.addMovie(movie);
                    count++;
                } catch (Exception e) {
                    System.out.println("영화 데이터 로드 실패: " + line + " - " + e.getMessage());
                }
            }
            System.out.println("영화 목록 로드 완료: " + count + "개");
        } catch (IOException e) {
            System.out.println("영화 목록 로드 실패: " + e.getMessage());
        }
    }

    /**
     * 사용자 정보 저장
     * 형식: 이름|비밀번호|총결제금액|예매번호카운터
     */
    public void saveUsers() {
        try {
            File file = new File(USERS_FILE);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                for (User user : manager.getUsers()) {
                    writer.write(user.toFileString());
                    writer.newLine();
                }
                // 예매번호 카운터 저장
                writer.write("COUNTER|" + User.getReservationCounter());
                writer.newLine();
            }
            System.out.println("사용자 정보 저장 완료.");
        } catch (IOException e) {
            System.out.println("사용자 정보 저장 실패: " + e.getMessage());
        }
    }

    /**
     * 사용자 정보 로드
     */
    public void loadUsers() {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            System.out.println("사용자 정보 파일이 없습니다.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // 예매번호 카운터 처리
                if (line.startsWith("COUNTER|")) {
                    try {
                        int counter = Integer.parseInt(line.split("\\|")[1]);
                        User.setReservationCounter(counter);
                        continue;
                    } catch (Exception e) {
                        System.out.println("예매번호 카운터 로드 실패: " + line);
                    }
                }

                String[] parts = line.split("\\|");
                if (parts.length != 4) {
                    System.out.println("잘못된 형식의 사용자 데이터: " + line);
                    continue;
                }

                try {
                    String name = parts[0];
                    String password = parts[1];
                    int totalPaid = Integer.parseInt(parts[2]);
                    int counter = Integer.parseInt(parts[3]);

                    User user = new User(name, password);
                    // 총 결제 금액 복원
                    for (int i = 0; i < totalPaid / 10000; i++) {
                        user.addToTotalPaid(10000); // 대략적인 복원
                    }
                    manager.getUsers().add(user);
                    count++;
                } catch (Exception e) {
                    System.out.println("사용자 데이터 로드 실패: " + line + " - " + e.getMessage());
                }
            }
            System.out.println("사용자 정보 로드 완료: " + count + "명");
        } catch (IOException e) {
            System.out.println("사용자 정보 로드 실패: " + e.getMessage());
        }
    }

    /**
     * 예약 정보 저장
     * 형식: 사용자이름|예매번호1,예매번호2,...|영화1,영화2,...|좌석1,좌석2,...
     */
    public void saveReservations() {
        try {
            File file = new File(RESERVATIONS_FILE);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                for (User user : manager.getUsers()) {
                    if (!user.getReservedMovies().isEmpty()) {
                        writer.write(user.getName() + "|" + user.reservationsToFileString());
                        writer.newLine();
                    }
                }
            }
            System.out.println("예약 정보 저장 완료.");
        } catch (IOException e) {
            System.out.println("예약 정보 저장 실패: " + e.getMessage());
        }
    }

    /**
     * 예약 정보 로드
     */
    public void loadReservations() {
        File file = new File(RESERVATIONS_FILE);
        if (!file.exists()) {
            System.out.println("예약 정보 파일이 없습니다.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length != 4) {
                    System.out.println("잘못된 형식의 예약 데이터: " + line);
                    continue;
                }

                try {
                    String userName = parts[0];
                    String[] numbers = parts[1].split(",");
                    String[] movies = parts[2].split(",");
                    String[] seats = parts[3].split(",");

                    if (numbers.length != movies.length || numbers.length != seats.length) {
                        System.out.println("예약 데이터 불일치: " + line);
                        continue;
                    }

                    User user = manager.getUser(userName);
                    if (user == null) {
                        System.out.println("사용자를 찾을 수 없습니다: " + userName);
                        continue;
                    }

                    for (int i = 0; i < numbers.length; i++) {
                        if (!numbers[i].isEmpty() && !movies[i].isEmpty() && !seats[i].isEmpty()) {
                            try {
                                int seatNum = Integer.parseInt(seats[i].trim());
                                user.addReservation(movies[i].trim(), seatNum);
                                count++;
                            } catch (NumberFormatException e) {
                                System.out.println("잘못된 좌석 번호: " + seats[i]);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("예약 데이터 로드 실패: " + line + " - " + e.getMessage());
                }
            }
            System.out.println("예약 정보 로드 완료: " + count + "개");
        } catch (IOException e) {
            System.out.println("예약 정보 로드 실패: " + e.getMessage());
        }
    }

    /**
     * 설정 정보 저장 (할인율 등)
     */
    public void saveSettings() {
        try {
            File file = new File(SETTINGS_FILE);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                writer.write("DISCOUNT_RATE=" + manager.getDiscountRate());
                writer.newLine();
            }
            System.out.println("설정 정보 저장 완료.");
        } catch (IOException e) {
            System.out.println("설정 정보 저장 실패: " + e.getMessage());
        }
    }

    /**
     * 설정 정보 로드
     */
    public void loadSettings() {
        File file = new File(SETTINGS_FILE);
        if (!file.exists()) {
            System.out.println("설정 파일이 없습니다. 기본 설정을 사용합니다.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || !line.startsWith("DISCOUNT_RATE=")) {
                    continue;
                }

                try {
                    int rate = Integer.parseInt(line.split("=")[1]);
                    manager.setDiscountRate(rate);
                    System.out.println("할인율 로드 완료: " + rate + "%");
                } catch (Exception e) {
                    System.out.println("할인율 로드 실패: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("설정 정보 로드 실패: " + e.getMessage());
        }
    }
}
