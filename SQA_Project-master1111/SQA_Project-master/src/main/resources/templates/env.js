export const server = {
  url: 'http://localhost:8080/api/v1',
}

export const token = (() => {return localStorage.getItem('token')})();