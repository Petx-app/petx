import { useForm } from "react-hook-form";
import { autenticar } from "./funLogin";
import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";

type DataInput = {
  email: string;
  senha: string;
};

const Entrar = ({ onCriarContaClick }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<DataInput>();

  const onSubmit = async (data: any) => {
    try{
      await autenticar(data);
      alert("autenticado")
    }catch(e){
      toast.error(e.message)
    }
  }

  return (
    <>
    <div className="relative w-full lg:w-2/3 xl:w-1/2 h-full flex flex-col bg-glass-blue backdrop-blur-md justify-center items-center sm:rounded-xl lg:rounded-l-xl lg:rounded-none">
      <h1 className="mb-5 lg:mb-0 w-4/5 text-4xl font-roboto font-bold text-custom-blue">
        Login
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
        <div className="mb-4">
          <label
            className="block text-xl mb-2 text-custom-blue"
            htmlFor="password"
          >
            Senha
          </label>
          <input
            className="w-full h-12 px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-custom-blue"
            type="password"
            id="password"
            placeholder="Insira sua senha"
            {...register("senha", {
              required: "O campo de senha é obrigatório"
            })}
          />
          {errors.senha && (
              <p className="pt-2 text-red-500">{errors.senha.message}</p>
            )}
        </div>
        
        <p className="text-xs font-semibold font-roboto text-custom-blue cursor-pointer">
          Esqueci minha senha
        </p>

        <button className="w-full h-12 bg-custom-blue text-m font-roboto rounded-md text-custom-yellow mt-7 mb-4"
        type="submit">
          Entrar
        </button>

        <div className="w-full flex gap-4">
          <button
            className="w-1/2 h-12 bg-custom-yellow text-m font-roboto rounded-md"
            onClick={onCriarContaClick}
          >
            Criar uma conta
          </button>
          <button className="w-1/2 h-12 bg-custom-yellow text-m font-roboto rounded-md">
            Entrar com google
          </button>
        </div>
      </form>
    </div>
    <ToastContainer/>
    </>
  );
};

export default Entrar;
