# 상품 관리 시스템 (ArrayList CRUD + File 입출력)

## 폴더 구조

```text
java/a0813/product/
├── Product.java          # 상품 클래스
├── ProductManager.java   # CRUD + 파일 읽기/쓰기
├── ProductApp.java       # 메뉴 메인
├── products.txt          # 데이터 파일
└── README.md
```

## 기능

| 메뉴 | 기능 |
|------|------|
| 1 | 상품 등록 (Create) |
| 2 | 상품 조회 (Read) |
| 3 | 상품 수정 (Update) |
| 4 | 상품 삭제 (Delete) |
| 5 | 전체 조회 |
| 6 | 파일 저장 |
| 7 | 파일 불러오기 |
| 8 | 종료 |

## 더미 데이터 (3개)

| 코드 | 상품명 | 가격 | 재고 |
|------|--------|------|------|
| P001 | 노트북 | 1200000 | 5 |
| P002 | 무선마우스 | 25000 | 30 |
| P003 | 키보드 | 45000 | 20 |

## 파일 형식

```text
상품코드|상품명|가격|재고
```

## 실행 방법

```bash
cd d:/sande/teaching_plan/java
javac a0813/product/*.java
java a0813.product.ProductApp
```

## 핵심 코드

### File로 읽기

```java
File file = new File(filename);
BufferedReader reader = new BufferedReader(new FileReader(file));
```

### File로 쓰기

```java
BufferedWriter writer = new BufferedWriter(new FileWriter(file));
writer.write(product.toFileString());
writer.newLine();
```

### ArrayList CRUD

```java
products.add(product);      // Create
findProduct(id);            // Read
product.setName(...);       // Update
products.remove(product);   // Delete
```
