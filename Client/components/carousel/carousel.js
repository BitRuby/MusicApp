import React from 'react';
import {View, Text, ScrollView} from 'react-native';
import Album from '../album/album';
import styles from './carousel.style';

const Carousel = props => {
  [list, setList] = React.useState([
    {
      title: 'You Lied',
      artist: 'Green Day',
      image: require('../../assets/images/1.jpg'),
    },
    {
      title: 'Having a Blast',
      artist: 'Green Day',
      image: require('../../assets/images/2.jpg'),
    },
    {
      title: 'Holiday',
      artist: 'Green Day',
      image: require('../../assets/images/3.png'),
    },
    {
      title: 'The Resistance',
      artist: 'Muse',
      image: require('../../assets/images/4.jpg'),
    },
    {
      title: 'Shadow of the Day',
      artist: 'Linkin Park',
      image: require('../../assets/images/5.jpg'),
    },
  ]);
  return (
    <ScrollView horizontal={true} showsHorizontalScrollIndicator={false}>
      <View>
        <Text style={styles.text}>{props.title}</Text>
      </View>
      {list.map((el, i) => (
        <Album element={el} key={i} />
      ))}
    </ScrollView>
  );
};
export default Carousel;
