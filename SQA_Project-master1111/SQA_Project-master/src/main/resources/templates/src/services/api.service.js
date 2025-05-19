import { token } from "../../env.js";

const api = {
  get: async (url, paramObject) => {
    if (Object.keys(paramObject).length >= 1) {
      url = `${url}?${_paramSerializer(paramObject)}`;
    }
    const res = await fetch(url, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      },
    });
    const data = await res.json();
    return data;
  }
}

function _paramSerializer(paramObject) {
  return Object.keys(paramObject).map(key => {
    return `${key}=${paramObject[key]}`;
  }).join('&');
}

export default api;