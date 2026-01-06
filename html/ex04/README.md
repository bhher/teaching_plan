# 박스 모델과 레이아웃 기본 예제

이 폴더는 CSS 박스 모델과 레이아웃 기본을 학습하기 위한 예제 파일들을 포함합니다.

## 예제 파일 목록

### 1. `01-box-model.html`
- Padding (내부 여백) 사용법
- Margin (외부 여백) 사용법
- Border (테두리) 사용법
- Box-sizing (content-box vs border-box)
- Margin Collapse (마진 겹침)
- 박스 모델 전체 구조 시각화
- 개발자 도구로 박스 모델 확인하기

### 2. `02-display.html`
- Block (블록) 요소
- Inline (인라인) 요소
- Inline-block (인라인 블록) 요소
- None vs Visibility: hidden
- Display 비교표
- 실전 예제: 버튼 그룹

### 3. `03-float-clear.html`
- Float 기본 사용법 (left, right)
- Float 문제: 부모 높이 사라짐
- Clear 속성 (left, right, both)
- Clearfix 해결 방법
- Overflow 해결 방법
- Float 레이아웃 (과거 방식)
- 현대적 대체 방법 (Flexbox, Grid)
- Float 사용 권장/비권장 시기

### 4. `04-debugging.html`
- 개발자 도구 사용법
- 요소가 보이지 않는 문제 해결
- 레이아웃이 깨지는 문제 해결
- 스타일이 적용되지 않는 문제 해결
- 디버깅 체크리스트
- 유용한 디버깅 기법
- 실전 디버깅 예제
- 개발자 도구 단축키 요약

### 5. `05-complete-example.html`
- 모든 내용을 종합한 완성 예제
- 실제 웹사이트 레이아웃 형태
- 박스 모델, Display, Flexbox를 모두 활용
- 반응형 디자인 포함

## 사용 방법

1. 각 HTML 파일을 웹 브라우저에서 열어보세요
2. 브라우저의 개발자 도구(F12)를 사용하여 소스 코드를 확인하세요
3. 각 예제의 CSS 코드를 수정해보며 학습하세요
4. 개발자 도구의 Computed 탭에서 박스 모델을 시각화해보세요

## 학습 체크리스트

각 예제를 학습한 후 다음 사항을 확인하세요:

### 박스 모델
- [ ] margin, padding, border를 사용할 수 있는가?
- [ ] box-sizing의 차이를 이해했는가?
- [ ] margin collapse를 이해했는가?
- [ ] 개발자 도구로 박스 모델을 확인할 수 있는가?

### Display
- [ ] block, inline, inline-block의 차이를 이해했는가?
- [ ] 각 display 값의 특징을 알고 있는가?
- [ ] 적절한 display 값을 선택할 수 있는가?

### Float와 Clear
- [ ] float의 개념을 이해했는가?
- [ ] clear의 필요성을 이해했는가?
- [ ] clearfix를 사용할 수 있는가?
- [ ] 현대적 대체 방법(Flexbox/Grid)을 알고 있는가?

### 디버깅
- [ ] 개발자 도구를 사용할 수 있는가?
- [ ] 박스 모델을 시각화할 수 있는가?
- [ ] 일반적인 문제를 해결할 수 있는가?

## 주요 개념

### Box-sizing
- **content-box**: width = 콘텐츠만 (기본값)
- **border-box**: width = 콘텐츠 + padding + border (권장)

### Display
- **block**: 새 줄에서 시작, width/height 사용 가능
- **inline**: 같은 줄에 배치, width/height 사용 불가
- **inline-block**: 같은 줄에 배치, width/height 사용 가능

### Float
- 원래 목적: 텍스트 주변에 이미지 배치
- 레이아웃 용도: 비권장 (Flexbox/Grid 사용 권장)
- 문제: 부모 높이 사라짐 → Clearfix로 해결

### 디버깅
- 개발자 도구 (F12) 활용
- Computed 탭에서 박스 모델 확인
- Styles 탭에서 적용된 규칙 확인
- Console 탭에서 에러 확인

## 참고 사항

- 모든 예제는 최신 CSS 표준을 따릅니다
- box-sizing: border-box를 전역으로 설정하는 것을 권장합니다
- Float는 레이아웃보다는 텍스트 주변 이미지 배치에 사용하는 것을 권장합니다
- 현대적 웹 개발에서는 Flexbox나 Grid를 사용하는 것이 좋습니다

## 추가 학습 자료

- [MDN Web Docs - Box Model](https://developer.mozilla.org/ko/docs/Web/CSS/CSS_Box_Model)
- [MDN Web Docs - Display](https://developer.mozilla.org/ko/docs/Web/CSS/display)
- [MDN Web Docs - Float](https://developer.mozilla.org/ko/docs/Web/CSS/float)
- [Can I Use](https://caniuse.com/) - 브라우저 호환성 확인

## 다음 단계

- Flexbox 레이아웃
- Grid 레이아웃
- Position 속성 (absolute, relative, fixed, sticky)
- 반응형 웹 디자인


