import FormLoginOrganisms from "@/components/organisms/login/formLoginOrganisms";
import FormValidarEmailOrganisms from "@/components/organisms/login/formValidarEmailOrganisms";
import Logo from "../../../../public/logo-login.png";
import { useState } from "react";
import classNames from "classnames";

const LoginTemplate = () => {
  const [stateForm, setStateForm] = useState<Boolean>(false);

  const handleSetForm = () => {
    setStateForm(!stateForm);
  };

  return (
    <div className="relative w-full h-screen dark:bg-white bg-[url('/background-petx.png')] bg-repeat bg-cover bg-top flex justify-center items-center">
      <div className="relative w-full sm:w-4/6 lg:w-5/6 2xl:w-4/6 h-full sm:h-4/6 flex flex-col sm:flex-row shadow-lg rounded-xl justify-center items-center">
        {!stateForm && <FormLoginOrganisms onCriarContaClick={handleSetForm} />}
        <div
          className={`hidden lg:flex h-full bg-custom-blue justify-center items-center transition-all duration-500 ease-in-out",
            ${stateForm ? "lg:w-0 rounded-l-xl" : "lg:w-1/2 rounded-r-xl"}`}
        >
          <img src={Logo.src} alt="petx" className="w-1/2" />
        </div>
        {stateForm && (
          <FormValidarEmailOrganisms onTenhoContaClick={handleSetForm} />
        )}
      </div>
    </div>
  );
};

export default LoginTemplate;
