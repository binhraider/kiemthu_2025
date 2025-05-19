import { useRef, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './ChangePassword.css';

export default function ChangePassword() {
  const passwordRef = useRef<any>(null);
  const usernameRef = useRef<any>(null); // Tạo ref cho ô nhập username
  const navigate = useNavigate();

  useEffect(() => {
    // Focus vào ô nhập username khi trang được tải
    usernameRef.current.focus();
    document.title = 'Đổi mật khẩu';
  }, []); // [] đảm bảo hành động này chỉ được thực hiện một lần khi component được render

  const handleEnterPassword = (event: any) => {
    if (event && event.key === 'Enter') {
      navigate('/');
    }
  };

  return (
    <div>
      <div className='headers'>
        <ul>
          <li>
            <i className='fa-solid fa-house'></i>
          </li>
          <li>
            <a href='/'>Trang chủ</a>
          </li>
        </ul>
      </div>
      <div className='banners'></div>

      <div className='contents'>
        <div className='mains'>
          <h1 className='tits'> Lấy lại mật khẩu</h1>
          <form id='doimk'>
            <div className='content_announces'>
              <div className='tx1s'>
                <p className='tx2s'>Mã sinh viên</p>
                <input type='text' id='msv' />
              </div>

              <div className='tx1s'>
                <p className='tx2s'>Email đã đăng kí trong hệ thống</p>
                <input type='text' id='email' />
              </div>
              <button type='submit'>Gửi</button>
              <div
                id='errorMessage1'
                style={{ color: 'red', textAlign: 'center', margin: '10px;' }}
              ></div>
            </div>
          </form>
        </div>

        <div className='logins'>
          <h1 className='title1s'>Đăng nhập</h1>
          <form id='loginForm'>
            <div className='input_boxs'>
              <i className='fa-solid fa-user'></i>
              <input
                type='text'
                id='username'
                value=''
                placeholder='username'
                ref={usernameRef} // Gán ref cho ô nhập username
              />
              <i className='fa-solid fa-lock'></i>
              <input
                type='password'
                id='password'
                value=''
                placeholder='password'
                onKeyDown={handleEnterPassword}
                ref={passwordRef}
              />
              {/* <a className=' forget_passwords' href='#'>
                Quên mật khẩu
              </a> */}
              <i className='fa-solid fa-right-to-bracket'></i>
              <button type='submit'>Đăng nhập</button>
              <div
                id='errorMessage'
                style={{ color: 'red', textAlign: 'center', margin: '10px;' }}
              ></div>
            </div>
          </form>
        </div>
      </div>
      <div className='footers'>
        <div className='footer_contents'>
          <p>Thống kê truy cập</p>

          <p>
            <i className='icon fa-solid fa-users'></i> Đang truy nhập:
            <span>200</span>
          </p>

          <p>
            <i className='icon fa-solid fa-user-graduate'></i>SV đăng nhập:
            <span>200</span>
          </p>

          <p>
            <i className='icon fa-solid fa-user-tie'></i> GV đăng nhập:
            <span>200</span>
          </p>
        </div>
      </div>
    </div>
  );
}
