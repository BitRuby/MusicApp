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
  const {onChange, onFocus} = props;
  return (
    <View style={styles.view}>
      {props.focused ? (
        <FontAwesome
          name="arrow-left"
          onPress={() => onBack()}
          size={30}
          style={styles.iconBack}
          color="#B8B8B8"
        />
      ) : null}
      {props.value.length > 0 ? (
        <FontAwesome
          name="times"
          onPress={() => onChange('')}
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
        onFocus={() => onFocus(true)}
        onChangeText={text => onChange(text)}
        placeholder="Wyszukiwarka utworów"
        placeholderTextColor="#B8B8B8"></TextInput>
    </View>
  );
};

Searchbox.propTypes = {};

Searchbox.defaultProps = {};

//Add Redux

export default Searchbox;
