import type { NextPage } from "next";
import { useTranslation } from "next-i18next";
import { serverSideTranslations } from "next-i18next/serverSideTranslations";
import Link from "next/link";
import { useRouter } from "next/router";

export const getStaticProps = async ({ locale }: any) => ({
  props: {
    ...(await serverSideTranslations(locale, ["common"])),
  },
});

const Home: NextPage = () => {
  const router = useRouter();
  const { t } = useTranslation("common");

  return (
    <div>
      <h1>{t("hello")}</h1>
      <Link href="/" locale="en">
        <button>English</button>
      </Link>
      <Link href="/" locale="ko">
        <button>한국어</button>
      </Link>
    </div>
  );
};

export default Home;
