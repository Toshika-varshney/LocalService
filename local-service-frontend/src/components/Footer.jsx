import React from "react";

const Footer = () => {
  return (
    <footer className="bg-gray-800 text-blue-200 py-4 text-center mt-auto">
      <p className="text-sm">
        © {new Date().getFullYear()} ServEase. All rights reserved.
      </p>
    </footer>
  );
};

export default Footer;
