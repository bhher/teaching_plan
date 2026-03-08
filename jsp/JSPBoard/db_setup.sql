-- DB 생성
CREATE DATABASE jspboard;
USE jspboard;

-- 회원 테이블
CREATE TABLE member(
    id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100),
    name VARCHAR(50),
    email VARCHAR(100),
    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 게시판 테이블
CREATE TABLE board(
    bno INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200),
    content TEXT,
    writer VARCHAR(50),
    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
