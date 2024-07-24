import { useEffect, useState } from "react";
import FormCadastrarUsuarioOrganisms from "@/components/organisms/cadastrar/formCadastrarUsuarioOrganisms";
import FormCodigoVerificacaoEmailOrganisms from "@/components/organisms/cadastrar/formCodigoVerificacaoEmailOrganisms";
import { useRouter } from "next/router";
import { useEmail } from "@/context/emailContext";
import { consultaCookieEmail } from "@/utils/checkCookies";

const CadastrarTemplate = ({ email }) => {
  const [stateForm, setStateForm] = useState<boolean>(true);

  return (
    <>
      <div
        className={`w-screen h-screen flex justify-center items-center  bg-white  ${!stateForm && "bg-[url('/background-petx.png')]"}`}
      >
        <div className="xl:w-full xl:h-full flex flex-col sm:flex-row x rounded-xl justify-center items-center">
          {stateForm ? (
            <FormCodigoVerificacaoEmailOrganisms
              email={email}
              setStateForm={setStateForm}
            />
          ) : (
            <FormCadastrarUsuarioOrganisms email={email} />
          )}
        </div>
      </div>
    </>
  );
};
export default CadastrarTemplate;
