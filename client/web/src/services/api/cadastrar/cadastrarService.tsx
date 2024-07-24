import api from "@/services/axios";
import Cookies from "js-cookie";

export const confirmEmail = async (data: any) => {
  try {
    await api.post("/usuario/confirmar/email", data);
  } catch (error) {
    if (error.response && error.response.data) {
      const errorMessage = error.response.data.message;
      console.log(errorMessage);
      throw new Error(errorMessage);
    } else {
      console.error("Erro desconhecido:", error);
      throw new Error("Erro ao confirmar codigo");
    }
  }
};

export const cadastrar = async (data: any) => {
  try {
    const response = await api.post("/usuario", data);
    Cookies.set("auth", response.data.token, { expires: 7 });
  } catch (error) {
    if (error.response && error.response.data) {
      const errorMessage = error.response.data.message;
      console.log(errorMessage);
      throw new Error(errorMessage);
    } else {
      console.error("Erro desconhecido:", error);
      throw new Error("Erro ao confirmar codigo");
    }
  }
};
