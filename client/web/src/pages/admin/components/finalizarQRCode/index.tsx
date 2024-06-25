import QRCode from "qrcode.react";
import {
  finalizarQRCode,
  consultarQRCodeDisponiveis,
} from "./funFinalizarQRCode";
import { toast } from "react-toastify";
import { useEffect, useRef, useState } from "react";

const FinalizarQRCode = ({ fetchRegistros, fetchState }) => {
  const [link, setLink] = useState<String>();
  const [uuid, setUuid] = useState<String>();
  const [handleState, setHandleState] = useState<Boolean>(false);
  const qrCodeRef = useRef();

  const url = "www.petx.com.br/";

  useEffect(() => {
    const consultarUUIDDisponivel = async () => {
      try {
        const response = await consultarQRCodeDisponiveis();
        setLink(url + response[0].uuidqrcode);
        setUuid(response[0].uuidqrcode);
      } catch (e) {
        setUuid("");
      }
    };
    consultarUUIDDisponivel();
  }, [handleState, fetchState]);

  const cadastrarUUID = async () => {
    try {
      await finalizarQRCode(uuid);
      toast.success("QRCode finalizado");
      fetchRegistros();
    } catch (e) {
      toast.error(e.message);
    }
  };

  const handleDownload = () => {
    const canvas = qrCodeRef.current.querySelector("canvas");
    const pngUrl = canvas
      .toDataURL("image/png")
      .replace("image/png", "image/octet-stream");
    const downloadLink = document.createElement("a");
    downloadLink.href = pngUrl;
    downloadLink.download = `${uuid}.png`;
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
  };

  return (
    <>
      <div className="w-full h-full flex justify-between flex-col font-roboto border border-custom-blue border-solid p-5 rounded-2xl shadow-md">
        {uuid ? (
          <div className="w-full flex justify-between">
            <div ref={qrCodeRef}>
              <QRCode value={link} size={160} />
            </div>

            <div className="flex flex-col h-auto justify-between items-center">
              <div className="flex flex-col">
                <p className="font-bold text-xl">UUID</p>
                <p className="text-zinc-500">{uuid}</p>
              </div>
              <button
                onClick={handleDownload}
                className="w-3/4 h-10 bg-stone-200 rounded-xl text-black"
              >
                Baixar QRCode
              </button>
            </div>
          </div>
        ) : (
          <div className="w-full flex justify-center items-center">
            <p className="text-center">
              qrcode Indisponivel
              <br />
              cadastre!
            </p>
          </div>
        )}

        {uuid && (
          <button
            className="font-bold text-2xl w-full h-10 mt-10 flex justify-center items-center border border-custom-blue border-solid p-5 rounded-xl"
            onClick={cadastrarUUID}
          >
            Finalizar
          </button>
        )}
      </div>
    </>
  );
};

export default FinalizarQRCode;
