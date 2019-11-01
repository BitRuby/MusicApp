import React from 'react';
import {TextInput, View} from 'react-native';
import styles from './searchbox.style';
import Icon from 'react-native-vector-icons/FontAwesome5';

const Searchbox = () => {
  const [value, onChange] = React.useState('');
  const [active, onActive] = React.useState(true);

  return (
    <View style={styles.view}>
      {active ? (
        <Icon
          name="arrow-left"
          onPress={() => onActive()}
          size={24}
          style={styles.iconBack}
          color="#B8B8B8"
        />
      ) : null}
      {value.length > 0 ? (
        <Icon
          name="times"
          onPress={() => onChange('')}
          size={24}
          style={styles.icon}
          color="#B8B8B8"
        />
      ) : (
        <Icon name="search" size={20} style={styles.icon} color="#B8B8B8" />
      )}
      <TextInput
        style={styles.textInput}
        value={value}
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
