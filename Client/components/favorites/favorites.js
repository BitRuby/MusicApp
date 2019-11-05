import React from 'react';
import {View, Text, Image, ScrollView} from 'react-native';
import styles from './favorites.style';
import Icon from 'react-native-vector-icons/FontAwesome5';
import Element from '../element/element';
import LinearGradient from 'react-native-linear-gradient';
import {connect} from 'react-redux';
import * as actions from '../../store/actions/index';
const Favorites = props => {
  const [favIno, setFavIno] = React.useState({
    coverUrl: require('../../assets/images/1.jpg'),
    quantity: '58 utworów',
  });
  React.useEffect(() => {
    props.onInit();
  },[]);
  return (
    <LinearGradient colors={['#1A1A1A', '#3B3B3B']} style={{flex: 1}}>
      <Image source={favIno.coverUrl} style={styles.cover}></Image>
      <View style={styles.innerFrame}></View>
      <View style={styles.headerIcon}>
        <Icon name="arrow-left" size={20} color="#FFF" />
      </View>
      <View style={styles.headerText}>
        <Text style={styles.title}>Ulubione</Text>
        <Text style={styles.subtitle}>{favIno.quantity}</Text>
      </View>
      <ScrollView>
        {props.favorites.map((el, i) => (
          <Element key={i} el={el} />
        ))}
      </ScrollView>
    </LinearGradient>
  );
};

const mapStateToProps = state => {
  return {
    favorites: state.favorites,
  };
};

const mapDispatchToProps = dispatch => {
  return {
    onInit: () => dispatch(actions.initFavorites()),
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Favorites);
