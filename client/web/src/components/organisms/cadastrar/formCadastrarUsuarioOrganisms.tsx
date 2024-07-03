import {
  RegisterOptions,
  UseFormRegisterReturn,
  useForm,
} from "react-hook-form";
import { cadastrar } from "@/services/api/cadastrar/cadastrarService";
import { useEffect, useState } from "react";
import LabeledInput from "@/components/molecules/labeledinput";
import { toast } from "react-toastify";

type DataInput = {
  nome: string;
  email: string;
  telefone: string;
  cidade: string;
  estado: string;
  senha: string;
  senhaConfirmar: string;
};

const FormCadastrarUsuarioOrganisms = ({ email }) => {
  const [errorMessage, setErrorMessage] = useState<string>("");

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<DataInput>();

  const onSubmit = async (data: any) => {
    let error;
    if (data.senha !== data.senhaConfirmar) {
      error = "as senhas sao diferentes";
    }

    if (!error) {
      const { senhaConfirmar, ...dataSemConfirmar } = data;
      const finalData = { ...dataSemConfirmar, email };
      try {
        await cadastrar(finalData);
        alert("usuario cadastrado");
      } catch (e) {
        setErrorMessage(e.message);
        console.log(errorMessage);
      }
    } else {
      toast.error(error);
    }
  };

  return (
    <div className="w-full xl:w-1/2 h-full sm:h-4/6 xl:h-auto flex flex-col sm:flex-row shadow-lg rounded-xl justify-start items-center bg-glass-blue backdrop-blur-md p-5">
      <div className="w-full h-full flex flex-col pt-7 justify-start items-center">
        <h1 className="w-auto text-center sm:text-start text-2xl sm:text-4xl font-roboto font-bold text-custom-blue">
          Preencha as informações
        </h1>

        <form
          onSubmit={handleSubmit(onSubmit)}
          className="w-full sm:w-4/6 h-full flex flex-col mt-5 gap-2"
        >
          <LabeledInput
            id={"nome"}
            label={"Nome"}
            color={"text-custom-blue"}
            fontSize={"text-xl"}
            type={"text"}
            placeholder={"Digite seu Nome"}
            width={"w-full"}
            height={"h-10"}
            register={register}
            name={"nome"}
          />

          <LabeledInput
            id={"telefone"}
            label={"Telefone"}
            color={"text-custom-blue"}
            fontSize={"text-xl"}
            type={"text"}
            placeholder={"Digite um número que possua WhatsApp para contato"}
            width={"w-full"}
            height={"h-10"}
            register={register}
            name={"telefone"}
          />

          <LabeledInput
            id={"cidade"}
            label={"Cidade"}
            color={"text-custom-blue"}
            fontSize={"text-xl"}
            type={"text"}
            placeholder={"Digite a cidade que mora"}
            width={"w-full"}
            height={"h-10"}
            register={register}
            name={"cidade"}
          />

          <LabeledInput
            id={"estado"}
            label={"Estado"}
            color={"text-custom-blue"}
            fontSize={"text-xl"}
            type={"text"}
            placeholder={"Digite o estado que mora"}
            width={"w-full"}
            height={"h-10"}
            register={register}
            name={"estado"}
          />

          <LabeledInput
            id={"senha"}
            label={"Senha"}
            color={"text-custom-blue"}
            fontSize={"text-xl"}
            type={"password"}
            placeholder={"Digite uma senha"}
            width={"w-full"}
            height={"h-10"}
            register={register}
            name={"senha"}
          />

          <LabeledInput
            id={"senhaConfirmar"}
            label={"Confirmar senha"}
            color={"text-custom-blue"}
            fontSize={"text-xl"}
            type={"password"}
            placeholder={"Digite a senha novamente"}
            width={"w-full"}
            height={"h-10"}
            register={register}
            name={"senhaConfirmar"}
          />

          <div className="w-full h-full flex justify-end items-center mt-5">
            <button
              type="submit"
              className="w-full h-12 bg-custom-yellow rounded-xl text-xl text-custom-blue"
            >
              Cadastrar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default FormCadastrarUsuarioOrganisms;
