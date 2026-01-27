package a0127.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
    private ArrayList<Account> accounts;
    private String filename;
    
    public AccountManager() {
        this.accounts = new ArrayList<>();
        this.filename = "c:/Users/TJ/memo/account.txt";
    }
    
    // 출력 디렉토리(폴더) 확인 및 생성
    private void ensureDirectory() {
        File file = new File(filename);
        File parentDir = file.getParentFile(); // c:/Users/TJ/memo/
        if (parentDir != null && !parentDir.exists()) { // 폴더가 없으면
            parentDir.mkdirs(); // 폴더를 만들어라
            System.out.println("디렉토리가 생성되었습니다: " + parentDir.getPath());
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountManager manager = new AccountManager();
        
        while (true) {
            System.out.println("\n=== 은행 계좌 관리 시스템 ===");
            System.out.println("1. 계좌 생성");
            System.out.println("2. 입금");
            System.out.println("3. 출금");
            System.out.println("4. 계좌 조회");
            System.out.println("5. 전체 계좌 조회");
            System.out.println("6. 계좌 삭제");
            System.out.println("7. 파일 저장");
            System.out.println("8. 파일 불러오기");
            System.out.println("9. 종료");
            System.out.print("선택 > ");
            
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // 버퍼 비우기
            } catch (Exception e) {
                System.out.println("숫자만 입력하세요.");
                sc.nextLine(); // 잘못된 입력 제거
                continue;
            }
            
            switch (choice) {
                case 1:
                    System.out.print("계좌번호 입력: ");
                    String accountNumber = sc.nextLine();
                    System.out.print("예금주명 입력: ");
                    String ownerName = sc.nextLine();
                    System.out.print("초기 잔액 입력: ");
                    try {
                        int initialBalance = sc.nextInt();
                        sc.nextLine();
                        manager.createAccount(accountNumber, ownerName, initialBalance);
                    } catch (Exception e) {
                        System.out.println("잔액은 숫자로 입력하세요.");
                        sc.nextLine();
                    }
                    break;
                case 2:
                    System.out.print("계좌번호 입력: ");
                    String depositAccount = sc.nextLine();
                    System.out.print("입금할 금액 입력: ");
                    try {
                        int depositAmount = sc.nextInt();
                        sc.nextLine();
                        manager.deposit(depositAccount, depositAmount);
                    } catch (Exception e) {
                        System.out.println("금액은 숫자로 입력하세요.");
                        sc.nextLine();
                    }
                    break;
                case 3:
                    System.out.print("계좌번호 입력: ");
                    String withdrawAccount = sc.nextLine();
                    System.out.print("출금할 금액 입력: ");
                    try {
                        int withdrawAmount = sc.nextInt();
                        sc.nextLine();
                        manager.withdraw(withdrawAccount, withdrawAmount);
                    } catch (Exception e) {
                        System.out.println("금액은 숫자로 입력하세요.");
                        sc.nextLine();
                    }
                    break;
                case 4:
                    System.out.print("계좌번호 입력: ");
                    String searchAccount = sc.nextLine();
                    manager.findAccount(searchAccount);
                    break;
                case 5:
                    manager.printAll();
                    break;
                case 6:
                    System.out.print("삭제할 계좌번호 입력: ");
                    String deleteAccount = sc.nextLine();
                    manager.deleteAccount(deleteAccount);
                    break;
                case 7:
                    manager.saveToFile();
                    break;
                case 8:
                    manager.loadFromFile();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    return;
                default:
                    System.out.println("1~9번 중에 선택하세요.");
            }
        }
    }
    
    // 계좌 찾기 (계좌번호로)
    private Account findAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
    
    // 계좌 생성
    private void createAccount(String accountNumber, String ownerName, int initialBalance) {
        // 계좌번호 중복 체크
        if (findAccountByNumber(accountNumber) != null) {
            System.out.println("이미 존재하는 계좌번호입니다.");
            return;
        }
        
        accounts.add(new Account(accountNumber, ownerName, initialBalance));
        System.out.println("계좌가 생성되었습니다.");
    }
    
    // 입금
    private void deposit(String accountNumber, int amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("입금 완료! 현재 잔액: " + account.getBalance() + "원");
            saveToFile(); // 입금 후 자동 저장
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }
    
    // 출금
    private void withdraw(String accountNumber, int amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            if (account.withdraw(amount)) {
                System.out.println("출금 완료! 현재 잔액: " + account.getBalance() + "원");
                saveToFile(); // 출금 후 자동 저장
            } else {
                System.out.println("잔액이 부족합니다. 현재 잔액: " + account.getBalance() + "원");
            }
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }
    
    // 계좌 조회
    private void findAccount(String accountNumber) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            System.out.println("=== 계좌 정보 ===");
            System.out.println("계좌번호: " + account.getAccountNumber());
            System.out.println("예금주명: " + account.getOwnerName());
            System.out.println("잔액: " + account.getBalance() + "원");
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }
    
    // 전체 계좌 조회
    private void printAll() {
        if (accounts.isEmpty()) {
            System.out.println("등록된 계좌가 없습니다.");
            return;
        }
        System.out.println("=== 전체 계좌 목록 ===");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i));
        }
    }
    
    // 계좌 삭제
    private void deleteAccount(String accountNumber) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            accounts.remove(account);
            System.out.println("계좌가 삭제되었습니다.");
            saveToFile(); // 삭제 후 자동 저장
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }
    
    // 파일에서 계좌 정보 읽기
    private void loadFromFile() {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("파일이 없습니다. 새로 시작합니다.");
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Account account = Account.fromFileString(line); // 123-456-789|홍길동|100000
                    if (account != null) {
                        accounts.add(account);
                    }
                }
            }
            System.out.println("파일 불러오기 완료! (" + accounts.size() + "개 계좌)");
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }
    
    // 파일에 계좌 정보 저장
    private void saveToFile() {
        // 디렉토리 확인 및 생성
        ensureDirectory();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Account account : accounts) {
                bw.write(account.toFileString());
                bw.newLine(); // 줄바꿈
            }
            System.out.println("파일 저장 완료: " + filename);
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage());
        }
    }
}
