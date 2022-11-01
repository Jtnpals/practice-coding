import { useSelectedLayoutSegment } from "next/navigation";

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div>
      슬러그 레이아웃
      {children}
      슬러그
    </div>
  );
}
