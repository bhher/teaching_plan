# 회원 관리 시스템 - ArrayList 변환 정답

## 수정된 DAO.java

```java
package day9.example01;

import java.util.ArrayList;
import java.util.Scanner;

// Data Access Object : 데이터에 접근하는 객체 (CRUD)
/*
	Database의 data에 접근을 위한 객체
	Database에 접근을 하기위한 로직과 비즈니스 로직을 분리하기 위해서 사용
 */

public class DAO {
	//TODO: Member Variables
	Scanner sc = new Scanner(System.in);
	// 배열 대신 ArrayList 사용
	private ArrayList<MemberDTO> mList = new ArrayList<>();
	private FileClass file = new FileClass("sun", "memberInfo");
	// listSize 변수 제거 (ArrayList의 size() 메서드 사용)
	
	
	// 생성자 constructor
	public DAO() {
		// ArrayList는 자동으로 초기화되므로 별도 초기화 불필요
		
		// 테스트용 데이터
		MemberDTO m1 = new MemberDTO();
		m1.setId(0);
		m1.setName("test1");
		m1.setAge(11);
		m1.setAddress("서울");
		mList.add(m1);
		
		MemberDTO m2 = new MemberDTO();
		m2.setId(1);
		m2.setName("test2");
		m2.setAge(22);
		m2.setAddress("부산");
		mList.add(m2);
		
		MemberDTO m3 = new MemberDTO();
		m3.setId(2);
		m3.setName("test3");
		m3.setAge(33);
		m3.setAddress("대구");
		mList.add(m3);
	}
	
	
	// TODO: CRUD
	// insert
	private void insert(MemberDTO m) {
		// ArrayList의 add() 메서드 사용
		mList.add(m);
		// listSize++ 제거 (ArrayList가 자동으로 크기 관리)
	}
	
	// delete
	private void delete(int index) {
		// ArrayList의 remove() 메서드 사용
		mList.remove(index);
	}
	
	// select 
	private MemberDTO select(int index) {
		// ArrayList의 get() 메서드 사용
		return mList.get(index);
	}
	
	// update
	private void update(int index, MemberDTO m) {
		// ArrayList의 set() 메서드 사용
		mList.set(index, m);
	}
	
	// printAll
	public void printAll() {
		System.out.println("< 전체 회원목록 >");
		int index = 1;
		// mList.length 대신 mList.size() 사용
		for (int i = 0; i < mList.size(); i++) {
			try {
				// mList[i] 대신 mList.get(i) 사용
				MemberDTO m = mList.get(i);
				if(m != null && m.getName() != null) {
					System.out.println("< "+ index +". "+ m.getName() + " 회원 >");
					System.out.println("회원번호 : " + m.getId());
					System.out.println("나이: " + m.getAge());
					// 오류 수정: getAge() 대신 getAddress() 사용
					System.out.println("주소: " + m.getAddress());
					index++;
				}
			} catch(Exception e) {
				continue;
			}
		}
	}
	
	
	// 인덱스 찾기 : 키 - 이름 
	private int findIndex() {
		System.out.print("회원이름을 입력하시오 : ");
		String name = sc.next();
		// mList.length 대신 mList.size() 사용
		for (int i = 0; i < mList.size(); i++) {
			try {
				// mList[i] 대신 mList.get(i) 사용
				MemberDTO m = mList.get(i);
				if(m != null && m.getName().equals(name)) {
					return i;
				}
			} catch (Exception e) {
				continue;
			}
		}
		return -1;
	}

	
	
	//user메소드 : 사용자의 입력값있음.
	
	// user Insert
	public void userInsert() {
		// 크기 제한 제거 (ArrayList는 동적으로 크기 조절)
		MemberDTO m = new MemberDTO();
		System.out.println("< 회원 정보입력 >");
		System.out.print("회원번호 : ");
		int id = sc.nextInt();
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("주소 : ");
		String ad = sc.next();
		
		m.setId(id);
		m.setAge(age);
		m.setName(name);
		m.setAddress(ad);
		insert(m);
		System.out.println("회원이 등록되었습니다.");
	}
	
	//user Delete 
	public void userDelete() {
		int index = findIndex();
		if(index != -1) {
			delete(index);
			System.out.println("회원을 삭제했습니다.");
		} else {
			// 이름이 없는 경우
			System.out.println("해당 회원이 없습니다.");
		}
	}
	
	
	// userSelect 멤버 값 리턴하기 
	public void userSelect() {
		int index = findIndex();
		if(index != -1) { // 인덱스 있는 경우 
			MemberDTO m = select(index);
			int id = m.getId();
			int age = m.getAge();
			String name = m.getName();
			String address = m.getAddress();
			
			System.out.println("< "+name + " 의 회원정보 >");
			System.out.println(" - 회원번호 : " + id);
			System.out.println(" - 이름 : " + name);
			System.out.println(" - 나이 : " + age);
			System.out.println(" - 주소 : " + address);
		} else {
			System.out.println("회원이 없습니다.");
		}
	}
	
	
	//user Update
	public void userUpdate() {
		int index = findIndex();
		if(index != -1) {
			// 이름 있는 경우
			MemberDTO m = new MemberDTO();
			MemberDTO oldMember = mList.get(index);
			m.setId(oldMember.getId());
			System.out.println("< "+ oldMember.getName() + " 회원 정보수정 >");
			System.out.println("회원번호 : " + m.getId());
			System.out.print("이름 : ");
			m.setName(sc.next());
			System.out.print("나이 : ");
			m.setAge(sc.nextInt());
			System.out.print("주소 : ");
			m.setAddress(sc.next());
			
			update(index, m);
			System.out.println("수정되었습니다.");
		} else {
			// 이름이 없는 경우
			System.out.println("해당 회원이 없습니다.");
		}
	}
	
	
	// File method
	
	public void dataSave() throws Exception {
		file.create();
		String str = " 번호\t 이름\t 나이\t 주소\n"
				+ "-------------------------------------\n";
		// mList.length 대신 mList.size() 사용
		for (int i = 0; i < mList.size(); i++) {
			try {
				// mList[i] 대신 mList.get(i) 사용
				MemberDTO m = mList.get(i);
				if(m != null && m.getName() != null) {
					str += m.toString() + "\n";
				}
			} catch (Exception e) {
				continue;
			}
		}
		// 파일에 한 번만 쓰기 (반복문 밖에서)
		file.write(str);
		System.out.println("데이터를 저장했습니다.");
	}
	
	public void dataLoad() {
		try {
			file.read();
		} catch (Exception e) {
			System.out.println("읽을 파일이 없습니다.");
		}
	}
}
```

