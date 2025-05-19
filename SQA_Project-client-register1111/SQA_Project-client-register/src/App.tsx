import './App.css';
import 'react-toastify/dist/ReactToastify.min.css';
import Header from './components/Header';
import { ToastContainer } from 'react-toastify';
// import Register from './pages/Register';
import { BrowserRouter } from 'react-router-dom';
import Router from './Router';

function AppContext() {
  return (
    <>
      <ToastContainer
        position='top-right'
        autoClose={1700}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme='light'
      />
      <BrowserRouter>
        <Header />
        <div style={{ gap: 8 }}>
          <Router />
          <div style={{ width: 300 }}></div>
        </div>
      </BrowserRouter>
    </>
  );
}

function App() {
  return (
    // <Data>
    <AppContext />
    // </Data>
  );
}

export default App;
