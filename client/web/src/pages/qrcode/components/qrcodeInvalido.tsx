import logo from "./../../../../public/logo-petx-blue.png";
import { FaExclamationCircle } from "react-icons/fa";

const QrcodeInvalido = () => {
  return (
    <div className="w-full h-full flex flex-col justify-start items-center p-5 gap-5 font-roboto">
      <img src={logo.src} alt="" className="w-1/2" />
      <div className="w-4/5 h-auto bg-custom-blue flex flex-col gap-3 mt-8 justify-center items-center p-5 rounded-md">
        <FaExclamationCircle color="#F5CF46" size={70} />
        <p className="text-white text-3xl"> QRCODE Invalido !</p>
      </div>
    </div>
  );
};

export default QrcodeInvalido;
