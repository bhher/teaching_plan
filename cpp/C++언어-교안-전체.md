# C++ 교안 (기초 → 실습 → 종합)

**총 12주차**

**목표:**
- C++ 문법 이해 (C 확장 + 객체지향 기초)
- 배열·vector·함수·포인터·참조 습득
- class·파일 입출력으로 콘솔 프로그램 제작
- (선택) 작업형·알고리즘 문제 대비

---

## 📚 교안 파일 목록

| 주차 | 주제 | 파일 |
|------|------|------|
| 1-2 | 개요·변수·자료형 | [C++언어-교안-1-2주차.md](./C++언어-교안-1-2주차.md) |
| 3-4 | 입출력·연산자 | [C++언어-교안-3-4주차.md](./C++언어-교안-3-4주차.md) |
| 5-6 | 조건문·반복문 | [C++언어-교안-5-6주차.md](./C++언어-교안-5-6주차.md) |
| 7-8 | 배열·vector·string | [C++언어-교안-7-8주차.md](./C++언어-교안-7-8주차.md) |
| 9-10 | 함수·포인터·참조 | [C++언어-교안-9-10주차.md](./C++언어-교안-9-10주차.md) |
| 11-12 | class·파일·종합 | [C++언어-교안-11-12주차.md](./C++언어-교안-11-12주차.md) |

**환경 설정:** [VSCode-C-C++-설치가이드.md](../VSCode-C-C++-설치가이드.md)

---

## 📅 12주 커리큘럼 한눈에

| 주 | 핵심 | C++ 포인트 |
|----|------|------------|
| 1 | Hello World, 컴파일 | `iostream`, `cout`, `g++` |
| 2 | 변수·자료형 | `bool`, `string`, `iomanip` |
| 3 | cin·getline | `cin.ignore`, 버퍼 |
| 4 | 연산자 | `bool` 결과, 캐스팅 |
| 5 | if / switch | C와 동일 흐름 |
| 6 | for / while | range-for 예고 |
| 7 | 배열·**vector** | STL, `push_back` |
| 8 | **string** | `length`, `+`, `getline` |
| 9 | 함수 | **오버로딩**, **기본 인수** |
| 10 | 포인터·**참조** | `swapRef`, `new`/`delete` |
| 11 | struct·**class** | 생성자, 메서드 |
| 12 | **fstream** | 종합 학생 관리 |

---

## C 교안과 대응

| C (12주) | C++ (12주) |
|----------|------------|
| printf / scanf | cout / cin / getline |
| char[] | string |
| 배열만 | 배열 + **vector** |
| 함수 | 함수 + **오버로딩** |
| 포인터 | 포인터 + **참조** |
| struct | struct + **class** |
| FILE* / fprintf | **fstream** |

---

## 컴파일 명령

```bash
g++ main.cpp -o main.exe
main.exe
```

C++11 이상 (range-for 등):

```bash
g++ -std=c++11 main.cpp -o main.exe
```

---

## 주차별 학습 체크

- [ ] 1-2: Hello + 변수 + cout 서식
- [ ] 3-4: cin + getline + 연산자
- [ ] 5-6: if/switch + for/while 메뉴
- [ ] 7-8: vector + string 실습
- [ ] 9-10: 함수 분리 + 참조 swap
- [ ] 11-12: class + 파일 저장 종합

---

## 심화 (12주 이후)

1. 상속 · 다형성 · virtual  
2. STL (map, algorithm)  
3. 스마트 포인터  
4. Qt / OpenGL / 알고리즘  

C 교안 전체 통합본: [../c/C언어-교안-전체.md](../c/C언어-교안-전체.md)  
C++는 **주차별 파일**로 나누어 두었습니다. (수정·인쇄 편의)
