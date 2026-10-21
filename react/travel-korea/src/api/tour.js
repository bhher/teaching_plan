export const AREAS = [
  { code: '', name: '전체' },
  { code: '1', name: '서울' },
  { code: '6', name: '부산' },
  { code: '2', name: '인천' },
  { code: '31', name: '경기' },
  { code: '32', name: '강원' },
  { code: '35', name: '경북' },
  { code: '36', name: '경남' },
  { code: '37', name: '전북' },
  { code: '38', name: '전남' },
  { code: '39', name: '제주' },
];

export const CONTENT_TYPES = [
  { code: '', name: '전체' },
  { code: '12', name: '관광지' },
  { code: '15', name: '축제' },
  { code: '39', name: '음식점' },
];

const API_KEY = import.meta.env.VITE_TOUR_API_KEY;
const BASE = '/tour-api/B551011/KorService1';

export function hasApiKey() {
  return Boolean(API_KEY && API_KEY.trim());
}

function commonParams(extra = {}) {
  return new URLSearchParams({
    serviceKey: API_KEY,
    MobileOS: 'ETC',
    MobileApp: 'travelkorea',
    _type: 'json',
    ...extra,
  });
}

async function tourFetch(path, params) {
  const qs = commonParams(params);
  const res = await fetch(`${BASE}${path}?${qs}`);
  if (!res.ok) throw new Error('TourAPI 요청 실패');
  const json = await res.json();
  const body = json?.response?.body;
  if (!body) throw new Error('응답 형식이 올바르지 않습니다. 서비스키를 확인하세요.');
  const items = body.items?.item;
  const list = !items ? [] : Array.isArray(items) ? items : [items];
  return {
    list,
    totalCount: Number(body.totalCount || 0),
    pageNo: Number(body.pageNo || 1),
    numOfRows: Number(body.numOfRows || 12),
  };
}

export async function fetchPlaces({ areaCode, contentTypeId, pageNo = 1, keyword = '' }) {
  if (!hasApiKey()) {
    return getMockPage({ areaCode, contentTypeId, pageNo, keyword });
  }

  if (keyword.trim()) {
    return tourFetch('/searchKeyword1', {
      keyword: keyword.trim(),
      areaCode: areaCode || '',
      contentTypeId: contentTypeId || '',
      pageNo: String(pageNo),
      numOfRows: '12',
      arrange: 'C',
    });
  }

  return tourFetch('/areaBasedList1', {
    areaCode: areaCode || '',
    contentTypeId: contentTypeId || '',
    pageNo: String(pageNo),
    numOfRows: '12',
    arrange: 'C',
    listYN: 'Y',
  });
}

export async function fetchDetail(contentId) {
  if (!hasApiKey()) {
    const found = MOCK.find((m) => String(m.contentid) === String(contentId));
    if (!found) throw new Error('장소를 찾을 수 없습니다.');
    return found;
  }
  const data = await tourFetch('/detailCommon1', {
    contentId: String(contentId),
    defaultYN: 'Y',
    firstImageYN: 'Y',
    areacodeYN: 'Y',
    addrinfoYN: 'Y',
    mapinfoYN: 'Y',
    overviewYN: 'Y',
    numOfRows: '1',
    pageNo: '1',
  });
  if (!data.list.length) throw new Error('상세 정보가 없습니다.');
  return data.list[0];
}

function getMockPage({ areaCode, contentTypeId, pageNo, keyword }) {
  let filtered = [...MOCK];
  if (areaCode) filtered = filtered.filter((m) => String(m.areacode) === String(areaCode));
  if (contentTypeId) {
    filtered = filtered.filter((m) => String(m.contenttypeid) === String(contentTypeId));
  }
  if (keyword.trim()) {
    const q = keyword.trim().toLowerCase();
    filtered = filtered.filter(
      (m) =>
        m.title.toLowerCase().includes(q) ||
        (m.addr1 || '').toLowerCase().includes(q)
    );
  }
  const numOfRows = 6;
  const start = (pageNo - 1) * numOfRows;
  const list = filtered.slice(start, start + numOfRows);
  return {
    list,
    totalCount: filtered.length,
    pageNo,
    numOfRows,
  };
}

