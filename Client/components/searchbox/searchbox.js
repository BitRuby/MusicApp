import React from 'react';
import {TextInput, View, Keyboard} from 'react-native';
import styles from './searchbox.style';
import Icon from 'react-native-vector-icons/FontAwesome5';

const Searchbox = props => {
  const onBack = () => {
    props.onFocus(false);
    Keyboard.dismiss();
    props.onChange('');
  };
  return (
    <View style={styles.view}>
      {props.focused ? (
        <Icon
          name="arrow-left"
          onPress={() => onBack()}
          size={24}
          style={styles.iconBack}
          color="#B8B8B8"
        />
      ) : null}
      {props.value.length > 0 ? (
        <Icon
          name="times"
          onPress={() => props.onChange('')}
          size={24}
          style={styles.icon}
          color="#B8B8B8"
        />
      ) : (
        <Icon name="search" size={20} style={styles.icon} color="#B8B8B8" />
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
