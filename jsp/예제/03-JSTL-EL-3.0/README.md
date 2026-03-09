# 🚀 JSTL-EL 3.0 예제

## 📋 개요

JSTL 3.0과 EL 3.0의 고급 기능을 학습하는 예제입니다.

---

## 🎯 주요 기능

### EL 3.0 신규 기능
- ✅ 람다 표현식
- ✅ 스트림 API
- ✅ 함수형 프로그래밍 스타일
- ✅ 집계 함수
- ✅ 필터링 및 변환

---

## 📦 필수 요구사항

### 1. Jakarta EE 8 이상
- Tomcat 9.0 이상
- 또는 Jakarta EE 8 호환 서버

### 2. JSTL 3.0 라이브러리

**Maven:**
```xml
<dependency>
    <groupId>jakarta.servlet.jsp.jstl</groupId>
    <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
    <version>3.0.0</version>
</dependency>
<dependency>
    <groupId>org.glassfish.web</groupId>
    <artifactId>jakarta.servlet.jsp.jstl</artifactId>
    <version>3.0.0</version>
</dependency>
```

**수동 설치:**
- `jakarta.servlet.jsp.jstl-api-3.0.0.jar`
- `jakarta.servlet.jsp.jstl-3.0.0.jar`
- `WEB-INF/lib/` 폴더에 추가

---

## 🔧 설정

### web.xml 설정

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
         https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd"
         version="5.0">
    
    <!-- JSP 설정 -->
    <jsp-config>
        <jsp-property-group>
            <url-pattern>*.jsp</url-pattern>
            <el-ignored>false</el-ignored>
        </jsp-property-group>
    </jsp-config>
</web-app>
```

---

## 📝 주요 문법

### 1. 람다 표현식
```jsp
${(x) -> x * 2}
${(x, y) -> x + y}
```

### 2. 스트림 필터링
```jsp
${list.stream().filter(x -> x > 5).toList()}
```

### 3. 스트림 변환
```jsp
${list.stream().map(x -> x * 2).toList()}
```

### 4. 집계 함수
```jsp
${list.stream().sum()}
${list.stream().average().get()}
${list.stream().max().get()}
```

---

## ⚠️ 주의사항

1. **호환성**: Jakarta EE 8 이상 필요
2. **성능**: 큰 데이터셋에서는 주의
3. **브라우저**: 서버 측 처리이므로 브라우저와 무관

---

**JSTL-EL 3.0의 강력한 기능을 활용하세요! 💪**
