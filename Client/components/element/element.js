import React from 'react';
import {View, Text} from 'react-native';
import styles from './element.style';

const Element = props => {
  const {el} = props;
  const millisToMinutesAndSeconds = (millis) => {
    var minutes = Math.floor(millis / 60000);
    var seconds = ((millis % 60000) / 1000).toFixed(0);
    return minutes + ":" + (seconds < 10 ? '0' : '') + seconds;
  }
  return (
    <View style={styles.view}>
      <View>
        <Text style={styles.title}>{el?.title}</Text>
        <Text style={styles.artist}>{el?.artist?.name}</Text>
      </View>
      <View>
        <Text style={styles.duration}>
          {props.el?.duration_ms ? millisToMinutesAndSeconds(props.el?.duration_ms) : null}
        </Text>
      </View>
    </View>
  );
};

export default Element;
