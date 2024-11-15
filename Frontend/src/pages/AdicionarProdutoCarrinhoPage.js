// src/pages/AdicionarProdutoCarrinhoPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/AdicionarProdutoCarrinho.css';

const AdicionarProdutoCarrinhoPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Produtos no Carrinho</h1>
            <div className="action-buttons">
                <Link to="/adicionar-produto-carrinho" className="action-button">
                    Listar Produtos no Carrinho
                </Link>
                <Link to="/adicionar-produto-carrinho/new" className="action-button">
                    Adicionar Novo Produto ao Carrinho
                </Link>
            </div>
        </div>
    );
};

export default AdicionarProdutoCarrinhoPage;