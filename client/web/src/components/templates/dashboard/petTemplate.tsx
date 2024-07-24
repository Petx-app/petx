import MessageNotPet from "@/components/atoms/messageNotPet";
import TitleTemplate from "@/components/atoms/titleTemplate";
import ShowPetsOrganisms from "@/components/organisms/pet/showPetsOrganisms";
import { useEffect, useState } from "react";
import Loading from "./../../../../public/logo-flip-amarelo.png";
import { consultaListPet } from "./utils";

const PetTemplate = () => {
  const [listPets, setListPets] = useState([]);
  const [fetchPets, setFetchPets] = useState(false);
  const [stateRender, setStateRender] = useState<string>("Loading");

  useEffect(() => {
    consultaListPet(setListPets, setStateRender);
  }, [fetchPets]);

  const renderComponent = () => {
    switch (stateRender) {
      case "NotPet":
        return <MessageNotPet />;
      case "PetPresent":
        return (
          <ShowPetsOrganisms
            listPets={listPets}
            fetchPets={fetchPets}
            setFetchPets={setFetchPets}
          />
        );
      default:
        return <img src={Loading.src} className="w-10 h-10 animation-spin" />;
    }
  };
  return (
    <div className="w-full min-h-screen bg-slate-50 font-roboto p-2 pt-10">
      <TitleTemplate titleText={"Área do seu pet"} />
      <div className="w-full min-h-fit flex p-3 ">{renderComponent()}</div>
    </div>
  );
};

export default PetTemplate;
