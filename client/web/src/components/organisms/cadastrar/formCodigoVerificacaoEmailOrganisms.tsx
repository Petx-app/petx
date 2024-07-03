import { useState } from "react";
import { confirmEmail } from "@/services/api/cadastrar/cadastrarService";
import { useRouter } from "next/router";
import LabeledInput from "@/components/molecules/labeledinput";
import { validar } from "@/services/api/login/loginService";
import { useForm } from "react-hook-form";
import { toast } from "react-toastify";

type DataInput = {
  codigoVerificacao: string;
};

const FormCodigoVerificacaoEmailOrganisms = ({
  email,
  onConfirmSuccess,
}: string) => {
  const router = useRouter();

  const [errorMessage, setErrorMessage] = useState<string>("");
  const [buttonLoadingReenviarEmail, setButtonLoadingReenviarEmail] =
    useState<boolean>(true);
  const [timeRemaining, setTimeRemaining] = useState<number>(60);
  const [buttonValidarState, setButtonValidarState] = useState<boolean>(true);

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<DataInput>();

  const periodoReenviarEmail = () => {
    setButtonLoadingReenviarEmail(false);
    setTimeRemaining(60);
    const interval = setInterval(() => {
      setTimeRemaining((prev) => {
        if (prev === 1) {
          clearInterval(interval);
          setButtonLoadingReenviarEmail(true);
          return 0;
        }
        return prev - 1;
      });
    }, 1000);
  };

  const reenviarEmail = async () => {
    try {
      toast.success("Email enviado com sucesso!");
      periodoReenviarEmail();
      await validar({ email });
      setButtonValidarState(true);
    } catch (e) {
      console.log(e);
    }
  };

  const onSubmit = async (data: any) => {
    try {
      await confirmEmail({ ...data, email });
      setButtonLoadingReenviarEmail(true);
      onConfirmSuccess();
    } catch (e) {
      if (e.message == "Codigo incorreto") {
        setErrorMessage(e.message);
      } else if (e.message == "Codigo expirado") {
        setErrorMessage(e.message);
        setButtonValidarState(false);
      }
      if (e.message == "Erro ao salvar usuario sem autenticar") {
        router.push("/login");
      }
    }
  };

  return (
    <div className="w-full mx-auto flex flex-col justify-center items-center font-roboto p-4 md:w-1/2 sm:h-1/2">
      <h1 className="w-full text-custom-blue text-4xl font-bold mb-5 text-center">
        Verificar Email
      </h1>
      <div className="w-full h-auto flex flex-col justify-center bg-slate-50 border-2 border-solid items-center rounded-xl md:p-5 p-10 shadow gap-10">
        <p className="w-full text-neutral-500 text-start">
          Um e-mail foi enviado com um código de validação para o endereço de
          e-mail <span className="font-bold">{email}</span>
          <br />
          Por favor, insira o código de 4 dígitos para validar seu e-mail.
        </p>
        <form
          className="w-full h-auto flex flex-col justify-center items-center gap-9"
          onSubmit={handleSubmit(onSubmit)}
        >
          <div className="w-full xl:w-1/2">
            <LabeledInput
              id={"codigoVerificacao"}
              label={"Codigo de verificacao"}
              color={"text-custom-blue"}
              fontSize={"text-md"}
              type={"text"}
              placeholder={"Digite o codigo de 4 digitos"}
              width={"w-full"}
              height={"h-10"}
              register={register}
              name={"codigoVerificacao"}
              error={errors.codigoVerificacao}
              maxLength={4}
              minLength={4}
            />
            {errorMessage && (
              <p className="text-red-500 pt-2 text-center font-bold">
                {errorMessage}
              </p>
            )}
          </div>
          <div className="w-full h-12 flex flex-col sm:flex-row justify-evenly items-center">
            <div className="w-full sm:w-1/2 h-full flex flex-col justify-center items-center text-custom-blue-2">
              {buttonLoadingReenviarEmail ? (
                <button
                  onClick={reenviarEmail}
                  className="border-0 border-b-2 hover:border-custom-blue-2"
                >
                  Enviar um novo codigo
                </button>
              ) : (
                <p className="text-sm">
                  Aguarde {timeRemaining} segundos para enviar novamente{" "}
                </p>
              )}
            </div>

            <button
              type="submit"
              disabled={buttonValidarState ? false : true}
              className={`w-full sm:w-1/3 h-full shadow rounded-xl text-white text-2xl mt-4 sm:mt-0 ${buttonValidarState ? "bg-custom-blue" : "bg-gray-400"}`}
            >
              Validar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default FormCodigoVerificacaoEmailOrganisms;
