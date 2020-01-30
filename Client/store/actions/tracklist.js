import * as actionTypes from "./actionTypes";
import axios from "axios";

export const setTracklist = tracklist => {
  return {
    type: actionTypes.SET_TRACKLIST,
    tracklist: tracklist
  };
};

export const initTracklist = id => async dispatch => {
  return new Promise(() => {
    axios
      .get(`http://192.168.0.55:8075/api/track/${id}`)
      .then(response => {
        dispatch(setTracklist(response.data));
      })
      .catch(error => {
        dispatch(setError(error));
      });
  });
};
