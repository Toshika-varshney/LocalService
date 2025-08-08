import React, { useState } from "react";
import { Link, useLocation } from "react-router-dom";
import { Menu, X } from "lucide-react";

export default function Sidebar() {
  const [open, setOpen] = useState(true);
  const location = useLocation();

  const linkClass = (path) =>
    `hover:bg-gray-200 p-2 rounded transition ${location.pathname === path ? 'bg-blue-400 text-white' : ''}`;

  return (
    <div className={`bg-white shadow-lg ${open ? "w-64" : "w-16"} duration-300`}>
      <div className="flex items-center justify-between p-4">
        <h1 className="font-bold text-xl">{open && "Vendor"}</h1>
        <button onClick={() => setOpen(!open)}>
          {open ? <X size={20} /> : <Menu size={20} />}
        </button>
      </div>
      <nav className="flex flex-col p-2 space-y-1">
        <Link to="/home" className={linkClass("/home")}>🏠 {open && "Home"}</Link>
        <Link to="/dashboard" className={linkClass("/dashboard")}>📊 {open && "Dashboard"}</Link>
        <Link to="/services" className={linkClass("/services")}>🛠 {open && "Services"}</Link>
        <Link to="/bookings" className={linkClass("/bookings")}>📅 {open && "Bookings"}</Link>
        <Link to="/feedback" className={linkClass("/feedback")}>💬 {open && "Feedback"}</Link>
      </nav>
    </div>
  );
}
