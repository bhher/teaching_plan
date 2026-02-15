package a0402.javaair;

import java.time.LocalDate;
import java.time.Period;

public class Passenger {
    private String name;
    private int birthDate;
    private String pw;
    private String seat;
    
    public Passenger(String name, int birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Passenger(String name, int birthDate, String pw) {
        this.name = name;
        this.birthDate = birthDate;
        this.pw = pw;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public String getSeat() {
        return seat;
    }
    public void setSeat(String seat) {
        this.seat = seat;
    }
    //15세 이상만 국제선 예약이 가능
    public boolean man15(Passenger p){
        int y = p.birthDate / 10000;// 831016이라면 83만 남음
        int m = (p.birthDate % 10000) /100; // 1016에 100나눠서 10만 남음
        int d = p.birthDate % 100; //16만 남음

        if(y > 0 && y <= 25){
            y = y +2000;
        } else {
            y += 1900;
        }
        //1983/10/16
        LocalDate birthDate2 = LocalDate.of(y, m, d);//생년월일
        LocalDate currentDate = LocalDate.now();//오늘 년월일
        int age = Period.between(birthDate2, currentDate).getYears();//생년월일과 오늘의 년월일을 비교해서 연도만 빼서 연령을 구함
        return age > 15;
    }
    /*
        public boolean isEligibleForInternationalFlight() {
            int y = birthDate / 10000;
            int m = (birthDate % 10000) / 100;
            int d = birthDate % 100;

            y = (y > 0 && y <= 24) ? y + 2000 : y + 1900;

            LocalDate birthDate2 = LocalDate.of(y, m, d);
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(birthDate2, currentDate).getYears();

            return age > 15;
        }
     */
}
