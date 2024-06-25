import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import { gerarQRCode } from "./funPopularBanco";

const PopularBanco = ({ fetchRegistros }) => {
  const [qtd, setQtd] = useState<number>(0);

  const handleGerarQRCode = async () => {
    if (qtd > 0) {
      try {
        await gerarQRCode(qtd);
        toast.success(qtd + " QRCode gerado(s)");
        setQtd(0);
        fetchRegistros();
      } catch (e: any) {
        toast.error(e.message);
      }
    } else {
      toast.error("Cadastre um numero valido");
    }
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const inputValue = e.target.value;
    const numericValue = inputValue.replace(/[^0-9]/g, "");
    setQtd(Number(numericValue));
  };

  return (
    <>
      <div className="w-full h-auto flex flex-col border border-custom-blue border-solid p-5 rounded-2xl shadow-md">
        <h2 className=" text-xl">Registrar pet no banco:</h2>
        <label htmlFor="" className="text-sm">
          Quantidade:
        </label>
        <div className="flex">
          <div className="w-auto h-auto flex rounded-sm">
            <button
              onClick={() => {
                if (qtd > 0) setQtd(qtd - 1);
              }}
              className="bg-slate-200 w-10 text-black rounded-l-md"
            >
              -
            </button>
            <input
              type="text"
              className="w-1/2 h-10 px-3 py-2 border border-slate-200 focus:outline-none focus:ring-2 dark:bg-black white:bg-white "
              value={qtd}
              onChange={handleInputChange}
            />
            <button
              onClick={() => {
                setQtd(qtd + 1);
              }}
              className="bg-slate-200 w-10 text-black rounded-r-md"
            >
              +
            </button>
          </div>
          <button
            className="w-1/3 bg-custom-blue p-2 ml-2 rounded-md text-white"
            onClick={handleGerarQRCode}
          >
            Gerar
          </button>
        </div>
      </div>
    </>
  );
};

export default PopularBanco;
