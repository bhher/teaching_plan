## Figma 컴포넌트 프로퍼티: 버튼 Variant 변환 (0:00–7:50)

아래 내용은 유튜브 영상 [Figma Component Property 튜토리얼](https://www.youtube.com/watch?v=2zCGpd6vEqs&t=963s) 0:00부터 7:50 구간을 정리한 것이다.

### 1. 기존 버튼 컴포넌트 이해
- 여러 색상/상태가 개별 컴포넌트로 흩어져 있으면 유지보수가 어렵다.
- 우선 기존 버튼들을 하나의 컴포넌트 세트로 묶어 구조를 파악한다.

### 2. Variant 기반 구조 설계
- 버튼에 필요한 상태를 텍스트로 적어보고 `Variant` 이름 (예: `Primary`, `Secondary`, `Disabled`)을 정한다.
- Variant 조합이 너무 많아지지 않도록 `Interaction state`, `Color theme` 등을 분리해두면 이후 관리가 쉽다.

### 3. Variant 생성 절차
1. 버튼들을 모두 선택해 `Combine as Variants` 적용.
2. 오른쪽 패널에서 `Properties` → `Variant` 이름을 `Variant` 혹은 `State` 등으로 지정.
3. 각 Variant의 속성 값(`Primary`, `Secondary` 등)을 원하는 이름으로 바꿔준다.

### 4. 컴포넌트 프로퍼티 연결
- Variant 외에도 `Text`, `Icon`, `Boolean` 프로퍼티를 추가해 필요한 조합을 만든다.
- 예: `Icon left` Boolean 프로퍼티를 만들어 켜면 아이콘이 노출되고 끄면 텍스트만 남도록 설정.

### 5. 인스턴스 사용 흐름
- 인스턴스를 선택하고 `Properties` 패널에서 `Variant` 드롭다운을 통해 버튼 스타일을 빠르게 교체한다.
- 텍스트/아이콘/상태를 한 번에 지정할 수 있어 디자이너와 개발자가 동일한 명명 규칙을 공유한다.

### 6. 실무 팁
- Variant 이름은 개발 측의 토큰 네이밍과 맞추면 협업이 편하다.
- 사용 빈도가 낮은 Variant는 제거하거나 별도 세트로 관리해 복잡성을 줄인다.
- 버튼별로 `Auto Layout`을 유지해 Variant 교체 시에도 패딩과 정렬이 깨지지 않도록 한다.




