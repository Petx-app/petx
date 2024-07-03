import ShowPetsOrganisms from "@/components/organisms/pet/showPetsOrganisms";

const PetTemplate = () => {
  return (
    <div className="w-full h-screen bg-slate-50 font-roboto p-5 pt-10">
      <h1 className="text-custom-yellow font-bold text-4xl">Area do seu Pet</h1>
      <div className="w-full flex p-3">
        <ShowPetsOrganisms />
      </div>
    </div>
  );
};

export default PetTemplate;