const MOCK = [
  {
    contentid: '1001',
    contenttypeid: '12',
    title: '경복궁',
    addr1: '서울 종로구 사직로 161',
    areacode: '1',
    firstimage: 'https://images.unsplash.com/photo-1583417319070-4a69db38a482?w=600&q=80',
    mapx: '126.9769',
    mapy: '37.5796',
    overview: '조선 왕조의 법궁. 근정전과 경회루를 둘러볼 수 있습니다. (데모)',
  },
  {
    contentid: '1002',
    contenttypeid: '15',
    title: '서울 빛초롱축제',
    addr1: '서울 종로구 청계천로',
    areacode: '1',
    firstimage: 'https://images.unsplash.com/photo-1542051841857-5f90071e7989?w=600&q=80',
    mapx: '126.9780',
    mapy: '37.5690',
    overview: '청계천을 따라 펼쳐지는 야간 등불 축제. (데모)',
  },
  {
    contentid: '1003',
    contenttypeid: '39',
    title: '광장시장 빈대떡',
    addr1: '서울 종로구 창경궁로 88',
    areacode: '1',
    firstimage: 'https://images.unsplash.com/photo-1555939594-58d7cb561ad1?w=600&q=80',
    mapx: '126.9996',
    mapy: '37.5700',
    overview: '전통시장에서 즐기는 길거리 음식. (데모)',
  },
  {
    contentid: '2001',
    contenttypeid: '12',
    title: '해운대 해수욕장',
    addr1: '부산 해운대구 우동',
    areacode: '6',
    firstimage: 'https://images.unsplash.com/photo-1580655653886-b4d1f0b5e3b0?w=600&q=80',
    mapx: '129.1604',
    mapy: '35.1587',
    overview: '부산 대표 해변. 산책과 일출 명소. (데모)',
  },
  {
    contentid: '2002',
    contenttypeid: '39',
    title: '자갈치시장',
    addr1: '부산 중구 자갈치해안로 52',
    areacode: '6',
    firstimage: 'https://images.unsplash.com/photo-1559339352-11d035aa65de?w=600&q=80',
    mapx: '129.0300',
    mapy: '35.0966',
    overview: '신선한 해산물과 회 센터. (데모)',
  },
  {
    contentid: '3001',
    contenttypeid: '12',
    title: '성산일출봉',
    addr1: '제주 서귀포시 성산읍',
    areacode: '39',
    firstimage: 'https://images.unsplash.com/photo-1590523277543-a94d2e4eb500?w=600&q=80',
    mapx: '126.9425',
    mapy: '33.4580',
    overview: '유네스코 세계자연유산. 일출 명소. (데모)',
  },
  {
    contentid: '3002',
    contenttypeid: '15',
    title: '제주 불꽃축제',
    addr1: '제주 제주시 탑동',
    areacode: '39',
    firstimage: 'https://images.unsplash.com/photo-1492684223066-81342ee5ff30?w=600&q=80',
    mapx: '126.5219',
    mapy: '33.5160',
    overview: '바닷가에서 즐기는 불꽃놀이. (데모)',
  },
  {
    contentid: '4001',
    contenttypeid: '12',
    title: '남이섬',
    addr1: '강원 춘천시 남산면',
    areacode: '32',
    firstimage: 'https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=600&q=80',
    mapx: '127.5250',
    mapy: '37.7910',
    overview: '산책로와 메타세쿼이아길. (데모)',
  },
  {
    contentid: '4002',
    contenttypeid: '39',
    title: '춘천 닭갈비 골목',
    addr1: '강원 춘천시',
    areacode: '32',
    firstimage: 'https://images.unsplash.com/photo-1529042410759-befb1204b468?w=600&q=80',
    mapx: '127.7300',
    mapy: '37.8810',
    overview: '춘천 대표 먹거리. (데모)',
  },
];
