import React from "react";

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <head>
        <title></title>
      </head>
      <body className="p-2">{children}</body>
    </html>
  );
}
