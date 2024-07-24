import CadastrarTemplate from "@/components/templates/cadastrar/cadastrarTemplate";
import { useEmail } from "@/context/emailContext";
import { consultaCookieEmail } from "@/utils/checkCookies";
import { useRouter } from "next/router";
import { useEffect } from "react";
import { ToastContainer } from "react-toastify";

const Cadastrar = () => {
  const router = useRouter();
  const { email, setEmail } = useEmail();

  useEffect(() => {
    if (!email) {
      const emailcookie = consultaCookieEmail();
      if (emailcookie) {
        setEmail(emailcookie);
      } else {
        router.push("/login");
      }
    }
  }, [email, router]);

  return (
    <>
      <CadastrarTemplate email={email} />
      <ToastContainer />
    </>
  );
};
export default Cadastrar;
