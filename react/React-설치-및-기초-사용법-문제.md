# React 설치 및 기초 사용법 — 문제

교안: [React-설치-및-기초-사용법-교안.md](./React-설치-및-기초-사용법-교안.md)  
정답: [React-설치-및-기초-사용법-정답.md](./React-설치-및-기초-사용법-정답.md)  
**환경:** Vite + React + **`.jsx`**

---

# Part 1. 개념 · 환경

## 1번

React에 대한 설명으로 **올바른** 것은?

① CSS 전용 프레임워크이다  
② UI를 만들기 위한 **JavaScript 라이브러리**이다  
③ 데이터베이스이다  
④ Python 웹 서버이다  

---

## 2번

React의 가장 큰 특징으로 **적절한** 것은?

① 화면을 **컴포넌트** 단위로 나눠 개발한다  
② HTML 파일만으로 동작한다  
③ MySQL이 필수이다  
④ CSS를 사용할 수 없다  

---

## 3번

React 프로젝트 생성·실행에 **반드시** 필요한 것은?

① Photoshop  
② **Node.js** (npm)  
③ MySQL Workbench  
④ Android Studio  

---

## 4번

Node.js 설치 확인 명령으로 **올바른** 것은?

① `java -version`  
② `node -v` / `npm -v`  
③ `mysql -u root`  
④ `git clone react`  

---

## 5번

이 교안에서 권장하는 React 프로젝트 생성 도구는?

① Create React App만  
② **Vite**  
③ Eclipse  
④ Tomcat  

---

## 6번 (코드/명령)

Vite로 React(JavaScript) 프로젝트를 **한 줄**로 만드는 명령을 쓰시오.  
(프로젝트 이름: `my-react-app`)

---

## 7번

프로젝트 생성 후 패키지 설치 → 개발 서버 실행 순서로 **올바른** 것은?

① `npm run build` → `npm start`  
② `npm install` → `npm run dev`  
③ `npm create` → `npm uninstall`  
④ `npx serve` → `npm init`  

---

## 8번

Vite 개발 서버 기본 주소로 **흔한** 것은?

① `http://localhost:3306`  
② `http://localhost:5173`  
③ `http://localhost:8080`  
④ `http://localhost:3001` 만 가능  

---

# Part 2. 구조 · main · App

## 9번

Vite React 프로젝트에서 **프로그램 시작점** 파일은?

① `App.css`  
② `main.jsx`  
③ `vite.config.js`  
④ `package-lock.json`  

---

## 10번

`index.html`의 `<div id="root"></div>` 와 관련해 **올바른** 설명은?

① CSS만 들어간다  
② React 컴포넌트가 **이 안에 렌더링**된다  
③ 삭제해도 된다  
④ MySQL 연결점이다  

---

## 11번

요즘 React에서 화면에 그리는 방식으로 **올바른** 것은?

① `ReactDOM.render(<App />, ...)` 만 사용  
② `createRoot(...).render(<App />)`  
③ `document.write(<App />)`  
④ `alert(<App />)`  

---

## 12번 (코드)

`App.jsx`에 화면에 `Hello React!` 를 출력하는 가장 단순한 컴포넌트를 작성하시오.  
(`export default` 포함)

---

## 13번 (단답)

렌더링 흐름을 순서대로 쓰시오.  
`index.html` → (   ) → (   ) → 자식 컴포넌트

---

# Part 3. JSX

## 14번

JSX에서 CSS 클래스를 지정할 때 **올바른** 문법은?

① `class="box"`  
② `className="box"`  
③ `classList="box"`  
④ `css="box"`  

---

## 15번

JSX 안에서 JavaScript 변수를 넣을 때 사용하는 기호는?

① `[]`  
② `{}`  
③ `()`  
④ `<>`  

---

## 16번

다음 중 **오류**가 나는 코드는?

① `<input />`  
② `<br />`  
③ `<input>` (닫지 않음)  
④ `<Hello />`  

---

## 17번

두 개 이상의 요소를 return할 때 **올바른** 방법은?

① 그냥 나란히 return  
② 하나의 부모 태그 또는 **Fragment `<>...</>`** 로 감싼다  
③ `return`을 두 번 쓴다  
④ `export`를 두 번 쓴다  

---

## 18번 (코드)

인라인 style로 글자색을 `red`, 글자 크기를 `24`로 주는 `style` 객체를 작성하시오.  
(camelCase 주의)

