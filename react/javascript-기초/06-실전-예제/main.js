// 할일 목록 상태 (React의 useState를 시뮬레이션)
let todos = [];

// 예제 1: 할일 목록
function addTodo() {
    const input = document.getElementById("todo-input");
    const text = input.value.trim();
    
    if (text === "") {
        alert("할일을 입력하세요!");
        return;
    }
    
    // React의 setTodos([...todos, newTodo]) 패턴
    todos = [...todos, { id: Date.now(), text, done: false }];
    input.value = "";
    renderTodos();
}

function toggleTodo(id) {
    // React의 setTodos(todos.map(...)) 패턴
    todos = todos.map(todo => 
        todo.id === id ? { ...todo, done: !todo.done } : todo
    );
    renderTodos();
}

function deleteTodo(id) {
    // React의 setTodos(todos.filter(...)) 패턴
    todos = todos.filter(todo => todo.id !== id);
    renderTodos();
}

function renderTodos() {
    const todoList = document.getElementById("todo-list");
    const resultDiv = document.getElementById("todo-result");
    
    if (todos.length === 0) {
        todoList.innerHTML = "<li style='padding: 10px; color: #999;'>할일이 없습니다.</li>";
        resultDiv.innerHTML = "";
        return;
    }
    
    todoList.innerHTML = todos.map(todo => `
        <li class="todo-item ${todo.done ? 'completed' : ''}">
            <span onclick="toggleTodo(${todo.id})" style="cursor: pointer; flex: 1;">
                ${todo.done ? '✓' : '○'} ${todo.text}
            </span>
            <button onclick="deleteTodo(${todo.id})" style="background-color: #f44336; padding: 5px 10px; font-size: 12px;">삭제</button>
        </li>
    `).join("");
    
    const doneCount = todos.filter(t => t.done).length;
    resultDiv.innerHTML = `총 ${todos.length}개 중 ${doneCount}개 완료`;
}

// 예제 2: 사용자 목록 필터링
const users = [
    { id: 1, name: "홍길동", age: 25 },
    { id: 2, name: "김철수", age: 30 },
    { id: 3, name: "이영희", age: 28 },
    { id: 4, name: "박영수", age: 35 },
    { id: 5, name: "최민지", age: 22 }
];

function filterUsers() {
    const ageInput = document.getElementById("age-filter");
    const minAge = parseInt(ageInput.value) || 0;
    const resultDiv = document.getElementById("user-result");
    
    // React의 filter + map 패턴
    const filtered = users
        .filter(user => user.age >= minAge)
        .map(user => `${user.name} (${user.age}세)`);
    
    if (filtered.length === 0) {
        resultDiv.innerHTML = "조건에 맞는 사용자가 없습니다.";
    } else {
        resultDiv.innerHTML = 
            `나이 ${minAge}세 이상 사용자:\n` +
            filtered.join("\n");
    }
    
    console.log("필터링 결과:", filtered);
}

// 예제 3: 폼 데이터 처리
function handleSubmit(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    
    // React의 상태 업데이트 패턴 시뮬레이션
    const data = {
        name: formData.get("name"),
        email: formData.get("email"),
        age: formData.get("age")
    };
    
    const resultDiv = document.getElementById("form-result");
    resultDiv.innerHTML = 
        `제출된 데이터:\n` +
        `이름: ${data.name}\n` +
        `이메일: ${data.email}\n` +
        `나이: ${data.age}`;
    
    console.log("폼 데이터:", data);
    form.reset();
}

// 예제 4: API 데이터 처리
async function loadUsers() {
    const resultDiv = document.getElementById("api-result");
    resultDiv.innerHTML = "<span style='color: #00BCD4;'>로딩 중...</span>";
    
    try {
        // 실제 API 대신 시뮬레이션
        await new Promise(resolve => setTimeout(resolve, 1000));
        
        const mockUsers = [
            { id: 1, name: "홍길동", email: "hong@test.com" },
            { id: 2, name: "김철수", email: "kim@test.com" },
            { id: 3, name: "이영희", email: "lee@test.com" }
        ];
        
        // React의 setState 패턴 시뮬레이션
        const userList = mockUsers.map(user => 
            `- ${user.name} (${user.email})`
        ).join("\n");
        
        resultDiv.innerHTML = 
            `사용자 목록:\n${userList}`;
        
        console.log("API 데이터:", mockUsers);
    } catch (error) {
        resultDiv.innerHTML = `에러: ${error.message}`;
    }
}

// 예제 5: 조건부 렌더링
let isLoading = false;

function toggleLoading() {
    const checkbox = document.getElementById("loading-check");
    isLoading = checkbox.checked;
    renderConditional();
}

function renderConditional() {
    const resultDiv = document.getElementById("conditional-result");
    
    // React의 조건부 렌더링 패턴
    if (isLoading) {
        resultDiv.innerHTML = "<span style='color: #00BCD4;'>로딩 중...</span>";
    } else {
        resultDiv.innerHTML = "데이터가 준비되었습니다!";
    }
}

// 초기화
renderTodos();
filterUsers();
renderConditional();

console.log("=== 실전 예제 ===");
console.log("각 예제를 테스트해보세요!");
