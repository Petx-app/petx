import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import { ToastContainer, toast } from "react-toastify";
import { autenticar } from "./funLogin";
import { useAuth } from "@/context/authContext";
import { useRouter } from "next/router";
import Cookies from "js-cookie";

type DataInput = {
  usuario: string;
  senha: string;
};
const Login = () => {
  const [showPassword, setShowPassword] = useState<boolean>(true);
  const { isAuth } = useAuth();
  const router = useRouter();

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<DataInput>();

  const onSubmit = async (data: any) => {
    try {
      await autenticar(data);
      isAuth(true);
      router.push({
        pathname: "/admin",
      });
    } catch (e: any) {
      toast.error(e.message);
    }
  };

  useEffect(() => {
    Cookies.remove("jwt");
  }, []);

  return (
    <div className="bg-custom-blue w-full h-screen flex justify-center items-center">
      <div className="w-2/5 m-h-2/5 flex flex-col bg-slate-50 p-10 rounded-xl">
        <h1 className="text-custom-blue font-bold text-4xl">Admin</h1>
        <form className="w-full mt-5 h-full" onSubmit={handleSubmit(onSubmit)}>
          <div className="relative mb-6">
            <label
              className="block text-xl mb-2 text-custom-blue"
              htmlFor="email"
            >
              Usuario
            </label>
            <input
              className="w-full h-12 px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-custom-blue"
              type="text"
              id="email"
              placeholder="Insira seu email"
              {...register("usuario", {
                required: "O campo de e-mail é obrigatório",
              })}
            />

            {errors.usuario && (
              <p className="pt-2 text-red-500">{errors.usuario.message}</p>
            )}
          </div>
          <div className="mb-4">
            <label
              className="block text-xl mb-2 text-custom-blue"
              htmlFor="password"
            >
              Senha
            </label>
            <div className="relative w-full h-12">
              <input
                className="w-full h-12 px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-custom-blue mr-2"
                type={showPassword ? "password" : "text"}
                id="password"
                placeholder="Insira sua senha"
                {...register("senha", {
                  required: "O campo de senha é obrigatório",
                })}
              />
              <div
                className="absolute inset-y-0 right-0 flex items-center pr-3 cursor-pointer"
                onClick={() => setShowPassword(!showPassword)}
              >
                {showPassword ? (
                  <FaEyeSlash size={20} color="#185E8D" />
                ) : (
                  <FaEye size={20} color="#185E8D" />
                )}
              </div>
            </div>
            {errors.senha && (
              <p className="pt-2 text-red-500">{errors.senha.message}</p>
            )}
          </div>

          <button
            type="submit"
            className="w-full h-12 text-white bg-custom-blue rounded-xl"
          >
            Entrar
          </button>
        </form>
      </div>
      <ToastContainer />
    </div>
  );
};

export default Login;
