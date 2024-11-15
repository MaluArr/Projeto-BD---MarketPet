// src/pages/CartaoPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Cartao.css';

const CartaoPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Cartões</h1>
            <div className="action-buttons">
                <Link to="/cartoes" className="action-button">
                    Listar Cartões
                </Link>
                <Link to="/cartoes/new" className="action-button">
                    Adicionar Novo Cartão
                </Link>
            </div>
        </div>
    );
};

export default CartaoPage;