import React from "react";
import { View, Text, Image, ScrollView } from "react-native";
import styles from "./playlist.style";
import { FontAwesome } from "@expo/vector-icons";
import Element from "../element/element";
import { connect } from "react-redux";
import * as actions from "../../store/actions/index";
import PropTypes from "prop-types";
import { Actions } from "react-native-router-flux";

const Playlist = props => {
  const [favIno, setFavIno] = React.useState({
    coverUrl: { uri: play?.imgCover },
    quantity: "Pobieranie metadanych..."
  });
  const [play, setPlaylist] = React.useState([]);
  const back = () => {
    Actions.pop();
  }
  React.useEffect(() => {
    const [el] = playlist?.filter(e => {
      return e.id === id;
    });
    setPlaylist(el);
    setFavIno({
      coverUrl: { uri: el?.imgCover },
      quantity: el?.content?.length + " utworów"
    });
  }, [playlist, id]);
  const { id, playlist } = props;
  return (
    <View style={{ flex: 1, backgroundColor: "#2f3640" }}>
      <Image source={favIno.coverUrl} style={styles.cover}></Image>
      <View style={styles.innerFrame}></View>
      <View style={styles.headerIcon}>
        <FontAwesome name="arrow-left" size={20} color="#FFF" onPress={() => back()} />
      </View>
      <View style={styles.headerText}>
        <Text style={styles.title}>{play.name}</Text>
        <Text style={styles.subtitle}>{favIno.quantity}</Text>
      </View>
      <ScrollView>
        {play?.content?.map((el, i) => (
          <Element key={i} el={el} />
        ))}
      </ScrollView>
    </View>
  );
};

const PlaylistType = {
  id: PropTypes.string,
  name: PropTypes.string,
  content: PropTypes.array,
  imgCover: PropTypes.string
};

Playlist.propTypes = {
  playlist: PropTypes.arrayOf(PlaylistType)
};

Playlist.defaultProps = {
  playlist: []
};

const mapStateToProps = state => {
  return {
    playlist: state.playlist
  };
};
const mapDispatchToProps = dispatch => {
  return {};
};

export default connect(mapStateToProps, mapDispatchToProps)(Playlist);
