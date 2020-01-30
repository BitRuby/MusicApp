import React from 'react';
import styles from './player.style';
import {View, Text, Image, Slider, ScrollView, Dimensions, BackHandler} from 'react-native';
import {connect} from 'react-redux';
import * as actions from '../../store/actions/index';
import { FontAwesome } from '@expo/vector-icons';
import { Actions } from 'react-native-router-flux';
import PropTypes from "prop-types";

const Player = props => {
  const width = Dimensions.get('window').width;
  const ref = React.createRef();
  const [play, setPlay] = React.useState(0);
  const {tracklist, id, onInit} = props;
  const millisToMinutesAndSeconds = (millis) => {
    var minutes = Math.floor(millis / 60000);
    var seconds = ((millis % 60000) / 1000).toFixed(0);
    return minutes + ":" + (seconds < 10 ? '0' : '') + seconds;
  }
  onInit(id?.trackID);
  React.useEffect(() => {
    setPlay(tracklist?.track_number);
    getAlbum(tracklist?.album?.id);
  }, [tracklist]);
  const [getCurrentPos, setCurrentPos] = React.useState(0);
  const func = event => {
    if (event.nativeEvent.contentOffset.x % width == 0) {
      if (getCurrentPos > event.nativeEvent.contentOffset.x) {
        setPlay(play - 1);
      } else if (getCurrentPos < event.nativeEvent.contentOffset.x) {
        setPlay(play + 1);
      }
      setCurrentPos(event.nativeEvent.contentOffset.x);
    }
  };
  const next = () => {
    ref.current.scrollTo({x: getCurrentPos + width, y: 0, animated: true});
  };
  const prev = () => {
    ref.current.scrollTo({x: getCurrentPos - width, y: 0, animated: true});
  };
  const [value, onChange] = React.useState('60');
  return tracklist.length > 0 ? (
    <View style={{flex: 1, backgroundColor: '#2f3640'}}>
      <View style={styles.view}>
        <Text style={styles.header}>Odtwarzanie z albumu</Text>
        <Text style={styles.album}>{tracklist?.album?.name}</Text>
        <ScrollView
          ref={ref}
          style={styles.imageSlider}
          horizontal={true}
          scrollEventThrottle={16}
          pagingEnabled={true}
          onScroll={func}
          showsHorizontalScrollIndicator={false}
          decelerationRate={'fast'}>
          {album.map((el, i) => (
            <View style={styles.imageContainer} key={i}>
              <Image source={el.image} style={styles.image}></Image>
            </View>
          ))}
        </ScrollView>
        <View style={styles.description}>
          <View>
            <ScrollView style={styles.descriptionContent} horizontal={true}>
              <Text numberOfLines={1} ellipsizeMode="tail" style={styles.title}>
                {album[play].title}
              </Text>
            </ScrollView>
            <ScrollView style={styles.descriptionContent} horizontal={true}>
              <Text numberOfLines={1} style={styles.artist}>
                {"album[play].artist"}
              </Text>
            </ScrollView>
          </View>
          <View>
            <FontAwesome
              name="heart"
              style={styles.heartIcon}
              size={18}
              color="#777"
              solid
            />
          </View>
        </View>
        <View style={styles.time}>
          <Text style={styles.timelapse}>1:56</Text>
          <Text style={styles.timelapse}>{millisToMinutesAndSeconds(album[play].duration_ms)}</Text>
        </View>
        <Slider
          minimumTrackTintColor="#FB266E"
          maximumTrackTintColor="#777"
          thumbTintColor="#fff"
          style={styles.slider}
          step={1}
          onValueChange={() => onChange()}
          value={value}
        />
        <View style={styles.playerIcons}>
          <FontAwesome name="refresh" size={20} color="white" />
          <FontAwesome name="backward" size={20} color="white" />
          <FontAwesome name="play" size={20} color="white" />
          <FontAwesome name="forward" size={20} color="white" />
          <FontAwesome name="random" size={20} color="white" />
        </View>
      </View>
    </View>
  ) : null;
};

Playlist.propTypes = {
  tracklist: PropTypes.array,
  album: PropTypes.array
};

Playlist.defaultProps = {
  tracklist: [],
  album: []
};

const mapStateToProps = state => {
  return {
    tracklist: state.tracklist,
    album: state.album
  };
};

const mapDispatchToProps = dispatch => {
  return {
    onInit: (id) => dispatch(actions.initTracklist(id)),
    getAlbum: (id) => dispatch(actions.initAlbum(id)),
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Player);
