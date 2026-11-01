# 18. 자바빈즈 액션 태그 예제

이론: [../../JSP-자바빈즈-액션태그.md](../../JSP-자바빈즈-액션태그.md)

## 파일 구조

```
18-자바빈즈/
├── src/com/example/Member.java   ← JavaBean
└── WebContent/
    ├── member.jsp                ← useBean + setProperty(value) + getProperty
    ├── form.jsp                  ← 입력 폼
    └── memberResult.jsp          ← property="*"
```

## Eclipse에서 적용

1. Dynamic Web Project 생성 (또는 기존 프로젝트)
2. `src`에 `com.example` 패키지 만들고 `Member.java` 복사
3. `WebContent`에 JSP 3개 복사
4. Tomcat 실행 후 접속
   - `/프로젝트명/member.jsp`
   - `/프로젝트명/form.jsp`

## 학습 순서

1. `member.jsp` — 값 직접 넣기
2. `form.jsp` → `memberResult.jsp` — 폼 자동 매핑
