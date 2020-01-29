import {StyleSheet} from 'react-native';

const styles = StyleSheet.create({
  view: {},
  title: {
    display: 'flex',
    flexDirection: 'row',
    backgroundColor: '#000',
    justifyContent: 'center',
    alignItems: 'center',
    paddingVertical: 15,
  },
  play: {
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#FB266E',
    width: 30,
    height: 30,
    borderRadius: 100 / 2,
  },
  text: {
    color: '#FFF',
    fontSize: 12,
  },
  player: {
    display: 'flex',
    flexDirection: 'row',
    backgroundColor: '#FB266E',
    justifyContent: 'space-around',
    paddingHorizontal: 50,
    paddingVertical: 30,
  },
});

export default styles;
