-- 예전에 image_path(전체 경로)만 쓰던 DB용: 컬럼명을 image_filename으로 바꾸고 값을 파일명만 남김 (선택)
USE kculture_platform;

ALTER TABLE post CHANGE COLUMN image_path image_filename VARCHAR(255) NULL DEFAULT NULL
  COMMENT 'uploads 폴더 내 파일명 예: 1730-abc.jpg';

UPDATE post
SET image_filename = REPLACE(image_filename, '/uploads/', '')
WHERE image_filename LIKE '/uploads/%';
