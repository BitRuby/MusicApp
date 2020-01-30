import * as actionTypes from "./actionTypes";

export const setError = error => {
    return {
      type: actionTypes.API_ERROR,
      error: error
    };
  };
  