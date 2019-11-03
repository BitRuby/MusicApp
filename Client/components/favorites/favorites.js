import React from 'react';
import {View, Text, Image} from 'react-native';
import styles from './favorites.style';
import Icon from 'react-native-vector-icons/FontAwesome5';
import Element from '../element/element';
const Favorites = props => {
  const [favIno, setFavIno] = React.useState({
    coverUrl: require('../../assets/images/1.jpg'),
    quantity: '58 utworów',
  });
  const [list, setList] = React.useState([
    {
      title: 'High Hopes',
      artist: 'Panic! At the Disco',
      duration: '3:10',
      playing: false,
    },
    {
      title: 'In the End',
      artist: 'Linkin Park',
      duration: '3:37',
      playing: false,
    },
    {
      title: 'Jesus of Suburbia',
      artist: 'Green Day',
      duration: '9:10',
      playing: true,
    },
    {
      title: 'Sk8ter boi',
      artist: 'Avril Lavigne',
      duration: '3:24',
      playing: false,
    },
    {
      title: 'Turn me Loose',
      artist: 'The Longshot',
      duration: '3:24',
      playing: false,
    },
  ]);
  return (
    <View>
      <Image source={favIno.coverUrl} style={styles.cover}></Image>
      <View style={styles.innerFrame}></View>
      <View style={styles.headerIcon}>
        <Icon name="arrow-left" size={20} color="#FFF" />
      </View>
      <View style={styles.headerText}>
        <Text style={styles.title}>Ulubione</Text>
        <Text style={styles.subtitle}>{favIno.quantity}</Text>
      </View>
      {list.map((el, i) => (
        <Element key={i} el={el} />
      ))}
    </View>
  );
};

//Add PropTypes, DefaultValues, Redux, StyleSheet

export default Favorites;
