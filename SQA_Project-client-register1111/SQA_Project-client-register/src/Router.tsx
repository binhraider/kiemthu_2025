import { Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Register from './pages/Register';
import ChangePassword from './pages/ChangePassword';
import Login from './pages/Login';
import Data from './context/DataContext/Data.context';

function Router() {
  return (
    <Routes>
      <Route index element={<Home />} />
      <Route
        path='register'
        element={
          <Data>
            <Register />
          </Data>
        }
      />
      <Route path='change-password' element={<ChangePassword />} />
      <Route path='Login' element={<Login />} />
    </Routes>
  );
}

export default Router;
