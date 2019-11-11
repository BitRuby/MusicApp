import * as actionTypes from './actionTypes';

export const setFavorites = favorites => {
  return {
    type: actionTypes.SET_FAVORITES,
    favorites: favorites,
  };
};

export const setPlaylist = playlist => {
  return {
    type: actionTypes.SET_PLAYLIST,
    playlist: playlist,
  };
};

export const setTracklist = tracklist => {
  return {
    type: actionTypes.SET_TRACKLIST,
    tracklist: tracklist,
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