## 주요 변경 사항 설명

### 1. 필드 변경

**기존 (배열)**
```java
private MemberDTO mList[] = new MemberDTO[10];
private int listSize;
```

**변경 (ArrayList)**
```java
private ArrayList<MemberDTO> mList = new ArrayList<>();
// listSize 변수 제거
```

**설명:**
- 배열은 크기가 고정되어 있어 `new MemberDTO[10]`으로 선언하면 최대 10개만 저장 가능
- ArrayList는 동적 크기 조절이 가능하여 크기 제한이 없음
- `listSize` 변수는 ArrayList의 `size()` 메서드로 대체 가능

### 2. 생성자 변경

**기존**
```java
public DAO() {
    for (int i = 0; i < mList.length; i++) {
        mList[i] = new MemberDTO();
    }
    // 테스트 데이터 추가
    mList[0].setId(0);
    // ...
    listSize++;
}
```

**변경**
```java
public DAO() {
    // ArrayList는 초기화 불필요
    MemberDTO m1 = new MemberDTO();
    m1.setId(0);
    // ...
    mList.add(m1);  // add()로 추가
}
```

**설명:**
- 배열은 모든 요소를 미리 초기화해야 했지만, ArrayList는 필요할 때만 추가
- `add()` 메서드로 요소 추가

### 3. insert 메서드 변경

