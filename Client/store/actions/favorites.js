import * as actionTypes from './actionTypes';
import axios from "axios";

export const setFavorites = favorites => {
  return {
    type: actionTypes.SET_FAVORITES,
    favorites: favorites,
  };
};

export const initFavorites = () => async dispatch => {
  return new Promise(() => {
    axios
      .get(`http://192.168.0.55:8075/api/favorites/5`)
      .then(response => {
        dispatch(setFavorites(response.data));
      })
      .catch(error => {
        dispatch(setError(error));
      });
  });
};
