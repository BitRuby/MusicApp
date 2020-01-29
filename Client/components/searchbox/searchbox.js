import React from 'react';
import {TextInput, View, Keyboard} from 'react-native';
import styles from './searchbox.style';
import { FontAwesome } from '@expo/vector-icons';

const Searchbox = props => {
  const onBack = () => {
    props.onFocus(false);
    Keyboard.dismiss();
    props.onChange('');
  };
  return (
    <View style={styles.view}>
      {props.focused ? (
        <FontAwesome
          name="arrow-left"
          onPress={() => onBack()}
          size={24}
          style={styles.iconBack}
          color="#B8B8B8"
        />
      ) : null}
      {props.value.length > 0 ? (
        <FontAwesome
          name="times"
          onPress={() => props.onChange('')}
          size={24}
          style={styles.icon}
          color="#B8B8B8"
        />
      ) : (
        <FontAwesome name="search" size={20} style={styles.icon} color="#B8B8B8" />
      )}
      <TextInput
        style={styles.textInput}
        value={props.value}
        onSubmitEditing={() => {
          Keyboard.dismiss();
        }}
        onFocus={() => props.onFocus(true)}
        onChangeText={text => props.onChange(text)}
        placeholder="Wyszukiwarka utworów"
        placeholderTextColor="#B8B8B8"></TextInput>
    </View>
  );
};

Searchbox.propTypes = {};

Searchbox.defaultProps = {};

//Add Redux

export default Searchbox;
