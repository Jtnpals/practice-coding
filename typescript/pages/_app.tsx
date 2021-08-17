import "../styles/globals.css";
import type { AppProps } from "next/app";
import Counter from "./Counter";

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <>
      <Component {...pageProps} />
      <Counter />
    </>
  );
}
export default MyApp;
