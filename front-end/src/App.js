import { useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, } from "react-router-dom";
import Home from './components/Home'
import Restaurants from './components/Restaurants'
import Dishes from './components/Dishes'
import './App.css';
import Navbar from './components/NavBar';

function App() 
{
  
  
  return (
    <Router className="App">
      <Navbar/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Restaurants" element={<Restaurants />} />
        <Route path="/Dishes" element={<Dishes/>} />
      </Routes>
      <h1>osdfijghohigu;sdrhilojkrg</h1>
    </Router>
  );
}

export default App;
