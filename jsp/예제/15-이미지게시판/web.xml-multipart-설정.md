# web.xml multipart 설정 가이드

## 🐛 오류: multipart 설정 없음

### 오류 메시지

```
IllegalStateException: 어떤 multi-part 설정도 제공되지 않았기 때문에, part들을 처리할 수 없습니다.
```

**원인:**
- JSP에서 `request.getPart()`를 사용하려면 `multipart-config` 설정 필요
- `web.xml`에 설정이 없음

---

## ✅ 해결 방법: web.xml에 multipart-config 추가

### web.xml 파일 위치

```
WebContent/WEB-INF/web.xml
```

### web.xml 설정 추가

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
         https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">

    <!-- ✅ multipart 설정 추가 -->
    <servlet>
        <servlet-name>JspServlet</servlet-name>
        <servlet-class>org.apache.jasper.servlet.JspServlet</servlet-class>
        <multipart-config>
            <!-- 최대 파일 크기: 10MB -->
            <max-file-size>10485760</max-file-size>
            <!-- 최대 요청 크기: 10MB -->
            <max-request-size>10485760</max-request-size>
            <!-- 파일 크기 임계값: 0 (모든 파일을 디스크에 저장) -->
            <file-size-threshold>0</file-size-threshold>
        </multipart-config>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>JspServlet</servlet-name>
        <url-pattern>*.jsp</url-pattern>
    </servlet-mapping>
    
    <!-- 인코딩 필터 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.apache.catalina.filters.SetCharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    
</web-app>
```

---

## 🔧 방법 2: 특정 JSP 파일만 설정 (더 정확)

### web.xml 설정

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
         https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">

    <!-- ✅ 파일 업로드 처리 JSP에만 multipart 설정 -->
    <servlet>
        <servlet-name>BoardWriteProcess</servlet-name>
        <jsp-file>/board_write_process.jsp</jsp-file>
        <multipart-config>
            <max-file-size>10485760</max-file-size>
            <max-request-size>10485760</max-request-size>
            <file-size-threshold>0</file-size-threshold>
        </multipart-config>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>BoardWriteProcess</servlet-name>
        <url-pattern>/board_write_process.jsp</url-pattern>
    </servlet-mapping>
    
    <!-- 수정 처리도 추가 -->
    <servlet>
        <servlet-name>BoardModifyProcess</servlet-name>
        <jsp-file>/board_modify_process.jsp</jsp-file>
        <multipart-config>
            <max-file-size>10485760</max-file-size>
            <max-request-size>10485760</max-request-size>
            <file-size-threshold>0</file-size-threshold>
        </multipart-config>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>BoardModifyProcess</servlet-name>
        <url-pattern>/board_modify_process.jsp</url-pattern>
    </servlet-mapping>
    
    <!-- 인코딩 필터 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.apache.catalina.filters.SetCharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    
</web-app>
```

---

## 📋 multipart-config 설정 설명

### 설정 항목

```xml
<multipart-config>
    <!-- 최대 파일 크기 (10MB) -->
    <max-file-size>10485760</max-file-size>
    
    <!-- 최대 요청 크기 (10MB) -->
    <max-request-size>10485760</max-request-size>
    
    <!-- 파일 크기 임계값 (0 = 모든 파일을 디스크에 저장) -->
    <file-size-threshold>0</file-size-threshold>
    
    <!-- 임시 파일 저장 위치 (선택사항) -->
    <location>C:/temp</location>
</multipart-config>
```

### 각 항목 설명

| 항목 | 설명 | 값 |
|------|------|-----|
| **max-file-size** | 개별 파일 최대 크기 | 10485760 (10MB) |
| **max-request-size** | 전체 요청 최대 크기 | 10485760 (10MB) |
| **file-size-threshold** | 메모리/디스크 전환 임계값 | 0 (항상 디스크) |
| **location** | 임시 파일 저장 위치 | 선택사항 |

---

