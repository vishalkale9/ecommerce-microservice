import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Login from './pages/Login'
import Signup from './pages/Signup';

function App() {
  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />

      <main className="container mx-auto px-4 py-8">
        <Routes>
          <Route path="/" element={<h1 className="text-3xl font-bold text-center mt-10">Welcome to the Store!</h1>} />

          {/* Add the Login Route! */}
          <Route path="/login" element={<Login />} />

          {/* Add the Signup Route! */}
          <Route path="/signup" element={<Signup />} />

        </Routes>
      </main>
    </div>
  );
}

export default App;
