import BackgroundLogin from "@/components/atoms/backgroundLogin";
import LabeledInput from "@/components/molecules/labeledinput";
import { useEmail } from "@/context/emailContext";
import { useRouter } from "next/router";
import { useState } from "react";
import { useForm } from "react-hook-form";
import LogoFlip from "./../../../../public/logo-flip-amarelo.svg";
import { enviarEmailDeValidacao } from "./utils";

type DataInput = {
  email: string;
};

const FormValidarEmailOrganisms = ({ setStateRender }) => {
  const [loading, setLoading] = useState<boolean>(false);
  const route = useRouter();
  const { setEmail } = useEmail();

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<DataInput>();

  const onSubmit = async (data: any) => {
    setLoading(true);
    setEmail(data.email);
    enviarEmailDeValidacao(data, setLoading, route);
  };

  return (
    <>
      <BackgroundLogin />
      <div className="relative w-full lg:w-2/3 xl:w-1/2 h-full flex flex-col bg-glass-blue backdrop-blur-md justify-center items-center sm:rounded-xl lg:rounded-r-xl lg:rounded-none transition-transform duration-300 ease-in-out transform">
        <h1 className="mb-5 lg:mb-0 w-4/5 text-4xl font-roboto font-bold text-custom-blue">
          Cadastrar
        </h1>
        <form className="w-4/5 mt-5" onSubmit={handleSubmit(onSubmit)}>
          <div className="relative mb-6">
            <LabeledInput
              id={"email"}
              label={"Email"}
              color={"text-custom-blue"}
              fontSize={"text-xl"}
              type={"text"}
              placeholder={"Insira seu email"}
              width={"w-full"}
              height={"h-12"}
              register={register}
              name={"email"}
              error={errors.email} 
              textShow={""}            />
          </div>
          <button
            className="w-full h-12 bg-custom-blue text-m font-roboto rounded-md text-custom-yellow mt-2 mb-4 flex justify-center items-center"
            type="submit"
          >
            {loading ? (
              <img src={LogoFlip.src} className="w-10 h-10 animate-spin " />
            ) : (
              "Cadastrar"
            )}
          </button>
        </form>
        <div className="w-4/5 flex gap-4">
          <button
            className="w-full h-12 bg-custom-yellow text-m font-roboto rounded-md"
            onClick={() => setStateRender("login")}
          >
            Tenho uma conta
          </button>
        </div>
      </div>
    </>
  );
};

export default FormValidarEmailOrganisms;
