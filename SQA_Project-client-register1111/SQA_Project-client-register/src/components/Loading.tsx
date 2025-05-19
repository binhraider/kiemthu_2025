import {} from 'react';

interface props {
  isShow: boolean;
}

function Loading({ isShow }: props) {
  return (
    <>
      {isShow && (
        <div id='loading'>
          <div className='loading-ctn'></div>
        </div>
      )}
    </>
  );
}

export default Loading;
