# tomcat-users.xml 설정 가이드 (Tomcat 10.1)

## 📋 파일 위치

**Tomcat 설치 경로:**
```
C:\apache-tomcat-10.1.52\conf\tomcat-users.xml
```

---

## ✅ 수정된 tomcat-users.xml

### 주요 변경 사항

1. **XML 선언 추가**
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   ```

2. **네임스페이스 추가** (Tomcat 10.1+ 권장)
   ```xml
   <tomcat-users xmlns="http://tomcat.apache.org/xml"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="http://tomcat.apache.org/xml
                                     http://tomcat.apache.org/xml/tomcat-users.xsd"
                 version="1.0">
   ```

3. **닫는 태그 추가**
   ```xml
   </tomcat-users>
   ```

4. **manager-script 역할 추가** (admin 사용자에 추가)

---

## 🔍 역할 설명

### 기본 역할

| 역할 | 설명 |
|------|------|
| **admin** | 관리자 역할 (일반) |
| **tomcat** | Tomcat 기본 역할 |
| **role1** | 일반 사용자 역할 |
| **manager-gui** | Tomcat Manager 웹 인터페이스 접근 |
| **admin-gui** | Tomcat Admin 웹 인터페이스 접근 |
| **manager-script** | Tomcat Manager 스크립트 접근 |

### 사용자 계정

| 사용자명 | 비밀번호 | 역할 |
|----------|----------|------|
| **tomcat** | tomcat1234 | tomcat |
| **both** | both1234 | tomcat, role1 |
| **role1** | role1234 | role1 |
| **admin** | admin1234 | role1, manager-gui, admin-gui, manager-script |

---

## 🎯 사용 목적

### manager-gui 역할

**용도:**
- Tomcat Manager 웹 인터페이스 접근
- URL: `http://localhost:8080/manager/html`

**기능:**
- 애플리케이션 배포/중지/재시작
- 세션 관리
- 서버 상태 확인

### admin-gui 역할

**용도:**
- Tomcat Admin 웹 인터페이스 접근
- URL: `http://localhost:8080/admin`

**기능:**
- 서버 설정 관리
- 가상 호스트 관리

### manager-script 역할

**용도:**
- Tomcat Manager 스크립트 접근
- REST API 사용

**기능:**
- 프로그래밍 방식으로 애플리케이션 관리

---

## 📝 설정 예시

### 최소 설정 (기본 사용)

```xml
<tomcat-users>
  <role rolename="manager-gui"/>
  <user username="admin" password="admin1234" roles="manager-gui"/>
</tomcat-users>
```

### 완전한 설정 (현재 버전)

```xml
<tomcat-users xmlns="http://tomcat.apache.org/xml"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://tomcat.apache.org/xml
                                  http://tomcat.apache.org/xml/tomcat-users.xsd"
              version="1.0">

  <role rolename="admin"/>
  <role rolename="tomcat"/>
  <role rolename="role1"/>
  <role rolename="manager-gui"/>
  <role rolename="admin-gui"/>
  <role rolename="manager-script"/>

  <user username="tomcat" password="tomcat1234" roles="tomcat"/>
  <user username="both" password="both1234" roles="tomcat,role1"/>
  <user username="role1" password="role1234" roles="role1"/>
  <user username="admin" password="admin1234" roles="role1,manager-gui,admin-gui,manager-script"/>

</tomcat-users>
```

---

## 🔧 설정 후 작업

### 1. 파일 저장

**위치:**
```
C:\apache-tomcat-10.1.52\conf\tomcat-users.xml
```

**주의:**
- 기존 파일 백업 권장
- Tomcat이 실행 중이면 중지 후 수정

### 2. 서버 재시작

**이클립스에서:**
- 서버 중지
- 서버 시작

**또는 직접:**
- Tomcat 서버 중지
- Tomcat 서버 시작

### 3. 접속 테스트

**Tomcat Manager:**
```
http://localhost:8080/manager/html
```

**로그인:**
- 사용자명: `admin`
- 비밀번호: `admin1234`

---

## 🐛 자주 발생하는 오류

### 오류 1: XML 파싱 오류

**원인:**
- 닫는 태그 누락
- XML 구조 오류

**해결:**
- `<tomcat-users>` 태그가 제대로 닫혔는지 확인
- XML 유효성 검사

### 오류 2: 역할 인식 안 됨

**원인:**
- 역할이 정의되지 않음
- 역할 이름 오타

**해결:**
- 역할이 사용자보다 먼저 정의되어 있는지 확인
- 역할 이름 정확히 확인

### 오류 3: Manager 접근 불가

**원인:**
- `manager-gui` 역할 없음
- 사용자에 역할 할당 안 됨

**해결:**
- `manager-gui` 역할 추가
- 사용자에 역할 할당

---

## ✅ 체크리스트

설정 확인:

- [ ] XML 선언 추가됨
- [ ] 네임스페이스 추가됨 (선택사항)
- [ ] 모든 역할 정의됨
- [ ] 사용자 정의됨
- [ ] 닫는 태그 `</tomcat-users>` 있음
- [ ] 서버 재시작
- [ ] Manager 접속 테스트

---

## 📚 요약

### 변경 사항

1. ✅ XML 선언 추가
2. ✅ 네임스페이스 추가 (Tomcat 10.1+ 권장)
3. ✅ 닫는 태그 추가
4. ✅ `manager-script` 역할 추가

### 파일 위치

```
C:\apache-tomcat-10.1.52\conf\tomcat-users.xml
```

### 사용자 계정

- **admin**: 모든 관리 권한 (manager-gui, admin-gui, manager-script)

**Tomcat 10.1에 맞게 수정 완료!** ✅
