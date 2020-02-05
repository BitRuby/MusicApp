import * as actionTypes from "./actionTypes";
import setError from "./error";
import axios from "axios";

export const setRecommend = recommended => {
  return {
    type: actionTypes.SET_RECOMMENDED,
    recommended: recommended
  };
};

export const initRecommend = () => async dispatch => {
  return new Promise(() => {
    axios
      .get(`http://192.168.0.55:8075/api/recommendations`)
      .then(response => {
        dispatch(setRecommend(response.data));
      })
      .catch(error => {
        dispatch(setError(error));
      });
  });
};
