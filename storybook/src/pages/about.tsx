import type { NextPage } from "next";
import { useTranslation } from "next-i18next";
import { useRouter } from "next/router";
import { serverSideTranslations } from "next-i18next/serverSideTranslations";

export const getStaticProps = async ({ locale }: any) => ({
  props: {
    ...(await serverSideTranslations(locale, ["about"])),
  },
});

const About: NextPage = () => {
  const router = useRouter();
  const { t } = useTranslation("about");
  return (
    <div>
      <ul>
        <li>
          {t("currentUrl")} : http://localhost:3000
          {router.locale !== "ko" && "/" + router.locale}
          {router.pathname}
        </li>
        <li>locale: {router.locale}</li>
        <li>pathname: {router.pathname}</li>
      </ul>
    </div>
  );
};

export default About;
