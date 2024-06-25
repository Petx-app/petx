import api from "../../../services/axios";
import Cookies from "js-cookie";

export const autenticar = async (data: any) => {
  Cookies.remove("jwt");

  try {
    const response = await api.post("/admin/autenticar", data);
    const token = response.data.token;
    Cookies.set("jwt", token, { expires: 7 });
  } catch (error: any) {
    if (error.response && error.response.data) {
      const errorMessage = error.response.data.message;
      throw new Error(errorMessage);
    } else {
      console.error("Erro desconhecido:", error);
    }
  }
};
