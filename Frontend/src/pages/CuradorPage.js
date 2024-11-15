// src/pages/CuradorPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Curador.css';

const CuradorPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Curadores</h1>
            <div className="action-buttons">
                <Link to="/curadores" className="action-button">
                    Listar Curadores
                </Link>
                <Link to="/curadores/new" className="action-button">
                    Adicionar Novo Curador
                </Link>
            </div>
        </div>
    );
};

export default CuradorPage;