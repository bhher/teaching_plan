import { API, SAMPLE_POST } from './config';

/**
 * 고전 AJAX = XMLHttpRequest
 * (jQuery $.ajax도 내부적으로 XHR을 사용)
 */
function xhrRequest({ method, url, body }) {
  return new Promise((resolve, reject) => {
    const xhr = new XMLHttpRequest();
    xhr.open(method, url);
    xhr.responseType = 'json';

    xhr.onload = () => {
      if (xhr.status >= 200 && xhr.status < 300) {
        resolve(xhr.response);
      } else {
        reject(new Error(`HTTP ${xhr.status}`));
      }
    };

    xhr.onerror = () => reject(new Error('네트워크 오류'));

    if (body) {
      xhr.setRequestHeader('Content-Type', 'application/json');
      xhr.send(JSON.stringify(body));
    } else {
      xhr.send();
    }
  });
}

export function getWithAjax() {
  return xhrRequest({ method: 'GET', url: `${API}?_limit=3` });
}

export function postWithAjax() {
  return xhrRequest({ method: 'POST', url: API, body: SAMPLE_POST });
}
