import { createContext, useContext, useEffect, useState } from "react";
import Cookies from "js-cookie";
import { jwtDecode } from "jwt-decode";

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [auth, isAuth] = useState<boolean | null>(null);

  useEffect(() => {
    const token = Cookies.get("jwt");
    if (token) {
      try {
        const decodedToken = jwtDecode(token);
        const oneDayInMilliseconds = 86400000; // 24 * 60 * 60 * 1000
        const isValidForOneMoreDay =
          decodedToken.exp * 1000 - Date.now() > oneDayInMilliseconds;
        console.log(isValidForOneMoreDay);
        isAuth(isValidForOneMoreDay); // Defina auth para true se o token for válido por mais de um dia
      } catch (error) {
        console.error("Token inválido:", error);
        isAuth(false);
      }
    } else {
      isAuth(false);
    }
  }, []);

  return (
    <AuthContext.Provider value={{ auth, isAuth }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
