import './App.css'
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import { Header } from './components/Header'
import { Main } from './components/Main';
import { Footer } from './components/Footer';
import { ContactDetails } from './components/ContactDetails';
import { FormUser } from './components/FormUser';
import { LogIn } from './components/LogIn';

function App() {

  return (
    <Router>
      <Header />
      <Routes>
        <Route path='/' element={<Main/>}/>
        <Route path='/contact' element={<ContactDetails/>} />
        <Route path='/registro' element={<FormUser/>}/>
        <Route path='/ingresar' element={<LogIn />} />
      </Routes>
      <Footer />
    </Router>
  )
}

export default App
