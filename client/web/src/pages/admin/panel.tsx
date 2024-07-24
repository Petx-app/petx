import PanelTemplate from "@/components/templates/admin/panelTemplate";
import { useAuth } from "@/context/authContext";
import { consultarCookieAuth } from "@/utils/checkCookies";
import { useRouter } from "next/router";
import { useEffect } from "react";
import { ToastContainer } from "react-toastify";

const panelAdmin = () => {
  const router = useRouter();
  const { auth, isAuth } = useAuth();

  useEffect(() => {
    if (!auth) {
      if (consultarCookieAuth()) {
        isAuth(true);
      } else {
        router.push("/admin/login");
      }
    }
  }, [auth, router]);

  return (
    <>
      <PanelTemplate title={"Administrador"} />
      <ToastContainer />
    </>
  );
};

export default panelAdmin;
