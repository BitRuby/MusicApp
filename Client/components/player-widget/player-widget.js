import React from 'react';
import {View, Text} from 'react-native';
import styles from './player-widget.style';
import Icon from 'react-native-vector-icons/FontAwesome5';

const PlayerWidget = props => {
  return (
    <View>
      <View style={styles.title}>
        <Text style={styles.text}>
          {props.title} • {props.artist}
        </Text>
      </View>
      <View style={styles.player}>
        <Icon name="sync" size={20} color="#000" />
        <Icon name="step-backward" size={20} color="#000" />
        <Icon name="play" size={20} color="#000" />
        <Icon name="step-forward" size={20} color="#000" />
        <Icon name="random" size={20} color="#000" />
      </View>
    </View>
  );
};

//Add PropTypes, DefaultValues, Redux, StyleSheet

export default PlayerWidget;
