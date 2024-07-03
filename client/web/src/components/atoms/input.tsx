import React from "react";

const Input = ({
  id,
  type = "text",
  onChange,
  placeholder,
  width,
  height,
  ...rest
}) => {
  console.log("Input Rest:", rest); // Adicione um console.log para ver o que está sendo passado
  return (
    <input
      id={id}
      type={type}
      onChange={onChange}
      placeholder={placeholder}
      className={`${width} ${height} px-3 py-2 border rounded-md focus:outline-none focus:ring-2 bg-white focus:ring-blue-500 text-custom-blue mr-2`}
      {...rest}
    />
  );
};

export default Input;
