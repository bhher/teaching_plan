-- 기존 DB에 K-컬쳐 카테고리 추가 (이미 schema.sql 실행한 경우)
USE kculture_platform;

-- 기존 6개 카테고리 유지, 8개 추가 (중복 방지)
INSERT IGNORE INTO category (code, name_en, name_ko, icon, sort_order) VALUES
('k-movie', 'K-Movie', '한국영화', '🎬', 4),
('k-beauty', 'K-Beauty', 'K뷰티', '💄', 5),
('k-fashion', 'K-Fashion', '패션', '👗', 6),
('festivals', 'Festivals', '축제', '🎉', 9),
('accommodation', 'Accommodation', '숙소', '🏨', 10),
('shopping', 'Shopping', '쇼핑', '🛍️', 11),
('nightlife', 'Nightlife', '나이트라이프', '🌃', 12),
('k-gaming', 'K-Gaming', '게임/e스포츠', '🎮', 13);
