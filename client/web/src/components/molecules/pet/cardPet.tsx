import { useEffect, useState } from "react";
import {
  TbSquareRoundedArrowDownFilled,
  TbSquareRoundedArrowUpFilled,
} from "react-icons/tb";

interface Pet {
  uuid: string;
  imagem: string;
  nome: string;
  raca: string;
  handleShowCardPet: () => void;
}

const CardPet = ({ imagem, uuid, nome, raca, handleShowCardPet }: Pet) => {
  const [renderContentPet, setRenderContentPet] = useState(false);
  return (
    <div
      className={`relative w-72 flex flex-col h-fit rounded-2xl overflow-hidden bg-white border-b-2 border-custom-yellow shadow-md${renderContentPet ? "shadow-lg" : "shadow-none"}`}
    >
      <img
        src={imagem}
        alt="pet"
        className={`w-full h-40 object-cover`}
        onClick={() => {
          setRenderContentPet(!renderContentPet);
        }}
      />
      <button
        className="absolute -bottom-2 left-1/2 transform -translate-x-1/2 bg-transparent"
        onClick={() => {
          setRenderContentPet(!renderContentPet);
        }}
      >
        {renderContentPet ? (
          <TbSquareRoundedArrowUpFilled
            size={40}
            color="#F5CF46"
            className="animate-bounce"
          />
        ) : (
          <TbSquareRoundedArrowDownFilled
            size={40}
            color="#F5CF46"
            className="animate-bounce"
          />
        )}
      </button>

      <div
        className={`overflow-hidden transition-height duration-500 ease-in-out bg-white items-center flex flex-col ${
          renderContentPet ? "h-40" : "h-0"
        }`}
      >
        <h1 className="text-2xl font-semibold text-custom-yellow mt-2">
          {nome}
        </h1>
        <p className="text-custom-yellow ">{raca}</p>

        <button
          className="w-3/4 h-8 mt-4 bg-custom-blue-2 rounded-3xl shadow-sm"
          onClick={() => handleShowCardPet(uuid)}
        >
          Ver mais
        </button>
      </div>
    </div>
  );
};

export default CardPet;
