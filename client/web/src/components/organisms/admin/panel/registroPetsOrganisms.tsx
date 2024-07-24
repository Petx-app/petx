import { useEffect, useState } from "react";
import { consultaRegistroPet } from "../utils";

const RegistroPetsOrganisms = ({ fetchState }) => {
  const [petsCadastrados, setPetsCadastrados] = useState<string>();
  const [QRCodeGerado, setQRCodeGerado] = useState<string>();
  const [QRCodeDisponiveis, setQRCodeDisponiveis] = useState<string>();

  useEffect(() => {
    consultaRegistroPet(
      setPetsCadastrados,
      setQRCodeGerado,
      setQRCodeDisponiveis,
    );
  }, [fetchState]);

  return (
    <div className="w-full h-auto md:h-40 flex flex-col md:flex-row justify-between items-center border border-custom-blue border-solid p-5 rounded-2xl shadow-md gap-4">
      <div className="w-full lg:w-auto h-auto flex flex-col gap-4 items-center font-roboto">
        <p>Pets Cadastrados</p>
        <p className="text-2xl lg:text-4xl">{petsCadastrados}</p>
      </div>
      <div className="w-full lg:w-auto h-auto flex flex-col gap-4 items-center">
        <p>QRCodes gerados</p>
        <p className="text-2xl lg:text-4xl">{QRCodeGerado}</p>
      </div>
      <div className="w-full lg:w-auto h-auto flex flex-col gap-4 items-center">
        <p>QRCodes disponíveis</p>
        <p className="text-2xl lg:text-4xl">{QRCodeDisponiveis}</p>
      </div>
    </div>
  );
};

export default RegistroPetsOrganisms;
