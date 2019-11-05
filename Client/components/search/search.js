import React from 'react';
import {Text, View} from 'react-native';
import styles from './search.style';
import Element from '../element/element';
const Search = props => {
  const list = [];
  return (
    <View>
      {props.value ? null : (
        <View>
          <Text style={styles.title}>Ostatnio wyszukiwane</Text>
          {list.map((el, i) => (
            <Element key={i} el={el} />
          ))}
        </View>
      )}
    </View>
  );
};

//Add PropTypes, DefaultValues, Redux, StyleSheet

export default Search;