**기존**
```java
private void insert(MemberDTO m) {
    mList[listSize] = m;
    listSize++;
}
```

**변경**
```java
private void insert(MemberDTO m) {
    mList.add(m);
}
```

**설명:**
- 배열은 인덱스를 직접 지정해야 했지만, ArrayList는 `add()`로 끝에 자동 추가
- 크기 관리가 자동으로 이루어짐

### 4. delete 메서드 변경

**기존**
```java
private void delete(int index) {
    mList[index] = null;  // null로만 설정 (실제 삭제 아님)
}
```

**변경**
```java
private void delete(int index) {
    mList.remove(index);  // 실제로 요소 제거
}
```

**설명:**
- 배열은 `null`로 설정만 했지만, 실제로는 메모리에 남아있음
- ArrayList의 `remove()`는 실제로 요소를 제거하고 뒤의 요소들을 앞으로 이동시킴

### 5. select 메서드 변경

**기존**
```java
private MemberDTO select(int index) {
    return mList[index];
}
```

**변경**
```java
private MemberDTO select(int index) {
    return mList.get(index);
}
```

**설명:**
- 배열은 `[]` 연산자로 접근
- ArrayList는 `get()` 메서드로 접근

### 6. update 메서드 변경

**기존**
```java
private void update(int index, MemberDTO m) {
    mList[index] = m;
}
```

**변경**
```java
private void update(int index, MemberDTO m) {
    mList.set(index, m);
}
```

**설명:**
- 배열은 `[]` 연산자로 직접 할당
- ArrayList는 `set()` 메서드로 수정

### 7. printAll 메서드 변경

**기존**
```java
public void printAll() {
    for (int i = 0; i < mList.length; i++) {
        if(mList[i].getName() != null) {
            // ...
            System.out.println("주소: " + mList[i].getAge());  // 오류!
        }
    }
}
```

**변경**
```java
public void printAll() {
    for (int i = 0; i < mList.size(); i++) {
        MemberDTO m = mList.get(i);
        if(m != null && m.getName() != null) {
            // ...
            System.out.println("주소: " + m.getAddress());  // 수정
        }
    }
}
```

**설명:**
- `length` → `size()` 변경
- `mList[i]` → `mList.get(i)` 변경
- 주소 출력 오류 수정 (`getAge()` → `getAddress()`)

### 8. findIndex 메서드 변경

**기존**
```java
private int findIndex() {
    for (int i = 0; i < mList.length; i++) {
        if(mList[i].getName().equals(name)) {
            return i;
        }
    }
    return -1;
}
```

**변경**
```java
private int findIndex() {
    for (int i = 0; i < mList.size(); i++) {
        MemberDTO m = mList.get(i);
        if(m != null && m.getName().equals(name)) {
            return i;
        }
    }
    return -1;
}
```

**설명:**
- `length` → `size()` 변경
- `mList[i]` → `mList.get(i)` 변경

### 9. userInsert 메서드 변경

**기존**
```java
public void userInsert() {
    if(listSize < 10) {  // 크기 제한
        // ...
        insert(m);
    } else {
        System.out.println("저장할 공간이 꽉 찼습니다.");
    }
}
```

**변경**
```java
public void userInsert() {
    // 크기 제한 제거
    MemberDTO m = new MemberDTO();
    // ...
    insert(m);
    System.out.println("회원이 등록되었습니다.");
}
```

**설명:**
- 배열은 크기 제한이 있어 체크가 필요했지만, ArrayList는 제한이 없음
- 메모리가 허용하는 한 무제한으로 추가 가능

### 10. dataSave 메서드 변경

**기존**
```java
public void dataSave() throws Exception {
    for (int i = 0; i < mList.length; i++) {
        if(mList[i].getName() != null) {
            str += mList[i].toString() + "\n";
            file.write(str);  // 반복문 안에서 쓰기 (비효율)
        }
    }
}
```

