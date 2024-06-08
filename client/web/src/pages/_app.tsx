import "../styles/globals.css";
import { EmailProvider } from "@/context/emailContext";
import type { AppProps } from "next/app";
import 'react-toastify/dist/ReactToastify.css';

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <EmailProvider>
      <Component {...pageProps} />
    </EmailProvider>
  );
}

export default MyApp;
