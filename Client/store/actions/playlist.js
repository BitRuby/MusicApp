import * as actionTypes from "./actionTypes";
import setError from "./error";
import axios from "axios";

export const setPlaylist = playlist => {
  return {
    type: actionTypes.SET_PLAYLIST,
    playlist: playlist
  };
};

export const initPlaylist = () => async dispatch => {
  return new Promise(() => {
    axios
      .get(`http://192.168.0.55:8075/api/playlists/10`)
      .then(response => {
        dispatch(setPlaylist(response.data));
      })
      .catch(error => {
        dispatch(setError(error));
      });
  });
};
