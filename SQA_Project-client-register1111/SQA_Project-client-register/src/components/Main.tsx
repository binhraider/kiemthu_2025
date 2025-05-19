function Main({ children }: any) {
  return (
    <main id='main' style={{ flex: 1 }}>
      <div className='title'>
        <i style={{ fontSize: 18 }} className='fa fa-gear'></i>
        <span>Đăng kí môn học học kì II - năm học 2023-2024</span>
      </div>
      {children}
    </main>
  );
}

export default Main;
