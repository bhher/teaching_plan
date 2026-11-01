-- ============================================================
-- JSP CRUD 게시판 (Bootstrap) — MySQL
-- ============================================================
-- 사용법: MySQL Workbench 또는 콘솔에서 전체 실행
-- 계정 예: root / 1234  (DBConnection.java와 맞출 것)
-- ============================================================

CREATE DATABASE IF NOT EXISTS crud_board
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE crud_board;

DROP TABLE IF EXISTS board;

CREATE TABLE board (
  id        INT AUTO_INCREMENT PRIMARY KEY COMMENT '글 번호',
  title     VARCHAR(200)  NOT NULL COMMENT '제목',
  content   TEXT          NOT NULL COMMENT '내용',
  writer    VARCHAR(50)   NOT NULL COMMENT '작성자',
  password  VARCHAR(100)  NOT NULL COMMENT '수정/삭제용 비밀번호',
  hit       INT           NOT NULL DEFAULT 0 COMMENT '조회수',
  reg_date  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
  mod_date  DATETIME      NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
  INDEX idx_reg_date (reg_date),
  INDEX idx_writer (writer)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='CRUD 게시판';

-- 샘플 데이터
INSERT INTO board (title, content, writer, password, hit) VALUES
('JSP CRUD 게시판을 시작합니다',
 'Bootstrap과 MySQL을 이용한 게시판입니다.\n목록 · 글쓰기 · 상세 · 수정 · 삭제를 연습하세요.',
 '관리자', '1234', 10),
('두번째 글입니다',
 '내용 수정과 삭제는 비밀번호(1234)가 필요합니다.',
 '홍길동', '1234', 3),
('부트스트랩 카드와 테이블',
 '목록은 table, 작성/수정은 form 컴포넌트를 사용합니다.',
 '김영희', '1234', 1);

-- 확인
SELECT id, title, writer, hit, reg_date FROM board ORDER BY id DESC;
