-- K-컬쳐 외국인 관광객 커뮤니티 플랫폼
CREATE DATABASE IF NOT EXISTS kculture_platform DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE kculture_platform;

-- 회원 (외국인 관광객)
CREATE TABLE member (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    nationality VARCHAR(50) DEFAULT NULL,    -- 국적
    language VARCHAR(20) DEFAULT 'en',       -- 선호 언어
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- K-컬쳐 카테고리 (다각화)
CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(30) NOT NULL UNIQUE,
    name_en VARCHAR(50) NOT NULL,
    name_ko VARCHAR(50) NOT NULL,
    icon VARCHAR(20) DEFAULT '📌',
    sort_order INT DEFAULT 0
);

-- K-컬쳐 카테고리 (다각화 확장)
INSERT INTO category (code, name_en, name_ko, icon, sort_order) VALUES
('k-food', 'K-Food', '한식', '🍜', 1),
('k-pop', 'K-Pop', '케이팝', '🎵', 2),
('k-drama', 'K-Drama', '드라마', '📺', 3),
('k-movie', 'K-Movie', '한국영화', '🎬', 4),
('k-beauty', 'K-Beauty', 'K뷰티', '💄', 5),
('k-fashion', 'K-Fashion', '패션', '👗', 6),
('travel', 'Travel', '여행', '✈️', 7),
('traditional', 'Traditional', '전통문화', '🏯', 8),
('festivals', 'Festivals', '축제', '🎉', 9),
('accommodation', 'Accommodation', '숙소', '🏨', 10),
('shopping', 'Shopping', '쇼핑', '🛍️', 11),
('nightlife', 'Nightlife', '나이트라이프', '🌃', 12),
('k-gaming', 'K-Gaming', '게임/e스포츠', '🎮', 13),
('tips', 'Tips', '팁&정보', '💡', 14);

-- 게시글
CREATE TABLE post (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT NOT NULL,
    member_id INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    view_count INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

-- 댓글
CREATE TABLE comment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    member_id INT NOT NULL,
    content TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES member(id)
);

-- 테스트 회원 (비밀번호: 1234)
INSERT INTO member (email, password, name, nationality, language) VALUES
('tourist@test.com', '1234', 'John', 'USA', 'en'),
('visitor@test.com', '1234', '田中', 'Japan', 'ja');
