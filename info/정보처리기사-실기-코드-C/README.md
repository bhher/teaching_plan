# 정보처리기사 실기 문제 코드 (C 언어)

이 폴더에는 정보처리기사 실기 문제의 실행 가능한 C 언어 코드가 포함되어 있습니다.

## 파일 구조

- `problem1_array.c` - 배열 처리 알고리즘 문제
- `problem2_string.c` - 문자열 처리 알고리즘 문제
- `problem3_loop.c` - 반복문과 조건문 활용 문제
- `problem4_struct.c` - 구조체와 포인터 활용 문제
- `problem6_student.c` - 종합 알고리즘 문제 (학생 점수 관리)

## 컴파일 및 실행 방법

### Windows (MinGW/GCC)

```bash
# 컴파일
gcc problem1_array.c -o problem1_array.exe

# 실행
problem1_array.exe
```

### Linux/Mac

```bash
# 컴파일
gcc problem1_array.c -o problem1_array

# 실행
./problem1_array
```

### Visual Studio Code

1. C/C++ 확장 설치
2. `Ctrl+Shift+P` → "C/C++: Run Code" 선택
3. 또는 터미널에서 직접 컴파일 및 실행

## 문제 설명

각 문제에 대한 상세한 설명과 해설은 다음 파일을 참고하세요:
- `../정보처리기사-실기-문제-C언어.md` - 문제 파일
- `../정보처리기사-실기-해설-C언어.md` - 해설 파일

## 주의사항

1. **컴파일러**: GCC, Clang, MSVC 등 표준 C 컴파일러 사용 가능
2. **C 표준**: C99 이상 권장 (stdbool.h 사용 시)
3. **메모리 관리**: 동적 할당 사용 시 메모리 누수 주의
4. **문자열 처리**: 한글 출력 시 인코딩 설정 확인 필요

## 문제별 컴파일 명령어

```bash
# 문제 1
gcc problem1_array.c -o problem1_array

# 문제 2
gcc problem2_string.c -o problem2_string

# 문제 3 (stdbool.h 사용)
gcc -std=c99 problem3_loop.c -o problem3_loop

# 문제 4
gcc problem4_struct.c -o problem4_struct

# 문제 6
gcc problem6_student.c -o problem6_student
```
