import QrcodeCadastrarPetOrganisms from "@/components/organisms/qrcode/cadastrarPetOrganisms";
import QrcodePetCadastradoOrganisms from "@/components/organisms/qrcode/petCadastradoOrganisms";
import QrcodeInvalidoOrganisms from "@/components/organisms/qrcode/qrcodeInvalidoOrganisms";
import LogoPetx from "./../../../../public/logo-petx-blue.png";
import { useEffect, useState } from "react";
import { consultaQRCode } from "@/services/api/qrcode/qrcodeService";

const QRCodeTemplate = ({ tag }) => {
  const [dadosPet, setDadosPet] = useState();
  const [stateRender, setStateRender] = useState<number>();

  useEffect(() => {
    const consultarQRCode = async (tag) => {
      try {
        const response = await consultaQRCode(tag);
        setDadosPet(response);
        setStateRender(1);
      } catch (e) {
        if (e.message == "pet nao cadastrado") {
          setStateRender(2);
        }
        if (e.message == "pet nao encontrado") {
          setStateRender(3);
        }
      }
    };
    if (tag) {
      consultarQRCode(tag);
    }
  }, [tag]);

  const renderComponent = () => {
    switch (stateRender) {
      case 1:
        return <QrcodePetCadastradoOrganisms dadosPet={dadosPet} />;
      case 2:
        return <QrcodeCadastrarPetOrganisms uuid={tag} logo={LogoPetx.src} />;
      case 3:
        return <QrcodeInvalidoOrganisms logo={LogoPetx.src} />;
      default:
        return (
          <div className="w-full h-full flex justify-center items-center">
            <p className="text-custom-blue">Carregando...</p>
          </div>
        );
    }
  };
  return (
    <>
      <div className="relative w-full h-screen dark:bg-white bg-[url('/background-petx.png')] bg-repeat bg-cover bg-top flex justify-center items-center px-1">
        <div className="w-full md:w-1/2 lg:w-1/3 h-3/4 bg-glass-blue backdrop-blur-md flex rounded-xl p-4 md:p-0">
          {renderComponent()}
        </div>
      </div>
    </>
  );
};

export default QRCodeTemplate;
