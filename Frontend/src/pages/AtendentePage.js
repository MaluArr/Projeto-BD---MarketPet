import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Atendente.css';
import axios from 'axios';

const AtendentePage = () => {
    const [atendentes, setAtendentes] = useState([]); // Estado para armazenar os atendentes
    const [error, setError] = useState(null); // Estado para erros

    const fetchAtendentes = () => {
        axios.get('http://localhost:8080/api/atendentes')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const atendentesFormatados = response.data.map(atendente => ({
                    idAtendente: atendente.idAtendente || atendente.id_atendente,
                    cpfFuncionario: atendente.cpfFuncionario || atendente.cpf_funcionario,
                }));
                setAtendentes(atendentesFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar atendentes');
                console.error('Erro ao buscar atendentes:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Atendentes</h1>
            <div className="action-buttons">
                <button onClick={fetchAtendentes}>Listar Atendentes</button>
                <Link to="/atendentes/new" className="action-button">
                    Adicionar Novo Atendente
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="atendente-list">
                {atendentes.length > 0 && (
                    <table className="atendente-table">
                        <thead>
                        <tr>
                            <th>ID do Atendente</th>
                            <th>CPF do Funcionário</th>
                        </tr>
                        </thead>
                        <tbody>
                        {atendentes.map(atendente => (
                            <tr key={atendente.idAtendente}>
                                <td>{atendente.idAtendente}</td>
                                <td>{atendente.cpfFuncionario}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default AtendentePage;
