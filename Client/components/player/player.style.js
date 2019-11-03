import {StyleSheet, Dimensions} from 'react-native';
const width = Dimensions.get('window').width;
const font = 'leagueSpartan';
const styles = StyleSheet.create({
  header: {
    color: '#fff',
    fontFamily: font,
    fontSize: 11,
  },
  timelapse: {
    color: '#fff',
    fontFamily: font,
    fontSize: 13,
  },
  album: {
    color: '#fff',
    fontFamily: font,
    fontSize: 15,
    paddingBottom: 20,
    color: '#fff',
  },
  view: {
    justifyContent: 'center',
    alignItems: 'center',
    flex: 1,
  },
  imageSlider: {
    maxHeight: 300,
  },
  imageContainer: {
    width: width,
    justifyContent: 'center',
    alignItems: 'center',
  },
  image: {
    width: 300,
    height: 300,
  },
  time: {
    width: 300,
    paddingVertical: 0,
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  description: {
    width: 300,
    paddingVertical: 20,
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  descriptionContent: {
    maxWidth: 250,
    paddingRight: 5,
  },
  title: {
    color: '#FFF',
    fontSize: 20,
    fontFamily: font,
  },
  artist: {
    color: '#909090',
    fontSize: 14,
    fontFamily: font,
  },
  heartIcon: {
    borderWidth: 1,
    backgroundColor: '#fff',
    borderRadius: 50,
    padding: 10,
    paddingTop: 12,
    paddingLeft: 12,
  },
  slider: {
    width: 340,
  },
  playerIcons: {
    justifyContent: 'space-between',
    alignItems: 'center',
    flexDirection: 'row',
    width: 300,
    marginVertical: 50,
  },
  playIcon: {
    borderWidth: 1,
    borderColor: '#fff',
    borderRadius: 50,
    padding: 30,
    paddingLeft: 33,
  },
});

export default styles;
