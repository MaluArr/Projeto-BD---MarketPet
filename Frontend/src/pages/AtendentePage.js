// src/pages/AtendentePage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Atendente.css';

const AtendentePage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Atendentes</h1>
            <div className="action-buttons">
                <Link to="/atendentes" className="action-button">
                    Listar Atendentes
                </Link>
                <Link to="/atendentes/new" className="action-button">
                    Adicionar Novo Atendente
                </Link>
            </div>
        </div>
    );
};

export default AtendentePage;