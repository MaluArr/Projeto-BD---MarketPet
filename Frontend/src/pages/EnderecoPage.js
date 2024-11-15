// src/pages/EnderecoPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Endereco.css';

const EnderecoPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Endereços</h1>
            <div className="action-buttons">
                <Link to="/enderecos" className="action-button">
                    Listar Endereços
                </Link>
                <Link to="/enderecos/new" className="action-button">
                    Adicionar Novo Endereço
                </Link>
            </div>
        </div>
    );
};

export default EnderecoPage;