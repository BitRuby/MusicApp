import React from 'react';
import {View} from 'react-native';
import Album from '../album/album';
const Carousel = () => {
  [list, setList] = React.useState([
    'Green Day - Jesus of Suburbia',
    "Linkin Park - What I've Done",
  ]);
  return (
    <View>
      {list.map((el, i) => (
        <Album title={el} key={i} />
      ))}
    </View>
  );
};
export default Carousel;
