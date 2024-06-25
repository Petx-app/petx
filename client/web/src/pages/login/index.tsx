import Entrar from "./entrar";
import ValidarEmail from "./validarEmail";
import Logo from "../../../public/logo-login.png";
import { useState } from "react";
import classNames from "classnames";

const login = () => {
  const [stateForm, setStateForm] = useState<Boolean>(false);

  const handleSetForm = () => {
    setStateForm(!stateForm);
  };

  return (
    <div className="relative w-full h-screen dark:bg-white bg-[url('/background-petx.png')] bg-repeat bg-cover bg-top flex justify-center items-center">
      <div className="relative w-full sm:w-4/6 lg:w-5/6 2xl:w-4/6 h-full sm:h-4/6 flex flex-col sm:flex-row shadow-lg rounded-xl justify-center items-center">
        {!stateForm && <Entrar onCriarContaClick={handleSetForm} />}
        <div
          className={classNames(
            "hidden lg:flex lg:w-1/2 h-full bg-custom-blue justify-center items-center",
            {
              "rounded-r-xl": !stateForm,
              "rounded-l-xl": stateForm,
            },
          )}
        >
          <img src={Logo.src} alt="petx" className="w-1/2" />
        </div>
        {stateForm && <ValidarEmail onTenhoContaClick={handleSetForm} />}
      </div>
    </div>
  );
};

export default login;
