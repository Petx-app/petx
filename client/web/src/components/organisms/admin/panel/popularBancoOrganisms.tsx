import { useState } from "react";
import { toast } from "react-toastify";
import { gerarQRCode } from "../utils";

const PopularBancoOrganisms = ({ setFetchStateRegistro }) => {
  const [qtd, setQtd] = useState<number>(0);

  const handleGerarQRCode = async () => {
    if (qtd > 0) {
      gerarQRCode(qtd, setQtd, setFetchStateRegistro);
    } else {
      toast.error("Cadastre um número válido");
    }
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const inputValue = e.target.value;
    const numericValue = inputValue.replace(/[^0-9]/g, "");
    setQtd(Number(numericValue));
  };

  return (
    <div className="w-full h-auto flex flex-col border border-custom-blue border-solid p-5 rounded-2xl shadow-md">
      <h2 className="text-xl mb-3">Registrar pet no banco:</h2>
      <label htmlFor="" className="text-sm mb-2">
        Quantidade:
      </label>
      <div className="flex flex-col sm:flex-row items-center sm:items-start">
        <div className="w-full sm:w-auto h-auto flex rounded-sm mb-3 sm:mb-0">
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
            className="w-full text-center md:text-start sm:w-1/2 h-10 px-3 py-2 border border-slate-200 focus:outline-none focus:ring-2 dark:bg-black white:bg-white"
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
          className="w-full sm:w-1/3 bg-custom-blue p-2 sm:ml-2 rounded-md text-white"
          onClick={handleGerarQRCode}
        >
          Gerar
        </button>
      </div>
    </div>
  );
};

export default PopularBancoOrganisms;
