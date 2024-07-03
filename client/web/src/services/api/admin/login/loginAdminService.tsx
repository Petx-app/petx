import api from "../../../axios";
import Cookies from "js-cookie";

export const autenticarAdmin = async (data: any) => {
  Cookies.remove("auth");
  try {
    const response = await api.post("/admin/autenticar", data);
    Cookies.set("auth", response.data.token, { expires: 7 });
  } catch (error: any) {
    if (error.response && error.response.data) {
      const errorMessage = error.response.data.message;
      throw new Error(errorMessage);
    } else {
      console.error("Erro desconhecido:", error);
    }
  }
};
