import { useState } from "react";
import { Link } from "react-router-dom";
import { FaUserCircle } from "react-icons/fa";
import ProfileMenu from "./ProfileMenu";



const Navbar = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false); // mock login state
  const [userName, setUserName] = useState("Toshika");

  return (
    <nav className="flex justify-between items-center bg-white px-6 py-4 shadow-md">
      {/* Left: Logo + Name */}
      <div className="flex items-center space-x-2">
        <img src="/logo.png" alt="Logo" className="h-8 w-8" />
        <span className="text-xl font-semibold text-blue-600">LocalService</span>
      </div>

    

      <div className="flex items-center space-x-6">
        
        <Link to="/" className="text-gray-700 hover:text-blue-500 font-medium">
          Home
        </Link>

        <Link to="/Services" className="text-gray-700 hover:text-blue-500 font-medium">
          Services
        </Link>
        
        <Link to="/list-your-business" className="text-gray-700 hover:text-blue-500 font-medium">
          List Your Business
        </Link>

        <ProfileMenu isLoggedIn={isLoggedIn} userName={userName} />
      </div>
    </nav>
  );
};

export default Navbar;
