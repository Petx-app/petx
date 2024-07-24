import { useState } from "react";
import RegistroPetsOrganisms from "@/components/organisms/admin/panel/registroPetsOrganisms";
import PopularBancoOrganisms from "@/components/organisms/admin/panel/popularBancoOrganisms";
import FinalizarQRCodeOrganisms from "@/components/organisms/admin/panel/finalizarQRCodeOrganisms";
import { useRouter } from "next/router";

const PanelTemplate = ({ title }) => {
  const [fetchStateRegistro, setFetchStateRegistro] = useState<boolean>(false);
  const route = useRouter();

  return (
    <div className="w-screen min-h-screen flex flex-col p-5">
      <h1 className="font-bold text-3xl pl-10 mb-5">{title}</h1>

      <div className="w-full h-auto flex flex-col lg:flex-row px-5 gap-5">
        <div className="w-full lg:w-3/4 h-auto flex flex-col gap-5">
          <RegistroPetsOrganisms fetchState={fetchStateRegistro} />

          <div className="w-full lg:w-1/2 h-auto flex">
            <PopularBancoOrganisms
              setFetchStateRegistro={setFetchStateRegistro}
            />
          </div>
        </div>
        <div className="w-full lg:w-1/2 h-auto flex">
          <FinalizarQRCodeOrganisms
            setFetchStateRegistro={setFetchStateRegistro}
            fetchStateRegistro={fetchStateRegistro}
          />
        </div>
      </div>
      <div className="w-full h-auto p-4 flex justify-end">
        <button
          className="bg-custom-blue p-2 rounded-xl md:w-20"
          onClick={() => route.push("/admin/login")}
        >
          Sair
        </button>
      </div>
    </div>
  );
};

export default PanelTemplate;
