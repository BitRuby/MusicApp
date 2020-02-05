import React, { useEffect } from "react";
import { View, ScrollView, BackHandler, Keyboard } from "react-native";
import Searchbox from "../searchbox/searchbox";
import Carousel from "../carousel/carousel";
import PlayerWidget from "../player-widget/player-widget";
import styles from "./dashboard.style";
import Search from "../search/search";
import { connect } from "react-redux";
import * as actions from "../../store/actions/index";
import PropTypes from "prop-types";

const Dashboard = props => {
  const [focused, onFocus] = React.useState(false);
  const [value, onChange] = React.useState("");
  useEffect(() => {
    const { getPlaylists, getFavorites, getRecommend } = props;
    getPlaylists();
    getFavorites();
    getRecommend();
    BackHandler.addEventListener("hardwareBackPress", backButtonPress);
    return () =>
      BackHandler.removeEventListener("hardwareBackPress", backButtonPress);
  }, []);
  const backButtonPress = () => {
    onFocus(false);
    Keyboard.dismiss();
    onChange("");
  };
  const { playlist, favorites, recommended } = props;
  return (
    <View style={{ flex: 1, backgroundColor: "#2f3640" }}>
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
          <View style={{ flex: 1 }}>
            <ScrollView  style={{ flex: 1 }}>
              <Carousel title="Playlisty" list={playlist} type="Playlist"/>
              <Carousel title="Polecane" list={recommended} type="Recommended"/>
              <Carousel title="Ulubione" list={favorites} type="Favorites"/>
            </ScrollView>
            <PlayerWidget title={"Whatsername"} artist={"Green Day"} />
          </View>
        )}
      </View>
    </View>
  );
};

Dashboard.propTypes = {
  playlist: PropTypes.array,
  favorites: PropTypes.array,
  recommended: PropTypes.array
};

Dashboard.defaultProps = {
  playlist: [],
  favorites: [],
  recommended: []
};

const mapStateToProps = state => {
  return {
    playlist: state.playlist,
    favorites: state.favorites,
    recommended: state.recommended
  };
};

const mapDispatchToProps = dispatch => {
  return {
    getPlaylists: () => dispatch(actions.initPlaylist()),
    getFavorites: () => dispatch(actions.initFavorites()),
    getRecommend: () => dispatch(actions.initRecommend()),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Dashboard);
