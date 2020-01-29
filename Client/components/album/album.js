import React from "react";
import { View, Image, Text, TouchableOpacity } from "react-native";
import styles from "./album.style";
const Album = props => {
  const {onPress} = props;
  return (
    <TouchableOpacity onPress={() => onPress()}>
      <View style={styles.view}>
        <Image style={styles.image} source={props.element.image}></Image>
        <Text style={styles.title}>{props.element.title}</Text>
        <Text style={styles.artist}>{props.element.artist}</Text>
      </View>
    </TouchableOpacity>
  );
};
export default Album;
