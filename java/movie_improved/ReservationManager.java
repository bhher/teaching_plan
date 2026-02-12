package movie_improved;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 예약 관리 핵심 클래스
 * 개선 사항:
 * 1. 예외 처리 강화
 * 2. 입력 검증 추가
 * 3. 코드 중복 제거
 */
public class ReservationManager {
    private ArrayList<Movie> movies;
    private ArrayList<User> users;
    private Scanner sc;
    private static Ticket ticket;
    private int discountRate = 0;

    public ReservationManager() {
        movies = new ArrayList<>();
        users = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    /**
     * 영화 추가
     */
    public void addMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("영화 객체는 null일 수 없습니다.");
        }
        // 중복 영화 확인
        if (getMovie(movie.getTitle()) != null) {
            throw new IllegalArgumentException("이미 존재하는 영화입니다: " + movie.getTitle());
        }
        movies.add(movie);
    }

    /**
     * 영화 목록 출력
     */
    public void showMovies() {
        System.out.println("\n현재 상영 중인 영화 목록");
        if (movies.isEmpty()) {
            System.out.println("상영 중인 영화가 없습니다.");
            return;
        }
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }
    }

    /**
     * 좌석 예약 처리
     */
    public boolean bookSeat(String userName, String movieTitle, int seatNumber) {
        // 입력 검증
        if (userName == null || userName.trim().isEmpty()) {
            System.out.println("사용자 이름을 입력하세요.");
            return false;
        }
        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            System.out.println("영화 제목을 입력하세요.");
            return false;
        }
        if (seatNumber <= 0) {
            System.out.println("올바른 좌석 번호를 입력하세요.");
            return false;
        }

        Movie movie = getMovie(movieTitle);
        if (movie == null) {
            System.out.println("해당 영화가 없습니다.");
            return false;
        }

        if (!movie.getTheater().reserveSeat(seatNumber)) {
            System.out.println("이미 예약된 좌석이거나 잘못된 좌석입니다.");
            return false;
        }

        User user = getUser(userName);
        if (user == null) {
            user = new User(userName);
            users.add(user);
        }

        user.addReservation(movieTitle, seatNumber);
        return true;
    }

    /**
     * 영화 제목으로 영화 객체 찾기
     */
    public Movie getMovie(String movieTitle) {
        if (movieTitle == null) {
            return null;
        }
        for (Movie movie : movies) {
            if (movie.getTitle().equals(movieTitle)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * 사용자 이름으로 사용자 객체 찾기
     */
    public User getUser(String userName) {
        if (userName == null) {
            return null;
        }
        for (User user : users) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    /**
     * 예약 조회
     */
    public void checkReservation() {
        System.out.print("사용자 이름 입력: ");
        String userName = sc.nextLine().trim();
        
        if (userName.isEmpty()) {
            System.out.println("사용자 이름을 입력하세요.");
            return;
        }
        
        User user = getUser(userName);
        
        if (user == null || user.getReservedMovies().isEmpty()) {
            System.out.println("예약된 내역이 없습니다.");
            return;
        }
        
        System.out.println("\n" + userName + "님의 예약 내역");
        for (int i = 0; i < user.getReservedMovies().size(); i++) {
            System.out.println("예매번호: " + user.getReservationNumbers().get(i) +
                               " | 영화: " + user.getReservedMovies().get(i) +
                               " | 좌석: " + user.getReservedSeats().get(i));
        }
        System.out.println("총 결제 금액: " + user.getTotalPaid() + "원");
    }

    /**
     * 예약 취소
     */
    public void cancelReservation() {
        System.out.print("사용자 이름 입력: ");
        String userName = sc.nextLine().trim();
        
        if (userName.isEmpty()) {
            System.out.println("사용자 이름을 입력하세요.");
            return;
        }
        
        User user = getUser(userName);
    
        if (user == null || user.getReservedMovies().isEmpty()) {
            System.out.println(userName + "님은 예약된 내역이 없습니다.");
            return;
        }
    
        user.showReservations();
        System.out.print("취소할 예매번호를 입력하세요: ");
        
        int reservationNumber = -1;
        try {
            reservationNumber = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요.");
            return;
        }
    
        int index = user.getReservationNumbers().indexOf(reservationNumber);
        if (index == -1) {
            System.out.println("해당 예매번호의 예약 내역이 없습니다.");
            return;
        }
    
        String movieTitle = user.getReservedMovies().get(index);
        int seatNumber = user.getReservedSeats().get(index);
        
        Movie movie = getMovie(movieTitle);
        if (movie != null) {
            movie.getTheater().cancelSeat(seatNumber);
            System.out.println("영화 [" + movieTitle + "] 좌석 [" + seatNumber + "] 예약이 취소되었습니다.");
        }
    
        user.cancelReservation(movieTitle, seatNumber);
    }

    /**
     * 영화 예매 프로세스
     */
    public void movieReservation() {
        System.out.print("영화 제목 입력: ");
        String title = sc.nextLine().trim();
        
        if (title.isEmpty()) {
            System.out.println("영화 제목을 입력하세요.");
            return;
        }
        
        System.out.print("사용자 이름 입력: ");
        String userName = sc.nextLine().trim();
        
        if (userName.isEmpty()) {
            System.out.println("사용자 이름을 입력하세요.");
            return;
        }
        
        Movie movie = getMovie(title);
        if (movie == null) {
            System.out.println("해당 영화가 없습니다.");
            return;
        }

        movie.getTheater().displaySeats();
        System.out.print("좌석 번호 선택: ");
        
        int seatNumber = -1;
        try {
            seatNumber = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요.");
            return;
        }

        if (bookSeat(userName, title, seatNumber)) {
            int price = movie.getPrice();
            int discount = (price * discountRate) / 100;
            int finalPrice = price - discount;
            
            System.out.println("\n예매가 완료되었습니다.");
            System.out.println("원가 : " + price + "원");
            System.out.println("할인율 : " + discountRate + "%");
            System.out.println("할인된 금액 : " + discount + "원");
            System.out.println("결제 금액 : " + finalPrice + "원");

            User user = getUser(userName);
            if (user != null) {
                user.addToTotalPaid(finalPrice);
            }
        }
    }

    /**
     * 티켓 출력
     */
    public void printTicket() {
        System.out.println("\n=== 티켓 출력 ===");
        System.out.print("예매 번호를 입력하세요 : ");
        
        int reservationNum = -1;
        try {
            reservationNum = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요.");
            return;
        }
    
        if (reservationNum <= 0) {
            System.out.println("올바른 예매번호를 입력하세요.");
            return;
        }
        
        if (ticket == null) {
            ticket = new Ticket(this);
        }
        
        ticket.printTicket(reservationNum);
    }

    /**
     * 예매번호로 예약 정보 조회
     */
    public String getReservationDetails(int reservationNumber) {
        for (User user : users) {
            if (user.getReservationNumbers().contains(reservationNumber)) {
                int index = user.getReservationNumbers().indexOf(reservationNumber);
                return "예매번호: " + reservationNumber +
                       " | 영화: " + user.getReservedMovies().get(index) +
                       " | 좌석: " + user.getReservedSeats().get(index);
            }
        }
        return null;
    }

    /**
     * 모든 예약 일괄 취소
     */
    public void cancelAllReservation() {
        System.out.print("사용자 이름 입력: ");
        String userName = sc.nextLine().trim();
        
        if (userName.isEmpty()) {
            System.out.println("사용자 이름을 입력하세요.");
            return;
        }
        
        User user = getUser(userName);
    
        if (user == null || user.getReservedMovies().isEmpty()) {
            System.out.println(userName + "님은 예약된 내역이 없습니다.");
            return;
        }
    
        // 모든 예약 정보 가져오기 (복사본)
        ArrayList<String> movies = new ArrayList<>(user.getReservedMovies());
        ArrayList<Integer> seats = new ArrayList<>(user.getReservedSeats());
        ArrayList<Integer> numbers = new ArrayList<>(user.getReservationNumbers());
    
        for (int i = 0; i < movies.size(); i++) {
            String movieTitle = movies.get(i);
            int seatNumber = seats.get(i);
            int reservationNumber = numbers.get(i);
    
            Movie movie = getMovie(movieTitle);
            if (movie != null) {
                movie.getTheater().cancelSeat(seatNumber);
                System.out.println("[" + reservationNumber + "] 영화 [" + movieTitle + "] 좌석 [" + seatNumber + "] 취소됨.");
            }
        }
    
        user.clearReservations();
        System.out.println("\n" + userName + "님의 모든 예약이 취소되었습니다.");
    }

    /**
     * 특정 영화의 모든 예약 취소 (코드 중복 제거)
     */
    private void cancelAllReservationsByMovie(String movieTitle) {
        Movie movie = getMovie(movieTitle);
        if (movie == null) {
            return;
        }
        
        for (User user : users) {
            ArrayList<String> reservedMovies = user.getReservedMovies();
            if (reservedMovies.contains(movieTitle)) {
                ArrayList<Integer> reservationNumbers = new ArrayList<>(user.getReservationNumbers());
                for (int i = 0; i < reservationNumbers.size(); i++) {
                    if (user.getReservedMovies().get(i).equals(movieTitle)) {
                        int seatNumber = user.getReservedSeats().get(i);
                        movie.getTheater().cancelSeat(seatNumber);
                        System.out.println("[" + reservationNumbers.get(i) + "] 예매도 함께 취소되었습니다.");
                    }
                }
                user.removeReservationsByMovie(movieTitle);
            }
        }
    }

    /**
     * 영화 삭제
     */
    public void deleteMovie(Scanner sc) {
        System.out.print("삭제할 영화 제목을 입력하세요: ");
        String title = sc.nextLine().trim();
        
        if (title.isEmpty()) {
            System.out.println("영화 제목을 입력하세요.");
            return;
        }
        
        Movie movie = getMovie(title);
        if (movie == null) {
            System.out.println("해당 영화가 존재하지 않습니다.");
            return;
        }
        
        // 공통 메서드 사용
        cancelAllReservationsByMovie(title);
        
        movies.remove(movie);
        System.out.println("[" + title + "] 영화가 삭제되었습니다.");
    }

    /**
     * 영화 정보 수정
     */
    public void modifyMovieInfo(Scanner sc) {
        System.out.print("수정할 영화 제목을 입력하세요: ");
        String title = sc.nextLine().trim();
        
        if (title.isEmpty()) {
            System.out.println("영화 제목을 입력하세요.");
            return;
        }
        
        Movie movie = getMovie(title);
        if (movie == null) {
            System.out.println("해당 영화가 없습니다.");
            return;
        }

        System.out.println("현재 정보: " + movie.getTitle() + ", " + movie.getShowtime() + ", " + movie.getPrice() + "원");
        System.out.println("수정할 정보를 입력하세요. (빈 입력 시 기존 정보 유지, 모든 예매는 취소됩니다)");
        
        // 공통 메서드 사용
        cancelAllReservationsByMovie(title);
        
        System.out.print("새 제목: ");
        String newTitle = sc.nextLine().trim();
        if (newTitle.isEmpty()) {
            newTitle = movie.getTitle();
        }
        
        System.out.print("새 상영 시간: ");
        String newTime = sc.nextLine().trim();
        if (newTime.isEmpty()) {
            newTime = movie.getShowtime();
        }
        
        System.out.print("새 가격: ");
        String priceInput = sc.nextLine().trim();
        int newPrice = movie.getPrice();
        if (!priceInput.isEmpty()) {
            try {
                newPrice = Integer.parseInt(priceInput);
                if (newPrice < 0) {
                    System.out.println("가격은 0 이상이어야 합니다. 기존 가격을 유지합니다.");
                    newPrice = movie.getPrice();
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 가격은 숫자여야 합니다. 기존 가격을 유지합니다.");
            }
        }

        System.out.print("새 좌석 수: ");
        String seatInput = sc.nextLine().trim();
        int newSeats = movie.getTheater().getTotalSeats();
        if (!seatInput.isEmpty()) {
            try {
                newSeats = Integer.parseInt(seatInput);
                if (newSeats <= 0) {
                    System.out.println("좌석 수는 1 이상이어야 합니다. 기존 좌석 수를 유지합니다.");
                    newSeats = movie.getTheater().getTotalSeats();
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 좌석 수는 숫자여야 합니다. 기존 좌석 수를 유지합니다.");
            }
        }

        // 새로운 Movie 객체로 대체
        Movie updatedMovie = new Movie(newTitle, newTime, newPrice, newSeats);
        movies.remove(movie);
        movies.add(updatedMovie);

        System.out.println("영화 정보가 수정되었습니다.");
    }

    /**
     * 할인율 설정
     */
    public void setDiscountRate(Scanner sc) {
        System.out.println("현재 할인율: " + discountRate + "%");
        System.out.print("할인율(%)을 입력하세요 (0-100): ");
        
        try {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("할인율을 입력하세요.");
                return;
            }
            
            int rate = Integer.parseInt(input);
            if (rate < 0 || rate > 100) {
                System.out.println("할인율은 0-100 사이의 값이어야 합니다.");
                return;
            }
            
            discountRate = rate;
            System.out.println("할인율이 " + discountRate + "%로 설정되었습니다.");
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
        }
    }

    public int getDiscountRate() {
        return discountRate;
    }

    // Getter 메서드 (DataManager에서 사용)
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setDiscountRate(int rate) {
        if (rate < 0 || rate > 100) {
            throw new IllegalArgumentException("할인율은 0-100 사이여야 합니다.");
        }
        this.discountRate = rate;
    }
}
