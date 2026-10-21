export default function CompareTable() {
  return (
    <section className="table-wrap">
      <h2>한눈에 비교</h2>
      <table>
        <thead>
          <tr>
            <th>항목</th>
            <th>fetch</th>
            <th>axios</th>
            <th>ajax (XHR)</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>설치</td>
            <td>불필요 (브라우저 내장)</td>
            <td><code>npm i axios</code></td>
            <td>불필요 (브라우저 내장)</td>
          </tr>
          <tr>
            <td>반환</td>
            <td>Promise → Response</td>
            <td>Promise → 이미 파싱된 data</td>
            <td>콜백 / Promise로 감싸기</td>
          </tr>
          <tr>
            <td>JSON 파싱</td>
            <td><code>res.json()</code> 직접</td>
            <td>자동</td>
            <td><code>responseType='json'</code> 또는 JSON.parse</td>
          </tr>
          <tr>
            <td>HTTP 에러</td>
            <td>4xx/5xx도 resolve (직접 체크)</td>
            <td>자동 reject</td>
            <td>status로 직접 체크</td>
          </tr>
          <tr>
            <td>요청 취소</td>
            <td>AbortController</td>
            <td>CancelToken / AbortController</td>
            <td><code>xhr.abort()</code></td>
          </tr>
          <tr>
            <td>인터셉터</td>
            <td>없음 (직접 래핑)</td>
            <td>있음 (요청/응답)</td>
            <td>없음</td>
          </tr>
          <tr>
            <td>언제 쓰나</td>
            <td>간단한 호출, 의존성 최소화</td>
            <td>실무 API 레이어, 공통 헤더</td>
            <td>레거시 코드 / jQuery 시절 이해</td>
          </tr>
        </tbody>
      </table>
    </section>
  );
}
