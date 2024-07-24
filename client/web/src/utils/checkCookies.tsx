import { consultarTokenAuthService } from "@/services/api/auth/authService";
import { ValidateUUID } from "./validateUUID";
import Cookies from "js-cookie";

export const consultaCookieEmail = () => {
  const emailCookie = Cookies.get("email");
  if (emailCookie) {
    return emailCookie;
  }
  return "";
};

export const consultarCookieAuth = () => {
  const authCookie = Cookies.get("auth");
  if (authCookie) {
    const consultarTokenService = async () => {
      try {
        return await consultarTokenAuthService();
      } catch {
        return false;
      }
    };
    return consultarTokenService();
  }
  return false;
};

export const consultaCookieQRCode = () => {
  const uuidQRCodePet = Cookies.get("uuid_qrcode");
  if (uuidQRCodePet) {
    if (ValidateUUID(uuidQRCodePet)) {
      return uuidQRCodePet;
    }
    return "";
  }
  return "";
};


export const consultaCookieEstadoCadastroUsuario = () => {
  const estadoCadastro = Cookies.get("formCadastro");
  if(estadoCadastro == "true"){
    return true;
  }
}