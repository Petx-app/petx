import React, { useEffect, useState } from "react";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import { UseFormRegister, FieldError } from "react-hook-form";
import InputMask from "react-input-mask";

interface LabeledInputProps {
  id: string;
  label: string;
  color: string;
  fontSize: string;
  type: string;
  placeholder: string;
  width: string;
  height: string;
  register: UseFormRegister<any>;
  name: string;
  error?: FieldError;
  textShow: string;
  maxLength?: number;
  minLength?: number;
}

const LabeledInput: React.FC<LabeledInputProps> = ({
  id,
  label,
  color,
  fontSize,
  type,
  placeholder,
  width,
  height,
  register,
  name,
  error,
  textShow,
  maxLength = 40,
  minLength = 0,
}) => {
  const [showPassword, setShowPassword] = useState<boolean>(false);

  useEffect(() => {
    if (type !== "password") {
      setShowPassword(false);
    }
  }, [type]);

  const handleSetType = () => {
    setShowPassword((prev) => !prev);
  };

  const validatePhone = (value: string) => {
    const cleanedValue = value.replace(/\D/g, "");
    return (
      cleanedValue.length === 11 ||
      `${label} é obrigatório e deve ter 11 dígitos`
    );
  };

  return (
    <div className="flex flex-col relative w-full">
      <label htmlFor={id} className={`${color} ${fontSize} mb-1`}>
        {label}
      </label>
      <div className="relative">
        {type === "text" && (
          <input
            id={id}
            type="text"
            placeholder={placeholder}
            className={`${width} ${height} px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-custom-blue`}
            {...register(name, {
              required: `${label} é obrigatório`,
              maxLength: {
                value: maxLength,
                message: `${label} deve ter no máximo ${maxLength} caracteres`,
              },
              minLength: {
                value: minLength,
                message: `${label} deve ter no mínimo ${minLength} caracteres`,
              },
            })}
          />
        )}

        {type === "show" && (
          <p
            className={`${width} ${height} px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-slate-100 focus:ring-blue-500 text-slate-500`}
          >
            {textShow}
          </p>
        )}

        {type === "telephone" && (
          <InputMask
            mask="(99) 99999-9999"
            placeholder={placeholder}
            id={id}
            className={`${width} ${height} px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-custom-blue`}
            {...register(name, {
              required: `${label} é obrigatório`,
              validate: validatePhone,
              minLength: {
                value: minLength,
                message: `${label} não está preenchido`,
              },
            })}
          />
        )}

        {type === "password" && (
          <input
            id={id}
            type={showPassword ? "text" : "password"}
            placeholder={placeholder}
            className={`${width} ${height} px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-custom-blue`}
            {...register(name, {
              required: `${label} é obrigatório`,
              maxLength: {
                value: maxLength,
                message: `${label} deve ter no máximo ${maxLength} caracteres`,
              },
              minLength: {
                value: minLength,
                message: `${label} deve ter no mínimo ${minLength} caracteres`,
              },
            })}
          />
        )}

        {type === "password" && (
          <div
            className="absolute inset-y-0 right-0 flex items-center pr-3 cursor-pointer top-1/2 transform -translate-y-1/2"
            onClick={handleSetType}
          >
            {showPassword ? (
              <FaEye size={20} color="#185E8D" />
            ) : (
              <FaEyeSlash size={20} color="#185E8D" />
            )}
          </div>
        )}
      </div>
      {error && <p className="pt-2 text-red-500 text-sm">{error.message}</p>}
    </div>
  );
};

export default LabeledInput;
