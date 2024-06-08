import api from "./../../services/axios";

export const autenticar = async (data: any) => {
  try {
    await api.post("/usuario/autenticar", data);
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
