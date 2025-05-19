import { useEffect } from 'react';
import AppRight from '../components/HomeRight1';
import './Login.css';
import banner from '../../public/banner.png';
import announce from '../../public/annouce.png';
function Login() {
  useEffect(() => {
    document.title = 'Đăng nhập';
  }, []);
  return (
    <div style={{ width: '100%' }}>
      {/* <div className='header'>
        <ul>
          <li className='home'>
            <i className='fa-solid fa-house'></i>
          </li>
          <li>
            <a href='home.html'>Trang chủ</a>
          </li>
          <li className='icon'>
            <i className='fa-solid fa-bars'></i>
          </li>
        </ul>
      </div> */}
      <div className='banner'>
        <img src={banner} alt='' />
      </div>

      <div className='content'>
        <div className='main'>
          <h1 className='tit'>Thông báo</h1>
          <div className='announce'>
            <img src={announce} alt='' />
          </div>
          <div className='content_announce'>
            <a href='#'>
              Thông báo: Mở hệ thống đăng ký môn học học kỳ 2 năm học 2023-2024
            </a>
            <hr />
            <a href='#'>
              Thông báo: Mở hệ thống đăng ký môn học học kỳ 2 năm học 2023-2024
            </a>
            <hr />
            <a href='#'>
              Thông báo: Mở hệ thống cho sinh viên đăng ký thời khóa biểu các
              lớp học phần trong đợt học lớp riêng Học kỳ I – năm học 2023-2024
            </a>
            <hr />
            <a href='#'>
              Thông báo: Mở hệ thống cho sinh viên đăng ký thời khóa biểu các
              lớp học phần trong đợt học lớp riêng Học kỳ I – năm học 2023-2024
            </a>
            <hr />
            <a href='#'>
              Thông báo: Mở hệ thống cho sinh viên đăng ký thời khóa biểu các
              lớp học phần trong đợt học lớp riêng Học kỳ I – năm học 2023-2024
            </a>
            <hr />
          </div>
        </div>
        <AppRight />
      </div>
      <div className='footer'>
        <div className='footer_content'>
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

export default Login;
