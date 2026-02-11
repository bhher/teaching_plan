# getAttribute / setAttribute 예제 설명

## 목차

1. [기본 개념](#기본-개념)
2. [예제 파일 목록](#예제-파일-목록)
3. [각 예제 상세 설명](#각-예제-상세-설명)
4. [핵심 학습 포인트](#핵심-학습-포인트)

---

## 기본 개념

### getAttribute(attributeName)
- 요소의 특정 속성 값을 읽어옵니다.
- 속성이 없으면 `null`을 반환합니다.

```javascript
const element = document.getElementById('myElement');
const value = element.getAttribute('src');  // src 속성 값 읽기
```

### setAttribute(name, value)
- 요소에 속성을 추가하거나 기존 속성 값을 변경합니다.

```javascript
const element = document.getElementById('myElement');
element.setAttribute('src', 'new-image.jpg');  // src 속성 설정
element.setAttribute('data-id', '123');        // data 속성 추가
```

### hasAttribute(attributeName)
- 요소에 특정 속성이 있는지 확인합니다.
- `true` 또는 `false`를 반환합니다.

```javascript
if (element.hasAttribute('disabled')) {
    // disabled 속성이 있으면
}
```

### removeAttribute(attributeName)
- 요소에서 특정 속성을 제거합니다.

```javascript
element.removeAttribute('disabled');  // disabled 속성 제거
```

---

## 예제 파일 목록

1. **example6_attribute_basic.html** - 기본 사용법 예제
2. **example7_data_attributes.html** - Data 속성 활용 (쇼핑몰)
3. **example8_theme_switcher.html** - 테마 변경 (data-theme)
4. **example9_image_gallery.html** - 이미지 갤러리

---

## 각 예제 상세 설명

### 예제 1: example6_attribute_basic.html

**목적:** getAttribute와 setAttribute의 기본 사용법을 학습합니다.

**주요 기능:**
- 이미지의 `src`, `alt`, `title` 속성 읽기/쓰기
- 링크의 `href`, `target` 속성 변경
- 입력 필드의 `type`, `placeholder`, `disabled` 속성 제어
- `data-*` 속성 활용
- `hasAttribute`, `removeAttribute` 사용

**핵심 코드:**
```javascript
// 속성 읽기
const src = img.getAttribute('src');
const alt = img.getAttribute('alt');

// 속성 설정
img.setAttribute('src', 'new-image.jpg');
img.setAttribute('alt', '새 이미지');

// 속성 확인
if (input.hasAttribute('disabled')) {
    // 비활성화되어 있음
}

// 속성 제거
input.removeAttribute('disabled');
```

**학습 포인트:**
- 기본 HTML 속성 조작
- 속성 존재 여부 확인
- 동적 속성 변경

---

### 예제 2: example7_data_attributes.html

**목적:** `data-*` 속성을 활용하여 상품 정보를 저장하고 관리합니다.

**주요 기능:**
- 상품 카드에 `data-product-id`, `data-product-name`, `data-price`, `data-stock` 저장
- 클릭한 상품의 정보 읽기
- 재고 업데이트 (`data-stock` 속성 변경)
- 장바구니 기능 구현

**핵심 코드:**
```javascript
// data 속성 읽기
const productId = card.getAttribute('data-product-id');
const price = card.getAttribute('data-price');
const stock = card.getAttribute('data-stock');

// data 속성 업데이트
card.setAttribute('data-stock', newStock);

// 재고 상태에 따른 스타일 변경
const stock = parseInt(card.getAttribute('data-stock'));
if (stock < 5) {
    stockElement.classList.add('low');
}
```

**학습 포인트:**
- `data-*` 속성의 활용
- 속성 값을 숫자로 변환 (`parseInt`)
- 속성 값에 따른 동적 스타일 변경
- 실전 활용 패턴

---

### 예제 3: example8_theme_switcher.html

**목적:** `data-theme` 속성을 사용하여 페이지 테마를 동적으로 변경합니다.

**주요 기능:**
- `body` 요소에 `data-theme` 속성 설정
- CSS 변수와 속성 선택자 활용
- 로컬 스토리지에 테마 저장
- 페이지 새로고침 후에도 테마 유지

**핵심 코드:**
```javascript
// 테마 변경
function changeTheme(theme) {
    document.body.setAttribute('data-theme', theme);
    localStorage.setItem('theme', theme);
}

// 현재 테마 읽기
const currentTheme = document.body.getAttribute('data-theme');
```

**CSS 예제:**
```css
[data-theme="dark"] {
    --bg-color: #1a1a1a;
    --text-color: #ffffff;
}

[data-theme="blue"] {
    --bg-color: #e3f2fd;
    --text-color: #0d47a1;
}
```

**학습 포인트:**
- CSS 속성 선택자와의 연동
- CSS 변수 활용
- 로컬 스토리지와의 연동
- 사용자 설정 저장

---

### 예제 4: example9_image_gallery.html

**목적:** 이미지 갤러리에서 `src`와 `alt` 속성을 동적으로 변경합니다.

**주요 기능:**
- 썸네일 클릭 시 메인 이미지 변경
- `data-full-image` 속성으로 전체 크기 이미지 URL 저장
- `data-description` 속성으로 이미지 설명 저장
- 이전/다음 버튼으로 이미지 이동
- 키보드 화살표 키 지원

**핵심 코드:**
```javascript
function changeImage(thumbnail) {
    // data 속성에서 값 읽기
    const fullImageUrl = thumbnail.getAttribute('data-full-image');
    const altText = thumbnail.getAttribute('alt');
    const description = thumbnail.getAttribute('data-description');
    
    // 메인 이미지 속성 변경
    mainImage.setAttribute('src', fullImageUrl);
    mainImage.setAttribute('alt', altText);
    
    // 설명 업데이트
    document.getElementById('imageDescription').textContent = description;
}
```

**학습 포인트:**
- 이미지 속성 동적 변경
- `data-*` 속성으로 추가 정보 저장
- 키보드 이벤트 처리
- 사용자 인터랙션 향상

---

## 핵심 학습 포인트

### 1. 속성 읽기 (getAttribute)

```javascript
// 기본 속성
const href = link.getAttribute('href');
const src = img.getAttribute('src');
const type = input.getAttribute('type');

// data 속성
const userId = element.getAttribute('data-user-id');
const price = element.getAttribute('data-price');

// 속성이 없으면 null 반환
const value = element.getAttribute('non-existent');
// value === null
```

### 2. 속성 설정 (setAttribute)

```javascript
// 기본 속성 설정
img.setAttribute('src', 'image.jpg');
link.setAttribute('href', 'https://example.com');
input.setAttribute('type', 'password');

// data 속성 설정
element.setAttribute('data-id', '123');
element.setAttribute('data-theme', 'dark');

// 속성 값은 항상 문자열로 저장됨
element.setAttribute('data-count', 5);  // "5"로 저장
```

### 3. 속성 확인 (hasAttribute)

```javascript
// 속성 존재 여부 확인
if (button.hasAttribute('disabled')) {
    console.log('버튼이 비활성화되어 있습니다');
}

// 조건부 처리
if (!input.hasAttribute('required')) {
    // required 속성이 없으면
}
```

### 4. 속성 제거 (removeAttribute)

```javascript
// 속성 제거
button.removeAttribute('disabled');
input.removeAttribute('readonly');
div.removeAttribute('data-temp');
```

### 5. data-* 속성 활용

```html
<!-- HTML -->
<div data-product-id="1" data-price="10000" data-stock="5">
    상품
</div>
```

```javascript
// JavaScript
const productId = div.getAttribute('data-product-id');
const price = parseInt(div.getAttribute('data-price'));
const stock = parseInt(div.getAttribute('data-stock'));

// 속성 업데이트
div.setAttribute('data-stock', stock - 1);
```

### 6. 속성과 CSS 연동

```css
/* CSS 속성 선택자 */
[data-theme="dark"] {
    background-color: #1a1a1a;
}

[data-status="active"] {
    color: green;
}
```

```javascript
// JavaScript로 테마 변경
document.body.setAttribute('data-theme', 'dark');
```

---

## 실전 활용 팁

### 1. 속성 값 타입 변환

```javascript
// data 속성은 항상 문자열
const count = element.getAttribute('data-count');  // "5"
const numCount = parseInt(count);                  // 5

const price = element.getAttribute('data-price');  // "10000"
const numPrice = parseInt(price);                  // 10000
```

### 2. 속성 존재 여부 확인 후 처리

```javascript
// 안전한 속성 읽기
const value = element.getAttribute('data-value');
if (value !== null) {
    // 속성이 있을 때만 처리
    console.log(value);
}
```

### 3. 여러 속성 일괄 처리

```javascript
// 여러 속성 읽기
const id = element.getAttribute('data-id');
const name = element.getAttribute('data-name');
const price = element.getAttribute('data-price');

// 객체로 관리
const productData = {
    id: element.getAttribute('data-product-id'),
    name: element.getAttribute('data-product-name'),
    price: parseInt(element.getAttribute('data-price'))
};
```

### 4. 속성 변경 감지

```javascript
// MutationObserver로 속성 변경 감지
const observer = new MutationObserver(function(mutations) {
    mutations.forEach(function(mutation) {
        if (mutation.type === 'attributes') {
            console.log('속성이 변경되었습니다:', mutation.attributeName);
        }
    });
});

observer.observe(element, {
    attributes: true,
    attributeFilter: ['data-theme']  // 특정 속성만 감지
});
```

---

## 연습 문제

1. **기본 문제:** 버튼을 클릭하면 이미지의 `src` 속성을 변경하는 코드를 작성하세요.

2. **중급 문제:** `data-count` 속성을 가진 요소의 값을 증가시키는 함수를 작성하세요.

3. **고급 문제:** 여러 테마를 가진 페이지에서 현재 테마를 읽고, 사용자가 선택한 테마로 변경하는 기능을 구현하세요.

---

## 참고 자료

- [MDN: Element.getAttribute()](https://developer.mozilla.org/ko/docs/Web/API/Element/getAttribute)
- [MDN: Element.setAttribute()](https://developer.mozilla.org/ko/docs/Web/API/Element/setAttribute)
- [MDN: Element.hasAttribute()](https://developer.mozilla.org/ko/docs/Web/API/Element/hasAttribute)
- [MDN: Element.removeAttribute()](https://developer.mozilla.org/ko/docs/Web/API/Element/removeAttribute)
- [MDN: data-* 속성](https://developer.mozilla.org/ko/docs/Web/HTML/Global_attributes/data-*)

---

**작성일:** 2026-01-30  
**범위:** getAttribute / setAttribute 예제 및 설명
