import { useState } from "react";
import { confirmEmail } from "./funCadastrar";
import { useRouter } from "next/router";

const ConfirmEmail = ({ email, onConfirmSuccess }: string) => {
  const router = useRouter();

  const [c1, setC1] = useState<string>("");
  const [c2, setC2] = useState<string>("");
  const [c3, setC3] = useState<string>("");
  const [c4, setC4] = useState<string>("");

  const [errorMessage, setErrorMessage] = useState<string>("");
  const [buttonState, setButtonState] = useState<boolean>(true);
  const [buttonLoading, setButtonLoading] = useState<boolean>(false);

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const data = {
      email: email,
      codigoVerificacao: c1 + c2 + c3 + c4,
    };
    if (email != "" && data.codigoVerificacao.length == 4) {
      onSubmit(data);
    } else {
      setErrorMessage("preencha todos os campos !");
    }
  };

  const handleInputChange = (
    e: React.ChangeEvent<HTMLInputElement>,
    setValue: React.Dispatch<React.SetStateAction<string>>
  ) => {
    const value = e.target.value.slice(0, 1);
    setValue(value);
  };

  const onSubmit = async (data: any) => {
    try {
      await confirmEmail(data);
      setButtonLoading(true);
      setTimeout(() => {
        onConfirmSuccess();
      }, 2000);
    } catch (e) {
      setButtonLoading(false);
      if (e.message == "Codigo incorreto") {
        setErrorMessage(e.message);
      }
      if (e.message == "Codigo expirado") {
        setButtonLoading(false);
        setButtonState(false);
        setErrorMessage(e.message);
      }
      if (e.message == "Erro ao salvar usuario sem autenticar") {
        router.push("/login");
      }
    }
  };

  return (
    <div className="w-1/2 flex flex-col justify-center items-center">
      <h1 className="w-full text-white text-4xl font-roboto font-bold mb-5 text-center">
        Confirmar Email
      </h1>
      <div className="w-full h-full flex flex-col bg-slate-100 border-solid border-2 border-neutral-300 items-center rounded-xl p-5 shadow">
        <p className="w-full text-neutral-500 text-justify">
          Um e-mail foi enviado com um código de validação para o endereço de
          e-mail informado.
          <br />
          Por favor, insira o código de 4 dígitos para validar seu e-mail.
        </p>
        <form
          className="w-full flex flex-col mt-5 justify-center items-center"
          onSubmit={handleSubmit}
        >
          <div className="w-full h-auto gap-3 flex justify-center items-center">
            <input
              type="text"
              className="h-24 w-20 border-solid  border-2 border-b-silver border-b-4 shadow bg-white text-5xl text-center text-neutral-400 rounded-xl"
              onChange={(e) => handleInputChange(e, setC1)}
              maxLength={1}
            />
            <input
              type="text"
              className="h-24 w-20 border-solid  border-2 border-b-silver border-b-4 shadow bg-white text-5xl text-center text-neutral-400 rounded-xl"
              onChange={(e) => handleInputChange(e, setC2)}
              maxLength={1}
            />
            <input
              type="text"
              className="h-24 w-20 border-solid  border-2 border-b-silver border-b-4 shadow bg-white text-5xl text-center text-neutral-400 rounded-xl"
              onChange={(e) => handleInputChange(e, setC3)}
              maxLength={1}
            />
            <input
              type="text"
              className="h-24 w-20 border-solid  border-2 border-b-silver border-b-4 shadow bg-white text-5xl text-center text-neutral-400 rounded-xl"
              onChange={(e) => handleInputChange(e, setC4)}
              maxLength={1}
            />
          </div>
          <div className="h-2 mt-3">
            {errorMessage && <p className=" text-red-400">{errorMessage}</p>}
          </div>

          {buttonLoading ? (
            <div className="mt-10 h-16 bg-slate-200 w-full shadow rounded-xl text-custom-blue-2 text-2xl flex items-center justify-center">
              <p className="text-sm">Carregando...</p>
            </div>
          ) : buttonState ? (
            <button
              type="submit"
              className="mt-10 h-16 bg-white w-full shadow rounded-xl text-custom-blue-2 text-2xl"
            >
              Validar
            </button>
          ) : (
            <button
              onClick={() => router.push("/login")}
              className="mt-10 h-16 bg-neutral-500 w-full shadow rounded-xl text-white text-2xl"
            >
              Voltar ao início
            </button>
          )}
        </form>
      </div>
    </div>
  );
};

export default ConfirmEmail;
