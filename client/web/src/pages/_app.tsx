import { AuthProvider } from "@/context/authContext";
import { EmailProvider } from "@/context/emailContext";
import { GoogleOAuthProvider } from '@react-oauth/google';
import "../styles/globals.css";
import type { AppProps } from "next/app";
import "react-toastify/dist/ReactToastify.css";

const clientId = '809487523540-nva40rqmb99u5f15jn2m1iebl65k7fr0.apps.googleusercontent.com';

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <GoogleOAuthProvider clientId={clientId}>
      <AuthProvider>
        <EmailProvider>
          <Component {...pageProps} />
        </EmailProvider>
      </AuthProvider>
    </GoogleOAuthProvider>
  );
}

export default MyApp;