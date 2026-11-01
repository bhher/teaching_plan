-- 여행지 · 숙소 추천 (member_board와 별도 DB)
CREATE DATABASE IF NOT EXISTS travel_stay
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE travel_stay;

DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS wishlist;
DROP TABLE IF EXISTS stay;
DROP TABLE IF EXISTS destination;
DROP TABLE IF EXISTS member;

CREATE TABLE member (
  id       VARCHAR(50)  NOT NULL PRIMARY KEY,
  password VARCHAR(100) NOT NULL,
  name     VARCHAR(50)  NOT NULL,
  role     VARCHAR(10)  NOT NULL DEFAULT 'USER' COMMENT 'USER | ADMIN',
  reg_date DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE destination (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(100) NOT NULL COMMENT '여행지명',
  region      VARCHAR(50)  NOT NULL COMMENT '지역',
  description TEXT         NOT NULL,
  image       VARCHAR(255) NULL COMMENT '이미지 파일명 또는 URL',
  reg_date    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE stay (
  id             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  destination_id INT          NOT NULL,
  name           VARCHAR(100) NOT NULL COMMENT '숙소명',
  address        VARCHAR(255) NOT NULL,
  price          INT          NOT NULL DEFAULT 0 COMMENT '1박 기준(원)',
  description    TEXT         NULL,
  image          VARCHAR(255) NULL,
  reg_date       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_stay_dest FOREIGN KEY (destination_id)
    REFERENCES destination(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE wishlist (
  member_id      VARCHAR(50) NOT NULL,
  destination_id INT         NOT NULL,
  reg_date       DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (member_id, destination_id),
  CONSTRAINT fk_wish_member FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
  CONSTRAINT fk_wish_dest FOREIGN KEY (destination_id) REFERENCES destination(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE review (
  id             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  destination_id INT          NOT NULL,
  member_id      VARCHAR(50)  NOT NULL,
  rating         INT          NOT NULL COMMENT '1~5',
  content        VARCHAR(500) NOT NULL,
  reg_date       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_review_dest FOREIGN KEY (destination_id) REFERENCES destination(id) ON DELETE CASCADE,
  CONSTRAINT fk_review_member FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO member (id, password, name, role) VALUES
('admin', '1234', '관리자', 'ADMIN'),
('hong', '1234', '홍길동', 'USER'),
('kim', '1234', '김민수', 'USER');

INSERT INTO destination (name, region, description, image) VALUES
('경복궁', '서울', '조선의 법궁. 근정전과 경회루를 둘러볼 수 있습니다.', 'https://images.unsplash.com/photo-1583417319070-4a69db38a482?w=800'),
('해운대 해수욕장', '부산', '부산 대표 해변. 산책과 일출 명소입니다.', 'https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=800'),
('성산일출봉', '제주', '유네스코 세계자연유산. 일출 명소입니다.', 'https://images.unsplash.com/photo-1562680802-9cf8b15f419d?w=800'),
('남이섬', '강원', '메타세쿼이아길과 산책로가 유명합니다.', 'https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=800');

INSERT INTO stay (destination_id, name, address, price, description, image) VALUES
(1, '광화문 호텔', '서울 종로구', 120000, '경복궁 인근 비즈니스 호텔', NULL),
(1, '북촌 한옥스테이', '서울 종로구 북촌', 90000, '한옥 체험 숙소', NULL),
(2, '해운대 오션뷰', '부산 해운대구', 150000, '해변 전망 숙소', NULL),
(3, '성산 게스트하우스', '제주 서귀포시 성산읍', 70000, '일출봉 인근', NULL),
(4, '춘천 호수 펜션', '강원 춘천시', 110000, '남이섬 근처 펜션', NULL);

INSERT INTO review (destination_id, member_id, rating, content) VALUES
(1, 'hong', 5, '가을 단풍 때 최고예요.'),
(2, 'kim', 4, '여름 해수욕하기 좋아요.');

SELECT d.id, d.name, d.region, COUNT(s.id) AS stay_count
FROM destination d
LEFT JOIN stay s ON d.id = s.destination_id
GROUP BY d.id, d.name, d.region;
