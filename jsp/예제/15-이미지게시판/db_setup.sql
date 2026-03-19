-- 이미지 게시판 데이터베이스 및 테이블 생성
CREATE DATABASE IF NOT EXISTS jsp_image_board DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE jsp_image_board;

-- 이미지 게시판 테이블
CREATE TABLE IF NOT EXISTS image_board (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 번호',
    title VARCHAR(200) NOT NULL COMMENT '제목',
    content TEXT COMMENT '내용',
    writer VARCHAR(50) NOT NULL COMMENT '작성자',
    password VARCHAR(255) COMMENT '비밀번호 (수정/삭제용)',
    image_file VARCHAR(255) COMMENT '이미지 파일명',
    image_original VARCHAR(255) COMMENT '원본 이미지 파일명',
    hit INT DEFAULT 0 COMMENT '조회수',
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
    mod_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    INDEX idx_writer (writer),
    INDEX idx_reg_date (reg_date),
    INDEX idx_title (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='이미지 게시판 테이블';

-- 테스트 데이터 (선택사항)
INSERT INTO image_board (title, content, writer, password) VALUES
('첫 번째 이미지 게시글', '이미지 게시판 테스트를 위한 첫 번째 게시글입니다.', '관리자', '1234'),
('두 번째 이미지 게시글', '이미지 게시판 테스트를 위한 두 번째 게시글입니다.', '홍길동', '1234');
