package a0402.javaair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class FileC {

    private FlightManager fm = new FlightManager();

    public void ticketSaveFile(Map<String, Flight> reservationMap, String name) {
        //이어 작성하기 구현 및 폴더 까지 생성 구현
        // try {
        //     File file = new File("d:\\ticket\\ticket.txt");
        //     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        //     //BufferedWriter를 사용하여 file에 데이터를 쓸 준비하는것
        //     //FileWriter는 기본적으로 기존 파일을 덮어씀
        //     if(file.isFile() && file.canWrite()){
        //         //.canWrite - 쓰기 권한이 있는지 확인 true - 쓰기 권한 있음
        //         bufferedWriter.write(fm.ticketPrint(reservationMap, name));//티겟 정보를 file에 작성
        //         bufferedWriter.flush();//버퍼에 있는 데이터를 파일에 저장
        //         System.out.println("파일 저장 성공");
        //         bufferedWriter.close();//버퍼 닫기
        //     } else {
        //         file = new File("d:\\ticket");
        //         file.mkdir();// 디렉토리에 폴더 생성
        //         file = new File("d:\\ticket"+"\\"+"ticket.txt");
        //         file.createNewFile();
        //     }
        // } catch (IOException e) {
        //     System.out.println("파일 저장 실패");
        // }

        try {
            File dir = new File("d:\\ticket");
            if (!dir.exists()) {
                dir.mkdir(); // 폴더가 없으면 생성
            }
    
            File file = new File(dir, "ticket.txt");
            boolean isNewFile = file.createNewFile(); // 파일이 없으면 생성
    
            // append 모드 활성화
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
    
            if (file.canWrite()) {
                // 기존 파일이 있으면 개행 후 추가
                if (!isNewFile) {
                    bufferedWriter.newLine();
                }
                bufferedWriter.write(fm.ticketPrint(reservationMap, name));
                bufferedWriter.flush();
                System.out.println("파일 저장 성공");
            }
    
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("파일 저장 실패: ");
        }
    }

    public void upload() {
        try {
            File file = new File("d:\\ticket\\schedule.txt");
            // BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String line;
            System.out.println("===============================================================");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String[] flight = line.split("/");// (/)로 끊어서 배열로 만들고 그 정보를 Flight에 넣는것
                FlightManager.getFlights().add(new Flight(flight[0], flight[1], Integer.parseInt(flight[2]), Boolean.parseBoolean(flight[3])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("schedule.txt 파일이 존재하지 않음");
        } catch (IOException e) {
            System.out.println("파일 읽기 실패");
        }
    }
}
