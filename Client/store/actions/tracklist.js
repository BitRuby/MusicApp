import * as actionTypes from './actionTypes';

export const setTracklist = tracklist => {
  return {
    type: actionTypes.SET_TRACKLIST,
    tracklist: tracklist,
  };
};

export const initTracklist = () => {
  return dispatch => {
    const tracklist = [
      {
        album: 'Shenanigans',
        title: 'Suffocate',
        artist: 'Green Day',
        image: require('../../assets/images/1.jpg'),
        track: 1,
      },
      {
        album: 'Shenanigans',
        title: 'Desensitized',
        artist: 'Green Day',
        image: require('../../assets/images/1.jpg'),
        track: 2,
      },
      {
        album: 'Shenanigans',
        title: 'You Lied',
        artist: 'Green Day',
        image: require('../../assets/images/1.jpg'),
        track: 3,
      },
      {
        album: 'Shenanigans',
        title: 'Outsider',
        artist: 'Green Day',
        image: require('../../assets/images/1.jpg'),
        track: 4,
      },
      {
        album: 'Shenanigans',
        title: "Don't Wanna Fall in Love",
        artist: 'Green Day',
        image: require('../../assets/images/1.jpg'),
        track: 5,
      },
      {
        album: 'Shenanigans',
        title: 'Espionage',
        artist: 'Green Day',
        image: require('../../assets/images/1.jpg'),
        track: 6,
      },
    ];
    dispatch(setTracklist(tracklist));
  };
};
