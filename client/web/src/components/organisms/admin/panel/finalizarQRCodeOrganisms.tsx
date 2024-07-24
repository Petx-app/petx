import { useEffect, useRef, useState } from "react";
import QRCode from "qrcode.react";
import { finalizarQRCode, consultarQRCodeDisponiveis } from "../utils";

const FinalizarQRCodeOrganisms = ({
  setFetchStateRegistro,
  fetchStateRegistro,
}) => {
  const [link, setLink] = useState<string>("");
  const [uuid, setUuid] = useState<string>("");
  const qrCodeRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    consultarQRCodeDisponiveis(setLink, setUuid);
  }, [fetchStateRegistro]);

  const cadastrarUUID = () => {
    finalizarQRCode(uuid, setFetchStateRegistro);
  };

  const handleDownload = () => {
    if (qrCodeRef.current) {
      const canvas = qrCodeRef.current.querySelector("canvas");
      if (canvas) {
        const pngUrl = canvas
          .toDataURL("image/png")
          .replace("image/png", "image/octet-stream");
        const downloadLink = document.createElement("a");
        downloadLink.href = pngUrl;
        downloadLink.download = `${uuid}.png`;
        document.body.appendChild(downloadLink);
        downloadLink.click();
        document.body.removeChild(downloadLink);
      }
    }
  };

  return (
    <div className="w-full h-full flex flex-col font-roboto border border-custom-blue border-solid p-5 rounded-2xl shadow-md">
      {uuid ? (
        <div className="w-full flex flex-col sm:flex-row items-center">
          <div className="flex justify-center sm:justify-start mb-4 sm:mb-0">
            <div ref={qrCodeRef}>
              <QRCode value={link} size={160} />
            </div>
          </div>
          <div className="flex flex-col items-center sm:items-start sm:ml-4">
            <div className="flex flex-col items-start">
              <p className="font-bold text-xl mb-2">UUID</p>
              <p className="text-zinc-500">{uuid}</p>
            </div>
            <button
              onClick={handleDownload}
              className="w-full sm:w-auto h-10 mt-4 rounded-xl text-white border border-custom-blue p-2"
            >
              Baixar QRCode
            </button>
          </div>
        </div>
      ) : (
        <div className="flex justify-center items-center">
          <p className="text-center">
            QRCode Indisponível
            <br />
            Cadastre um QRCode!
          </p>
        </div>
      )}

      {uuid && (
        <button
          className="font-bold text-2xl w-full h-10 mt-6 bg-transparent rounded-xl text-white border border-custom-blue border-solid"
          onClick={cadastrarUUID}
        >
          Finalizar
        </button>
      )}
    </div>
  );
};

export default FinalizarQRCodeOrganisms;
