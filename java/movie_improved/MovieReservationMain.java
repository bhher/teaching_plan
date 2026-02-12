package movie_improved;

import java.util.Scanner;

/**
 * 영화 예매 시스템 - 개선된 버전
 * 
 * 개선 사항:
 * 1. 데이터 영속성 (파일 저장/로드)
 * 2. 예외 처리 강화
 * 3. 코드 중복 제거
 * 4. 사용자 인증 개선
 */
public class MovieReservationMain {
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String DATA_DIR = "d:\\movieTicket";
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReservationManager manager = new ReservationManager();
        Ticket ticket = new Ticket(manager);
        DataManager dataManager = new DataManager(manager);

        // 프로그램 시작 시 저장된 데이터 로드
        System.out.println("=== 영화 예매 시스템 시작 ===");
        dataManager.loadMovies();
        dataManager.loadUsers();
        dataManager.loadSettings();
        System.out.println("데이터 로드 완료.\n");

        while (true) {
            System.out.println("\n=== 영화 예매 시스템 ===");
            System.out.println("1. 사용자 로그인");
            System.out.println("2. 운영자 로그인");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    userMenu(manager, sc);
                    // 사용자 메뉴 종료 시 데이터 저장
                    dataManager.saveAll();
                    break;
                case "2":
                    if (adminLogin(manager, ticket, sc)) {
                        // 운영자 메뉴 종료 시 데이터 저장
                        dataManager.saveAll();
                    }
                    break;
                case "0":
                    // 프로그램 종료 전 모든 데이터 저장
                    dataManager.saveAll();
                    System.out.println("시스템을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력하세요.");
            }
        }
    }

    /**
     * 사용자 메뉴
     */
    public static void userMenu(ReservationManager manager, Scanner sc) {
        End: while (true) {
            System.out.println("\n=== 영화관 예매 시스템 ===");
            System.out.println("1. 상영중인 영화 정보");
            System.out.println("2. 영화 예매");
            System.out.println("3. 예약 조회");
            System.out.println("4. 예약 취소");
            System.out.println("5. 예약 일괄 취소");
            System.out.println("6. 티켓 출력");
            System.out.println("0. 뒤로가기");
            System.out.print("선택: ");
            
            String input = sc.nextLine().trim();
            
            // 입력 검증
            if (input.isEmpty()) {
                System.out.println("값을 입력하세요.");
                continue;
            }
            
            int choice = -1;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    manager.showMovies();
                    break;
                case 2:
                    manager.showMovies();
                    manager.movieReservation();
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
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    /**
     * 운영자 로그인 및 메뉴
     * @return 로그인 성공 여부
     */
    public static boolean adminLogin(ReservationManager reservationManager, Ticket ticket, Scanner sc) {
        System.out.print("운영자 비밀번호를 입력하세요: ");
        String password = sc.nextLine().trim();
    
        if (!password.equals(ADMIN_PASSWORD)) {
            System.out.println("비밀번호가 틀렸습니다.");
            return false;
        }
    
        System.out.println("운영자 로그인 성공!");
        
        while (true) {
            System.out.println("\n=== 운영자 메뉴 ===");
            System.out.println("1. 영화 삭제");
            System.out.println("2. 상영 영화 갱신");
            System.out.println("3. 영화 정보 수정");
            System.out.println("4. 할인율 설정");
            System.out.println("5. 데이터 저장");
            System.out.println("0. 뒤로가기");
            System.out.print("선택: ");
            String choice = sc.nextLine().trim();
    
            switch (choice) {
                case "1":
                    reservationManager.showMovies();
                    reservationManager.deleteMovie(sc);
                    break;
                case "2":
                    ticket.updateMovieList();
                    System.out.println("영화 목록이 갱신되었습니다.");
                    break;
                case "3":
                    reservationManager.showMovies();
                    reservationManager.modifyMovieInfo(sc);
                    break;
                case "4":
                    reservationManager.setDiscountRate(sc);
                    break;
                case "5":
                    DataManager dataManager = new DataManager(reservationManager);
                    dataManager.saveAll();
                    System.out.println("데이터 저장 완료.");
                    break;
                case "0":
                    return true;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }
}
