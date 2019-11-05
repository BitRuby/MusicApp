import React from 'react';
import {Text, View} from 'react-native';
import Searchbox from '../searchbox/searchbox';
import Carousel from '../carousel/carousel';
import PlayerWidget from '../player-widget/player-widget';
import styles from './dashboard.style';
import Search from '../search/search';
import LinearGradient from 'react-native-linear-gradient';
import {connect} from 'react-redux';
import * as actions from '../../store/actions/index';

const Dashboard = props => {
  const [focused, onFocus] = React.useState(false);
  const [value, onChange] = React.useState('');
  React.useEffect(() => {
    props.onInit();
  }, []);
  return (
    <LinearGradient colors={['#1A1A1A', '#3B3B3B']} style={{flex: 1}}>
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
            <Carousel title="Playlisty" list={props.playlist} />
            <Carousel title="Ulubione" list={props.playlist} />
            <PlayerWidget title={'Whatsername'} artist={'Green Day'} />
          </View>
        )}
      </View>
    </LinearGradient>
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
