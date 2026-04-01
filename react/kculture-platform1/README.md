# K-Culture Platform **1** (이미지 업로드)

`kculture-platform`과 동일한 구조에 **게시글 이미지 업로드**를 추가한 예제입니다.

## 원본과 차이

| 항목 | `kculture-platform` | `kculture-platform1` |
|------|---------------------|----------------------|
| API 포트 (기본) | 3001 | **3002** |
| 세션 쿠키 이름 | `kculture.sid` | **`kculture1.sid`** |
| 게시글 이미지 | 없음 | **`post.image_filename`**(파일명만), 실제 파일은 **`server/uploads/`** |

## DB 마이그레이션 (필수)

기존 `kculture_platform` DB에 컬럼을 추가합니다.

```bash
mysql -u root -p < schema_post_image.sql
```

**예전에 `image_path` 컬럼만 추가해 둔 DB**라면, 대신 `schema_post_image_rename_from_path.sql`로 컬럼명을 `image_filename`으로 바꾸고 값을 파일명만 남깁니다.

## 설치·실행

1. `server/.env.example`을 복사해 `server/.env`로 두고 MySQL 정보를 맞춥니다.
2. `server`에서 `npm install`, `client`에서 `npm install` (최초 1회).
3. 터미널 A: `cd server` → `npm run dev` → **http://localhost:3002**
4. 터미널 B: `cd client` → `npm run dev` → Vite 주소 (보통 5173)

Vite는 `/api`와 **`/uploads`(이미지 정적 파일)** 를 API 서버로 프록시합니다.

## 이미지 저장

- 업로드 위치: **`react/kculture-platform1/server/uploads/`** (실행 시 없으면 생성)
- 허용 형식: JPEG, PNG, GIF, WebP · 최대 약 5MB
- DB에는 **파일명만** 저장합니다 (예: `1730-xxxx.jpg`). 브라우저에서는 `/uploads/파일명`으로 접근합니다.

원본 `kculture-platform`과 **동시에 띄울 때**는 포트(3001 vs 3002)와 `CLIENT_ORIGIN`(Vite 포트)만 겹치지 않게 맞추면 됩니다.
