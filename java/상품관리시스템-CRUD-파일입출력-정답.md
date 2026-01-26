# 자바 CRUD + 파일 입출력 예제 정답

## Product.java

```java
public class Product {
    private String id;
    private String name;
    private int price;
    private int stock;

    // 생성자
    public Product(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getter 메서드
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // Setter 메서드
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // 화면 출력용 문자열
    @Override
    public String toString() {
        return "상품코드: " + id + ", 상품명: " + name + ", 가격: " + price + "원, 재고: " + stock + "개";
    }

    // 파일 저장용 문자열 (파이프로 구분)
    public String toFileString() {
        return id + "|" + name + "|" + price + "|" + stock;
    }

    // 파일에서 읽은 문자열을 Product 객체로 변환
    public static Product fromFileString(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length != 4) {
                return null;
            }
            String id = parts[0].trim();
            String name = parts[1].trim();
            int price = Integer.parseInt(parts[2].trim());
            int stock = Integer.parseInt(parts[3].trim());
            return new Product(id, name, price, stock);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
```

## ProductManager.java

```java
import java.io.*;
import java.util.ArrayList;

public class ProductManager {
    private ArrayList<Product> products;
    private String filename;

    // 생성자 - 파일에서 데이터 로드
    public ProductManager() {
        this.products = new ArrayList<>();
        this.filename = "products.txt";
        loadFromFile();
    }

    // 파일에서 상품 데이터 읽기
    public void loadFromFile() {
        try {
            File file = new File(filename);
            
            // 파일이 없으면 생성
            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            // 파일 읽기
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Product product = Product.fromFileString(line);
                    if (product != null) {
                        products.add(product);
                    }
                }
            }
            
            reader.close();
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }

    // 파일에 상품 데이터 저장
    public void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            
            for (Product product : products) {
                writer.write(product.toFileString());
                writer.newLine();
            }
            
            writer.close();
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage());
        }
    }

    // 상품 추가
    public void addProduct(String id, String name, int price, int stock) {
        // 중복 확인
        if (isDuplicateId(id)) {
            System.out.println("이미 존재하는 상품 코드입니다");
            return;
        }

        Product product = new Product(id, name, price, stock);
        products.add(product);
        System.out.println("상품이 등록되었습니다");
        saveToFile(); // 파일에 저장
    }

    // 상품 코드로 상품 찾기
    public Product findProduct(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // 상품 정보 수정
    public boolean updateProduct(String id, String name, int price, int stock) {
        Product product = findProduct(id);
        if (product == null) {
            return false;
        }

        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        saveToFile(); // 파일에 저장
        return true;
    }

    // 상품 삭제
    public boolean deleteProduct(String id) {
        Product product = findProduct(id);
        if (product == null) {
            return false;
        }

        products.remove(product);
        saveToFile(); // 파일에 저장
        return true;
    }

    // 모든 상품 출력
    public void printAllProducts() {
        if (products.isEmpty()) {
            System.out.println("등록된 상품이 없습니다");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }
    }

    // 상품 코드 중복 확인
    public boolean isDuplicateId(String id) {
        return findProduct(id) != null;
    }
}
```

## ProductApp.java

