import React from 'react';
import styles from './player.style';
import {View, Text, Image, Slider, ScrollView, Dimensions} from 'react-native';
import {connect} from 'react-redux';
import * as actions from '../../store/actions/index';
import { Ionicons } from '@expo/vector-icons';

const Player = props => {
  const width = Dimensions.get('window').width;
  const ref = React.createRef();
  const [play, setPlay] = React.useState(0);
  React.useEffect(() => {
    props.onInit();
  }, []);
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
  const [value, onChange] = React.useState(60);
  return props.tracklist.length > 0 ? (
    <View style={{flex: 1, backgroundColor: '#2f3640'}}>
      <View style={styles.view}>
        <Text style={styles.header}>Odtwarzanie z albumu</Text>
        <Text style={styles.album}>{props.tracklist[play].album}</Text>
        <ScrollView
          ref={ref}
          style={styles.imageSlider}
          horizontal={true}
          scrollEventThrottle={16}
          pagingEnabled={true}
          onScroll={func}
          showsHorizontalScrollIndicator={false}
          decelerationRate={'fast'}>
          {props.tracklist.map((el, i) => (
            <View style={styles.imageContainer} key={i}>
              <Image source={el.image} style={styles.image}></Image>
            </View>
          ))}
        </ScrollView>
        <View style={styles.description}>
          <View>
            <ScrollView style={styles.descriptionContent} horizontal={true}>
              <Text numberOfLines={1} ellipsizeMode="tail" style={styles.title}>
                {props.tracklist[play].title}
              </Text>
            </ScrollView>
            <ScrollView style={styles.descriptionContent} horizontal={true}>
              <Text numberOfLines={1} style={styles.artist}>
                {props.tracklist[play].artist}
              </Text>
            </ScrollView>
          </View>
          <View>
            <Ionicons
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
          <Text style={styles.timelapse}>2:25</Text>
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
          <Ionicons name="md-sync" size={20} color="white" />
          <Ionicons name="stepbackward" size={20} color="white" />
          <Ionicons name="play" size={20} color="white" />
          <Ionicons name="stepforward" size={20} color="white" />
          <Ionicons name="random" size={20} color="white" />
        </View>
      </View>
    </View>
  ) : null;
};

const mapStateToProps = state => {
  return {
    tracklist: state.tracklist,
  };
};

const mapDispatchToProps = dispatch => {
  return {
    onInit: () => dispatch(actions.initTracklist()),
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Player);
