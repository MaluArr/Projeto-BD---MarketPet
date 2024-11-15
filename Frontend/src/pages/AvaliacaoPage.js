// src/pages/AvaliacaoPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Avaliacao.css';

const AvaliacaoPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Avaliações</h1>
            <div className="action-buttons">
                <Link to="/avaliacoes" className="action-button">
                    Listar Avaliações
                </Link>
                <Link to="/avaliacoes/new" className="action-button">
                    Adicionar Nova Avaliação
                </Link>
            </div>
        </div>
    );
};

export default AvaliacaoPage;