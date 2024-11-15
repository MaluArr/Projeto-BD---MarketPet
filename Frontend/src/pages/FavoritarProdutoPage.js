// src/pages/FavoritarProdutoPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/FavoritarProduto.css';

const FavoritarProdutoPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Produtos Favoritos</h1>
            <div className="action-buttons">
                <Link to="/favoritar-produtos" className="action-button">
                    Listar Produtos Favoritos
                </Link>
                <Link to="/favoritar-produtos/new" className="action-button">
                    Adicionar Novo Produto Favorito
                </Link>
            </div>
        </div>
    );
};

export default FavoritarProdutoPage;