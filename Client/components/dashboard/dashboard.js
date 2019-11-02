import React from 'react';
import {View} from 'react-native';
import Searchbox from '../searchbox/searchbox';
import Carousel from '../carousel/carousel';
import PlayerWidget from '../player-widget/player-widget';
import styles from './dashboard.style';
const Dashboard = () => {
  return (
    <View style={styles.view}>
      <Searchbox />
      <Carousel title="Playlisty" />
      <Carousel title="Ulubione" />
      <PlayerWidget title={'Whatsername'} artist={'Green Day'} />
    </View>
  );
};

//Add PropTypes, DefaultValues, Redux, StyleSheet

export default Dashboard;
