import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { consultaQRCode } from "./funConsultarQRCode";
import QrcodeCadastrarPet from "./components/cadastrarPet";
import QrcodeInvalido from "./components/qrcodeInvalido";
import QrcodePetCadastrado from "./components/petCadastrado";

const QRCode = () => {
  const router = useRouter();
  const { tag } = router.query;

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
        return <QrcodePetCadastrado dadosPet={dadosPet} />;
      case 2:
        return <QrcodeCadastrarPet uuid={tag} />;
      case 3:
        return <QrcodeInvalido />;
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
      <div className="relative w-full h-screen dark:bg-white bg-[url('/background-petx.png')] bg-repeat bg-cover bg-top flex justify-center items-center">
        <div className="w-1/3 h-3/4 bg-glass-blue backdrop-blur-md flex rounded-xl">
          {renderComponent()}
        </div>
      </div>
    </>
  );
};

export default QRCode;
