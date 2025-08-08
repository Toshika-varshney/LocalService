import { useState } from "react";

const ServiceDropdown = () => {
  const [open, setOpen] = useState(false);

  const services = {
    "Cleaning": ["Home Cleaning", "Carpet Cleaning"],
    "Salon": ["Haircut", "Facial", "Manicure"],
    "Repair": ["AC Repair", "Fridge Repair"],
  };

  return (
    <div className="relative">
      <button
        onClick={() => setOpen(!open)}
        className="text-gray-700 hover:text-blue-500 font-medium"
      >
        Services
      </button>

      {open && (
        <div className="absolute right-0 mt-2 w-56 bg-white border rounded shadow-md z-50 p-2">
          {Object.entries(services).map(([category, subs]) => (
            <div key={category} className="mb-2">
              <div className="font-semibold text-sm text-blue-600">{category}</div>
              <ul className="ml-2 text-sm text-gray-700">
                {subs.map((s, i) => (
                  <li key={i} className="hover:underline cursor-pointer">{s}</li>
                ))}
              </ul>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default ServiceDropdown;
