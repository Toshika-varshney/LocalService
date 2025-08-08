
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Navbar from "./components/Navbar";
import Footer from "./components/Footer"; 
import HomePage from "./pages/HomePage"; 
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import ListYourBusiness from "./pages/ListYourBusiness";
import ServicePage from "./pages/ServicePage";
import VendorDashboard from "./pages/vendorDashboard";



const App = () => {
  return (
     <Router>
      <div className="flex flex-col min-h-screen">
        <Navbar /> 
        
        <div className="flex-grow">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/list-your-business" element={<ListYourBusiness />} />
            <Route path="/services" element={<ServicePage />} />
          <Route path="/vendorDashboard" element={<VendorDashboard />} />
 
          </Routes>
        </div>
        <ToastContainer position="top-right" autoClose={3000} />

        <Footer />
      </div>
    </Router>
  );
};

export default App;
