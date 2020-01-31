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
import { Actions } from "react-native-router-flux";
import PropTypes from "prop-types";

const Player = props => {
  const width = Dimensions.get("window").width;
  const ref = React.createRef();
  const [play, setPlay] = React.useState(0);
  const { id, onInit, tracklist, album } = props;

  onInit("63yesoRJgXT5QALryYFV0X");
  React.useEffect(() => {
    const [number] = tracklist?.filter(e => {
      e.id === "63yesoRJgXT5QALryYFV0X";
    });
    setPlay(number?.id);
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
    ref.current.scrollTo({ x: getCurrentPos + width, y: 0, animated: true });
  };
  const prev = () => {
    ref.current.scrollTo({ x: getCurrentPos - width, y: 0, animated: true });
  };
  const [value, onChange] = React.useState(60);
  return  (
    <View style={{ flex: 1, backgroundColor: "#2f3640" }}>
      <View style={styles.view}>
        <Text style={styles.header}>Odtwarzanie z albumu</Text>
        <Text style={styles.album}>{album?.album?.name}</Text>
        <ScrollView
          ref={ref}
          style={styles.imageSlider}
          horizontal={true}
          scrollEventThrottle={16}
          pagingEnabled={true}
          onScroll={func}
          showsHorizontalScrollIndicator={false}
          decelerationRate={"fast"}
        >
          {/* {tracklist?.map((el, i) => (
            <View style={styles.imageContainer} key={i}>
              <Image source={{uri: album?.album?.url}} style={styles.image}></Image>
            </View>
          ))} */}
        </ScrollView>
        <View style={styles.description}>
          <View>
            <ScrollView style={styles.descriptionContent} horizontal={true}>
              <Text numberOfLines={1} ellipsizeMode="tail" style={styles.title}>
                {tracklist[play]?.title}
              </Text>
            </ScrollView>
            <ScrollView style={styles.descriptionContent} horizontal={true}>
              <Text numberOfLines={1} style={styles.artist}>
                {tracklist[play]?.artist?.name}
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
          <FontAwesome name="refresh" size={20} color="white" />
          <FontAwesome name="backward" size={20} color="white" />
          <FontAwesome name="play" size={20} color="white" />
          <FontAwesome name="forward" size={20} color="white" />
          <FontAwesome name="random" size={20} color="white" />
        </View>
      </View>
    </View>
  ) ;
};

const AlbumType = {
  title: PropTypes.string,
  album: {
    id: PropTypes.string,
    name: PropTypes.string,
    img: PropTypes.string,
  },
  artist: {
    id: PropTypes.string,
    name: PropTypes.string,
  },
  id: PropTypes.string,
  duration_ms: PropTypes.number,
  href: PropTypes.string,
  track_number: PropTypes.number,
  type: PropTypes.string,
}

Player.propTypes = {
  tracklist: PropTypes.array,
  album: PropTypes.shape(AlbumType)
};

Player.defaultProps = {
  tracklist: [],
  album: {}
};

const mapStateToProps = state => {
  return {
    tracklist: state.tracklist
  };
};
const mapDispatchToProps = dispatch => {
  return {
    onInit: id => dispatch(actions.initTracklist(id))
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(Player);
