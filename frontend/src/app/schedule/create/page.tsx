"use client";

import { useRouter } from "next/navigation";
import { Button } from "@mui/material";

export default function Create({}) {
  const router = useRouter();
  return (
    <div>
      <p>You will create your schedule here</p>
      <Button variant="contained" onClick={() => router.push("/")}>
        Go back
      </Button>
    </div>
  );
}
