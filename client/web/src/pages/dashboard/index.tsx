import NavbarOrganisms from "@/components/organisms/navbar/navBarOrganisms";
import HomeTemplate from "@/components/templates/dashboard/homeTemplate";
import PetTemplate from "@/components/templates/dashboard/petTemplate";
import ProfileTemplate from "@/components/templates/dashboard/profileTemplate";
import { useEffect, useState } from "react";

const Dashboard = () => {
  const [nameTemplate, setNameTemplate] = useState<String>("home");

  const renderTemplate = () => {
    switch (nameTemplate) {
      case "home":
        return <HomeTemplate />;
      case "profile":
        return <ProfileTemplate />;
      case "pet":
        return <PetTemplate />;
      default:
        return <h1>Vazio</h1>;
    }
  };

  return (
    <div className="w-full h-full flex">
      <NavbarOrganisms nome={"iago"} handleStateTemplate={setNameTemplate} />
      {renderTemplate()}
    </div>
  );
};

export default Dashboard;
