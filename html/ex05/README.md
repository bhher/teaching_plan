# Flexbox 실전 예제

이 폴더는 Flexbox 실전을 학습하기 위한 예제 파일들을 포함합니다.

## 예제 파일 목록

### 1. `01-flex-container.html`
- display: flex 기본 사용법
- flex-direction (주축 방향)
- flex-wrap (줄바꿈)
- flex-flow (단축 속성)
- Flexbox 구조 이해

### 2. `02-alignment.html`
- justify-content (주축 정렬)
- align-items (교차축 정렬)
- align-content (여러 줄 정렬)
- gap (간격)
- 정렬 속성 조합 예제

### 3. `03-flex-items.html`
- flex-grow (공간 분배)
- flex-shrink (축소 비율)
- flex-basis (초기 크기)
- flex (단축 속성)
- align-self (개별 정렬)
- order (순서 변경)

### 4. `04-practical-layouts.html`
- 헤더 레이아웃
- 카드 행 레이아웃
- 푸터 레이아웃
- 중앙 정렬
- 스티키 푸터
- 실전 조합 예제

### 5. `05-complete-example.html`
- 모든 내용을 종합한 완성 예제
- 실제 웹사이트 형태
- 헤더, 히어로 섹션, 카드 그리드, 푸터 포함
- 반응형 디자인

## 사용 방법

1. 각 HTML 파일을 웹 브라우저에서 열어보세요
2. 브라우저의 개발자 도구(F12)를 사용하여 소스 코드를 확인하세요
3. 각 예제의 CSS 코드를 수정해보며 학습하세요
4. 화면 크기를 조절하여 반응형 동작을 확인하세요

## 학습 체크리스트

각 예제를 학습한 후 다음 사항을 확인하세요:

### Flex 컨테이너
- [ ] display: flex를 사용할 수 있는가?
- [ ] flex-direction으로 방향을 변경할 수 있는가?
- [ ] flex-wrap으로 줄바꿈을 제어할 수 있는가?

### 정렬
- [ ] justify-content로 주축 정렬을 할 수 있는가?
- [ ] align-items로 교차축 정렬을 할 수 있는가?
- [ ] align-content를 이해했는가?
- [ ] gap으로 간격을 조절할 수 있는가?

### 아이템 크기
- [ ] flex-grow로 공간 분배를 할 수 있는가?
- [ ] flex-shrink를 이해했는가?
- [ ] flex-basis로 초기 크기를 지정할 수 있는가?
- [ ] flex 단축 속성을 사용할 수 있는가?

### 실전 레이아웃
- [ ] 헤더 레이아웃을 만들 수 있는가?
- [ ] 카드 행 레이아웃을 만들 수 있는가?
- [ ] 푸터 레이아웃을 만들 수 있는가?
- [ ] 중앙 정렬을 할 수 있는가?
- [ ] 스티키 푸터를 만들 수 있는가?

## 주요 개념

### Flexbox 구조
- **Flex Container**: display: flex를 가진 부모 요소
- **Flex Item**: Container의 직접 자식 요소
- **Main Axis**: 아이템이 배치되는 주요 방향
- **Cross Axis**: 주축에 수직인 방향

### 자주 사용하는 패턴
- `flex: 1`: 균등 분배
- `flex: 0 0 250px`: 고정 크기 (사이드바 등)
- `flex: 1 1 300px`: 반응형 카드 (최소 300px)
- `justify-content: space-between`: 헤더 레이아웃
- `align-items: center`: 중앙 정렬

### 반응형 디자인
- `flex-wrap: wrap`: 줄바꿈 허용
- `gap`: 간격 조절
- `flex: 1 1 300px`: 최소 크기 지정

## 참고 사항

- 모든 예제는 최신 CSS 표준을 따릅니다
- Flexbox는 1차원 레이아웃을 위한 도구입니다
- 2차원 레이아웃이 필요하면 Grid를 사용하세요
- gap 속성은 최신 브라우저에서만 지원됩니다 (구형 브라우저는 margin 사용)

## 추가 학습 자료

- [MDN Web Docs - Flexbox](https://developer.mozilla.org/ko/docs/Web/CSS/CSS_Flexible_Box_Layout)
- [CSS-Tricks - A Complete Guide to Flexbox](https://css-tricks.com/snippets/css/a-guide-to-flexbox/)
- [Flexbox Froggy](https://flexboxfroggy.com/) - 게임으로 배우는 Flexbox
- [Can I Use](https://caniuse.com/) - 브라우저 호환성 확인

## 다음 단계

- Grid 레이아웃
- Flexbox와 Grid 조합
- 반응형 웹 디자인 심화
- CSS 애니메이션