## 🔍 Tomcat 버전별 web.xml 차이

### Tomcat 10.1+ (Jakarta EE)

```xml
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
         https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">
```

### Tomcat 9.x (Java EE)

```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
```

---

## 🎯 완전한 web.xml 예제

### 전체 설정 예제

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
         https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">

    <display-name>Image Board</display-name>
    
    <!-- ✅ 파일 업로드 처리 JSP 설정 -->
    <servlet>
        <servlet-name>BoardWriteProcess</servlet-name>
        <jsp-file>/board_write_process.jsp</jsp-file>
        <multipart-config>
            <max-file-size>10485760</max-file-size>
            <max-request-size>10485760</max-request-size>
            <file-size-threshold>0</file-size-threshold>
        </multipart-config>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>BoardWriteProcess</servlet-name>
        <url-pattern>/board_write_process.jsp</url-pattern>
    </servlet-mapping>
    
    <!-- 수정 처리 JSP 설정 -->
    <servlet>
        <servlet-name>BoardModifyProcess</servlet-name>
        <jsp-file>/board_modify_process.jsp</jsp-file>
        <multipart-config>
            <max-file-size>10485760</max-file-size>
            <max-request-size>10485760</max-request-size>
            <file-size-threshold>0</file-size-threshold>
        </multipart-config>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>BoardModifyProcess</servlet-name>
        <url-pattern>/board_modify_process.jsp</url-pattern>
    </servlet-mapping>
    
    <!-- 인코딩 필터 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.apache.catalina.filters.SetCharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    
    <!-- 세션 타임아웃 설정 (선택사항) -->
    <session-config>
        <session-timeout>30</session-timeout>
    </session-config>
    
    <!-- 웰컴 파일 설정 (선택사항) -->
    <welcome-file-list>
        <welcome-file>board_list.jsp</welcome-file>
    </welcome-file-list>
    
</web-app>
```

---

## 🔄 설정 후 확인 사항

### 1. 서버 재시작

**중요:**
- `web.xml` 변경 후 반드시 서버 재시작 필요
- 이클립스에서 서버 중지 → 시작

### 2. 테스트

1. `board_write.jsp` 페이지 접속
2. 폼 작성 및 파일 선택
3. 제출 버튼 클릭
4. 오류 없이 처리되는지 확인

---

## 🐛 자주 발생하는 오류

### 오류 1: multipart 설정 없음

**오류 메시지:**
```
IllegalStateException: 어떤 multi-part 설정도 제공되지 않았기 때문에, part들을 처리할 수 없습니다.
```

**해결:**
- `web.xml`에 `multipart-config` 추가
- 서버 재시작

### 오류 2: 파일 크기 초과

**오류 메시지:**
```
IllegalStateException: 파일 크기가 최대 크기를 초과했습니다.
```

**해결:**
- `max-file-size` 값 증가
- 또는 파일 크기 제한

### 오류 3: web.xml 오류

**오류 메시지:**
```
cvc-complex-type.2.4.a: Invalid content was found starting with element 'multipart-config'
```

**해결:**
- `web-app` 버전 확인
- 올바른 네임스페이스 사용

---

## ✅ 체크리스트

설정 확인:

- [ ] `web.xml` 파일 존재 (`WebContent/WEB-INF/web.xml`)
- [ ] `multipart-config` 설정 추가
- [ ] `servlet` 및 `servlet-mapping` 설정
- [ ] 서버 재시작
- [ ] 파일 업로드 테스트

---

## 📚 요약

### 문제

- `IllegalStateException: 어떤 multi-part 설정도 제공되지 않았기 때문에, part들을 처리할 수 없습니다.`
- JSP에서 `request.getPart()` 사용 불가

### 해결

1. ✅ `web.xml`에 `multipart-config` 추가
2. ✅ `servlet` 및 `servlet-mapping` 설정
3. ✅ 서버 재시작

**web.xml에 multipart-config를 추가하면 해결됩니다!** ✅
