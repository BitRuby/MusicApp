import React from "react";
import styles from "./player.style";
import {
  View,
  Text,
  Image,
  Slider,
  ScrollView,
  Dimensions
} from "react-native";
import { connect } from "react-redux";
import * as actions from "../../store/actions/index";
import { FontAwesome } from "@expo/vector-icons";
import PropTypes from "prop-types";

const Player = props => {
  const { trackId, albumId, initAlbum, album } = props;
  const width = Dimensions.get("window").width;
  const ref = React.createRef();
  const [play, setPlay] = React.useState(0);
  const [getCurrentPos, setCurrentPos] = React.useState(0);
  const [value, onChange] = React.useState(0);
  React.useEffect(() => {
    initAlbum(albumId);
    setPlay(trackId);
    setPositionTo(trackId-1);
  }, []);
  const updateScrollPosition = event => {
    if (event.nativeEvent.contentOffset.x % width == 0) {
      if (getCurrentPos > event.nativeEvent.contentOffset.x) {
        setPlay(play - 1);
      } else if (getCurrentPos < event.nativeEvent.contentOffset.x) {
        setPlay(play + 1);
      }
    }
  };
  const setPositionTo = page => {
    ref.current.scrollTo({
      x: getCurrentPos + width * page,
      y: 0,
      animated: false
    });
  };
  const next = () => {
    ref.current.scrollTo({ x: getCurrentPos + width, y: 0, animated: true });
  };
  const prev = () => {
    ref.current.scrollTo({ x: getCurrentPos - width, y: 0, animated: true });
  };
  return (
    <View style={{ flex: 1, backgroundColor: "#2f3640" }}>
      <View style={styles.view}>
        <Text style={styles.header}>Odtwarzanie z albumu</Text>
        <Text style={styles.album}>{album?.name}</Text>
        <ScrollView
          ref={ref}
          style={styles.imageSlider}
          horizontal={true}
          scrollEventThrottle={16}
          pagingEnabled={true}
          onScroll={updateScrollPosition}
          showsHorizontalScrollIndicator={false}
          decelerationRate={"fast"}
        >
          {album?.content?.map((el, i) => (
            <View style={styles.imageContainer} key={i}>
              <Image source={{ uri: album?.img }} style={styles.image}></Image>
              <View style={styles.description}>
                <View>
                  <ScrollView
                    style={styles.descriptionContent}
                    horizontal={true}
                  >
                    <Text
                      numberOfLines={1}
                      ellipsizeMode="tail"
                      style={styles.title}
                    >
                      {el?.title}
                    </Text>
                  </ScrollView>
                  <ScrollView
                    style={styles.descriptionContent}
                    horizontal={true}
                  >
                    <Text numberOfLines={1} style={styles.artist}>
                      {el?.artist?.name}
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
                <Text style={styles.timelapse}>0:00</Text>
                <Text style={styles.timelapse}>0:30</Text>
              </View>
            </View>
          ))}
        </ScrollView>
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
  );
};

const ContentType = PropTypes.shape({
  title: PropTypes.string,
  artist: {
    id: PropTypes.string,
    name: PropTypes.string
  },
  id: PropTypes.string,
  duration_ms: PropTypes.number,
  href: PropTypes.string,
  track_number: PropTypes.number,
  type: PropTypes.string
});

const AlbumType = PropTypes.shape({
  id: PropTypes.string,
  name: PropTypes.string,
  img: PropTypes.string,
  content: PropTypes.arrayOf(ContentType)
});

Player.propTypes = {
  album: PropTypes.objectOf(AlbumType)
};

Player.defaultProps = {
  album: {}
};

const mapStateToProps = state => {
  return {
    album: state.album
  };
};
const mapDispatchToProps = dispatch => {
  return {
    initAlbum: id => dispatch(actions.initAlbum(id))
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(Player);
