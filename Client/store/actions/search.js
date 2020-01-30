import * as actionTypes from './actionTypes';
import axios from "axios";
import setError from "./error";

export const setSearch = search => {
  return {
    type: actionTypes.SET_SEARCH,
    search: search,
  };
};

export const search = (keyword) => async dispatch => {
  return new Promise(() => {
    axios
      .get(`http://192.168.0.55:8075/api/search/${keyword}`)
      .then(response => {
        dispatch(setSearch(response.data));
      })
      .catch(error => {
        dispatch(setError(error));
      });
  });
};
