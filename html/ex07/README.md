# 반응형 웹과 모바일 우선 설계 예제

이 폴더는 반응형 웹 디자인과 모바일 우선 설계를 학습하기 위한 예제 파일들을 포함합니다.

## 예제 파일 목록

### 1. `01-viewport-mobile-first.html`
- 뷰포트 메타 태그 설정
- 뷰포트 없을 때의 문제점
- 모바일 우선 설계 원칙
- 데스크톱 우선 vs 모바일 우선 비교
- 모바일 우선 개발 순서
- 뷰포트 테스트 방법

### 2. `02-media-queries.html`
- 기본 미디어 쿼리 (모바일 우선)
- 브레이크포인트 정의
- 화면 방향 (Orientation)
- 복합 조건 (AND, OR, NOT)
- 미디어 쿼리 적용 방법
- 실전 패턴

### 3. `03-fluid-layout-media.html`
- 고정 너비 vs 유동 너비
- 반응형 이미지 (max-width, object-fit)
- 반응형 비디오
- 유동형 레이아웃 (Flexbox)
- 퍼센트 레이아웃
- calc() 사용
- 반응형 이미지 고급 기법 (srcset, picture)

### 4. `04-layout-transitions.html`
- 네비게이션 메뉴 전환 (햄버거 → 가로 메뉴)
- 카드 그리드 전환 (1열 → 2열 → 3열 → 4열)
- 사이드바 레이아웃 전환 (하단 → 왼쪽)
- 텍스트 크기 전환
- 복합 레이아웃 전환
- 레이아웃 전환 체크리스트

### 5. `05-complete-example.html`
- 모든 내용을 종합한 완성 예제
- 실제 반응형 웹사이트 형태
- 모바일 우선 설계 적용
- 모든 브레이크포인트에서 테스트 가능
- 실제 사용 가능한 코드

## 사용 방법

1. 각 HTML 파일을 웹 브라우저에서 열어보세요
2. 브라우저의 개발자 도구(F12)를 사용하여 디바이스 모드로 전환하세요
3. 다양한 기기 크기를 선택하여 반응형 동작을 확인하세요
4. 각 예제의 CSS 코드를 수정해보며 학습하세요
5. 실제 모바일 기기에서도 테스트해보세요

## 학습 체크리스트

각 예제를 학습한 후 다음 사항을 확인하세요:

### 뷰포트
- [ ] 뷰포트 메타 태그를 올바르게 설정했는가?
- [ ] 모든 페이지에 뷰포트 메타 태그가 있는가?

### 모바일 우선
- [ ] 기본 스타일이 모바일용인가?
- [ ] 미디어 쿼리가 min-width를 사용하는가?
- [ ] 점진적 향상(Progressive Enhancement)을 따르는가?

### 미디어 쿼리
- [ ] 적절한 브레이크포인트를 정의했는가?
- [ ] 레이아웃이 각 브레이크포인트에서 올바르게 전환되는가?
- [ ] 불필요한 미디어 쿼리가 없는가?

### 반응형 요소
- [ ] 이미지가 반응형으로 처리되었는가?
- [ ] 비디오가 반응형으로 처리되었는가?
- [ ] 텍스트 크기가 적절한가?
- [ ] 터치 영역이 충분한가? (최소 44x44px)

### 테스트
- [ ] 다양한 화면 크기에서 테스트했는가?
- [ ] 실제 기기에서 테스트했는가?
- [ ] 가로 스크롤이 없는가?

## 주요 개념

### 뷰포트 메타 태그
```html
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```
- 반응형 웹 디자인의 필수 요소
- 모바일에서 올바른 크기로 표시

### 모바일 우선 설계
- 기본 스타일: 모바일 (작은 화면)
- 미디어 쿼리: 태블릿, 데스크톱 (큰 화면)
- min-width 사용

### 브레이크포인트
- 모바일: 320px ~ 479px
- 작은 태블릿: 480px ~ 767px
- 태블릿: 768px ~ 1023px
- 데스크톱: 1024px ~ 1439px
- 큰 데스크톱: 1440px ~

### 유동형 레이아웃
- `width: 100%; max-width: 1200px;`
- `flex: 1 1 300px;`
- `grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));`

### 반응형 이미지
- `max-width: 100%; height: auto;`
- `object-fit: cover;` 또는 `contain;`
- `srcset`과 `sizes` (고급)

## 참고 사항

- 모든 예제는 모바일 우선으로 디자인되었습니다
- 뷰포트 메타 태그는 반드시 필요합니다
- 실제 기기에서 테스트하는 것을 권장합니다
- 브라우저 개발자 도구의 디바이스 모드를 활용하세요
- 가로 스크롤이 발생하지 않도록 주의하세요

## 추가 학습 자료

- [MDN Web Docs - Responsive Design](https://developer.mozilla.org/ko/docs/Learn/CSS/CSS_layout/Responsive_Design)
- [MDN Web Docs - Media Queries](https://developer.mozilla.org/ko/docs/Web/CSS/Media_Queries)
- [Google - Mobile-Friendly Test](https://search.google.com/test/mobile-friendly)
- [Can I Use](https://caniuse.com/) - 브라우저 호환성 확인

## 다음 단계

- CSS 변수와 커스텀 속성
- CSS 애니메이션
- 실전 프로젝트
- 성능 최적화


