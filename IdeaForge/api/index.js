/**
 * Vercel 배포용 엔트리 (추후 연결 시 사용).
 * 로컬은 server/index.js 를 사용합니다.
 */
import appModule from '../server/vercel-app.js';

export default async function handler(req, res) {
  return appModule(req, res);
}
