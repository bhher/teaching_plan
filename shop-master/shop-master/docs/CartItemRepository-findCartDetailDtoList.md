# `findCartDetailDtoList` 쿼리 설명

`CartItemRepository`의 `findCartDetailDtoList(Long cartId)` 메서드에 붙은 `@Query` JPQL을 정리한 문서입니다.

## 역할

특정 장바구니(`cartId`)에 담긴 **장바구니 상품 목록**을 조회하되, 화면에 바로 쓰기 좋은 형태인 **`CartDetailDto` 리스트**로 한 번에 매핑합니다. `CartService.getCartList` 등에서 호출됩니다.

## DTO 생성 방식: `select new`

```text
select new com.shop.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl)
```

JPQL에서 `select new 패키지.DTO(인자...)`는 조회 결과를 **엔티티가 아니라 DTO 생성자**에 넘겨 인스턴스를 만드는 방식입니다. `CartDetailDto`의 생성자 순서와 인자 타입이 쿼리의 인자 순서와 일치해야 합니다.

| 쿼리 인자 | DTO 필드 | 의미 |
|-----------|----------|------|
| `ci.id` | `cartItemId` | 장바구니 항목 ID |
| `i.itemNm` | `itemNm` | 상품명 |
| `i.price` | `price` | 상품 가격 |
| `ci.count` | `count` | 담은 수량 |
| `im.imgUrl` | `imgUrl` | 이미지 URL |

## `from`과 `join`

- **`from CartItem ci, ItemImg im`**  
  `CartItem`과 `ItemImg`를 **둘 다 FROM 절**에 올린 형태(암시적 카티션 곱에 가깝게 쓰이지만, 아래 `where`로 실질적으로 조인 조건을 걸어 제한합니다).

- **`join ci.item i`**  
  `CartItem` → `Item`(상품) 연관을 명시적으로 조인합니다. `i`로 상품명·가격 등을 참조합니다.

## `where` 조건

| 조건 | 의미 |
|------|------|
| `ci.cart.id = :cartId` | 해당 장바구니에 속한 항목만 |
| `im.item.id = ci.item.id` | 같은 상품의 이미지 행만 매칭 |
| `im.repimgYn = 'Y'` | **대표 이미지** 한 장만 사용 (썸네일용) |

같은 상품에 대표 이미지가 하나라는 전제가 있으면 행이 한 줄로 떨어지고, 목록 조회에 적합합니다.

## `order by`

- **`order by ci.regTime desc`**  
  장바구니에 **나중에 담은 항목이 위로** 오도록 등록 시각 기준 내림차순 정렬입니다.

## 메서드 시그니처와 파라미터

```java
List<CartDetailDto> findCartDetailDtoList(Long cartId);
```

Spring Data JPA는 메서드 인자 `cartId`를 JPQL의 `:cartId`에 바인딩합니다.

## 요약

한 장바구니의 `CartItem`을 상품(`Item`)·대표 이미지(`ItemImg`)와 묶어서, **DTO 프로젝션**으로 반환하는 조회 쿼리입니다. N+1을 피하고 화면용 필드만 가져오려는 전형적인 패턴입니다.
