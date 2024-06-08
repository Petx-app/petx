import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { validar } from "./funLogin";
import { useRouter } from "next/router";
import { useEmail } from "@/context/emailContext";
import LogoFlip from "./../../../public/logo-flip-amarelo.svg";
import { ToastContainer, toast } from "react-toastify";

type DataInput = {
  email: string;
};

const ValidarEmail = ({ onTenhoContaClick }) => {
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
      router.push({
        pathname: "/cadastrar",
      });
      reset();
    } catch (e) {
      setButtonLoading(false);
      toast.error(e.message)
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
            <label
              className="block text-xl mb-2 text-custom-blue"
              htmlFor="email"
            >
              Email
            </label>
            <input
              className="w-full h-12 px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-custom-blue"
              type="text"
              id="email"
              placeholder="Insira seu email"
              {...register("email", {
                required: "O campo de e-mail é obrigatório",
                pattern: {
                  value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
                  message: "Por favor, insira um e-mail válido",
                },
              })}
            />

            {errors.email && (
              <p className="pt-2 text-red-500">{errors.email.message}</p>
            )}
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
      <ToastContainer/>
      </>
  );
};

export default ValidarEmail;
