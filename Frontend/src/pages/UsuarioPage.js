// src/pages/UsuarioPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Usuario.css';

const UsuarioPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Usuários</h1>
            <div className="action-buttons">
                <Link to="/usuarios" className="action-button">
                    Listar Usuários
                </Link>
                <Link to="/usuarios/new" className="action-button">
                    Adicionar Novo Usuário
                </Link>
            </div>
        </div>
    );
};

export default UsuarioPage;