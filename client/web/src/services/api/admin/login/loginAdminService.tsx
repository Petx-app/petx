import api from "../../../axios";
import Cookies from "js-cookie";

const adminBaseUrl = "/admin";

async function requestBaseApi({ url, ...options }) {
  return api.request({
    url: `${adminBaseUrl}${url}`,
    ...options,
  });
}

export const autenticarAdminApi = {
  autenticar: async function (data: any) {
    Cookies.remove("auth");
    const response = await requestBaseApi({
      url: `/autenticar`,
      method: "POST",
      data: data,
    });
    Cookies.set("auth", response.data.token, { expires: 7 });
    return response.data;
  },
};
