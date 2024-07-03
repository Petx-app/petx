import api from "@/services/axios";

export const consultarTokenAuthService = async () => {
  try {
    await api.get("/security/jwt");
  } catch (e) {
    console.log(e);
  }
};
