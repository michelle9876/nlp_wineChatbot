import React, { useEffect, useState } from 'react';
import './App.css';
import axios from 'axios';
import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom';
import Subject from './components/Subject';
import Content1 from './components/Content1';
import Content2 from './components/Content2';
import Content3 from './components/Content3';
import Content4 from './components/Content4';
import MapComponent from './components/MapComponent'; // 새로 추가한 지도 컴포넌트 import

function App() {
  const [subject] = useState({ title: "사장님 구해요" });
  const [contents] = useState([
    { id: 1, title: ['어디에 어떤 업종?', 'AI맞춤추천'], path: '/content1' },
    { id: 2, title: ['지도로 한눈에 확인', '랭킹 in 지도'], path: '/content2' },
    { id: 3, title: ['내 생각이 맞을까?', '확인하기'], path: '/content3' },
    { id: 4, title: ['휴일 추천해요', '휴일추천'], path: '/content4' },
  ]);

  const navigate = useNavigate();

  const handleClick = (content) => {
    navigate(content.path);
  };

  const [data, setData] = useState('');

  useEffect(() => {
    axios.get('/api/data')
      .then(res => setData(res.data))
      .catch(err => console.log(err));
  }, []);

  return (
    <div className="App">
      <Subject title={subject.title} />
      <MapComponent /> {/* 지도 컴포넌트 추가 */}
      <div className="buttons">
        {contents.map((item) => (
          <div key={item.id} className="button-container">
            <button onClick={() => handleClick(item)}>
              {item.title.map((line, index) => (
                <React.Fragment key={index}>
                  {line}
                  {index < item.title.length - 1 && <br />}
                </React.Fragment>
              ))}
            </button>
          </div>
        ))}
      </div>
      받아온 값 : {data}
    </div>
  );
}

function AppWrapper() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/content1" element={<Content1 />} />
        <Route path="/content2" element={<Content2 />} />
        <Route path="/content3" element={<Content3 />} />
        <Route path="/content4" element={<Content4 />} />
      </Routes>
    </Router>
  );
}

export default AppWrapper;
