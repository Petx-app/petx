import React, { useEffect, useState } from "react";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import { UseFormRegister, FieldError } from "react-hook-form";

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
  maxLength = 40,
  minLength = 0,
}) => {
  const [showPassword, setShowPassword] = useState<boolean>(false);
  const [typeInput, setTypeInput] = useState<string>(type);

  useEffect(() => {
    if (type === "password") {
      setTypeInput("password");
    } else {
      setTypeInput("text");
    }
  }, [type]);

  const handleSetType = () => {
    setShowPassword(!showPassword);
    setTypeInput(showPassword ? "password" : "text");
  };

  return (
    <div className="flex flex-col relative w-full">
      <label htmlFor={id} className={`${color} ${fontSize} mb-1`}>
        {label}
      </label>
      <div className="relative">
        <input
          id={id}
          type={typeInput}
          placeholder={placeholder}
          className={`${width} ${height} px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-custom-blue bg-white`}
          {...register(name, {
            required: `${label} é obrigatório`,
            maxLength: {
              value: maxLength,
              message: `${label} deve ter no máximo ${maxLength} caracteres`,
            },
            minLength: {
              value: minLength,
            },
          })}
        />
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
