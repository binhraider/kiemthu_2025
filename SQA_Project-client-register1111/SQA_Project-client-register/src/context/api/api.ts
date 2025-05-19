import axios from 'axios';

export const server = {
  url: 'http://26.95.61.160:8080/api/v1',
  // url: 'http://3.106.253.121/api/v1',
  // url: 'http://3.106.53.152/api/v1',
};

// export const token = (() => {
//   return localStorage.getItem('token');
// })();

const token =
  'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiMjBkY2NuMzUyIiwiaWF0IjoxNzEwNTY0NzAxLCJleHAiOjE3MTIzNjQ3MDF9.pG3Nph8EX7DT5QEgRKXHdRyvRM29PNToP3Dv0M9MJG4';
const headers = {
  Authorization: `Bearer ${token}`,
  Accept: '*/*',
};

export default {
  get: async <T>(path: string, params?: { [key: string]: any }) => {
    const _path = path[0] === '/' ? path.substring(1) : path;
    return await axios.get<T>(
      server.url + '/' + _path + (params ? '?' + _paramSerializer(params) : ''),
      { headers }
    );
  },

  post: async <T>(path: string, params?: { [key: string]: any }) => {
    const _path = path[0] === '/' ? path.substring(1) : path;
    return await axios.post<T>(
      server.url + '/' + _path + (params ? '?' + _paramSerializer(params) : ''),
      { headers }
    );
  },

  delete: async <T>(path: string, params?: { [key: string]: any }) => {
    const _path = path[0] === '/' ? path.substring(1) : path;
    return await axios.delete<T>(
      server.url + '/' + _path + (params ? '?' + _paramSerializer(params) : ''),
      { headers }
    );
  },
};

function _paramSerializer(paramObject?: { [key: string]: any }) {
  if (!paramObject) {
    return '';
  }
  const keys = Object.keys(paramObject);
  if (keys.length === 0) {
    return '';
  }
  return keys
    .map((key) => {
      return `${key}=${paramObject[key]}`;
    })
    .join('&');
}
