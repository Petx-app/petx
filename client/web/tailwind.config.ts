import type { Config } from "tailwindcss";

const config: Config = {
  important: true,
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      backgroundImage: {
        'background-petx': "url('/background-petx.png')"
      }, 
      colors: {
        'glass-blue': 'rgba(173, 216, 230, 0.6)',
        'custom-blue':'#185E8D', //texto
        'custom-blue-2':'#59C2DD',
        'custom-yellow': '#F5CF46', //texto
      },
      backdropBlur: {
        xs: '2px',
        sm: '4px',
        md: '8px',
        lg: '12px',
        xl: '16px',
      },
      fontFamily: {
        roboto: ['Roboto', 'sans-serif'],
      },
      height: {
        '95': '95%', // Adiciona uma altura de 95%
      },
    },
  },
  plugins: [],
};
export default config;
