// 1. this 바인딩 테스트
function testThis() {
    // 일반 함수
    const obj1 = {
        name: "객체1",
        greet: function() {
            return this.name;
        }
    };
    
    // 화살표 함수
    const obj2 = {
        name: "객체2",
        greet: () => {
            return this.name || "undefined (상위 스코프 참조)";
        }
    };
    
    document.getElementById("this-result").innerHTML = 
        `일반 함수: ${obj1.greet()}\n` +
        `화살표 함수: ${obj2.greet()}`;
    console.log("this 테스트:", obj1.greet(), obj2.greet());
}

// 2. 고차 함수 테스트
function testHigherOrder() {
    function multiplyBy(n) {
        return function(x) {
            return x * n;
        };
    }
    
    const double = multiplyBy(2);
    const triple = multiplyBy(3);
    
    document.getElementById("higher-order-result").innerHTML = 
        `double(5) = ${double(5)}\n` +
        `triple(5) = ${triple(5)}`;
    console.log("고차 함수 테스트:", double(5), triple(5));
}

// 3. 클로저 테스트
function testClosure() {
    function createCounter() {
        let count = 0;
        return function() {
            count++;
            return count;
        };
    }
    
    const counter = createCounter();
    const result1 = counter();
    const result2 = counter();
    const result3 = counter();
    
    document.getElementById("closure-result").innerHTML = 
        `첫 번째 호출: ${result1}\n` +
        `두 번째 호출: ${result2}\n` +
        `세 번째 호출: ${result3}`;
    console.log("클로저 테스트:", result1, result2, result3);
}

// 4. 콜백 함수 테스트
function testCallback() {
    function processData(data, callback) {
        const result = data.map(item => item * 2);
        callback(result);
    }
    
    let callbackResult = "";
    processData([1, 2, 3], (result) => {
        callbackResult = `결과: [${result.join(", ")}]`;
    });
    
    document.getElementById("callback-result").innerHTML = callbackResult;
    console.log("콜백 함수 테스트 완료");
}

// 5. IIFE 테스트
function testIIFE() {
    let result = "";
    
    (function() {
        const message = "즉시 실행!";
        result += message + "\n";
    })();
    
    (() => {
        result += "화살표 함수로 즉시 실행!";
    })();
    
    document.getElementById("iife-result").innerHTML = result;
    console.log("IIFE 테스트 완료");
}

// 6. 함수 합성 테스트
function testComposition() {
    const add = x => x + 1;
    const multiply = x => x * 2;
    const square = x => x * x;
    
    // 함수 합성
    const compose = (f, g, h) => x => f(g(h(x)));
    const result = compose(square, multiply, add)(3);
    
    // 단계별 계산
    const step1 = add(3);        // 4
    const step2 = multiply(step1); // 8
    const step3 = square(step2);  // 64
    
    document.getElementById("composition-result").innerHTML = 
        `입력: 3\n` +
        `1단계 (add): ${step1}\n` +
        `2단계 (multiply): ${step2}\n` +
        `3단계 (square): ${step3}\n` +
        `최종 결과: ${result}`;
    console.log("함수 합성 테스트:", result);
}

// 7. 커링 테스트
function testCurrying() {
    // 일반 함수
    function add(a, b, c) {
        return a + b + c;
    }
    
    // 커링 함수
    function addCurried(a) {
        return function(b) {
            return function(c) {
                return a + b + c;
            };
        };
    }
    
    // 화살표 함수 버전
    const addCurriedArrow = a => b => c => a + b + c;
    
    const result1 = add(1, 2, 3);
    const result2 = addCurried(1)(2)(3);
    const result3 = addCurriedArrow(1)(2)(3);
    
    document.getElementById("currying-result").innerHTML = 
        `일반 함수: add(1, 2, 3) = ${result1}\n` +
        `커링 함수: addCurried(1)(2)(3) = ${result2}\n` +
        `화살표 커링: addCurriedArrow(1)(2)(3) = ${result3}`;
    console.log("커링 테스트:", result1, result2, result3);
}

// 8. React 패턴 테스트
function testReactPattern() {
    // 이벤트 핸들러 시뮬레이션
    function createButton(id) {
        const handleClick = (e) => {
            return `버튼 ${id} 클릭됨!`;
        };
        return handleClick;
    }
    
    const button1 = createButton(1);
    const button2 = createButton(2);
    
    document.getElementById("react-pattern-result").innerHTML = 
        `버튼1 핸들러: ${button1()}\n` +
        `버튼2 핸들러: ${button2()}`;
    console.log("React 패턴 테스트 완료");
}

console.log("=== 함수 고급 예제 ===");
console.log("각 버튼을 클릭하여 테스트하세요!");
