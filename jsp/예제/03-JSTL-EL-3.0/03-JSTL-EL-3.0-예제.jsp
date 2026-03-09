<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="https://jakarta.ee/xml/ns/jakartaee/jsp/jstl/core" %>
<%@ page import="java.util.*" %>

<%
    // 테스트 데이터 설정
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    request.setAttribute("numbers", numbers);
    
    List<String> fruits = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
    request.setAttribute("fruits", fruits);
    
    List<Integer> ages = Arrays.asList(15, 20, 25, 30, 35, 40);
    request.setAttribute("ages", ages);
    
    List<Integer> scores = Arrays.asList(85, 92, 78, 96, 88);
    request.setAttribute("scores", scores);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSTL-EL 3.0 예제</title>
    <style>
        body {
            font-family: 'Malgun Gothic', sans-serif;
            max-width: 1000px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #333;
            border-bottom: 2px solid #667eea;
            padding-bottom: 10px;
            margin-top: 30px;
        }
        .example {
            background-color: #f9f9f9;
            padding: 15px;
            border-radius: 5px;
            margin: 15px 0;
            border-left: 4px solid #667eea;
        }
        .code {
            background-color: #282c34;
            color: #abb2bf;
            padding: 15px;
            border-radius: 5px;
            overflow-x: auto;
            font-family: 'Courier New', monospace;
            font-size: 14px;
            margin: 10px 0;
            white-space: pre-wrap;
            line-height: 1.6;
        }
        .result {
            background-color: #e8f5e9;
            padding: 10px;
            border-radius: 5px;
            margin-top: 10px;
            color: #2e7d32;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🚀 JSTL-EL 3.0 예제</h1>
        <p><strong>주의:</strong> JSTL 3.0은 Jakarta EE 8 이상에서만 동작합니다.</p>
        
        <h2>1. 람다 표현식</h2>
        <div class="example">
            <h3>기본 람다</h3>
            <div class="code">${(x) -> x * 2}</div>
            <div class="result">
                <c:set var="double" value="${(x) -> x * 2}" />
                double(5) = ${double(5)}<br>
                double(10) = ${double(10)}
            </div>
        </div>
        
        <h2>2. 스트림 필터링</h2>
        <div class="example">
            <h3>짝수만 필터링</h3>
            <div class="code">${numbers.stream().filter(x -> x % 2 == 0).toList()}</div>
            <div class="result">
                원본: ${numbers}<br>
                짝수만: ${numbers.stream().filter(x -> x % 2 == 0).toList()}
            </div>
        </div>
        
        <div class="example">
            <h3>5보다 큰 수만</h3>
            <div class="result">
                ${numbers.stream().filter(x -> x > 5).toList()}
            </div>
        </div>
        
        <h2>3. 스트림 변환 (map)</h2>
        <div class="example">
            <h3>각 요소를 2배로</h3>
            <div class="code">${numbers.stream().map(x -> x * 2).toList()}</div>
            <div class="result">
                원본: ${numbers}<br>
                2배: ${numbers.stream().map(x -> x * 2).toList()}
            </div>
        </div>
        
        <div class="example">
            <h3>문자열을 대문자로</h3>
            <div class="result">
                원본: ${fruits}<br>
                대문자: ${fruits.stream().map(f -> f.toUpperCase()).toList()}
            </div>
        </div>
        
        <h2>4. 집계 함수</h2>
        <div class="example">
            <h3>합계, 평균, 최대값, 최소값</h3>
            <div class="result">
                점수: ${scores}<br>
                합계: ${scores.stream().sum()}<br>
                평균: ${scores.stream().average().get()}<br>
                최고점: ${scores.stream().max().get()}<br>
                최저점: ${scores.stream().min().get()}<br>
                개수: ${scores.stream().count()}
            </div>
        </div>
        
        <h2>5. 정렬</h2>
        <div class="example">
            <h3>오름차순 정렬</h3>
            <div class="result">
                원본: ${ages}<br>
                정렬: ${ages.stream().sorted().toList()}
            </div>
        </div>
        
        <div class="example">
            <h3>내림차순 정렬</h3>
            <div class="result">
                ${ages.stream().sorted((a, b) -> b - a).toList()}
            </div>
        </div>
        
        <h2>6. 중복 제거</h2>
        <div class="example">
            <h3>distinct 사용</h3>
            <c:set var="duplicates" value="${[1, 2, 2, 3, 3, 3, 4]}" />
            <div class="result">
                원본: ${duplicates}<br>
                중복 제거: ${duplicates.stream().distinct().toList()}
            </div>
        </div>
        
        <h2>7. 제한 (Limit/Skip)</h2>
        <div class="example">
            <h3>처음 3개만</h3>
            <div class="result">
                ${numbers.stream().limit(3).toList()}
            </div>
        </div>
        
        <div class="example">
            <h3>처음 3개 제외</h3>
            <div class="result">
                ${numbers.stream().skip(3).toList()}
            </div>
        </div>
        
        <h2>8. 매칭 함수</h2>
        <div class="example">
            <h3>allMatch, anyMatch, noneMatch</h3>
            <div class="result">
                모든 수가 양수인가? ${numbers.stream().allMatch(x -> x > 0)}<br>
                5보다 큰 수가 있는가? ${numbers.stream().anyMatch(x -> x > 5)}<br>
                음수가 없는가? ${numbers.stream().noneMatch(x -> x < 0)}
            </div>
        </div>
        
        <h2>9. 복합 연산</h2>
        <div class="example">
            <h3>필터링 + 변환</h3>
            <div class="result">
                짝수를 2배로: ${numbers.stream().filter(x -> x % 2 == 0).map(x -> x * 2).toList()}
            </div>
        </div>
        
        <div class="example">
            <h3>필터링 + 정렬</h3>
            <div class="result">
                5보다 큰 수를 내림차순: ${numbers.stream().filter(x -> x > 5).sorted((a, b) -> b - a).toList()}
            </div>
        </div>
        
        <h2>10. 문자열 처리</h2>
        <div class="example">
            <h3>문자열 분리 및 처리</h3>
            <c:set var="text" value="Hello World Java JSP" />
            <div class="result">
                원본: ${text}<br>
                단어 분리: ${text.split(' ').stream().toList()}<br>
                대문자 변환: ${text.split(' ').stream().map(s -> s.toUpperCase()).toList()}<br>
                길이 4 이상: ${text.split(' ').stream().filter(s -> s.length() >= 4).toList()}
            </div>
        </div>
    </div>
</body>
</html>
