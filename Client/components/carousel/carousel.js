import React from 'react';
import {View, Text, ScrollView} from 'react-native';
import Album from '../album/album';
import styles from './carousel.style';
import { Actions } from 'react-native-router-flux';
const Carousel = props => {
  const goToPlayer = () => {
    Actions.player();
  }
  return (
    <ScrollView
      horizontal={true}
      showsHorizontalScrollIndicator={false}
      bounces={true}>
      <View style={styles.view}>
        <Text style={styles.text}>{props.title}</Text>
      </View>
      {props.list.map((el, i) => (
        <Album element={el} key={i} onPress={() => goToPlayer()}

        />
      ))}
    </ScrollView>
  );
};
export default Carousel;
