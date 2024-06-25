import { AuthProvider } from "@/context/authContext";
import "../styles/globals.css";
import { EmailProvider } from "@/context/emailContext";
import type { AppProps } from "next/app";
import "react-toastify/dist/ReactToastify.css";

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <AuthProvider>
      <EmailProvider>
        <Component {...pageProps} />
      </EmailProvider>
    </AuthProvider>
  );
}

export default MyApp;
