import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Funcionario.css';
import axios from 'axios';

const FuncionarioPage = () => {
    const [funcionarios, setFuncionarios] = useState([]); // Estado para armazenar os funcionários
    const [error, setError] = useState(null); // Estado para erros

    const fetchFuncionarios = () => {
        axios.get('http://localhost:8080/api/funcionarios')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const funcionariosFormatados = response.data.map(funcionario => ({
                    cpfFuncionario: funcionario.cpfFuncionario || funcionario.cpf_funcionario,
                    nome: funcionario.nome,
                }));
                setFuncionarios(funcionariosFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar funcionários');
                console.error('Erro ao buscar funcionários:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Funcionários</h1>
            <div className="action-buttons">
                <button onClick={fetchFuncionarios}>Listar Funcionários</button>
                <Link to="/funcionarios/new" className="action-button">
                    Adicionar Novo Funcionário
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="funcionario-list">
                {funcionarios.length > 0 && (
                    <table className="funcionario-table">
                        <thead>
                        <tr>
                            <th>CPF</th>
                            <th>Nome</th>
                        </tr>
                        </thead>
                        <tbody>
                        {funcionarios.map(funcionario => (
                            <tr key={funcionario.cpfFuncionario}>
                                <td>{funcionario.cpfFuncionario}</td>
                                <td>{funcionario.nome}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default FuncionarioPage;
