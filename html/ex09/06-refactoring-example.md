# 코드 리팩토링 예제

이 문서는 코드 리팩토링의 실제 예제를 보여줍니다.

## 1. 중복 코드 제거

### 리팩토링 전

```css
/* 중복된 스타일 */
.button-primary {
    padding: 10px 20px;
    border-radius: 5px;
    background-color: #3498db;
    color: white;
    border: none;
    cursor: pointer;
}

.button-secondary {
    padding: 10px 20px;
    border-radius: 5px;
    background-color: #95a5a6;
    color: white;
    border: none;
    cursor: pointer;
}

.button-danger {
    padding: 10px 20px;
    border-radius: 5px;
    background-color: #e74c3c;
    color: white;
    border: none;
    cursor: pointer;
}
```

### 리팩토링 후

```css
/* 공통 스타일 추출 */
.button {
    padding: 10px 20px;
    border-radius: 5px;
    color: white;
    border: none;
    cursor: pointer;
}

.button--primary {
    background-color: #3498db;
}

.button--secondary {
    background-color: #95a5a6;
}

.button--danger {
    background-color: #e74c3c;
}
```

**개선점:**
- 중복 코드 제거
- 유지보수 용이 (스타일 변경 시 한 곳만 수정)
- BEM 방법론 적용

## 2. CSS 변수 활용

### 리팩토링 전

```css
.card {
    padding: 20px;
    margin: 20px;
    border-radius: 8px;
    background-color: #ffffff;
    color: #333333;
    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

.button {
    padding: 20px;
    margin: 20px;
    border-radius: 8px;
    background-color: #3498db;
    color: #ffffff;
}

.container {
    padding: 20px;
    margin: 20px;
    border-radius: 8px;
}
```

### 리팩토링 후

```css
:root {
    --spacing: 20px;
    --border-radius: 8px;
    --bg-color: #ffffff;
    --text-color: #333333;
    --primary-color: #3498db;
    --shadow: 0 2px 5px rgba(0,0,0,0.1);
}

.card {
    padding: var(--spacing);
    margin: var(--spacing);
    border-radius: var(--border-radius);
    background-color: var(--bg-color);
    color: var(--text-color);
    box-shadow: var(--shadow);
}

.button {
    padding: var(--spacing);
    margin: var(--spacing);
    border-radius: var(--border-radius);
    background-color: var(--primary-color);
    color: var(--bg-color);
}

.container {
    padding: var(--spacing);
    margin: var(--spacing);
    border-radius: var(--border-radius);
}
```

**개선점:**
- 일관된 디자인 시스템
- 테마 변경 용이
- 유지보수성 향상

## 3. 컴포넌트 분리

### 리팩토링 전

```css
/* main.css - 1000줄 이상 */
/* 모든 스타일이 한 파일에 */
```

### 리팩토링 후

```
css/
├── base/
│   ├── reset.css
│   └── typography.css
├── components/
│   ├── button.css
│   ├── card.css
│   └── nav.css
├── layouts/
│   ├── header.css
│   └── footer.css
└── main.css
```

**main.css:**
```css
@import url('base/reset.css');
@import url('base/typography.css');
@import url('components/button.css');
@import url('components/card.css');
@import url('components/nav.css');
@import url('layouts/header.css');
@import url('layouts/footer.css');
```

**개선점:**
- 파일 구조 명확
- 필요한 부분만 수정 가능
- 협업 효율성 향상

## 4. 매직 넘버 제거

### 리팩토링 전

```css
.container {
    max-width: 1200px;
    padding: 20px;
    margin: 0 auto;
}

.card {
    padding: 20px;
    margin-bottom: 20px;
}

.button {
    padding: 12px 24px;
    font-size: 16px;
}
```

### 리팩토링 후

```css
:root {
    --container-width: 1200px;
    --spacing-base: 20px;
    --spacing-small: 12px;
    --spacing-medium: 24px;
    --font-size-base: 16px;
}

.container {
    max-width: var(--container-width);
    padding: var(--spacing-base);
    margin: 0 auto;
}

.card {
    padding: var(--spacing-base);
    margin-bottom: var(--spacing-base);
}

.button {
    padding: var(--spacing-small) var(--spacing-medium);
    font-size: var(--font-size-base);
}
```

**개선점:**
- 의미 있는 이름 사용
- 값 변경 용이
- 일관성 유지

## 5. 긴 선택자 단순화

### 리팩토링 전

```css
.header .nav .nav__list .nav__item .nav__link {
    color: white;
    text-decoration: none;
}

.header .nav .nav__list .nav__item .nav__link:hover {
    color: #3498db;
}
```

### 리팩토링 후

```css
.nav__link {
    color: white;
    text-decoration: none;
}

.nav__link:hover {
    color: #3498db;
}
```

**개선점:**
- 선택자 단순화
- 특이성 감소
- 가독성 향상

## 6. 반복되는 패턴 추출

### 리팩토링 전

```css
.card-1 {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

.card-2 {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

.card-3 {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}
```

### 리팩토링 후

```css
.card {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

.card--variant-1 { }
.card--variant-2 { }
.card--variant-3 { }
```

**개선점:**
- 공통 스타일 재사용
- 코드 중복 제거
- 유지보수 용이

## 7. HTML 구조 개선

### 리팩토링 전

```html
<div class="header">
    <div class="logo">로고</div>
    <div class="nav">
        <div class="menu-item">홈</div>
        <div class="menu-item">소개</div>
        <div class="menu-item">연락처</div>
    </div>
</div>
```

### 리팩토링 후

```html
<header class="header">
    <div class="header__logo">로고</div>
    <nav class="header__nav">
        <ul class="nav__list">
            <li class="nav__item">
                <a href="#" class="nav__link">홈</a>
            </li>
            <li class="nav__item">
                <a href="#" class="nav__link">소개</a>
            </li>
            <li class="nav__item">
                <a href="#" class="nav__link">연락처</a>
            </li>
        </ul>
    </nav>
</header>
```

**개선점:**
- 시맨틱 태그 사용
- 접근성 향상
- BEM 방법론 적용

## 8. 리팩토링 체크리스트

리팩토링 전 확인사항:
- [ ] 현재 코드가 정상 작동하는가?
- [ ] 테스트가 있는가?
- [ ] 백업이 있는가?

리팩토링 중:
- [ ] 기능이 유지되는가?
- [ ] 점진적으로 리팩토링하는가?
- [ ] 각 단계마다 테스트하는가?

리팩토링 후:
- [ ] 모든 기능이 정상 작동하는가?
- [ ] 코드가 더 읽기 쉬운가?
- [ ] 유지보수가 쉬워졌는가?
- [ ] 성능이 개선되었는가?

## 9. 리팩토링 원칙

1. **작은 단계로 진행**
   - 한 번에 모든 것을 바꾸지 않기
   - 점진적으로 개선

2. **기능 유지**
   - 리팩토링은 기능 변경 없이 구조만 개선
   - 동작은 동일하게 유지

3. **테스트**
   - 리팩토링 전후 동작 확인
   - 각 단계마다 테스트

4. **일관성**
   - 프로젝트 전체에 일관된 스타일 적용
   - 팀과 협의된 규칙 따르기


