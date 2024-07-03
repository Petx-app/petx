import { FaExclamationCircle } from "react-icons/fa";

const QrcodeInvalidoOrganisms = ({ logo }) => {
  return (
    <div className="w-full h-full p-4 md:p-5 flex flex-col justify-start items-center gap-4 md:gap-6 font-roboto">
      <img src={logo} alt="Logo" className="w-3/4 md:w-1/2" />

      <div className="w-full md:w-4/5 h-auto bg-custom-blue flex flex-col justify-center items-center p-4 md:p-5 rounded-md">
        <FaExclamationCircle color="#F5CF46" size={70} />
        <p className="text-white text-lg md:text-3xl mt-4 md:mt-6">
          QRCODE Inválido!
        </p>
      </div>
    </div>
  );
};

export default QrcodeInvalidoOrganisms;
