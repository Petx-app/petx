import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { validar } from "@/services/api/login/loginService";
import { useRouter } from "next/router";
import { useEmail } from "@/context/emailContext";
import LogoFlip from "./../../../../public/logo-flip-amarelo.svg";
import { ToastContainer, toast } from "react-toastify";
import LabeledInput from "@/components/molecules/labeledinput";
import Cookies from "js-cookie";

type DataInput = {
  email: string;
};

const FormValidarEmailOrganisms = ({ onTenhoContaClick }) => {
  const router = useRouter();
  const { setEmail } = useEmail();

  const [buttonLoading, setButtonLoading] = useState<boolean>(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<DataInput>();

  const onSubmit = async (data: any) => {
    try {
      setButtonLoading(true);
      await validar(data);
      setEmail(data.email);
      Cookies.set("email", data.email, { expires: 1 });
      router.push({
        pathname: "/cadastrar",
      });
    } catch (e) {
      setButtonLoading(false);
      toast.error(e.message);
    }
  };
  return (
    <>
      <div className="relative w-full lg:w-2/3 xl:w-1/2 h-full flex flex-col bg-glass-blue backdrop-blur-md justify-center items-center sm:rounded-xl lg:rounded-r-xl lg:rounded-none">
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
            />
          </div>
          <button
            className="w-full h-12 bg-custom-blue text-m font-roboto rounded-md text-custom-yellow mt-2 mb-4 flex justify-center items-center"
            type="submit"
          >
            {buttonLoading ? (
              <img src={LogoFlip.src} className="w-10 h-10 animate-spin " />
            ) : (
              "Cadastrar"
            )}
          </button>
        </form>
        <div className="w-4/5 flex gap-4">
          <button
            className="w-1/2 h-12 bg-custom-yellow text-m font-roboto rounded-md"
            onClick={onTenhoContaClick}
          >
            Tenho uma conta
          </button>
          <button className="w-1/2 h-12 bg-custom-yellow text-m font-roboto rounded-md">
            Criar com google
          </button>
        </div>
      </div>
      <ToastContainer />
    </>
  );
};

export default FormValidarEmailOrganisms;
