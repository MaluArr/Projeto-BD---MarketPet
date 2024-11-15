// src/pages/AtendimentoPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Atendimento.css';

const AtendimentoPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Atendimentos</h1>
            <div className="action-buttons">
                <Link to="/atendimentos" className="action-button">
                    Listar Atendimentos
                </Link>
                <Link to="/atendimentos/new" className="action-button">
                    Adicionar Novo Atendimento
                </Link>
            </div>
        </div>
    );
};

export default AtendimentoPage;