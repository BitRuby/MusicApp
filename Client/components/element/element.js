import React from 'react';
import {View, Text} from 'react-native';
import styles from './element.style';

const Element = props => {
  return (
    <View style={styles.view}>
      <View>
        <Text style={styles.title}>{props.el.title}</Text>
        <Text style={styles.artist}>{props.el.artist}</Text>
      </View>
      <View>
        <Text style={styles.duration}>
          {props.el.duration ? props.el.duration : null}
        </Text>
      </View>
    </View>
  );
};

export default Element;
