import * as actionTypes from '../actions/actionTypes';

const initialState = {
  favorites: [],
  playlist: [],
  tracklist: [],
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
    default:
      return state;
  }
};

export default reducer;
