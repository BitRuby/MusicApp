import * as actionTypes from "./actionTypes";

export const setSuccess = success => {
  return {
    type: actionTypes.API_SUCCESS,
    success: success
  };
};
