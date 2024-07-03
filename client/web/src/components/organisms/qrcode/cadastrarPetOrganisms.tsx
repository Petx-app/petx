import { useRouter } from "next/router";
import Cookies from "js-cookie";

const QrcodeCadastrarPetOrganisms = ({ uuid, logo }) => {
  const router = useRouter();

  const gravarUUID = () => {
    Cookies.set("UUID_QRCODE", uuid, { expires: 1 });
    router.push("/login");
  };

  return (
    <div className="w-full h-full p-4 md:p-5 flex flex-col font-roboto justify-start items-center gap-4 md:gap-6">
      <img src={logo} alt="Logo" className="w-3/4 md:w-1/2" />

      <h1 className="text-2xl md:text-3xl font-bold text-custom-blue border-b-4 border-custom-yellow">
        Bem vindo!
      </h1>
      <div className="w-full md:w-4/5 h-auto bg-custom-blue flex justify-center items-center p-4 md:p-5 rounded-md">
        <p className="text-white font-semibold text-sm md:text-md text-justify">
          Este QR code ainda{" "}
          <span className="bg-custom-yellow text-custom-blue p-1 rounded-sm shadow-sm">
            não
          </span>{" "}
          está registrado. <br /> Vamos começar agora mesmo e cadastrar o seu{" "}
          <span className="bg-custom-yellow text-custom-blue p-1 rounded-sm shadow-sm">
            pet?
          </span>
        </p>
      </div>

      <button
        className="bg-custom-yellow w-full md:w-4/5 h-10 md:h-12 mt-4 md:mt-5 text-white font-semibold text-lg md:text-xl rounded-md"
        onClick={gravarUUID}
      >
        Seguir
      </button>
    </div>
  );
};

export default QrcodeCadastrarPetOrganisms;
