// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import UsuarioPage from './pages/UsuarioPage';
import UsuarioList from './components/UsuarioList';
import UsuarioDetails from './components/UsuarioDetails';
import UsuarioForm from './components/UsuarioForm';
import VendedorList from './components/VendedorList';
import VendedorForm from './components/VendedorForm';
import VendedorDetails from './components/VendedorDetails';
import VendedorReports from './components/VendedorReports';
import EnderecoPage from './pages/EnderecoPage';
import EnderecoList from './components/EnderecoList';
import EnderecoForm from './components/EnderecoForm';
import CartaoPage from './pages/CartaoPage';
import CartaoList from './components/CartaoList';
import CartaoForm from './components/CartaoForm';
import CompradorList from './components/CompradorList';
import CompradorForm from './components/CompradorForm';
import CompradorPage from './pages/CompradorPage';

function App() {
    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/" element={<HomePage />} />

                    {/* Rotas de Usuário */}
                    <Route path="/usuarios" element={<UsuarioPage />} />
                    <Route path="/usuarios" element={<UsuarioList />} />
                    <Route path="/usuarios/:cpf" element={<UsuarioDetails />} />
                    <Route path="/usuarios/new" element={<UsuarioForm />} />
                    <Route path="/usuarios/:cpf/edit" element={<UsuarioForm />} />

                    {/* Rotas de Vendedor */}
                    <Route path="/vendedores" element={<VendedorList />} />
                    <Route path="/vendedores/new" element={<VendedorForm />} />
                    <Route path="/vendedores/:cpf" element={<VendedorDetails />} />
                    <Route path="/vendedores/:cpf/edit" element={<VendedorForm />} />
                    <Route path="/vendedores/relatorios" element={<VendedorReports />} />

                    {/* Rotas de Endereço */}
                    <Route path="/enderecos" element={<EnderecoPage />} />
                    <Route path="/enderecos/list" element={<EnderecoList />} />
                    <Route path="/enderecos/new" element={<EnderecoForm />} />
                    <Route path="/enderecos/:id/edit" element={<EnderecoForm />} />

                    {/* Rotas de Cartão */}
                    <Route path="/cartoes" element={<CartaoPage />} />
                    <Route path="/cartoes/list" element={<CartaoList />} />
                    <Route path="/cartoes/new" element={<CartaoForm />} />
                    <Route path="/cartoes/:id/edit" element={<CartaoForm />} />

                    {/* Rotas de Comprador */}
                    <Route path="/compradores" element={<CompradorPage />} />
                    <Route path="/compradores/list" element={<CompradorList />} />
                    <Route path="/compradores/new" element={<CompradorForm />} />
                    <Route path="/compradores/:cpf/edit" element={<CompradorForm />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;