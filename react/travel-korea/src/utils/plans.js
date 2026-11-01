const KEY = 'travel_korea_plans';

export function loadPlans() {
  try {
    return JSON.parse(localStorage.getItem(KEY)) || [];
  } catch {
    return [];
  }
}

export function savePlans(list) {
  localStorage.setItem(KEY, JSON.stringify(list));
}

export function addPlan(place) {
  const list = loadPlans();
  if (list.some((p) => p.contentid === place.contentid)) return list;
  const next = [
    ...list,
    {
      contentid: place.contentid,
      title: place.title,
      addr1: place.addr1,
      firstimage: place.firstimage || place.firstimage2 || '',
      mapx: place.mapx,
      mapy: place.mapy,
    },
  ];
  savePlans(next);
  return next;
}

export function removePlan(contentid) {
  const next = loadPlans().filter((p) => p.contentid !== contentid);
  savePlans(next);
  return next;
}
