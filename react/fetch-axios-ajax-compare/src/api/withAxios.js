import axios from 'axios';
import { API, SAMPLE_POST } from './config';

/** axios — 자동 JSON 파싱, 상태코드로 에러 throw */
export async function getWithAxios() {
  const { data } = await axios.get(API, { params: { _limit: 3 } });
  return data;
}

export async function postWithAxios() {
  const { data } = await axios.post(API, SAMPLE_POST);
  return data;
}
