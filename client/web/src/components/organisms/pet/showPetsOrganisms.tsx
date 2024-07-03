import { useEffect, useState } from "react";
//import { cachorro } from "../../../../public/cachorro_teste.jpeg"
import CardPet from "@/components/molecules/pet/cardPet";
import { consultarListaPet } from "@/services/api/pet/petService";

const ShowPetsOrganisms = () => {
  const [listPets, setListPets] = useState([]);

  useEffect(() => {
    const consultPet = async () => {
      try{
        const pets = (await consultarListaPet());
        setListPets(pets || []); 
      }catch(e){
        console.log(e)
      }
    }
    consultPet();
  }, []);

  return (
    <div className="flex flex-wrap gap-2 p-2">
      {listPets.map((pet, index) => (
        <CardPet 
        key={index}
        uuid={pet.uuid}
        nome={pet.nome}
        raca={pet.raca}
        />
      ))}
      
    </div>
  );
};

export default ShowPetsOrganisms;
