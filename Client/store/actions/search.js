import * as actionTypes from './actionTypes';

export const setSearch = search => {
  return {
    type: actionTypes.SET_SEARCH,
    search: search,
  };
};

export const search = keyword => {
  return dispatch => {
    const search = [
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
    let filterResults = search.filter(function(result) {
      return (
        result.title.toLowerCase().includes(keyword.toLowerCase()) ||
        result.artist.toLowerCase().includes(keyword.toLowerCase())
      );
    });
    dispatch(setSearch(filterResults));
  };
};
