import { useAuth } from "@/context/authContext";
import { useRouter } from "next/router";
import { useForm } from "react-hook-form";
import LabeledInput from "@/components/molecules/labeledinput";
import { autenticar } from "../utils";

type DataInput = {
  usuario: string;
  senha: string;
};

const FormLoginAdminOrganisms = () => {
  const { isAuth } = useAuth();
  const route = useRouter();

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<DataInput>();

  const onSubmit = async (data: any) => {
    autenticar(data, route);
    isAuth(true);
  };

  return (
    <form className="w-full mt-5 h-full" onSubmit={handleSubmit(onSubmit)}>
      <div className="relative mb-6">
        <LabeledInput
          id="usuario"
          label="Usuario"
          color="text-custom-blue"
          fontSize="font-xl"
          type="text"
          placeholder="Digite seu usuario"
          register={register}
          name="usuario"
          error={errors.usuario}
          width="w-full"
          height="h-12"
        />
      </div>
      <div className="relative mb-4">
        <LabeledInput
          id="senha"
          label="Senha"
          color="text-custom-blue"
          fontSize="font-xl"
          type="password"
          placeholder="Digite sua senha"
          register={register}
          maxLength={5}
          name="senha"
          error={errors.senha}
          width="w-full"
          height="h-12"
        />
      </div>

      <button
        type="submit"
        className="w-full h-12 text-white bg-custom-blue rounded-xl"
      >
        Entrar
      </button>
    </form>
  );
};

export default FormLoginAdminOrganisms;
