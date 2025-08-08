import React, { useState } from 'react';
import axios from 'axios';
import api from '../services/api';

const ListYourBusiness = () => {
  const [formData, setFormData] = useState({
    businessName: '',
    description: ''
  });

  const [successMsg, setSuccessMsg] = useState('');
  const [errorMsg, setErrorMsg] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSuccessMsg('');
    setErrorMsg('');

    try {
      await api.post('/vendor-requests/create', formData);
      setSuccessMsg('Request sent successfully! Await admin approval.');
      setFormData({ businessName: '', description: '' });
    } catch (error) {
      setErrorMsg(error.response?.data?.message || 'Something went wrong');
    }
  };

  const handleReset = () => {
    setFormData({ businessName: '', description: '' });
    setSuccessMsg('');
    setErrorMsg('');
  };

  return (
    <div className="relative min-h-screen flex items-center justify-center">
      {/* Background Layer */}
      <div
        className="absolute inset-0 bg-cover bg-center filter blur-sm brightness-75 z-0"
        style={{
          backgroundImage:
            "url(https://cdn.slidesharecdn.com/ss_thumbnails/criteriaforhouseholdservices-230513133428-1562387d-thumbnail.jpg?width=640&height=640&fit=bounds)"
        }}
      />

      {/* Form Container */}
      <div className="relative z-10 bg-white p-8 rounded-lg shadow-2xl w-full max-w-xl">
        <h2 className="text-2xl font-bold mb-6 text-center text-blue-700">List Your Business</h2>

        {successMsg && <p className="text-green-600 text-center mb-4">{successMsg}</p>}
        {errorMsg && <p className="text-red-600 text-center mb-4">{errorMsg}</p>}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-gray-700 font-medium">Business Name</label>
            <input
              type="text"
              name="businessName"
              value={formData.businessName}
              onChange={handleChange}
              required
              className="w-full mt-1 px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
            />
          </div>

          <div>
            <label className="block text-gray-700 font-medium">Description</label>
            <textarea
              name="description"
              value={formData.description}
              onChange={handleChange}
              required
              rows={4}
              className="w-full mt-1 px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
            />
          </div>

          <div className="flex justify-between">
            <button
              type="submit"
              className="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-[48%]"
            >
              Submit
            </button>
            <button
              type="button"
              onClick={handleReset}
              className="bg-gray-400 hover:bg-gray-500 text-white font-bold py-2 px-4 rounded w-[48%]"
            >
              Reset
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ListYourBusiness;
