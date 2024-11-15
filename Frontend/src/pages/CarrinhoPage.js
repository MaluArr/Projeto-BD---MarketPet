// src/pages/CarrinhoPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Carrinho.css';

const CarrinhoPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Carrinhos</h1>
            <div className="action-buttons">
                <Link to="/carrinhos" className="action-button">
                    Listar Carrinhos
                </Link>
                <Link to="/carrinhos/new" className="action-button">
                    Adicionar Novo Carrinho
                </Link>
            </div>
        </div>
    );
};

export default CarrinhoPage;