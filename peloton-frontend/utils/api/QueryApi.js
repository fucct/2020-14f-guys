import Axios from "axios";
import { SERVER_BASE_URL } from "../constants";

export const QueryApi = {
  getRaces: async (token) => {
    const response = await Axios({
      method: "GET",
      baseURL: SERVER_BASE_URL,
      url: "/api/queries/races",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data.raceResponses;
  },
};
