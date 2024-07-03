import { createContext, useContext, useEffect, useState } from "react";
import Cookies from "js-cookie";
import api from "@/services/axios";

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [auth, isAuth] = useState<boolean | null>(null);

  return (
    <AuthContext.Provider value={{ auth, isAuth }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
