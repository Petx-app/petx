import { consultarTokenAuthService } from "@/services/api/auth/authService";
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
