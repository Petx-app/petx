import { useRouter } from "next/router";
import logo from "./../../../../public/logo-petx-blue.png";
import Cookies from "js-cookie";

const QrcodeCadastrarPet = ({ uuid }) => {
  const router = useRouter();

  const gravarUUID = () => {
    Cookies.set("UUID_QRCODE", uuid, { expires: 1 });
    router.push("/login");
  };

  return (
    <div className="w-full h-full p-5 flex flex-col font-roboto justify-start items-center gap-4">
      <img src={logo.src} alt="" className="w-1/2" />

      <h1 className="text-3xl font-bold text-custom-blue border-b-4 border-custom-yellow">
        Bem vindo !
      </h1>
      <div className="w-4/5 h-auto bg-custom-blue flex justify-center items-center p-5 rounded-md">
        <p className="text-white font-semibold text-md text-justify">
          Este QR code ainda{" "}
          <span className="bg-custom-yellow text-custom-blue p-1 rounded-sm shadow-sm">
            não
          </span>{" "}
          está registrado. <br /> Vamos começar agora mesmo e cadastrar o seu{" "}
          <span className="bg-custom-yellow text-custom-blue p-1 rounded-sm shadow-sm">
            pet?{" "}
          </span>
        </p>
      </div>

      <button
        className="bg-custom-yellow w-4/5 h-12 mt-5 text-white font-semibold text-xl rounded-md"
        onClick={gravarUUID}
      >
        Seguir
      </button>
    </div>
  );
};

export default QrcodeCadastrarPet;
