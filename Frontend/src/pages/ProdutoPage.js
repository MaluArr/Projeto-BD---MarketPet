// src/pages/ProdutoPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Produto.css';

const ProdutoPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Produtos</h1>
            <div className="action-buttons">
                <Link to="/produtos" className="action-button">
                    Listar Produtos
                </Link>
                <Link to="/produtos/new" className="action-button">
                    Adicionar Novo Produto
                </Link>
            </div>
        </div>
    );
};

export default ProdutoPage;