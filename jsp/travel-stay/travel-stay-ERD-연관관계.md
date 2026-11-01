# travel-stay ERD · 연관관계 설명

**관련:** [상세 정리](./travel-stay-상세정리.md) · [db_setup.sql](./db_setup.sql)

이 문서는 `travel_stay` DB의 테이블 관계(FK, 1:N, N:M)를 수업용으로 정리한 것입니다.

---

## 1. 한눈에 보기

```text
member ──1:N──► wishlist ◄──N:1── destination
member ──1:N──► review   ◄──N:1── destination
                    ▲
destination ──1:N──► stay
```

중심은 **`destination`(여행지)** 입니다.  
숙소·찜·리뷰가 모두 여행지를 가리킵니다.

### 테이블 역할

| 테이블 | 역할 |
|--------|------|
| `member` | 회원 (USER / ADMIN) |
| `destination` | 여행지 |
| `stay` | 여행지 주변 숙소 |
| `wishlist` | 회원이 찜한 여행지 (중간 테이블) |
| `review` | 회원이 여행지에 남긴 리뷰 |

---

## 2. destination ↔ stay (1:N)

- 한 여행지에 **숙소 여러 개**가 붙을 수 있음  
- `stay.destination_id` → `destination.id`

예: 경복궁(`id = 1`) → 광화문 호텔, 북촌 한옥스테이 …

```sql
SELECT * FROM stay WHERE destination_id = 1;
```

- FK가 stay의 PK가 아니므로 ERD에서는 보통 **비식별 관계(점선)**  
- `ON DELETE CASCADE`: 여행지 삭제 시 연결된 숙소도 함께 삭제

```sql
CONSTRAINT fk_stay_dest FOREIGN KEY (destination_id)
  REFERENCES destination(id) ON DELETE CASCADE
```

---

## 3. member ↔ destination ↔ wishlist (N:M)

회원과 여행지는 **직접 연결되지 않고**, 중간 테이블 `wishlist`로 **다대다(N:M)** 를 만듭니다.

| 관계 | 의미 |
|------|------|
| member 1 : wishlist N | 한 회원이 찜을 여러 개 |
| destination 1 : wishlist N | 한 여행지를 여러 회원이 찜 |

### 복합 PK

```text
PRIMARY KEY (member_id, destination_id)
```

- 같은 회원이 같은 여행지를 **두 번 찜할 수 없음**  
- FK가 PK의 일부 → ERD에서 **식별 관계(실선)** 로 그려짐

```sql
CONSTRAINT fk_wish_member FOREIGN KEY (member_id)
  REFERENCES member(id) ON DELETE CASCADE,
CONSTRAINT fk_wish_dest FOREIGN KEY (destination_id)
  REFERENCES destination(id) ON DELETE CASCADE
```

회원 또는 여행지가 삭제되면 해당 찜 행도 CASCADE로 삭제됩니다.

---

## 4. member ↔ destination ↔ review

리뷰도 회원과 여행지를 잇지만, PK는 따로 `review.id`입니다.

| 관계 | 의미 |
|------|------|
| member 1 : review N | 한 회원이 리뷰 여러 개 |
| destination 1 : review N | 한 여행지에 리뷰 여러 개 |

- `review.destination_id`, `review.member_id`는 FK  
- **PK는 `id`만** → **비식별 관계(점선)**  
- 스키마상 같은 회원이 같은 여행지에 리뷰를 **여러 번** 쓸 수 있는 구조

```sql
CONSTRAINT fk_review_dest FOREIGN KEY (destination_id)
  REFERENCES destination(id) ON DELETE CASCADE,
CONSTRAINT fk_review_member FOREIGN KEY (member_id)
  REFERENCES member(id) ON DELETE CASCADE
```

---

## 5. stay와 review는 직접 연결 없음

ERD에서 선이 가까이 있어도, **`review` 테이블에는 `stay_id`가 없습니다.**

리뷰는 **숙소가 아니라 여행지**에 다는 설계입니다.

| 기능 | 연결 대상 |
|------|-----------|
| 주변 숙소 목록 | `stay.destination_id` |
| 평점·후기 | `review.destination_id` |

---

## 6. ERD 기호 읽는 법

| 표기 | 의미 |
|------|------|
| 금색 키 | Primary Key (PK) |
| 빨간 다이아 | Foreign Key (FK) |
| 파란 다이아 | 일반 컬럼 |
| 1 — N (까마귀발) | 한쪽 1건, 반대쪽 여러 건 |
| 실선 | **식별 관계** — FK가 자식 PK에 포함 (예: wishlist) |
| 점선 | **비식별 관계** — FK만 있고 별도 PK (예: stay, review) |

---

## 7. 수업용 한 줄 요약

- **여행지(`destination`)** 를 기준으로  
  - 숙소는 **1:N**  
  - 찜·리뷰는 회원과 여행지를 잇는 **연결(중간) 구조**  
- 찜 = 복합 PK로 **N:M을 깔끔하게**  
- 리뷰 = 별도 `id` PK로 **여러 건 작성 가능**  
- 삭제 시 **CASCADE**로 고아 행을 줄임  

---

## 8. JSP에서 관계가 보이는 곳

| 화면/코드 | 사용하는 관계 |
|-----------|----------------|
| `destination/list.jsp` | destination + stay/review JOIN (숙소수·평점) |
| `destination/view.jsp` | stay by destination_id, review, wishlist exists |
| `wishlist/toggleProcess.jsp` | member_id + destination_id |
| `review/writeProcess.jsp` | member_id + destination_id + rating |
| `admin` 여행지 삭제 | CASCADE로 stay·wishlist·review 동반 삭제 |
