import { useEffect, useState } from "react";

export default function Template({ children }: { children: React.ReactNode }) {
  return (
    <div>
      {/* {test2} */}
      이펙트
      {children}템플릿
    </div>
  );
}
