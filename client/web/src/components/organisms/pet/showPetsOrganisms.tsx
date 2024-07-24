import { useEffect, useState } from "react";
import CardPet from "@/components/molecules/pet/cardPet";
import FormPetOrganisms from "./formPetOrganisms";
import DeletePetOrganisms from "./deletePetOrganisms";
import { buscarPet } from "./utils";

const ShowPetsOrganisms = ({ listPets, fetchPets, setFetchPets }) => {
  const porta = process.env.NEXT_PUBLIC_BACK_APP_API_URL;
  const [stateRender, setStateRender] = useState<boolean>(true);
  const [pet, setPet] = useState({});

  const showCardPet = async (uuid: string) => {
    buscarPet(uuid, setPet, stateRender, setStateRender);
  };

  return (
    <div className="flex flex-wrap gap-2 min-h-min w-full justify-center">
      {stateRender ? (
        listPets.map((pet) => (
          <CardPet
            key={pet.uuid}
            imagem={porta + pet.imagem}
            uuid={pet.uuid}
            nome={pet.nome}
            raca={pet.raca}
            handleShowCardPet={showCardPet}
          />
        ))
      ) : (
        <>
          <FormPetOrganisms
            titleFormPet={pet.nome}
            uuid={pet.uuid}
            imagemPet={porta + pet.imagem}
            updateImage={true}
            petUpdate={pet}
            setStateRender={setStateRender}
            fetchPets={fetchPets}
            setFetchPets={setFetchPets}
          />
          <DeletePetOrganisms
            nome={pet.nome}
            uuid={pet.uuid}
            setStateRender={setStateRender}
            fetchPets={fetchPets}
            setFetchPets={setFetchPets}
          />
        </>
      )}
    </div>
  );
};

export default ShowPetsOrganisms;
