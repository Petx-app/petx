import { useForm } from "react-hook-form";
import LabeledInput from "@/components/molecules/labeledinput";
import { useRouter } from "next/router";
import BackgroundLogin from "@/components/atoms/backgroundLogin";
import { autenticaUsuario, autenticarGoogleUsuario } from "./utils";
import Spinnerloading from "@/components/atoms/spinner";
import { useState } from "react";
import { GoogleLogin } from '@react-oauth/google';
import { toast } from "react-toastify";

type DataInput = {
  email: string;
  senha: string;
};

const FormLoginOrganisms = ({ setStateRender }) => {
  const [loading, setLoading] = useState<boolean>(false);
  const route = useRouter();

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<DataInput>();

  const onSubmit = async (data: any) => {
    setLoading(true);
    autenticaUsuario(data, setLoading, route);
  };

  const autenticarGoogle = async (credential: any) => {
    autenticarGoogleUsuario(credential, route)
  }

  return (
    <>
      <div
        className={`relative w-full lg:w-2/3 xl:w-1/2 h-full flex flex-col bg-glass-blue backdrop-blur-md justify-center items-center sm:rounded-xl lg:rounded-l-xl lg:rounded-none transition-transform duration-300 ease-in-out`}
      >
        <h1 className="mb-5 lg:mb-0 w-4/5 text-4xl font-roboto font-bold text-custom-blue">
          Login
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
              error={errors.email} textShow={""}            />
          </div>
          <div className="mb-4">
            <LabeledInput
              id={"senha"}
              label={"Senha"}
              color={"text-custom-blue"}
              fontSize={"text-xl"}
              type={"password"}
              placeholder={"Insira sua senha"}
              width={"w-full"}
              height={"h-12"}
              register={register}
              name={"senha"}
              error={errors.senha}
              maxLength={20} textShow={""}            />
          </div>
          <button
            className="text-xs font-semibold font-roboto text-custom-blue cursor-pointer"
            onClick={() => setStateRender("esqueceuSenha")}
            type="button"
          >
            Esqueci minha senha
          </button>

          <button
            className="w-full h-12 bg-custom-blue text-m font-roboto rounded-md text-custom-yellow mt-2 mb-4 flex justify-center items-center"
            type="submit"
          >
            {loading ? (
              <Spinnerloading cor={"amarelo"} width={"w-5"} />
            ) : (
              "Entrar"
            )}
          </button>

          <div className="w-full flex md:flex-row flex-col gap-4">
            <button
              className="md:w-1/2 w-full h-10 bg-custom-yellow text-m font-roboto rounded-sm"
              onClick={() => setStateRender("validarEmail")}
            >
              Criar uma conta
            </button>
            <GoogleLogin
              onSuccess={credentialResponse => {
                autenticarGoogle(credentialResponse.credential)
              }}
              onError={() => {
                toast.success("Erro ao se conectar com Google.")
              }}
              useOneTap
              type="standard"
              theme="outline"
              shape="square"
              size="large"
              />
          </div>
        </form>
      </div>
      <BackgroundLogin />
    </>
  );
};

export default FormLoginOrganisms;
