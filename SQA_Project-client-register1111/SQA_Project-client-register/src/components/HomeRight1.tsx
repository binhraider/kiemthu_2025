import { useState, useEffect, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { server } from '../context/api/api';
import { toast } from 'react-toastify';
import Loading from './Loading';

export default function LoginForm() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const usernameInputRef = useRef<any>(null);
  const passwordInputRef = useRef<any>(null);

  const navigate = useNavigate();
  useEffect(() => {
    // Tự động đặt focus vào trường username khi component được tạo ra
    usernameInputRef.current.focus();
  }, []);

  const handleKeyDown = (event: any) => {
    if (event.key === 'Enter') {
      event.preventDefault();
      login();
    }
  };

  const login = () => {
    if (!username && !password) {
      setErrorMessage('Vui lòng nhập tên đăng nhập và mật khẩu.');
      usernameInputRef.current?.focus();
      return;
    }

    if (!username) {
      setErrorMessage('Vui lòng nhập tên đăng nhập.');
      usernameInputRef.current?.focus();
      return;
    }

    if (!password) {
      setErrorMessage('Vui lòng nhập mật khẩu.');
      passwordInputRef.current?.focus();
      return;
    }

    if (username.length < 6) {
      setErrorMessage('Tên đăng nhập quá ngắn.');
      usernameInputRef.current?.focus();
      return;
    }

    if (password.length < 6) {
      setErrorMessage('Mật khẩu quá ngắn.');
      passwordInputRef.current?.focus();
      return;
    }

    setIsLoading((_) => true);
    // Gọi API đăng nhập
    fetch(server.url + '/members/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username: username, password: password }),
    })
      .then((response) => {
        if (!response.ok) {
          toast.error('Đăng nhập thất bại.');
          throw new Error('Failed to login.');
        }
        return response.json();
      })
      .then((data) => {
        if (data.code === 400) {
          toast.error('Đăng nhập thất bại.');
          setErrorMessage(
            data?.details[0]?.errorMessage ?? 'Đăng nhập thất bại.'
          );
        } else {
          localStorage.setItem('username', data.username);
          localStorage.setItem('name', data.name);
          localStorage.setItem('studentId', data.id);
          toast.success('Đăng nhập thành công.');
          setIsLoading((_) => false);
          navigate('/');
        }
      })
      .catch((error) => {
        console.error('Error logging in:', error);
        // Xử lý lỗi đăng nhập
        setErrorMessage('Đăng nhập không thành công. Vui lòng thử lại sau.');
        setIsLoading((_) => false);
      })
      .finally(() => {
        setIsLoading((_) => false);
      });
  };

  return (
    <div className='login1'>
      <Loading isShow={isLoading} />
      <h1 className='title11'>Đăng nhập</h1>
      <form
        className='input_box1'
        onSubmit={(e) => {
          e.preventDefault();
          login();
        }}
      >
        <i className='fa-solid fa-user'></i>
        <input
          type='text'
          id='username'
          placeholder='Tên đăng nhập'
          value={username}
          maxLength={16}
          onChange={(e) => {
            setErrorMessage('');
            setUsername(e.target.value);
          }}
          onKeyDown={handleKeyDown}
          ref={usernameInputRef}
        />
        <i className='fa-solid fa-lock'></i>
        <input
          type='password'
          id='password'
          placeholder='Mật khẩu'
          value={password}
          maxLength={36}
          onChange={(e) => {
            setErrorMessage('');
            setPassword(e.target.value);
          }}
          onKeyDown={handleKeyDown}
          ref={passwordInputRef}
        />
        {/* <a className='forget_password' href='change-password'>
          Quên mật khẩu
        </a> */}
        <i className='fa-solid fa-right-to-bracket'></i>
        <button id='submit' type='submit'>
          Đăng nhập
        </button>
        <div
          id='errorMessage'
          style={{ color: 'red', textAlign: 'center', margin: '10px' }}
        >
          {errorMessage}
        </div>
      </form>
    </div>
  );
}
