-- 미니 노션 계층형 게시판 DB 스키마
CREATE DATABASE IF NOT EXISTS mini_notion DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE mini_notion;

CREATE TABLE note (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL DEFAULT '',
    content TEXT,
    writer VARCHAR(50) DEFAULT '익명',
    -- 계층형 구조 (Re-level, Re-step)
    ref INT NOT NULL DEFAULT 0,        -- 원글(그룹) ID - 같은 원글에 달린 답글들은 같은 ref
    re_level INT NOT NULL DEFAULT 0,   -- 들여쓰기 단계 (0=원글, 1=1단계 답글, 2=2단계 답글...)
    re_step INT NOT NULL DEFAULT 0,  -- 같은 ref 내 정렬 순서
    -- Soft Delete (휴지통)
    deleted TINYINT NOT NULL DEFAULT 0,   -- 0=정상, 1=휴지통
    deleted_at DATETIME NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 정렬: ref 내림차순(최신 원글 먼저), re_step 오름차순(답글 순서)
-- SELECT * FROM note WHERE deleted=0 ORDER BY ref DESC, re_step ASC;