```java
import java.util.Scanner;

public class ProductApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager manager = new ProductManager();

        while (true) {
            // 메뉴 표시
            System.out.println("=== 상품 관리 시스템 ===");
            System.out.println("1. 상품 등록");
            System.out.println("2. 상품 조회");
            System.out.println("3. 상품 수정");
            System.out.println("4. 상품 삭제");
            System.out.println("5. 전체 조회");
            System.out.println("6. 종료");
            System.out.print("선택 > ");

            String choice = scanner.nextLine().trim();

            try {
                int menu = Integer.parseInt(choice);

                switch (menu) {
                    case 1: // 상품 등록
                        System.out.print("상품 코드 입력: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("상품명 입력: ");
                        String name = scanner.nextLine().trim();
                        
                        int price = 0;
                        while (true) {
                            try {
                                System.out.print("가격 입력: ");
                                price = Integer.parseInt(scanner.nextLine().trim());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("숫자로 입력하세요");
                            }
                        }
                        
                        int stock = 0;
                        while (true) {
                            try {
                                System.out.print("재고 입력: ");
                                stock = Integer.parseInt(scanner.nextLine().trim());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("숫자로 입력하세요");
                            }
                        }
                        
                        manager.addProduct(id, name, price, stock);
                        break;

                    case 2: // 상품 조회
                        System.out.print("상품 코드 입력: ");
                        String searchId = scanner.nextLine().trim();
                        Product found = manager.findProduct(searchId);
                        if (found != null) {
                            System.out.println(found);
                        } else {
                            System.out.println("상품을 찾을 수 없습니다");
                        }
                        break;

                    case 3: // 상품 수정
                        System.out.print("수정할 상품 코드 입력: ");
                        String updateId = scanner.nextLine().trim();
                        Product updateProduct = manager.findProduct(updateId);
                        
                        if (updateProduct == null) {
                            System.out.println("상품을 찾을 수 없습니다");
                            break;
                        }
                        
                        System.out.print("새 상품명 입력: ");
                        String newName = scanner.nextLine().trim();
                        
                        int newPrice = 0;
                        while (true) {
                            try {
                                System.out.print("새 가격 입력: ");
                                newPrice = Integer.parseInt(scanner.nextLine().trim());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("숫자로 입력하세요");
                            }
                        }
                        
                        int newStock = 0;
                        while (true) {
                            try {
                                System.out.print("새 재고 입력: ");
                                newStock = Integer.parseInt(scanner.nextLine().trim());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("숫자로 입력하세요");
                            }
                        }
                        
                        if (manager.updateProduct(updateId, newName, newPrice, newStock)) {
                            System.out.println("수정 완료");
                        } else {
                            System.out.println("상품을 찾을 수 없습니다");
                        }
                        break;

                    case 4: // 상품 삭제
                        System.out.print("삭제할 상품 코드 입력: ");
                        String deleteId = scanner.nextLine().trim();
                        if (manager.deleteProduct(deleteId)) {
                            System.out.println("삭제 완료");
                        } else {
                            System.out.println("상품을 찾을 수 없습니다");
                        }
                        break;

                    case 5: // 전체 조회
                        manager.printAllProducts();
                        break;

                    case 6: // 종료
                        System.out.println("프로그램을 종료합니다.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("1~6번 중에 선택하세요");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요");
            }

            System.out.println(); // 빈 줄 추가
        }
    }
}
```

## 실행 결과 예시

### 첫 실행 시

```
=== 상품 관리 시스템 ===
1. 상품 등록
2. 상품 조회
3. 상품 수정
4. 상품 삭제
5. 전체 조회
6. 종료
선택 > 1
상품 코드 입력: P001
상품명 입력: 노트북
가격 입력: 1000000
재고 입력: 10
상품이 등록되었습니다

=== 상품 관리 시스템 ===
1. 상품 등록
2. 상품 조회
3. 상품 수정
4. 상품 삭제
5. 전체 조회
6. 종료
선택 > 1
상품 코드 입력: P002
상품명 입력: 마우스
가격 입력: 25000
재고 입력: 50
상품이 등록되었습니다

=== 상품 관리 시스템 ===
1. 상품 등록
2. 상품 조회
3. 상품 수정
4. 상품 삭제
5. 전체 조회
6. 종료
선택 > 5
상품코드: P001, 상품명: 노트북, 가격: 1000000원, 재고: 10개
상품코드: P002, 상품명: 마우스, 가격: 25000원, 재고: 50개

=== 상품 관리 시스템 ===
1. 상품 등록
2. 상품 조회
3. 상품 수정
4. 상품 삭제
5. 전체 조회
6. 종료
선택 > 2
상품 코드 입력: P001
상품코드: P001, 상품명: 노트북, 가격: 1000000원, 재고: 10개

=== 상품 관리 시스템 ===
1. 상품 등록
2. 상품 조회
3. 상품 수정
4. 상품 삭제
5. 전체 조회
6. 종료
선택 > 3
수정할 상품 코드 입력: P001
새 상품명 입력: 노트북 Pro
새 가격 입력: 1500000
새 재고 입력: 5
수정 완료

=== 상품 관리 시스템 ===
1. 상품 등록
2. 상품 조회
3. 상품 수정
4. 상품 삭제
5. 전체 조회
6. 종료
선택 > 5
상품코드: P001, 상품명: 노트북 Pro, 가격: 1500000원, 재고: 5개
상품코드: P002, 상품명: 마우스, 가격: 25000원, 재고: 50개

=== 상품 관리 시스템 ===
1. 상품 등록
2. 상품 조회
3. 상품 수정
4. 상품 삭제
5. 전체 조회
6. 종료
선택 > 4
삭제할 상품 코드 입력: P002
삭제 완료

=== 상품 관리 시스템 ===
1. 상품 등록
2. 상품 조회
3. 상품 수정
4. 상품 삭제
5. 전체 조회
6. 종료
선택 > 5
상품코드: P001, 상품명: 노트북 Pro, 가격: 1500000원, 재고: 5개

=== 상품 관리 시스템 ===
1. 상품 등록
2. 상품 조회
3. 상품 수정
4. 상품 삭제
5. 전체 조회
6. 종료
선택 > 6
프로그램을 종료합니다.
```

