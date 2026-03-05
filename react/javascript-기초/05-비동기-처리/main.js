// 유틸리티 함수들
function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function fetchUser(id) {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve({ id: id, name: `사용자${id}`, email: `user${id}@test.com` });
        }, 500);
    });
}

function fetchPosts(userId) {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve([
                { id: 1, title: "게시글 1", userId: userId },
                { id: 2, title: "게시글 2", userId: userId }
            ]);
        }, 500);
    });
}

// 1. Promise 기본 테스트
function testPromise() {
    const resultDiv = document.getElementById("promise-result");
    resultDiv.innerHTML = "<span class='loading'>로딩 중...</span>";
    
    const promise = new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve("성공!");
        }, 1000);
    });
    
    promise.then(result => {
        resultDiv.innerHTML = `결과: ${result}`;
        console.log("Promise 테스트:", result);
    });
}

// 2. Promise 체이닝 테스트
function testPromiseChain() {
    const resultDiv = document.getElementById("promise-chain-result");
    resultDiv.innerHTML = "<span class='loading'>로딩 중...</span>";
    
    fetchUser(1)
        .then(user => {
            resultDiv.innerHTML = `1단계: 사용자 조회 완료 - ${user.name}\n`;
            return fetchPosts(user.id);
        })
        .then(posts => {
            resultDiv.innerHTML += `2단계: 게시글 조회 완료 - ${posts.length}개`;
            console.log("Promise 체이닝 테스트:", posts);
        })
        .catch(error => {
            resultDiv.innerHTML = `에러: ${error}`;
        });
}

// 3. async/await 테스트
async function testAsyncAwait() {
    const resultDiv = document.getElementById("async-await-result");
    resultDiv.innerHTML = "<span class='loading'>로딩 중...</span>";
    
    try {
        const user = await fetchUser(1);
        resultDiv.innerHTML = `1단계: 사용자 조회 완료 - ${user.name}\n`;
        
        await delay(500);
        
        const posts = await fetchPosts(user.id);
        resultDiv.innerHTML += `2단계: 게시글 조회 완료 - ${posts.length}개`;
        console.log("async/await 테스트:", user, posts);
    } catch (error) {
        resultDiv.innerHTML = `에러: ${error}`;
    }
}

// 4. Promise.all() 테스트
function testPromiseAll() {
    const resultDiv = document.getElementById("promise-all-result");
    resultDiv.innerHTML = "<span class='loading'>로딩 중...</span>";
    
    const promises = [
        fetchUser(1),
        fetchUser(2),
        fetchUser(3)
    ];
    
    Promise.all(promises)
        .then(users => {
            const userNames = users.map(u => u.name).join(", ");
            resultDiv.innerHTML = `모든 사용자 조회 완료:\n${userNames}`;
            console.log("Promise.all 테스트:", users);
        })
        .catch(error => {
            resultDiv.innerHTML = `에러: ${error}`;
        });
}

// 5. fetch API 테스트
function testFetch() {
    const resultDiv = document.getElementById("fetch-result");
    resultDiv.innerHTML = "<span class='loading'>로딩 중...</span>";
    
    // 실제 API 대신 시뮬레이션
    fetch('https://jsonplaceholder.typicode.com/users/1')
        .then(response => {
            if (!response.ok) {
                throw new Error('네트워크 오류');
            }
            return response.json();
        })
        .then(data => {
            resultDiv.innerHTML = `사용자 정보:\n이름: ${data.name}\n이메일: ${data.email}`;
            console.log("fetch 테스트:", data);
        })
        .catch(error => {
            resultDiv.innerHTML = `에러: ${error.message}\n(실제 API 호출 실패 시 로컬 데이터 사용)`;
            // 로컬 데이터로 대체
            const localData = { name: "홍길동", email: "hong@test.com" };
            resultDiv.innerHTML = `로컬 데이터:\n이름: ${localData.name}\n이메일: ${localData.email}`;
        });
}

// 6. React 패턴 테스트
async function testReactPattern() {
    const resultDiv = document.getElementById("react-pattern-result");
    resultDiv.innerHTML = "<span class='loading'>로딩 중...</span>";
    
    // React의 useEffect를 시뮬레이션
    async function fetchUserData(userId) {
        try {
            const user = await fetchUser(userId);
            return user;
        } catch (error) {
            throw error;
        }
    }
    
    try {
        const user = await fetchUserData(1);
        resultDiv.innerHTML = 
            `React 패턴 시뮬레이션:\n` +
            `사용자 ID: ${user.id}\n` +
            `이름: ${user.name}\n` +
            `이메일: ${user.email}`;
        console.log("React 패턴 테스트:", user);
    } catch (error) {
        resultDiv.innerHTML = `에러: ${error}`;
    }
}

console.log("=== 비동기 처리 예제 ===");
console.log("각 버튼을 클릭하여 테스트하세요!");
