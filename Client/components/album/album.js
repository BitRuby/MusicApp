import React from "react";
import { View, Image, Text, TouchableOpacity } from "react-native";
import styles from "./album.style";
const Album = props => {
  const {onPress, element} = props;
  return (
    <TouchableOpacity onPress={() => onPress()}>
      <View style={styles.view}>
        <Image style={styles.image} source={{uri: element?.album?.img || element?.imgCover}}></Image>
        <Text style={styles.title}>{element?.title || element?.name}</Text>
      </View>
    </TouchableOpacity>
  );
};
export default Album;
