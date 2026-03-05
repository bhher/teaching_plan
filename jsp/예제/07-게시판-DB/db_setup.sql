-- 게시판 데이터베이스 및 테이블 생성
CREATE DATABASE IF NOT EXISTS jsp_board DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE jsp_board;

-- 게시판 테이블
CREATE TABLE IF NOT EXISTS board (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 번호',
    title VARCHAR(200) NOT NULL COMMENT '제목',
    content TEXT COMMENT '내용',
    writer VARCHAR(50) NOT NULL COMMENT '작성자',
    password VARCHAR(255) COMMENT '비밀번호 (수정/삭제용)',
    hit INT DEFAULT 0 COMMENT '조회수',
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
    mod_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    INDEX idx_writer (writer),
    INDEX idx_reg_date (reg_date),
    INDEX idx_title (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='게시판 테이블';

-- 댓글 테이블 (선택사항)
CREATE TABLE IF NOT EXISTS board_reply (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '댓글 번호',
    board_id INT NOT NULL COMMENT '게시글 번호',
    writer VARCHAR(50) NOT NULL COMMENT '작성자',
    content TEXT NOT NULL COMMENT '댓글 내용',
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
    FOREIGN KEY (board_id) REFERENCES board(id) ON DELETE CASCADE,
    INDEX idx_board_id (board_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='댓글 테이블';

-- 테스트 데이터 (선택사항)
INSERT INTO board (title, content, writer, password) VALUES
('첫 번째 게시글입니다', '게시판 테스트를 위한 첫 번째 게시글입니다.', '관리자', '1234'),
('두 번째 게시글입니다', '게시판 테스트를 위한 두 번째 게시글입니다.', '홍길동', '1234'),
('세 번째 게시글입니다', '게시판 테스트를 위한 세 번째 게시글입니다.', '김철수', '1234');
