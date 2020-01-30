import * as actionTypes from "./actionTypes";
import setError from "./error";
import axios from "axios";

export const setAlbum = album => {
  return {
    type: actionTypes.SET_ALBUM,
    album: album
  };
};

export const initAlbum = (id) => async dispatch => {
  return new Promise(() => {
    axios
      .get(`http://192.168.0.55:8075/api/trackByAlbum/${id}`)
      .then(response => {
        dispatch(setAlbum(response.data));
      })
      .catch(error => {
        dispatch(setError(error));
      });
  });
};
