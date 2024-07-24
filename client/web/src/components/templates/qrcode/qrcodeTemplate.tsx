import QrcodeCadastrarPetOrganisms from "@/components/organisms/qrcode/cadastrarPetOrganisms";
import QrcodePetCadastradoOrganisms from "@/components/organisms/qrcode/petCadastradoOrganisms";
import QrcodeInvalidoOrganisms from "@/components/organisms/qrcode/qrcodeInvalidoOrganisms";
import LogoPetx from "./../../../../public/logo-petx-blue.png";
import { useEffect, useState } from "react";
import { consultaQRCode } from "./utils";
import Spinnerloading from "@/components/atoms/spinner";
import { UUID } from "crypto";

const QRCodeTemplate = ({ tag }: any) => {
  const [dadosPet, setDadosPet] = useState<string>("");
  const [stateRenderQRCode, setStateRenderQRCode] = useState<string>("");

  useEffect(() => {
    consultaQRCode(tag, setDadosPet, setStateRenderQRCode);
  }, [tag]);

  return (
    <>
      <div className="relative w-full h-screen dark:bg-white bg-[url('/background-petx.png')] bg-repeat bg-cover bg-top flex justify-center items-center px-1">
        <div className="w-full md:w-1/2 lg:w-1/3 min-h-3/4 bg-glass-blue backdrop-blur-md flex rounded-xl p-4 md:p-0 justify-center">
          {(() => {
            switch (stateRenderQRCode) {
              case "petEncontrado":
                return <QrcodePetCadastradoOrganisms dadosPet={dadosPet} />;
              case "cadastrarPet":
                return (
                  <QrcodeCadastrarPetOrganisms uuid={tag} logo={LogoPetx.src} />
                );
              case "petInvalido":
                return <QrcodeInvalidoOrganisms logo={LogoPetx.src} />;
              default:
                return <Spinnerloading cor={"azul"} width={"w-12"} />;
            }
          })()}
        </div>
      </div>
    </>
  );
};

export default QRCodeTemplate;
