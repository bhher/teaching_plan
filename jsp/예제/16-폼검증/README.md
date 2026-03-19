# 폼 검증 예제 모음

JSP 폼 검증에 대한 다양한 예제와 가이드입니다.

## 📁 파일 목록

### 가이드 문서
- **폼-검증-가이드.md** - 상세한 설명과 예제

### 예제 파일
- **validation01_basic.jsp** - 기본 폼 검증 예제
- **validation01_process.jsp** - 기본 검증 처리 페이지
- **validation02_advanced.jsp** - 고급 폼 검증 예제 (정규식 사용)
- **validation02_process.jsp** - 고급 검증 처리 페이지
- **validation03_realtime.jsp** - 실시간 검증 예제
- **validation03_process.jsp** - 실시간 검증 처리 페이지

## 🚀 빠른 시작

1. 기본 예제 실행: `validation01_basic.jsp`
2. 고급 예제 실행: `validation02_advanced.jsp`
3. 실시간 검증 예제 실행: `validation03_realtime.jsp`

## 📚 학습 순서

1. **기본 검증** → `validation01_basic.jsp`
   - `onsubmit` 사용법
   - 기본적인 필수 입력 검증

2. **고급 검증** → `validation02_advanced.jsp`
   - 정규식을 사용한 형식 검증
   - 길이 제한 검증
   - 비밀번호 확인 검증

3. **실시간 검증** → `validation03_realtime.jsp`
   - 입력 중 실시간 피드백
   - 시각적 피드백 제공

## 💡 핵심 개념

- **onsubmit**: 폼 제출 전 검증 실행
- **return false**: 검증 실패 시 폼 제출 방지
- **return true**: 검증 통과 시 폼 제출 허용
- **서버 사이드 검증**: 보안을 위한 필수 검증

## ⚠️ 주의사항

클라이언트 사이드 검증만으로는 충분하지 않습니다. 반드시 서버 사이드에서도 검증해야 합니다.
