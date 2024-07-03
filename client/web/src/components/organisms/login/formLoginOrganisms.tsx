import { useForm } from "react-hook-form";
import { autenticar } from "@/services/api/login/loginService";
import { ToastContainer, toast } from "react-toastify";
import LabeledInput from "@/components/molecules/labeledinput";
import { useRouter } from "next/router";

type DataInput = {
  email: string;
  senha: string;
};

const FormLoginOrganisms = ({ onCriarContaClick }) => {
  const route = useRouter();
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<DataInput>();

  const onSubmit = async (data: any) => {
    try {
      await autenticar(data);
      route.push('/dashboard')
    } catch (e) {
      toast.error(e.message);
    }
  };

  return (
    <>
      <div className="relative w-full lg:w-2/3 xl:w-1/2 h-full flex flex-col bg-glass-blue backdrop-blur-md justify-center items-center sm:rounded-xl lg:rounded-l-xl lg:rounded-none">
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
              error={errors.email}
            />
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
              maxLength={20}
            />
          </div>

          <p className="text-xs font-semibold font-roboto text-custom-blue cursor-pointer">
            Esqueci minha senha
          </p>

          <button
            className="w-full h-12 bg-custom-blue text-m font-roboto rounded-md text-custom-yellow mt-7 mb-4"
            type="submit"
          >
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
      <ToastContainer />
    </>
  );
};

export default FormLoginOrganisms;
