import * as actionTypes from "./actionTypes";
import axios from "axios";
import querystring from "querystring";
import setError from "./error";
import setSuccess from "./success";

export const setFavorites = favorites => {
  return {
    type: actionTypes.SET_FAVORITES,
    favorites: favorites
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

export const addToFavorites = idx => async dispatch => {
  return new Promise(() => {
    const requestBody = {
      id: idx
    };

    const config = {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      }
    };

    axios
      .post(
        `http://192.168.0.55:8075/api/addTrack`,
        querystring.stringify(requestBody),
        config
      )
      .then(response => {
        dispatch(setSuccess(response));
      })
      .catch(error => {
        dispatch(setError(error));
      });
  });
};
