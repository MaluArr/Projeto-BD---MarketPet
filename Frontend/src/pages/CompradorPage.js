// src/pages/CompradorPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Comprador.css';

const CompradorPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Compradores</h1>
            <div className="action-buttons">
                <Link to="/compradores" className="action-button">
                    Listar Compradores
                </Link>
                <Link to="/compradores/new" className="action-button">
                    Adicionar Novo Comprador
                </Link>
                <Link to="/compradores/relatorios" className="action-button">
                    Gerar Relatórios
                </Link>
            </div>
        </div>
    );
};

export default CompradorPage;