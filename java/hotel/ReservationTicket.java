package a0212.hotel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReservationTicket {
    private ReservationManager reservationManager; // 예약 관리자

    // 기존 예약 관리자를 받아서 사용하도록 변경
    public ReservationTicket(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    public void printTicket(int reservationNum) {
        try {
            File dir = new File("d:\\hotelReservation");
            if(!dir.exists()) { // 폴더가 없으면
                dir.mkdir(); // 생성
            }
            File file = new File(dir, "ticket_" + reservationNum + ".txt");
            boolean isNewFile = file.createNewFile(); // 파일이 없으면 생성

            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                if(file.canWrite()) {
                    if(!isNewFile) {
                        bufferedWriter.newLine(); // 기존 파일이면 개행(줄바꿈) 추가
                    }
                    // 예약 정보를 가져와서 저장
                    String ticketInfo = reservationManager.getReservationDetails(reservationNum);
                    if (ticketInfo == null) {
                        System.out.println("예약 정보를 찾을 수 없습니다.");
                        return;
                    }
                    bufferedWriter.write(ticketInfo);
                    bufferedWriter.flush();
                    System.out.println("티켓 출력 성공");
                } else {
                    System.out.println("티켓 출력 실패: 권한 문제");
                }
            }
        } catch (Exception e) {
            System.out.println("티켓 출력 실패: " + e.getMessage());
        }
    }

    public void updateHotelList() {
        File file = new File("d:\\hotelReservation\\hotelList.txt");

        if (!file.exists()) {
            System.out.println("hotelList.txt 파일이 존재하지 않습니다.");
            return;
        }
        
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            System.out.println("======================================");
            
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue; // 빈줄일때는 건너뜀

                String[] hotels = line.split("/");
                
                if(hotels.length != 4) {
                    System.out.println("잘못된 형식의 호텔 정보: " + line);
                    continue; // 다음 줄로 넘어감
                }
                
                try {
                    String name = hotels[0];        // 호텔 이름
                    String location = hotels[1];    // 위치
                    int price = Integer.parseInt(hotels[2]);      // 가격
                    int roomCount = Integer.parseInt(hotels[3]);   // 방 개수
                    
                    // 중복 호텔 확인
                    if (reservationManager.getHotel(name) != null) {
                        System.out.println("중복된 호텔 [" + name + "] 은(는) 추가되지 않습니다.");
                        continue;
                    }
                    
                    // Hotel 객체 생성 및 추가
                    Hotel hotel = new Hotel(name, location, price, roomCount);
                    reservationManager.addHotel(hotel);
                    System.out.println("호텔 추가됨: " + hotel.getName());
                    
                } catch (NumberFormatException e) {
                    System.out.println("가격 또는 방 개수가 잘못된 숫자입니다: " + line);
                } catch (Exception e) {
                    System.out.println("호텔 추가 중 오류 발생: " + line + " - " + e.getMessage());
                }
            }
            
            System.out.println("======================================");
            
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }
}
