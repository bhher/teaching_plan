# CSS Grid 실전 예제

이 폴더는 CSS Grid 실전을 학습하기 위한 예제 파일들을 포함합니다.

## 예제 파일 목록

### 1. `01-grid-basics.html`
- display: grid 기본 사용법
- grid-template-columns (열 정의)
- grid-template-rows (행 정의)
- repeat() 함수 활용
- Grid 구조 이해

### 2. `02-grid-areas-gap.html`
- gap (간격) 설정
- grid-column / grid-row (영역 지정)
- grid-area (영역 이름으로 배치)
- grid-template-areas 사용법
- 복잡한 영역 배치 예제

### 3. `03-practical-layouts.html`
- 복잡한 페이지 레이아웃
- 반응형 그리드 (auto-fit)
- 카드 그리드 (auto-fill)
- 잡지형 레이아웃 (Magazine Layout)
- 반응형 페이지 레이아웃

### 4. `04-grid-flex-combination.html`
- Grid 안에 Flexbox
- Flexbox 안에 Grid
- 실전 조합 예제
- 언제 Grid를 사용할까? 언제 Flexbox를 사용할까?
- 실전 패턴 모음

### 5. `05-complete-example.html`
- 모든 내용을 종합한 완성 예제
- 실제 웹사이트 형태
- Grid와 Flexbox 조합
- 헤더, 사이드바, 히어로, 카드 그리드, 푸터 포함
- 반응형 디자인

## 사용 방법

1. 각 HTML 파일을 웹 브라우저에서 열어보세요
2. 브라우저의 개발자 도구(F12)를 사용하여 소스 코드를 확인하세요
3. 각 예제의 CSS 코드를 수정해보며 학습하세요
4. 화면 크기를 조절하여 반응형 동작을 확인하세요

## 학습 체크리스트

각 예제를 학습한 후 다음 사항을 확인하세요:

### Grid 기본
- [ ] display: grid를 사용할 수 있는가?
- [ ] grid-template-columns로 열을 정의할 수 있는가?
- [ ] grid-template-rows로 행을 정의할 수 있는가?
- [ ] repeat() 함수를 사용할 수 있는가?

### 영역 배치
- [ ] grid-area로 영역을 배치할 수 있는가?
- [ ] grid-column/grid-row로 위치를 지정할 수 있는가?
- [ ] grid-template-areas로 레이아웃을 정의할 수 있는가?
- [ ] gap으로 간격을 조절할 수 있는가?

### 실전 레이아웃
- [ ] 복잡한 페이지 레이아웃을 만들 수 있는가?
- [ ] 반응형 그리드를 만들 수 있는가?
- [ ] auto-fit과 auto-fill의 차이를 이해했는가?

### Grid와 Flexbox 조합
- [ ] Grid 안에 Flexbox를 사용할 수 있는가?
- [ ] Flexbox 안에 Grid를 사용할 수 있는가?
- [ ] 언제 Grid를 사용하고 언제 Flexbox를 사용할지 판단할 수 있는가?

## 주요 개념

### Grid 구조
- **Grid Container**: display: grid를 가진 부모 요소
- **Grid Item**: Container의 직접 자식 요소
- **Grid Line**: 그리드를 나누는 선
- **Grid Track**: 그리드 선 사이의 공간 (행 또는 열)
- **Grid Cell**: 행과 열이 교차하는 영역
- **Grid Area**: 하나 이상의 셀로 구성된 영역

### 자주 사용하는 패턴
- `grid-template-columns: repeat(3, 1fr)`: 3개 열, 균등 분배
- `grid-template-columns: repeat(auto-fit, minmax(250px, 1fr))`: 반응형 그리드
- `grid-template-areas`: 영역 이름으로 레이아웃 정의
- `gap: 20px`: 간격 설정

### Grid vs Flexbox
- **Grid**: 2차원 레이아웃 (가로 + 세로)
- **Flexbox**: 1차원 레이아웃 (가로 또는 세로)
- **조합 사용**: 전체 레이아웃은 Grid, 내부 정렬은 Flexbox

## 참고 사항

- 모든 예제는 최신 CSS 표준을 따릅니다
- Grid는 2차원 레이아웃을 위한 도구입니다
- 1차원 레이아웃이 필요하면 Flexbox를 사용하세요
- Grid와 Flexbox를 조합하여 사용하는 것이 가장 효과적입니다
- gap 속성은 최신 브라우저에서만 지원됩니다 (구형 브라우저는 grid-gap 사용)

## 추가 학습 자료

- [MDN Web Docs - Grid](https://developer.mozilla.org/ko/docs/Web/CSS/CSS_Grid_Layout)
- [CSS-Tricks - A Complete Guide to Grid](https://css-tricks.com/snippets/css/complete-guide-grid/)
- [Grid Garden](https://cssgridgarden.com/) - 게임으로 배우는 Grid
- [Can I Use](https://caniuse.com/) - 브라우저 호환성 확인

## 다음 단계

- Grid 고급 기법 (subgrid, masonry)
- 반응형 웹 디자인 심화
- CSS 애니메이션
- 실전 프로젝트


