# 계좌 관리 프로그램 정답 (ArrayList)

문제: [계좌관리-Account-BankApplication-ArrayList-문제.md](./계좌관리-Account-BankApplication-ArrayList-문제.md)

---

## Account.java

```java
public class Account {
    private String ano;
    private String owner;
    private int balance;

    public Account(String ano, String owner, int balance) {
        this.ano = ano;
        this.owner = owner;
        this.balance = balance;
    }

    public String getAno() { return ano; }
    public void setAno(String ano) { this.ano = ano; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }
}
```

---

## BankApplication.java

```java
import java.util.ArrayList;
import java.util.Scanner;

public class BankApplication {
    private static ArrayList<Account> accountList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 더미 데이터 3개
        accountList.add(new Account("111-111", "홍길동", 10000));
        accountList.add(new Account("111-222", "강자바", 20000));
        accountList.add(new Account("111-333", "이코딩", 30000));

        boolean run = true;

        while (run) {
            System.out.println("----------------------------------------------------------");
            System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
            System.out.println("----------------------------------------------------------");
            System.out.print("선택> ");

            int selectNo = Integer.parseInt(scanner.nextLine());

            if (selectNo == 1) {
                createAccount();
            } else if (selectNo == 2) {
                accountList();
            } else if (selectNo == 3) {
                deposit();
            } else if (selectNo == 4) {
                withdraw();
            } else if (selectNo == 5) {
                run = false;
            }
        }
        System.out.println("프로그램 종료");
    }

    private static void createAccount() {
        System.out.println("--------");
        System.out.println("계좌생성");
        System.out.println("--------");

        System.out.print("계좌번호: ");
        String ano = scanner.nextLine();
        System.out.print("계좌주: ");
        String owner = scanner.nextLine();
        System.out.print("초기입금액: ");
        int balance = Integer.parseInt(scanner.nextLine());

        accountList.add(new Account(ano, owner, balance));
        System.out.println("결과: 계좌가 생성되었습니다.");
    }

    private static void accountList() {
        System.out.println("--------");
        System.out.println("계좌목록");
        System.out.println("--------");

        for (Account account : accountList) {
            System.out.println(account.getAno() + "     "
                    + account.getOwner() + "    "
                    + account.getBalance());
        }
    }

    private static void deposit() {
        System.out.println("--------");
        System.out.println("예금");
        System.out.println("--------");

        System.out.print("계좌번호: ");
        String ano = scanner.nextLine();
        System.out.print("예금액: ");
        int money = Integer.parseInt(scanner.nextLine());

        Account account = findAccount(ano);
        if (account == null) {
            System.out.println("결과: 계좌가 없습니다.");
            return;
        }
        account.setBalance(account.getBalance() + money);
        System.out.println("결과: 예금이 성공되었습니다.");
    }

    private static void withdraw() {
        System.out.println("--------");
        System.out.println("출금");
        System.out.println("--------");

        System.out.print("계좌번호: ");
        String ano = scanner.nextLine();
        System.out.print("출금액: ");
        int money = Integer.parseInt(scanner.nextLine());

        Account account = findAccount(ano);
        if (account == null) {
            System.out.println("결과: 계좌가 없습니다.");
            return;
        }
        account.setBalance(account.getBalance() - money);
        System.out.println("결과: 출금이 성공되었습니다.");
    }

    private static Account findAccount(String ano) {
        for (Account account : accountList) {
            if (account.getAno().equals(ano)) {
                return account;
            }
        }
        return null;
    }
}
```

---

## 핵심 차이 (배열 → ArrayList)

```java
// 배열
accountArray[i] = newAccount;

// ArrayList
accountList.add(new Account(ano, owner, balance));
```
