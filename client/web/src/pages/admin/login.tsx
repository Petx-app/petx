import LoginTemplate from "@/components/templates/admin/loginTemplate";
import Cookies from "js-cookie";
import { useEffect } from "react";

const Login = () => {
  useEffect(() => {
    Cookies.remove("auth");
  }, []);
  return (
    <>
      <LoginTemplate title={"Admin"} />
    </>
  );
};
export default Login;
