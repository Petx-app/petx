import { createContext, useContext, useState } from "react";

const EmailContext = createContext(null);

export const EmailProvider = ({ children }) => {
  const [email, setEmail] = useState<String>("");

  return (
    <EmailContext.Provider value={{ email, setEmail }}>
      {children}
    </EmailContext.Provider>
  );
};

export const useEmail = () => useContext(EmailContext);
