import TitleTemplate from "@/components/atoms/titleTemplate";
import FormProfileOrganisms from "@/components/organisms/profile/formProfileOrganisms";
import FormPasswordProfileOrganisms from "@/components/organisms/profile/formPasswordProfileOrganims";
import MenuProfileOrganisms from "@/components/organisms/profile/menuProfileOrganisms";
import { useState } from "react";
import { ToastContainer } from "react-toastify";
import DeleteUserProfileOrganisms from "@/components/organisms/profile/deleteUserProfileOrganisms";

const ProfileTemplate = ({stateRenderProfile}) => {
  const [stateRender, setStateRender] = useState<string>(stateRenderProfile);

  const renderBox = () => {
    switch (stateRender) {
      case "menu":
        return <MenuProfileOrganisms setStateRender={setStateRender} />;
      case "formProfile":
        return <FormProfileOrganisms setStateRender={setStateRender} />;
      case "formPasswordProfile":
        return <FormPasswordProfileOrganisms setStateRender={setStateRender} />;
      case "deleteUser":
        return <DeleteUserProfileOrganisms setStateRender={setStateRender} />;
    }
  };

  return (
    <div className="w-full min-h-screen bg-slate-50 font-roboto p-2 pt-10">
      <TitleTemplate titleText="Perfil" />
      <div className="w-full h-auto flex justify-center items-center pt-10">
        {renderBox()}
      </div>
      <ToastContainer />
    </div>
  );
};

export default ProfileTemplate;
