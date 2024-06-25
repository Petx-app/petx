import api from "@/services/axios";

export const finalizarQRCode = async (uuid: string) => {
  try {
    await api.put(`/admin/qrcode/${uuid}`);
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

export const consultarQRCodeDisponiveis = async () => {
  try {
    const response = await api.get(`/admin/uuid-disponiveis`);
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
