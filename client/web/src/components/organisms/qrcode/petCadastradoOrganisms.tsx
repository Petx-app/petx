const QrcodePetCadastradoOrganisms = ({ dadosPet }) => {
  return (
    <div className="h-full w-full flex flex-col font-roboto py-6 md:py-10 justify-center items-center">
      <h1 className="text-custom-blue font-bold text-2xl md:text-3xl">
        Dados do pet:
      </h1>

      <div className="h-full w-full flex flex-col justify-start items-center mt-4 md:mt-5 gap-4 md:gap-10">
        <div className="w-full md:w-3/4 h-auto flex flex-col justify-center">
          <label
            className="w-full text-white font-semibold text-sm md:text-md"
            htmlFor="nome do pet"
          >
            Nome do {dadosPet.especie}
          </label>
          <div className="w-full h-10 flex justify-start items-center bg-white px-3 rounded-md">
            <p className="text-custom-blue text-lg md:text-xl font-semibold">
              {dadosPet.nomePet}
            </p>
          </div>
        </div>
        <div className="w-full md:w-3/4 h-auto flex flex-col justify-center">
          <label
            className="w-full text-white font-semibold text-sm md:text-md"
            htmlFor="nome do pet"
          >
            Nome do dono:
          </label>
          <div className="w-full h-10 flex justify-start items-center bg-white px-3 rounded-md">
            <p className="text-custom-blue text-lg md:text-xl font-semibold">
              {dadosPet.nomeDono}
            </p>
          </div>
        </div>
        <div className="w-full md:w-3/4 h-auto flex flex-col justify-center">
          <label
            className="w-full text-white font-semibold text-sm md:text-md"
            htmlFor="nome do pet"
          >
            Telefone de contato:
          </label>
          <div className="w-full h-10 flex justify-start items-center bg-white px-3 rounded-md">
            <p className="text-custom-blue text-lg md:text-xl font-semibold">
              {dadosPet.telefoneDono}
            </p>
          </div>
        </div>
      </div>

      <div className="w-full md:w-3/4 h-auto bg-custom-blue flex justify-center items-center p-4 md:p-5 rounded-md mt-4 md:mt-6">
        <p className="text-white font-semibold text-sm md:text-md text-justify">
          Pet identificado! Por favor, entre em contato com{" "}
          <span className="bg-custom-yellow text-custom-blue p-1 rounded-sm shadow-sm">
            {dadosPet.nomeDono}
          </span>{" "}
          através dos detalhes acima e informe que o encontrou{" "}
          <span className="bg-custom-yellow text-custom-blue p-1 rounded-sm shadow-sm">
            {dadosPet.nomePet}
          </span>
        </p>
      </div>
    </div>
  );
};

export default QrcodePetCadastradoOrganisms;
