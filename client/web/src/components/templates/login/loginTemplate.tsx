import FormEsqueceuSenhaOrganisms from "@/components/organisms/login/formEsqueceuSenhaOrganisms";
import FormLoginOrganisms from "@/components/organisms/login/formLoginOrganisms";
import FormValidarEmailOrganisms from "@/components/organisms/login/formValidarEmailOrganisms";
import { useState } from "react";

const LoginTemplate = () => {
  const [stateRender, setStateRender] = useState<string>("login");

  return (
    <div className="relative w-full h-screen dark:bg-white bg-[url('/background-petx.png')] bg-repeat bg-cover bg-top flex justify-center items-center">
      <div className="relative w-full sm:w-4/6 lg:w-5/6 2xl:w-4/6 h-full sm:h-4/6 flex flex-col sm:flex-row shadow-lg rounded-xl justify-center items-center">
        {(() => {
          switch (stateRender) {
            case "login":
              return <FormLoginOrganisms setStateRender={setStateRender} />;
            case "validarEmail":
              return (
                <FormValidarEmailOrganisms setStateRender={setStateRender} />
              );
            case "esqueceuSenha":
              return (
                <FormEsqueceuSenhaOrganisms setStateRender={setStateRender} />
              );
          }
        })()}
      </div>
    </div>
  );
};

export default LoginTemplate;
