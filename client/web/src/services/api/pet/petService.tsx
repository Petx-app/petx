import api from "@/services/axios";

export const consultarListaPet = async () => {
    try{
        const response = await api.get(`/pet/list`)
        return response.data
    }catch (error) {
        if (error.response && error.response.data) {
          const errorMessage = error.response.data.message;
          throw new Error(errorMessage);
        } else {
          console.error("Erro desconhecido:", error);
          throw new Error("Erro ao autenticar usuario");
        }
    }
}