### 다음 실행 시 (파일에서 자동 로드)

```
(프로그램 시작 시 자동으로 products.txt 파일에서 데이터 로드)
=== 상품 관리 시스템 ===
1. 상품 등록
2. 상품 조회
3. 상품 수정
4. 상품 삭제
5. 전체 조회
6. 종료
선택 > 5
상품코드: P001, 상품명: 노트북 Pro, 가격: 1500000원, 재고: 5개
```

## 코드 설명

### 1. Product 클래스

- **toFileString()**: 상품 정보를 파일 저장 형식으로 변환 (파이프 구분자 사용)
- **fromFileString()**: 파일에서 읽은 문자열을 Product 객체로 변환하는 정적 메서드
  - `split("\\|")`로 파이프 구분자 분리
  - `NumberFormatException` 처리로 잘못된 형식 감지

### 2. ProductManager 클래스

- **loadFromFile()**: 
  - 파일이 없으면 생성
  - `BufferedReader`로 한 줄씩 읽기
  - `Product.fromFileString()`으로 객체 변환
  - IOException 예외 처리

- **saveToFile()**: 
  - `BufferedWriter`로 파일에 쓰기
  - 각 상품의 `toFileString()` 결과 저장
  - IOException 예외 처리

- **CRUD 메서드**:
  - 각 작업 후 `saveToFile()` 호출하여 파일에 즉시 저장
  - 중복 확인, 검색, 수정, 삭제 기능 구현

### 3. ProductApp 클래스

- **메뉴 반복**: while(true)로 무한 반복
- **입력 처리**: Scanner로 사용자 입력 받기
- **예외 처리**: 
  - 메뉴 선택 시 NumberFormatException 처리
  - 가격/재고 입력 시 NumberFormatException 처리
- **종료 시**: 파일에 자동 저장 (각 작업마다 저장하므로 별도 저장 불필요)

## 주요 학습 포인트

1. **파일 입출력**
   - `File`, `FileReader`, `FileWriter` 사용
   - `BufferedReader`, `BufferedWriter`로 효율적인 입출력
   - IOException 예외 처리

2. **데이터 형식 변환**
   - 객체 → 문자열 (toFileString)
   - 문자열 → 객체 (fromFileString)
   - 파이프 구분자로 필드 분리

3. **CRUD 패턴**
   - Create: addProduct
   - Read: findProduct, printAllProducts
   - Update: updateProduct
   - Delete: deleteProduct

4. **예외 처리**
   - IOException: 파일 입출력 오류
   - NumberFormatException: 숫자 변환 오류

5. **데이터 영속성**
   - 프로그램 종료 후에도 데이터 유지
   - 프로그램 시작 시 자동 로드
