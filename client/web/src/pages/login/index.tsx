import LoginTemplate from "@/components/templates/login/loginTemplate";
import Cookies from "js-cookie";
import { useEffect } from "react";
import { ToastContainer } from "react-toastify";

const login = () => {
  useEffect(() => {
    Cookies.remove("auth");
  }, []);

  return (
    <>
      <LoginTemplate />
      <ToastContainer />
    </>
  );
};

export default login;
