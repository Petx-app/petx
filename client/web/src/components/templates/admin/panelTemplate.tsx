import { useEffect, useState } from "react";
import { ToastContainer } from "react-toastify";
import RegistroPetsOrganisms from "@/components/organisms/admin/panel/registroPetsOrganisms";
import PopularBancoOrganisms from "@/components/organisms/admin/panel/popularBancoOrganisms";
import FinalizarQRCodeOrganisms from "@/components/organisms/admin/panel/finalizarQRCodeOrganisms";

const panelTemplate = ({ title }) => {
  const [fetchStateRegistro, setFetchStateRegistro] = useState<boolean>(false);

  const handleStateRegistros = () => {
    setFetchStateRegistro(!fetchStateRegistro);
  };

  return (
    <div className="w-screen h-screen flex flex-col p-5">
      <h1 className="font-bold text-3xl pl-10 mb-5">{title}</h1>

      <div className="w-full h-auto flex px-10 gap-5">
        <div className="w-3/4 h-auto flex flex-col gap-5">
          <RegistroPetsOrganisms fetchState={fetchStateRegistro} />

          <div className="w-1/2 h-auto flex">
            <PopularBancoOrganisms fetchRegistros={handleStateRegistros} />
          </div>
        </div>
        <div className="w-1/2 h-auto flex">
          <FinalizarQRCodeOrganisms
            fetchRegistros={handleStateRegistros}
            fetchState={fetchStateRegistro}
          />
        </div>
      </div>
      <ToastContainer />
    </div>
  );
};
export default panelTemplate;
