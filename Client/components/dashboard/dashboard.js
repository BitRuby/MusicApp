import React from 'react';
import {View} from 'react-native';
import Searchbox from '../searchbox/searchbox';
import Carousel from '../carousel/carousel';
import PlayerWidget from '../player-widget/player-widget';
import styles from './dashboard.style';
import Search from '../search/search';
const Dashboard = () => {
  const [focused, onFocus] = React.useState(false);
  const [value, onChange] = React.useState('');
  return (
    <View style={styles.view}>
      <Searchbox
        focused={focused}
        onFocus={onFocus}
        value={value}
        onChange={onChange}
      />
      {focused ? (
        <Search value={value} onChange={onChange} />
      ) : (
        <View style={{flex: 1}}>
          <Carousel title="Playlisty" />
          <Carousel title="Ulubione" />
          <PlayerWidget title={'Whatsername'} artist={'Green Day'} />
        </View>
      )}
    </View>
  );
};

//Add PropTypes, DefaultValues, Redux, StyleSheet

export default Dashboard;
