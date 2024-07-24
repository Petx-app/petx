import { useState } from "react";
import { FaHome } from "react-icons/fa";
import { IoPerson } from "react-icons/io5";
import { IoIosExit, IoMdPaw } from "react-icons/io";
import { useRouter } from "next/router";
import { RiMenu4Line } from "react-icons/ri";

const NavbarOrganisms = ({ handleStateTemplate }) => {
  const [navBarWidthState, setNavBarWidthState] = useState(false);
  const router = useRouter();

  const handleButtonClick = (template) => {
    handleStateTemplate(template);
    setNavBarWidthState(false); // Fechar o menu após clicar no botão
  };

  const handleLogout = () => {
    router.push("/login");
    setNavBarWidthState(false); // Fechar o menu após clicar no botão
  };

  return (
    <div
      className={`bg-custom-blue-2  md:h-screen h-auto flex flex-col md:pt-10 md:flex-col md:items-center md:gap-10 transition-all duration-500 ease-in-out ${navBarWidthState ? "md:w-1/5 h-full " : "md:w-1/12 h-full"}`}
    >
      <button
        onClick={() => setNavBarWidthState(!navBarWidthState)}
        className={`md:h-auto h-20  ${!navBarWidthState && "max-w-fit"}`}
      >
        {!navBarWidthState ? (
          <RiMenu4Line size={26} className="flex md:ml-0 ml-3" />
        ) : (
          <RiMenu4Line size={26} className="md:flex hidden" />
        )}
        {navBarWidthState && <h3 className="md:hidden text-3xl">Fechar</h3>}
      </button>
      <div
        className={`md:w-full w-full md:flex flex-col md:gap-10 md:mt-0 justify-center items-center transition-all duration-500 ease-in-out ${navBarWidthState ? "flex h-auto" : "hidden overflow-hidden"} `}
      >
        <button
          onClick={() => handleButtonClick("home")}
          className="flex flex-col md:h-auto h-24 justify-center items-center gap-1 md:w-auto w-full md:border-none border-t border-glass-blue"
        >
          <FaHome size={24} className="md:flex hidden" />
          {navBarWidthState && <h3 className="md:text-base text-3xl">Home</h3>}
        </button>

        <button
          onClick={() => handleButtonClick("profile")}
          className="flex flex-col md:h-auto h-24 justify-center items-center gap-1 md:w-auto w-full md:border-none border-t border-glass-blue"
        >
          <IoPerson size={24} className="md:flex hidden" />
          {navBarWidthState && (
            <h3 className="md:text-base text-3xl">Perfil</h3>
          )}
        </button>

        <button
          onClick={() => handleButtonClick("pet")}
          className="flex flex-col md:h-auto h-24 justify-center items-center gap-1 md:w-auto w-full md:border-none border-t border-glass-blue"
        >
          <IoMdPaw size={24} className="md:flex hidden" />
          {navBarWidthState && (
            <h3 className="md:text-base text-3xl md:w-auto w-full ">Pet</h3>
          )}
        </button>

        <button
          onClick={handleLogout}
          className="flex flex-col md:h-auto h-24 justify-center items-center gap-1 md:w-auto w-full md:border-none border-t border-glass-blue"
        >
          <IoIosExit
            size={24}
            className="transform rotate-180 md:flex hidden"
          />
          {navBarWidthState && <h3 className="md:text-base text-3xl">Sair</h3>}
        </button>
      </div>
    </div>
  );
};

export default NavbarOrganisms;