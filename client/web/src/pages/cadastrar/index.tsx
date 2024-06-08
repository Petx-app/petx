import { useEffect, useState } from "react";
import ConfirmEmail from "./confirmEmail";
import FormularioCadastrar from "./formularioCadastrar";
import { useRouter } from "next/router";
import { useEmail } from "@/context/emailContext";
import { ToastContainer } from "react-toastify";

const Cadastrar = () => {
  const router = useRouter();
  const { email } = useEmail();

  const [stateForm, setStateForm] = useState<boolean>(false);

  // useEffect(() => {
  //   if (!email) {
  //     router.push("/login");
  //   }
  // }, [email, router]);

  function handleComponentCadastro() {
    setStateForm(true);
  }

  return (
    <div className="w-screen h-screen flex justify-center items-center bg-custom-blue-2">
      <div className="xl:w-full xl:h-full flex flex-col sm:flex-row x rounded-xl justify-center items-center">
        {!stateForm ? (
          <ConfirmEmail
            email={email}
            onConfirmSuccess={handleComponentCadastro}
          />
        ) : (
          <FormularioCadastrar email={email} />
        )}
      </div>
    </div>
  );
};
export default Cadastrar;
