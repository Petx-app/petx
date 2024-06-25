import { useAuth } from "@/context/authContext";
import { useRouter } from "next/router";
import GerarQRCode from "./components/popularbanco";
import PopularBanco from "./components/popularbanco";
import { useEffect, useState } from "react";
import FinalizarQRCode from "./components/finalizarQRCode/index";
import RegistroPets from "./components/registroPet";
import { ToastContainer } from "react-toastify";

const main = () => {
  const router = useRouter();
  const { auth } = useAuth();
  const [fetchStateRegistro, setFetchStateRegistro] = useState<boolean>(false);

  useEffect(() => {
    if (auth === false) {
      router.push("/admin/login");
    }
  }, [auth, router]);

  if (auth === null) {
    return <div>Carregando...</div>;
  }

  const handleStateRegistros = () => {
    setFetchStateRegistro(!fetchStateRegistro);
  };

  return (
    <div className="w-screen h-screen flex flex-col p-5">
      <h1 className="font-bold text-3xl pl-10 mb-5">Administrador</h1>

      <div className="w-full h-auto flex px-10 gap-5">
        <div className="w-3/4 h-auto flex flex-col gap-5">
          <RegistroPets fetchState={fetchStateRegistro} />

          <div className="w-1/2 h-auto flex">
            <PopularBanco fetchRegistros={handleStateRegistros} />
          </div>
        </div>
        <div className="w-1/2 h-auto flex">
          <FinalizarQRCode
            fetchRegistros={handleStateRegistros}
            fetchState={fetchStateRegistro}
          />
        </div>
      </div>
      <ToastContainer />
    </div>
  );
};
export default main;
