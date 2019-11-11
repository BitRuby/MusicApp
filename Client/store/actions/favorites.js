import * as actionTypes from './actionTypes';

export const setFavorites = favorites => {
  return {
    type: actionTypes.SET_FAVORITES,
    favorites: favorites,
  };
};

export const initFavorites = () => {
  return dispatch => {
    const favorites = [
      {
        title: 'High Hopes',
        artist: 'Panic! At the Disco',
        duration: '3:11',
        playing: false,
      },
      {
        title: 'In the End',
        artist: 'Linkin Park',
        duration: '3:37',
        playing: false,
      },
      {
        title: 'Jesus of Suburbia',
        artist: 'Green Day',
        duration: '9:10',
        playing: true,
      },
      {
        title: 'Sk8ter boi',
        artist: 'Avril Lavigne',
        duration: '3:24',
        playing: false,
      },
      {
        title: 'Turn me Loose',
        artist: 'The Longshot',
        duration: '3:24',
        playing: false,
      },
      {
        title: 'High Hopes',
        artist: 'Panic! At the Disco',
        duration: '3:11',
        playing: false,
      },
      {
        title: 'In the End',
        artist: 'Linkin Park',
        duration: '3:37',
        playing: false,
      },
      {
        title: 'Jesus of Suburbia',
        artist: 'Green Day',
        duration: '9:10',
        playing: true,
      },
      {
        title: 'Sk8ter boi',
        artist: 'Avril Lavigne',
        duration: '3:24',
        playing: false,
      },
      {
        title: 'Turn me Loose',
        artist: 'The Longshot',
        duration: '3:24',
        playing: false,
      },
    ];
    dispatch(setFavorites(favorites));
  };
};
