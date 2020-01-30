import React from 'react';
import {View, Text, ScrollView} from 'react-native';
import Album from '../album/album';
import styles from './carousel.style';
import { Actions } from 'react-native-router-flux';
const Carousel = props => {
  const goTo = (type, id) => {
    type === "Playlist" ? Actions.playlist({id: id?.id}) : Actions.player({id: id});
  }
  const {type, list, title} = props;
  return (
    <ScrollView
      horizontal={true}
      showsHorizontalScrollIndicator={false}
      bounces={true}>
      <View style={styles.view}>
        <Text style={styles.text}>{title}</Text>
      </View>
      {list.map((el, i) => (
        <Album element={el} key={i} onPress={() => goTo(type, el)}
        />
      ))}
    </ScrollView>
  );
};
export default Carousel;
