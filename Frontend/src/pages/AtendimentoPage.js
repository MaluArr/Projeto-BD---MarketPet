import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Atendimento.css';
import axios from 'axios';

const AtendimentoPage = () => {
    const [atendimentos, setAtendimentos] = useState([]); // Estado para armazenar os atendimentos
    const [error, setError] = useState(null); // Estado para erros

    const fetchAtendimentos = () => {
        axios.get('http://localhost:8080/api/atendimentos')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const atendimentosFormatados = response.data.map(atendimento => ({
                    idAtendimento: atendimento.idAtendimento || atendimento.id_atendimento,
                    cpfFuncionario: atendimento.cpfFuncionario || atendimento.cpf_funcionario,
                    cpfUsuario: atendimento.cpfUsuario || atendimento.cpf_usuario,
                    idChat: atendimento.idChat || atendimento.id_chat,
                    dataAtendimento: atendimento.dataAtendimento || atendimento.data_atendimento,
                    categoria: atendimento.categoria,
                }));
                setAtendimentos(atendimentosFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar atendimentos');
                console.error('Erro ao buscar atendimentos:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Atendimentos</h1>
            <div className="action-buttons">
                <button onClick={fetchAtendimentos}>Listar Atendimentos</button>
                <Link to="/atendimentos/new" className="action-button">
                    Adicionar Novo Atendimento
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="atendimento-list">
                {atendimentos.length > 0 && (
                    <table className="atendimento-table">
                        <thead>
                        <tr>
                            <th>ID do Atendimento</th>
                            <th>CPF do Funcionário</th>
                            <th>CPF do Usuário</th>
                            <th>ID do Chat</th>
                            <th>Data do Atendimento</th>
                            <th>Categoria</th>
                        </tr>
                        </thead>
                        <tbody>
                        {atendimentos.map(atendimento => (
                            <tr key={atendimento.idAtendimento}>
                                <td>{atendimento.idAtendimento}</td>
                                <td>{atendimento.cpfFuncionario}</td>
                                <td>{atendimento.cpfUsuario}</td>
                                <td>{atendimento.idChat}</td>
                                <td>
                                    {atendimento.dataAtendimento
                                        ? new Date(atendimento.dataAtendimento).toLocaleDateString()
                                        : "N/A"}
                                </td>
                                <td>{atendimento.categoria}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default AtendimentoPage;