---

## 19번

JSX 주석으로 **올바른** 것은?

① `// 주석` 만 JSX 텍스트 사이에 쓰면 항상 안 보인다  
② `{/* 주석 */}`  
③ `<!-- 주석 -->` 만 사용  
④ `# 주석`  

---

## 20번 (코드)

`name`, `age` 변수를 화면에 출력하는 JSX를 완성하시오.

```jsx
function App() {
  const name = '홍길동'
  const age = 20
  return (
    <div>
      {/* 여기에 이름, 나이 출력 */}
    </div>
  )
}
```

---

# Part 4. 컴포넌트 · props · children

## 21번

컴포넌트를 다른 파일에서 쓰려면 보통 필요한 것은?

① `export default` 후 `import`  
② `CREATE TABLE`  
③ `npm uninstall`  
④ `localhost:3306`  

---

## 22번 (코드)

`src/Hello.jsx`에 **"안녕하세요"** 를 반환하는 함수형 컴포넌트를 작성하시오.

---

## 23번 (코드)

`App.jsx`에서 `Hello`를 import하여 **세 번** 화면에 그리시오.

---

## 24번

props에 대한 설명으로 **올바른** 것은?

① 자식 → 부모로만 전달된다  
② 부모 → 자식으로 값을 전달한다  
③ CSS 파일 이름이다  
④ npm 패키지이다  

---

## 25번 (코드)

`Hello`에 `name="react"`, `color="blue"` 를 넘기고,  
Hello에서는 비구조화 할당으로 받아 색이 적용된 인사말을 출력하시오.  
(`Hello.jsx` + `App.jsx` 둘 다)

---

## 26번

함수형 컴포넌트에서 props 기본값으로 **이 교안이 권장**하는 방식은?

① `Hello.defaultProps = { name: '이름없음' }`  
② 기본 매개변수 `function Hello({ name = '이름없음' })`  
③ `window.name = '이름없음'`  
④ `localStorage`에 저장  

---

## 27번 (코드)

`name`을 안 넘기면 **"이름없음"** 이 나오도록 `Hello`를 **기본 매개변수**로 작성하시오.

---

## 28번

`props.children`의 역할은?

① npm 버전 확인  
② 컴포넌트 **태그 사이에 넣은 내용**을 받아서 렌더링  
③ CSS만 불러오기  
④ DB 연결  

---

## 29번 (코드)

테두리·패딩이 있는 `Wrapper` 컴포넌트를 만들고,  
안에서 `Hello` 두 개를 `children`으로 보여 주시오.

---

# Part 5. 조건부 렌더링 · 종합

## 30번

`isSpecial`이 true일 때만 `<b>*</b>` 를 보여줄 때 **간편한** 문법은?

① `isSpecial ? null : <b>*</b>` 만 가능  
② `isSpecial && <b>*</b>`  
③ `isSpecial || <b>*</b>` 만  
④ `if isSpecial then`  

---

## 31번

`<Hello isSpecial />` 는 무엇과 같은가?

① `isSpecial={false}`  
② `isSpecial={true}`  
③ `isSpecial="isSpecial"`  
④ props가 없다  

---

## 32번 (종합 실습)

Vite 프로젝트에서 다음을 **직접 구현**하시오. (파일은 모두 `.jsx`)

1. `Hello.jsx` — props: `name`, `color`, `isSpecial`  
   - `name` 기본값 `'이름없음'`  
   - `isSpecial`이면 앞에 `*`  
2. `Wrapper.jsx` — `children`을 테두리 박스 안에 표시  
3. `App.jsx`  
   - Wrapper 안에 Hello 두 개  
   - 하나는 `name="react" color="red" isSpecial`  
   - 다른 하나는 `color="pink"` 만  

---

# 채점 가이드 (강사용)

| 구분 | 문항 | 배점 예 |
|------|------|---------|
| 객관·단답 | 1~5, 7~11, 14~17, 19, 21, 24, 26, 28, 30~31 | 각 2점 |
| 명령·코드 | 6, 12, 18, 20, 22~23, 25, 27, 29 | 각 3~4점 |
| 종합 | 13, 32 | 5 / 15점 |
| **합계** | | **약 100점** |

**통과 권장:** 70점 + 32번 동작 확인  
**감점:** `.js`만 쓰거나 `defaultProps`만 고집, `class=` 사용, 태그 미닫음
