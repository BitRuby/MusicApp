import {StyleSheet, Dimensions} from 'react-native';
const width = Dimensions.get('window').width;
const height = Dimensions.get('window').height;
const styles = StyleSheet.create({
  headerIcon: {
    paddingHorizontal: 20,
    paddingVertical: height / 24,
  },
  headerText: {
    paddingHorizontal: 30,
    paddingVertical: height / 20,
  },
  title: {
    color: '#FFF',
    fontSize: 21,
    fontFamily: 'leagueSpartan',
  },
  subtitle: {
    color: '#FFF',
    fontSize: 14,
    fontFamily: 'leagueSpartan',
  },
  innerFrame: {
    position: 'absolute',
    height: height / 4,
    width: width,
    backgroundColor: 'rgba(0, 0, 0, .5)',
  },
  cover: {
    position: 'absolute',
    height: height / 4,
    width: width,
  },
});

export default styles;
