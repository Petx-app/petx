import { useEffect, useState } from "react";
import { consultarRegistroPet } from "./funRegistroPet";
import { toast } from "react-toastify";

const RegistroPets = ({ fetchState }) => {
  const [petsCadastrados, setPetsCadastrados] = useState<String>();
  const [QRCodeGerado, setQRCodeGerado] = useState<String>();
  const [QRCodeDisponiveis, setQRCodeDisponiveis] = useState<String>();

  useEffect(() => {
    const consultarRegistros = async () => {
      try {
        const response = await consultarRegistroPet();
        setPetsCadastrados(response.petCadastrado);
        setQRCodeGerado(response.qrcodeGerado);
        setQRCodeDisponiveis(response.disponivel);
      } catch (e: any) {
        toast.error(e.message);
      }
    };
    consultarRegistros();
  }, [fetchState]);

  return (
    <div className="w-full h-40 flex justify-between items-center border border-custom-blue border-solid p-5 rounded-2xl shadow-md">
      <div className="w-auto h-auto flex flex-col gap-4 items-center font-roboto">
        <p>Pets Cadastrados</p>
        <p className="text-4xl">{petsCadastrados}</p>
      </div>
      <div className="w-auto h-auto flex flex-col gap-4 items-center">
        <p>QRCodes gerados</p>
        <p className="text-4xl">{QRCodeGerado}</p>
      </div>
      <div className="w-auto h-auto flex flex-col gap-4 items-center">
        <p>QRCodes disponiveis</p>
        <p className="text-4xl">{QRCodeDisponiveis}</p>
      </div>
    </div>
  );
};

export default RegistroPets;
