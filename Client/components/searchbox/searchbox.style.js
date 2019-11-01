import {StyleSheet} from 'react-native';

const styles = StyleSheet.create({
  view: {
    flexDirection: 'row',
    justifyContent: 'flex-end',
    alignItems: 'center',
    zIndex: 0,
  },
  textInput: {
    fontFamily: 'leagueSpartan',
    flex: 1,
    padding: 10,
    paddingLeft: 50,
    margin: 30,
    borderColor: '#FB266E',
    borderWidth: 1,
    color: '#B8B8B8',
  },
  icon: {
    position: 'absolute',
    right: 50,
    zIndex: 1,
  },
  iconBack: {
    position: 'absolute',
    left: 50,
    zIndex: 1,
  },
});

export default styles;
