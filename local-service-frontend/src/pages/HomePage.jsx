import React from "react";
import { FaSearchLocation, FaSearch } from "react-icons/fa";

const HomePage = () => {
  return (
    <div className="w-full">
      {/* Hero Section */}
      <section className="bg-blue-900 text-white p-8 text-center ">
        <h1 className="text-4xl font-bold mb-4">
          Find Trusted Services at Your Fingertips!
        </h1>
        <p className="text-lg">Your Town's Best Service Providers, All in One Place</p>

        {/* Search Bars */}
        <div className="flex justify-center mt-6 gap-4 flex-wrap">
          <div className="flex items-center bg-white rounded-md overflow-hidden px-2 w-64 text-black">
            <FaSearchLocation className="mr-2" />
            <input
              type="text"
              placeholder="Enter your location"
              className="p-2 w-full outline-none"
              defaultValue="Aligarh"
            />
          </div>
          <div className="flex items-center bg-white rounded-md overflow-hidden px-2 w-64 text-black">
            <FaSearch className="mr-2" />
            <input
              type="text"
              placeholder="Find your service"
              className="p-2 w-full outline-none"
            />
          </div>
        </div>
      </section>

      {/* What Are You Looking For */}
      <section className="py-10 px-6 bg-white text-center">
        <h2 className="text-2xl font-semibold mb-6 text-black">What are you looking for?</h2>
        <div className="grid grid-cols-2 md:grid-cols-4 gap-4 max-w-5xl mx-auto">
          {["Electrician", "Plumber", "Cleaner", "Painter", "Carpenter", "Gardener", "AC Service", "Mechanic"].map((service, index) => (
            <div
              key={index}
              className="border rounded-lg p-4 bg-gray-100 hover:bg-blue-50 cursor-pointer"
            >
              <img src={`/${service.toLowerCase()}.png`} alt={service} className="h-16 mx-auto mb-2" />
              <p>{service}</p>
            </div>
          ))}
        </div>
      </section>

      {/* Example Section - Home Cleaning */}
      <section className="py-10 px-6 bg-white">
        <h2 className="text-xl font-semibold mb-4">Home Cleaning</h2>
        <div className="flex flex-wrap gap-4">
          {["Full Home Cleaning", "Sofa & Carpet Cleaning", "Bathroom Cleaning", "Kitchen Cleaning"].map((sub, index) => (
            <div key={index} className="border rounded-lg p-4 bg-gray-50 hover:bg-blue-50 cursor-pointer">
              {sub}
            </div>
          ))}
        </div>
      </section>

      {/* Example Section - Tution Services */}
      <section className="py-10 px-6 bg-white">
        <h2 className="text-xl font-semibold mb-4">Tution Services</h2>
        <div className="flex flex-wrap gap-4">
          {["Physics Tution", "Chemistry Tution", "Math Tution", "All Subjects Tution"].map((sub, index) => (
            <div key={index} className="border rounded-lg p-4 bg-gray-50 hover:bg-blue-50 cursor-pointer">
              {sub}
            </div>
          ))}
        </div>
      </section>
    </div>
  );
};

export default HomePage;
