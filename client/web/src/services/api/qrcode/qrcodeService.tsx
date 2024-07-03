import api from "@/services/axios";

export const consultaQRCode = async (uuid) => {
  try {
    const response = await api.get(`/qrcode/${uuid}`);
    return response.data;
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
