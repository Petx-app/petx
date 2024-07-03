import { useState } from "react";
import { RiMenu4Line } from "react-icons/ri";
import {
  AiOutlineHome,
  AiOutlineUser,
  AiOutlineUsergroupAdd,
} from "react-icons/ai";
import { RxExit } from "react-icons/rx";
import { IoMdPaw } from "react-icons/io";
import { useRouter } from "next/router";

const NavbarOrganisms = ({ nome, handleStateTemplate }) => {
  const [navBarWidthState, setNavBarWidthState] = useState<Boolean>(false);
    const router = useRouter();

  return (
    <div
      className={`bg-custom-blue-2 h-screen flex flex-col items-center pt-10 gap-10 transition-width duration-500 ease-in-out ${navBarWidthState ? "w-1/5" : "w-1/12"}`}
    >
      <button onClick={() => setNavBarWidthState(!navBarWidthState)}>
        <RiMenu4Line size={26} />
      </button>

      <button
        onClick={() => handleStateTemplate("home")}
        className="flex flex-col justify-center items-center gap-1"
      >
        <AiOutlineHome size={24} />
        {navBarWidthState && <h3>Home</h3>}
      </button>

      <button
        onClick={() => handleStateTemplate("profile")}
        className="flex flex-col justify-center items-center gap-1"
      >
        <AiOutlineUser size={24} />
        {navBarWidthState && <h3>{nome}</h3>}
      </button>

      <button
        onClick={() => handleStateTemplate("pet")}
        className="flex flex-col justify-center items-center gap-1"
      >
        <IoMdPaw size={24} />
        {navBarWidthState && <h3 className="nav-text-effect">Pet</h3>}
      </button>

      <button
        onClick={() => router.push("/login")}
        className="flex flex-col justify-center items-center gap-1"
      >
        <RxExit size={24} className="transform rotate-180 mr-2" />
        {navBarWidthState && <h3 className="nav-text-effect">Sair</h3>}
      </button>
    </div>
  );
};

export default NavbarOrganisms;
