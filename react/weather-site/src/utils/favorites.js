const KEY = 'weather_favorites';

export function loadFavorites() {
  try {
    return JSON.parse(localStorage.getItem(KEY)) || [];
  } catch {
    return [];
  }
}

export function saveFavorites(list) {
  localStorage.setItem(KEY, JSON.stringify(list));
}
