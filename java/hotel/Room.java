package a0212.hotel;

import java.util.ArrayList;

public class Room {
    private ArrayList<String> rooms;
    
    public Room(int roomCount) {
        rooms = new ArrayList<>();
        for(int i = 0; i < roomCount; i++) {
            rooms.add((i+1) + ""); // 문자로 저장
            // 초기 방 번호 저장
        }
    }
    
    public int getAvailableRooms() {
        int count = 0;
        for(String room : rooms) {
            if(!room.equals("X")) count++;
            // 예약되지 않은 방 수 카운트
        }
        return count;
    }
    
    public void displayRooms() {
        System.out.println("\n 방 배치 (예약된 방: X)");
        for(int i = 0; i < rooms.size(); i++) {
            System.out.printf("%2s ", rooms.get(i));
            // 방 번호 또는 "X" 출력
            if((i+1) % 10 == 0) System.out.println(); // 10개가 채워지면 줄바꿈
        }
        System.out.println();
    }
    
    public boolean reserveRoom(int roomNumber) {
        if(roomNumber > 0 && roomNumber <= rooms.size() && !rooms.get(roomNumber - 1).equals("X")) {
            rooms.set(roomNumber - 1, "X");
            // 방 예약시 인덱스 번호 해당하는 값을 "X" 변경
            return true;
        }
        return false;
    }
    
    public void cancelRoom(int roomNumber) {
        if(roomNumber > 0 && roomNumber <= rooms.size() && rooms.get(roomNumber - 1).equals("X")) {
            rooms.set(roomNumber - 1, String.valueOf(roomNumber)); // roomNumber를 문자로 변경 저장
            // 예약 취소시 원래 방 번호로 변경
        }
    }
}
