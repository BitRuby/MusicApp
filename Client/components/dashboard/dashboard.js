import React, {useEffect} from 'react';
import { View, ScrollView, BackHandler, Keyboard } from 'react-native';
import Searchbox from '../searchbox/searchbox';
import Carousel from '../carousel/carousel';
import PlayerWidget from '../player-widget/player-widget';
import styles from './dashboard.style';
import Search from '../search/search';
import {connect} from 'react-redux';
import * as actions from '../../store/actions/index';
import { Actions } from 'react-native-router-flux';

const Dashboard = props => {
  const [focused, onFocus] = React.useState(false);
  const [value, onChange] = React.useState('');
  useEffect(() => {
    const {onInit} = props;
    onInit();
    BackHandler.addEventListener('hardwareBackPress', backButtonPress);
    return () => BackHandler.removeEventListener('hardwareBackPress', backButtonPress);
  }, []);
  const backButtonPress = () => {
    onFocus(false);
    Keyboard.dismiss();
    onChange('');
  }
  const {playlist} = props;
  return (
    <View style={{flex: 1, backgroundColor: '#2f3640'}}>
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
          <ScrollView vertical={true}>
            <Carousel title="Playlisty" list={playlist} />
            <Carousel title="Ulubione" list={playlist} />
          </ScrollView>
         <PlayerWidget title={'Whatsername'} artist={'Green Day'} />
          </View>
        )}
      </View>
    </View>
  );
};

//Add PropTypes, DefaultValues, Redux, StyleSheet
const mapStateToProps = state => {
  return {
    playlist: state.playlist,
  };
};

const mapDispatchToProps = dispatch => {
  return {
    onInit: () => dispatch(actions.initPlaylist()),
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Dashboard);
