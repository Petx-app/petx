import { useForm } from "react-hook-form";
import { cadastrar } from "./funCadastrar";
import { useState } from "react";

type DataInput = {
  nome: string;
  email: string;
  senha: string;
  telefone: string;
  cidade: string;
};

const FormularioCadastrar = ({ email }) => {
  const [errorMessage, setErrorMessage] = useState<string>("");

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<DataInput>();

  const onSubmit = async (data: any) => {
    data = { ...data, email };
    try {
      await cadastrar(data);
      alert("usuario cadastrado");
      reset();
    } catch (e) {
      setErrorMessage(e.message);
      console.log(errorMessage);
    }
  };

  return (
    <div className="w-full sm:w-4/6 lg:w-5/6 2xl:w-4/6 h-full sm:h-4/6 xl:h-5/6 flex flex-col sm:flex-row shadow-lg rounded-xl justify-start items-center bg-glass-blue backdrop-blur-md rounded-xl">
      <div className="h-full w-60 bg-custom-blue rounded-l-xl p-2 flex flex-col gap-y-5">
        <h1 className="text-custom-yellow font-roboto text-4xl p-5 text-center">
          Seja <br /> Bem vindo
        </h1>
      </div>

      <div className="w-full h-full flex flex-col pl-10 pt-7 justify-start">
        <h1 className="pt-2 w-full text-start text-4xl font-roboto font-bold">
          Preencha as informacoes.
        </h1>

        <form
          onSubmit={handleSubmit(onSubmit)}
          className="w-4/6 h-full flex flex-col"
        >
          <div className="mb-4">
            <label
              className="block text-xl mb-2 text-custom-blue"
              htmlFor="nome"
            >
              Nome
            </label>
            <input
              className="w-full h-12 px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-custom-blue"
              type="text"
              id="nome"
              placeholder="Insira seu nome completo"
              {...register("nome", {
                required: "O campo de nome é obrigatório",
                maxLength: 35,
              })}
            />
          </div>

          <div className="mb-4">
            <label
              className="block text-xl mb-2 text-custom-blue"
              htmlFor="email"
            >
              Email
            </label>
            <input
              className="w-full h-12 px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-neutral-300"
              type="text"
              id="email"
              value={email}
              {...register("email")}
              disabled
            />
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
                required: "O campo de senha é obrigatório",
                maxLength: 10,
              })}
            />
          </div>

          <div className="mb-4">
            <label
              className="block text-xl mb-2 text-custom-blue"
              htmlFor="email"
            >
              Telefone
            </label>
            <input
              className="w-full h-12 px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-custom-blue"
              type="text"
              id="telefone"
              placeholder="Telefone de contato"
              {...register("telefone", {
                required: "O campo de telefone é obrigatório",
                maxLength: 11,
              })}
            />
          </div>

          <div className="mb-4">
            <label
              className="block text-xl mb-2 text-custom-blue"
              htmlFor="email"
            >
              Cidade
            </label>
            <input
              className="w-full h-12 px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-custom-blue"
              type="text"
              id="cidade"
              placeholder="Cidade que reside"
              // {...register("cidade", {
              //   required: "O campo de cidade é obrigatório",
              //   maxLength: 15,
              // })}
            />
          </div>

          <div className="w-full h-full p-5 flex justify-end items-end">
            <button
              type="submit"
              className="w-4/6 h-12 bg-custom-yellow rounded-xl"
            >
              Cadastrar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default FormularioCadastrar;
