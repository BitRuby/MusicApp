import * as actionTypes from '../actions/actionTypes';

const initialState = {
  favorites: [],
  playlist: [],
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
    default:
      return state;
  }
};

export default reducer;
