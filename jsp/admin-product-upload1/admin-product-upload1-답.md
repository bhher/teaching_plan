# admin-product-upload1 — 정답

문제: [admin-product-upload1-문제.md](./admin-product-upload1-문제.md)  
이론: [admin-product-upload1-상세정리.md](./admin-product-upload1-상세정리.md)

---

## 객관식

| 번호 | 정답 | 해설 |
|------|------|------|
| Q1 | **②** | 수정·삭제가 upload1의 핵심 추가분 |
| Q2 | **③** | `selectById`로 한 건 조회 |
| Q3 | **②** | hidden `id`로 UPDATE 대상 지정 |
| Q4 | **②** | 파일 변경 가능 → multipart |
| Q5 | **②** | 새 파일 없으면 기존 파일명 유지 |
| Q6 | **②** | `WHERE id = ?` |
| Q7 | **②** | POST + hidden + confirm |
| Q8 | **②** | 일반 폼 → `request.getParameter` |
| Q9 | **③** | Write 필수 / Update 선택 |
| Q10 | **②** | 세션 로그인 검사 |

---

## 단답

### Q11
```text
id
Process   (productUpdateProcess.jsp)
update
```

### Q12
`Image` → `old.getImage()`

### Q13
`id`

### Q14
`uploads`

### Q15
`selectById`, `update`, `delete`

---

## 코드 고치기

### Q16
1. `fileName`이 null이면 DB image가 비게 됨 → **기존 image를 기본값**으로 둔다.  
2. (권장) `selectById`로 존재 여부 확인 후 update.

```jsp
ProductDTO old = dao.selectById(id);
String image = old.getImage();
if (fileName != null && !fileName.isEmpty()) {
    image = fileName;
}
p.setImage(image);
dao.update(p);
```

### Q17
1. **`enctype="multipart/form-data"`** 추가  
2. **`<input type="hidden" name="id" value="...">`** 추가  

### Q18
- GET으로 삭제되어 주소창·북마크·봇으로도 호출될 수 있음 → **POST** 권장  
- **`confirm` 확인**이 없음  
- (이 프로젝트는 form POST + confirm 사용)

---

## 서술

### Q19
수정은 「사진까지 반드시 다시 올리기」가 아니라 이름·가격만 바꾸는 경우가 많다.  
`required`를 넣으면 매번 파일을 골라야 해서 불편하고,  
Process에서 새 파일이 없을 때 기존 파일명을 유지하는 설계와 맞춘다.

### Q20
- **폼 화면:** `selectById`로 기존 값을 input에 채워 보여 줌  
- **Process:** 상품 존재 확인 + 새 사진 없을 때 **기존 image**를 읽기 위해 사용  

---

## 실습 가이드

### 실습 1
상품명만 바꿔도 썸네일이 그대로면 image 유지 로직 정상.

### 실습 2
`getFilesystemName`이 새 이름을 주고, 목록 URL이 새 파일을 가리키면 교체 성공.  
(이전 파일은 폴더에 남을 수 있음 — 물리 삭제는 실습 5)

### 실습 3
취소 → 요청 자체가 안 감 → DB 행 유지.

### 실습 4
주석 위치 예: (1) savePath·Multipart (2) 파라미터·검증 (3) selectById (4) image if (5) update (6) redirect 목록

### 실습 5 (예시 의사코드)

```text
id 받기
ProductDTO old = selectById(id)
if old == null → 목록으로 return
delete(id)   // DB 먼저 또는 파일 먼저 — 팀 규칙에 맞게
if old.image 있으면
  new File(savePath, old.image).delete()
목록으로 redirect
```
