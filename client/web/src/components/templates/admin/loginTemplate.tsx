import FormLoginAdminOrganisms from "@/components/organisms/admin/login/formLoginAdminOrganisms";
import { ToastContainer } from "react-toastify";

const LoginTemplate = ({ title }) => {
  return (
    <div className="bg-custom-blue min-h-screen flex justify-center items-center p-2">
      <div className="2xl:w-2/5 xl:w-2/6 md:w-1/2 w-full md:max-w-md bg-slate-100 p-10 rounded-xl">
        <h1 className="text-custom-blue font-bold text-4xl">{title}</h1>
        <FormLoginAdminOrganisms />
      </div>
      <ToastContainer />
    </div>
  );
};

export default LoginTemplate;
