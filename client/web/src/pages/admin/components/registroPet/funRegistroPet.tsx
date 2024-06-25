import api from "@/services/axios";

export const consultarRegistroPet = async () => {
  try {
    const response = await api.get(`/admin/dados-pets`);
    return response.data;
  } catch (error: any) {
    if (error.response && error.response.data) {
      const errorMessage = error.response.data.message;
      throw new Error(errorMessage);
    } else {
      console.error("Erro desconhecido:", error);
      throw new Error("Erro desconhecido");
    }
  }
};
