// src/pages/VendaPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Venda.css';

const VendaPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Vendas</h1>
            <div className="action-buttons">
                <Link to="/vendas" className="action-button">
                    Listar Vendas
                </Link>
                <Link to="/vendas/new" className="action-button">
                    Adicionar Nova Venda
                </Link>
            </div>
        </div>
    );
};

export default VendaPage;