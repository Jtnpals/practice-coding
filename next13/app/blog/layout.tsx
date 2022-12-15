"use client";

import { useSelectedLayoutSegments } from "next/navigation";

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const segment = useSelectedLayoutSegments();

  return (
    <div>
      [{segment}] 블로그 레이아웃
      {children}
      블로그
    </div>
  );
}
