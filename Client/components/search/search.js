import React from "react";
import { Text, View, ScrollView } from "react-native";
import styles from "./search.style";
import Element from "../element/element";
import { connect } from "react-redux";
import * as actions from "../../store/actions/index";
import PropTypes from "prop-types";

const Search = props => {
  const list = [];
  const { search, onSearch, value } = props;
  React.useEffect(() => {
    value.length >= 3 ? onSearch(value) : "";
  }, [value]);
  return (
    <View>
      {props.value ? (
        <View>
          <Text style={styles.title}>Wyniki wyszukiwania</Text>
          {search?.trackList?.length > 0 && (
            <ScrollView>
              <Text style={styles.title}>Utwory</Text>
              {search?.trackList?.map((el, i) => (
                <Element key={i} el={el} />
              ))}
            </ScrollView>
          )}
        </View>
      ) : (
        <ScrollView>
          <Text style={styles.title}>Ostatnio wyszukiwane</Text>
          {list.map((el, i) => (
            <Element key={i} el={el} />
          ))}
        </ScrollView>
      )}
    </View>
  );
};

const SearchType = {
  artistList: PropTypes.array,
  albumList: PropTypes.array,
  trackList: PropTypes.array
};

Search.propTypes = {
  search: PropTypes.shape(SearchType)
};

Search.defaultProps = {
  search: {}
};

const mapStateToProps = state => {
  return {
    search: state.search
  };
};

const mapDispatchToProps = dispatch => {
  return {
    onSearch: keyword => dispatch(actions.search(keyword))
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Search);