**변경**
```java
public void dataSave() throws Exception {
    String str = " 번호\t 이름\t 나이\t 주소\n...\n";
    for (int i = 0; i < mList.size(); i++) {
        MemberDTO m = mList.get(i);
        if(m != null && m.getName() != null) {
            str += m.toString() + "\n";
        }
    }
    file.write(str);  // 반복문 밖에서 한 번만 쓰기
}
```

**설명:**
- `length` → `size()` 변경
- `mList[i]` → `mList.get(i)` 변경
- 파일 쓰기를 반복문 밖으로 이동하여 효율성 개선

## 배열 vs ArrayList 비교표

| 항목 | 배열 | ArrayList |
|------|------|-----------|
| 크기 | 고정 (선언 시 결정) | 동적 (자동 조절) |
| 선언 | `MemberDTO[] arr = new MemberDTO[10]` | `ArrayList<MemberDTO> list = new ArrayList<>()` |
| 요소 접근 | `arr[index]` | `list.get(index)` |
| 요소 추가 | `arr[i] = value` (인덱스 지정 필요) | `list.add(value)` (끝에 추가) |
| 요소 수정 | `arr[index] = value` | `list.set(index, value)` |
| 요소 삭제 | `arr[index] = null` (실제 삭제 아님) | `list.remove(index)` (실제 삭제) |
| 크기 확인 | `arr.length` | `list.size()` |
| 크기 제한 | 있음 (선언한 크기만큼) | 없음 (메모리 허용 범위 내) |
| null 요소 | 가능 | 가능 (하지만 권장하지 않음) |

## ArrayList의 장점

1. **동적 크기 조절**: 필요에 따라 자동으로 크기 증가/감소
2. **편리한 메서드**: `add()`, `remove()`, `get()`, `set()` 등 제공
3. **크기 제한 없음**: 메모리가 허용하는 한 무제한 추가 가능
4. **실제 삭제**: `remove()`로 실제로 요소를 제거하고 인덱스 재정렬
5. **유용한 메서드**: `contains()`, `indexOf()`, `clear()` 등 추가 기능 제공

## ArrayList의 단점

1. **성능**: 배열보다 약간 느릴 수 있음 (하지만 실질적 차이는 미미)
2. **메모리**: 내부적으로 배열을 사용하므로 약간의 오버헤드 존재
3. **기본 타입 불가**: `ArrayList<int>` 불가, `ArrayList<Integer>` 사용

## 실행 결과 예시

```
1. 회원정보 입력
2. 회원정보 삭제
3. 회원정보 검색
4. 회원정보 수정
5. 회원 목록 보기
6. 파일로 저장하기
7. 회원정보 파일 불러오기
0. 종료
>> 1
< 회원 정보입력 >
회원번호 : 3
이름 : 홍길동
나이 : 25
주소 : 서울시 강남구
회원이 등록되었습니다.

>> 5
< 전체 회원목록 >
< 1. test1 회원 >
회원번호 : 0
나이: 11
주소: 서울
< 2. test2 회원 >
회원번호 : 1
나이: 22
주소: 부산
< 3. test3 회원 >
회원번호 : 2
나이: 33
주소: 대구
< 4. 홍길동 회원 >
회원번호 : 3
나이: 25
주소: 서울시 강남구

>> 2
회원이름을 입력하시오 : test2
회원을 삭제했습니다.

>> 5
< 전체 회원목록 >
< 1. test1 회원 >
회원번호 : 0
나이: 11
주소: 서울
< 2. test3 회원 >
회원번호 : 2
나이: 33
주소: 대구
< 3. 홍길동 회원 >
회원번호 : 3
나이: 25
주소: 서울시 강남구
```

## 학습 포인트

1. **배열의 한계**: 크기가 고정되어 있어 유연성이 떨어짐
2. **ArrayList의 장점**: 동적 크기 조절로 유연한 데이터 관리
3. **메서드 사용**: 배열의 `[]` 연산자 대신 ArrayList의 메서드 사용
4. **실제 삭제**: ArrayList의 `remove()`는 실제로 요소를 제거
5. **코드 간소화**: 크기 관리 코드 제거로 코드가 더 간결해짐
