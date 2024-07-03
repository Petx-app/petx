import api from "@/services/axios";
import Cookies from "js-cookie";

export const autenticar = async (data: any) => {
  Cookies.remove("auth");
  try {
    const response = await api.post("/usuario/autenticar", data);
    Cookies.set("auth", response.data.token, { expires: 7 });
  } catch (error) {
    if (error.response && error.response.data) {
      const errorMessage = error.response.data.message;
      throw new Error(errorMessage);
    } else {
      console.error("Erro desconhecido:", error);
      throw new Error("Erro ao autenticar usuario");
    }
  }
};

export const validar = async (data: any) => {
  try {
    await api.post("/usuario/validar/email", data);
  } catch (error) {
    if (error.response && error.response.data) {
      const errorMessage = error.response.data.message;
      throw new Error(errorMessage);
    } else {
      console.error("Erro desconhecido:", error);
      throw new Error("Erro ao validar email");
    }
  }
};
