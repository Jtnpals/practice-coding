"use client";

import Link from "next/link";
import { useRouter } from "next/navigation";

export default function Page({
  params,
  searchParams,
}: {
  params: { slug: string };
  searchParams: { id: string; test: string };
}) {
  const router = useRouter();

  return (
    <div>
      <button onClick={() => router.refresh()}>test</button>
      <Link href="/csr" className="" style={{ color: "red" }}>
        test
      </Link>
      내부 페이지
      <p>{params.slug}</p>
      <p>{searchParams.id}</p>
      <p>{typeof searchParams.test}</p>
    </div>
  );
}
