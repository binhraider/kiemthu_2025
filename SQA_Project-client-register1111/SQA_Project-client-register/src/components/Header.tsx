import { Link } from 'react-router-dom';

function Header() {
  return (
    <header id='header' style={{ fontWeight: 'bold', fontSize: 18 }}>
      <i className='fa fa-home'></i>
      <Link className='title' to='/'>
        Trang chủ
      </Link>
    </header>
  );
}

export default Header;
