import api from "@/services/axios";

export const gerarQRCode = async (qtd: number) => {
  try {
    await api.post(`/admin/pet/${qtd}`);
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
