// src/pages/VendedorPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Vendedor.css';

const VendedorPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Vendedores</h1>
            <div className="action-buttons">
                <Link to="/vendedores" className="action-button">
                    Listar Vendedores
                </Link>
                <Link to="/vendedores/new" className="action-button">
                    Adicionar Novo Vendedor
                </Link>
            </div>
        </div>
    );
};

export default VendedorPage;