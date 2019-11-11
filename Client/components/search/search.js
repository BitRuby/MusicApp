import React from 'react';
import {Text, View} from 'react-native';
import styles from './search.style';
import Element from '../element/element';
import {connect} from 'react-redux';
import * as actions from '../../store/actions/index';

const Search = props => {
  const list = [];
  React.useEffect(() => {
    props.onSearch(props.value);
  }, [props.value]);
  return (
    <View>
      {props.value ? (
        <View>
          <Text style={styles.title}>Wyniki wyszukiwania</Text>
          {props.search.map((el, i) => (
            <Element key={i} el={el} />
          ))}
        </View>
      ) : (
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

const mapStateToProps = state => {
  return {
    search: state.search,
  };
};

const mapDispatchToProps = dispatch => {
  return {
    onSearch: keyword => dispatch(actions.search(keyword)),
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Search);
