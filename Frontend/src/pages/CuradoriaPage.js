// src/pages/CuradoriaPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Curadoria.css';

const CuradoriaPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Curadorias</h1>
            <div className="action-buttons">
                <Link to="/curadorias" className="action-button">
                    Listar Curadorias
                </Link>
                <Link to="/curadorias/new" className="action-button">
                    Adicionar Nova Curadoria
                </Link>
            </div>
        </div>
    );
};

export default CuradoriaPage;