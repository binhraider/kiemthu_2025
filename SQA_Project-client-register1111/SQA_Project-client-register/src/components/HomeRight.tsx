import { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom'; // Import useNavigate từ react-router-dom
import { toast } from 'react-toastify';

export default function HomeRight({
  isDisableRegisterNavigation,
}: {
  isDisableRegisterNavigation?: boolean;
}) {
  const navigate = useNavigate(); // Sử dụng useNavigate để tạo navigate
  const [isProfile, setIsProfile] = useState<boolean>(false);
  useEffect(() => {
    // Lấy thông tin người dùng từ local storage
    const username = localStorage.getItem('username');
    const name = localStorage.getItem('name');

    // Hiển thị thông tin người dùng trên trang home
    if (username && name) {
      document.getElementById('usernameDisplay')!.textContent = username;
      document.getElementById('nameDisplay')!.textContent = name;
    } else {
      navigate('/login');
    }
  }, []); // useEffect chỉ được gọi một lần sau khi component được tạo ra

  // Hàm xử lý sự kiện đăng xuất
  const handleLogout = () => {
    // Xóa thông tin người dùng khỏi localStorage
    localStorage.removeItem('username');
    localStorage.removeItem('name');
    localStorage.removeItem('studentId');
    toast.info('Đăng xuất thành công.');
    // Điều hướng đến trang đăng nhập
    navigate('/login');
    // Điều hướng đến trang đăng nhập, định nghĩa lại đường dẫn cho phù hợp
  };

  return (
    <div className='login'>
      <h1 className='title1' onClick={() => setIsProfile((prev) => !prev)}>
        {localStorage.getItem('studentId') ? 'Thông tin' : 'Đăng nhập'}
      </h1>

      <form id='loginForm' data-show={isProfile ? 'true' : 'false'}>
        <div className='input_box'>
          <p className='tx1'>
            Tài khoản:{' '}
            <span
              style={{ fontWeight: 700, textTransform: 'uppercase' }}
              id='usernameDisplay'
            ></span>
          </p>
          <p className='tx1'>
            Họ và tên:{' '}
            <span style={{ fontWeight: 700 }} id='nameDisplay'></span>
          </p>
          <i className='fa-solid fa-right-to-bracket'></i>
          <div className='logoutHome'>
            <button id='logoutButton' onClick={handleLogout}>
              Đăng xuất
            </button>
          </div>
          {/* Gắn sự kiện đăng xuất vào nút đăng xuất */}
          {/* <a className='forget_password' href='change-password'>
            Quên mật khẩu
          </a> */}
        </div>
      </form>

      <div className='function'>
        <h1>Tính năng</h1>
        <div style={{ width: '100%' }}>
          {/* <a href='#'>
          <i
            className='fa-solid fa-plus'
            style={{ color: '#ad171c', marginRight: '10px' }}
          ></i>
          Xem thời khóa biểu
        </a> */}
          {!isDisableRegisterNavigation && (
            <Link to='/register'>
              <i
                className='fa-solid fa-plus'
                style={{ color: '#ad171c', marginRight: '10px' }}
              ></i>
              Đăng kí lịch học
            </Link>
          )}
          {isDisableRegisterNavigation && (
            <a className='nav-disable'>
              {' '}
              <i
                className='fa-solid fa-plus'
                style={{ color: '#ad171c', marginRight: '10px' }}
              ></i>
              Đăng kí lịch học
            </a>
          )}
        </div>

        {/* <a href='#'>
          <i
            className='fa-solid fa-plus'
            style={{ color: '#ad171c', marginRight: '10px' }}
          ></i>
          Xem học phí
        </a> */}
      </div>
    </div>
  );
}
