-- 기존 kculture_platform DB에 게시글 이미지 파일명 컬럼 추가 (한 번만 실행)
USE kculture_platform;

ALTER TABLE post
  ADD COLUMN image_filename VARCHAR(255) NULL DEFAULT NULL
  COMMENT 'uploads 폴더 내 파일명 예: 1730-abc.jpg'
  AFTER content;
