// src/pages/FuncionarioPage.js
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Funcionario.css';

const FuncionarioPage = () => {
    return (
        <div className="entity-page">
            <h1>Gerenciamento de Funcionários</h1>
            <div className="action-buttons">
                <Link to="/funcionarios" className="action-button">
                    Listar Funcionários
                </Link>
                <Link to="/funcionarios/new" className="action-button">
                    Adicionar Novo Funcionário
                </Link>
            </div>
        </div>
    );
};

export default FuncionarioPage;