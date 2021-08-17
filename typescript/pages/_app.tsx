import "../styles/globals.css";
import type { AppProps } from "next/app";
import Counter from "./Counter";
import MyForm from "./MyForm";

function MyApp({ Component, pageProps }: AppProps) {
  const onSubmit = (form: { name: string; description: string }) => {
    console.log(form);
  };
  return (
    <>
      <Component {...pageProps} />
      <Counter />
      <MyForm onSubmit={onSubmit} />
    </>
  );
}
export default MyApp;
