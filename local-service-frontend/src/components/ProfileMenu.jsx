import { useState } from "react";
import { FaUserCircle } from "react-icons/fa";
import { Link } from "react-router-dom";

const ProfileMenu = ({ isLoggedIn, userName }) => {
  const [open, setOpen] = useState(false);

  return (
    <div className="relative">
      <FaUserCircle
        className="text-2xl cursor-pointer text-gray-700 hover:text-blue-500"
        onClick={() => setOpen(!open)}
      />
      {open && (
        <div className="absolute right-0 mt-2 w-40 bg-white border rounded shadow-md">
          {!isLoggedIn ? (
            <div className="flex flex-col text-sm">
              <Link to="/login" className="px-4 py-2 hover:bg-gray-100">Login</Link>
              <Link to="/signup" className="px-4 py-2 hover:bg-gray-100">Sign Up</Link>
            </div>
          ) : (
            <div className="flex flex-col text-sm">
              <span className="px-4 py-2 font-semibold text-blue-600">{userName}</span>
              <Link to="/bookings" className="px-4 py-2 hover:bg-gray-100">View Bookings</Link>
              <button className="px-4 py-2 text-left hover:bg-gray-100">Logout</button>
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default ProfileMenu;
