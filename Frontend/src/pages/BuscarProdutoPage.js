// src/pages/BuscarProdutoPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/BuscarProduto.css';

const BuscarProdutoPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Buscas de Produto</h1>
            <div className="action-buttons">
                <Link to="/buscar-produto" className="action-button">
                    Listar Buscas de Produto
                </Link>
                <Link to="/buscar-produto/new" className="action-button">
                    Adicionar Nova Busca de Produto
                </Link>
            </div>
        </div>
    );
};

export default BuscarProdutoPage;