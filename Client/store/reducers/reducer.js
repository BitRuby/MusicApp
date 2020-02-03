import * as actionTypes from '../actions/actionTypes';

const initialState = {
  favorites: [],
  playlist: [],
  tracklist: [],
  search: [],
  album: {},
  error: {}
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.SET_FAVORITES:
      return {
        ...state,
        favorites: action.favorites,
      };
    case actionTypes.SET_PLAYLIST:
      return {
        ...state,
        playlist: action.playlist,
      };
    case actionTypes.SET_TRACKLIST:
      return {
        ...state,
        tracklist: action.tracklist,
      };
    case actionTypes.SET_SEARCH:
      return {
        ...state,
        search: action.search,
      };
    case actionTypes.SET_ALBUM:
      return {
        ...state,
        album: action.album,
      };
    case actionTypes.API_ERROR:
      return {
        ...state,
        error: action.error,
      };
    default:
      return state;
  }
};

export default reducer;
