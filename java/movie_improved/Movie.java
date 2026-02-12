package movie_improved;

import java.text.DecimalFormat;

/**
 * 영화 정보를 담는 클래스
 */
public class Movie {
    private String title;
    private String showtime;
    private int price;
    private Theater theater;
    private DecimalFormat priceFormat = new DecimalFormat("#,###원");

    public Movie(String title, String showtime, int price, int seatCount) {
        this.title = title;
        this.showtime = showtime;
        this.price = price;
        this.theater = new Theater(seatCount);
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getShowtime() {
        return showtime;
    }
    
    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }
        this.price = price;
    }
    
    public Theater getTheater() {
        return theater;
    }

    /**
     * 영화 정보를 파일 저장용 문자열로 변환
     * 형식: 제목/상영시간/가격/총좌석수
     */
    public String toFileString() {
        return title + "/" + showtime + "/" + price + "/" + theater.getTotalSeats();
    }

    @Override
    public String toString() {
        String formattedPrice = priceFormat.format(price);
        return "영화 제목: " + title + 
               " | 상영 시간: " + showtime + 
               " | 가격: " + formattedPrice + 
               " | 남은 좌석: " + theater.getAvailableSeats() + "석";
    }
}
