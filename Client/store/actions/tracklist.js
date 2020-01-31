import * as actionTypes from "./actionTypes";
import axios from "axios";
import setError from "./error";

export const setTracklist = tracklist => {
  return {
    type: actionTypes.SET_TRACKLIST,
    tracklist: tracklist
  };
};
export const setAlbum = album => {
  return {
    type: actionTypes.SET_ALBUM,
    album: album
  };
};

export const initTracklist = id => async dispatch => {
  return new Promise(() => {
    axios
      .get(`http://192.168.0.55:8075/api/track/${id}`)
      .then(response => {
        dispatch(setAlbum(response.data));
        return axios.get(
          `http://192.168.0.55:8075/api/trackByAlbum/${response.data.album.id}`
        );
      })
      .then(ret => {
        dispatch(setTracklist(ret));
      })
      .catch(error => {
        dispatch(setError(error));
      });
  });
};
