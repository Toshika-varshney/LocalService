import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const ServicePage = () => {
  const [categoryData, setCategoryData] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    fetchCategoriesAndServices();
  }, []);

  const fetchCategoriesAndServices = async () => {
    try {
      const response = await axios.get("/api/vendor/services/category-with-services");
      setCategoryData(response.data);
    } catch (error) {
      console.error("Error fetching services:", error);
    }
  };

  const handleServiceClick = (serviceName) => {
    navigate(`/vendors/${serviceName}`);
  };

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold text-center mb-6">Explore Our Services</h1>

      {Object.entries(categoryData).map(([category, services]) => (
        <div key={category} className="mb-8">
          <h2 className="text-xl font-semibold text-gray-700 mb-4">{category}</h2>
          <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 gap-4">
            {services.map((serviceName) => (
              <div
                key={serviceName}
                onClick={() => handleServiceClick(serviceName)}
                className="cursor-pointer bg-white p-4 shadow-md rounded-lg hover:shadow-lg transition"
              >
                <img
                  src={`https://source.unsplash.com/200x150/?${serviceName}`}
                  alt={serviceName}
                  className="w-full h-32 object-cover rounded-md mb-2"
                />
                <p className="text-center font-medium text-gray-800">{serviceName}</p>
              </div>
            ))}
          </div>
        </div>
      ))}
    </div>
  );
};

export default ServicePage;
