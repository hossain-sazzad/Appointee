"use client";

import { Button } from "@mui/material";
import { useRouter } from "next/navigation";

export default function Home() {
  const router = useRouter();

  return (
    <main>
      <Button
        variant="contained"
        onClick={() => router.push("/schedule/create")}
      >
        Create schedule
      </Button>
    </main>
  );
}
