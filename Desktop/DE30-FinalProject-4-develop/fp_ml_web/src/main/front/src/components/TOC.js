//import React from 'react';
//
//function TOC(props) {
//  console.log('TOC render');
//  const lists = [];
//  const data = props.data;
//  let i = 0;
//  while(i < data.length){
//    lists.push(<li key={data[i].id}><a href={"/content/"+data[i].id}>{data[i].title}</a></li>);
//    i = i + 1;
//  }
//  return (
//    <nav>
//      <ul>
//        {lists}
//      </ul>
//    </nav>
//  );
//}
//
//export default TOC;

import React from 'react';

function TOC(props) {
  const lists = props.data.map(item => {
    return (
      <button key={item.id} onClick={() => props.onClick(item)}>
        {item.title}
      </button>
    );
  });
  return (
    <nav>
      {lists}
    </nav>
  );
}

export default TOC;

