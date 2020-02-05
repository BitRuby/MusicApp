import React from "react";
import { View, Text, ScrollView } from "react-native";
import Album from "../album/album";
import styles from "./carousel.style";
import { Actions } from "react-native-router-flux";
const Carousel = props => {
  const goTo = (type, data) => {
    switch (type) {
      case "Playlist":
        Actions.playlist({ id: data?.id });
        break;
      case "Recommended":
        Actions.player({
          trackId: data?.track_number,
          albumId: data?.album?.id
        });
        break;
      case "Favorites":
        Actions.player({
          trackId: data?.track_number,
          albumId: data?.album?.id
        });
        break;
    }
  };
  const { type, list, title } = props;
  return (
    <ScrollView
      horizontal={true}
      showsHorizontalScrollIndicator={false}
      bounces={true}
    >
      <View style={styles.view}>
        <Text style={styles.text}>{title}</Text>
      </View>
      {list.map((el, i) => (
        <Album element={el} key={i} onPress={() => goTo(type, el)} />
      ))}
    </ScrollView>
  );
};
export default Carousel;
