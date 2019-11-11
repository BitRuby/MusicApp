import * as actionTypes from './actionTypes';

export const setPlaylist = playlist => {
  return {
    type: actionTypes.SET_PLAYLIST,
    playlist: playlist,
  };
};

export const initPlaylist = () => {
  return dispatch => {
    const playlist = [
      {
        title: 'You Lied',
        artist: 'Green Day',
        image: require('../../assets/images/1.jpg'),
      },
      {
        title: 'Having a Blast',
        artist: 'Green Day',
        image: require('../../assets/images/2.jpg'),
      },
      {
        title: 'Holiday',
        artist: 'Green Day',
        image: require('../../assets/images/3.png'),
      },
      {
        title: 'The Resistance',
        artist: 'Muse',
        image: require('../../assets/images/4.jpg'),
      },
      {
        title: 'Shadow of the Day',
        artist: 'Linkin Park',
        image: require('../../assets/images/5.jpg'),
      },
    ];
    dispatch(setPlaylist(playlist));
  };
};
