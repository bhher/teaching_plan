-- 회원 테이블 생성
CREATE DATABASE IF NOT EXISTS jsp_member DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE jsp_member;

-- 회원 테이블
CREATE TABLE IF NOT EXISTS members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL UNIQUE COMMENT '아이디',
    password VARCHAR(255) NOT NULL COMMENT '비밀번호',
    name VARCHAR(50) NOT NULL COMMENT '이름',
    email VARCHAR(100) COMMENT '이메일',
    gender VARCHAR(10) COMMENT '성별',
    city VARCHAR(50) COMMENT '거주지',
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '가입일시',
    INDEX idx_user_id (user_id),
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='회원 정보 테이블';

-- 회원 취미 테이블 (다대다 관계)
CREATE TABLE IF NOT EXISTS member_hobbies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL COMMENT '회원 ID',
    hobby VARCHAR(50) NOT NULL COMMENT '취미',
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    INDEX idx_member_id (member_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='회원 취미 테이블';

-- 테스트 데이터 (선택사항)
-- INSERT INTO members (user_id, password, name, email, gender, city) 
-- VALUES ('admin', '1234', '관리자', 'admin@test.com', '남', '서울');
