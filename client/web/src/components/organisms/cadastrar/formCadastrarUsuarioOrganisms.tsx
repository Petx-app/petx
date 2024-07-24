import {
  RegisterOptions,
  UseFormRegisterReturn,
  useForm,
} from "react-hook-form";
import { cadastrar } from "@/services/api/cadastrar/cadastrarService";
import { useEffect, useState } from "react";
import LabeledInput from "@/components/molecules/labeledinput";
import { toast } from "react-toastify";
import { useRouter } from "next/router";
import { cadastrarUsuario } from "./utils";

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
  const route = useRouter();

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
      cadastrarUsuario(finalData, route);
    } else {
      setErrorMessage(error);
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
            error={errors.nome}
            textShow={""}
          />

          <LabeledInput
            id={"telefone"}
            label={"Telefone"}
            color={"text-custom-blue"}
            fontSize={"text-xl"}
            type={"telephone"}
            placeholder={"Digite um número que possua WhatsApp para contato"}
            width={"w-full"}
            height={"h-10"}
            register={register}
            name={"telefone"}
            maxLength={11}
            minLength={11}
            textShow={""}
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
            error={errors.cidade}
            textShow={""}
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
            maxLength={25}
            error={errors.estado}
            textShow={""}
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
            minLength={4}
            maxLength={50}
            error={errors.senha}
            textShow={""}
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
            minLength={4}
            maxLength={50}
            error={errors.senhaConfirmar}
            textShow={""}
          />
          {errorMessage && <p className="text-red-400"> {errorMessage}</p>}

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
