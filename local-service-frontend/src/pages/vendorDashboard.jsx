import React, { useEffect, useState } from "react";
import api from '../services/api';
import { Bar } from "react-chartjs-2";

export default function VendorDashboard() {
  const [services, setServices] = useState([]);
  const [bookings, setBookings] = useState([]);

  const vendorId = 2; // replace with actual vendor ID

  useEffect(() => {
    api.get(`/service/${vendorId}`).then(res => setServices(res.data));
    api.get(`/all-booking/${vendorId}`).then(res => setBookings(res.data));
  }, []);

  const data = {
    labels: ['Services', 'Bookings'],
    datasets: [{
      label: 'Count',
      backgroundColor: ['#60a5fa', '#1f2937'],
      data: [services.length, bookings.length]
    }]
  };

  return (
    <div>
      <h2 className="text-2xl font-bold mb-4">Dashboard Overview</h2>
      <div className="bg-white rounded p-4 shadow-md w-full max-w-xl">
        <Bar data={data} />
      </div>
    </div>
  );
}
