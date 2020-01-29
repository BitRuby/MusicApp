import React from 'react';
import {View, Text} from 'react-native';
import styles from './player-widget.style';
import {FontAwesome} from '@expo/vector-icons';

const PlayerWidget = props => {
  return (
    <View>
      <View style={styles.title}>
        <Text style={styles.text}>
          {props.title} • {props.artist}
        </Text>
      </View>
      <View style={styles.player}>
        <FontAwesome name="refresh" size={20} color="white" />
        <FontAwesome name="backward" size={20} color="white" />
        <FontAwesome name="play" size={20} color="white" />
        <FontAwesome name="forward" size={20} color="white" />
        <FontAwesome name="random" size={20} color="white" />
      </View>
    </View>
  );
};

//Add PropTypes, DefaultValues, Redux, StyleSheet

export default PlayerWidget;
