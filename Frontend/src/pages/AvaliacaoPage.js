import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Avaliacao.css';
import axios from 'axios';

const AvaliacaoPage = () => {
    const [avaliacoes, setAvaliacoes] = useState([]); // Estado para armazenar as avaliações
    const [error, setError] = useState(null); // Estado para erros

    const fetchAvaliacoes = () => {
        axios.get('http://localhost:8080/api/avaliacoes')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const avaliacoesFormatadas = response.data.map(avaliacao => ({
                    idAvaliacao: avaliacao.idAvaliacao || avaliacao.id_avaliacao,
                    cpfComprador: avaliacao.cpfComprador || avaliacao.cpf_comprador,
                    codigoProduto: avaliacao.codigoProduto || avaliacao.codigo_produto,
                    idVenda: avaliacao.idVenda || avaliacao.id_venda,
                    nota: avaliacao.nota,
                }));
                setAvaliacoes(avaliacoesFormatadas);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar avaliações');
                console.error('Erro ao buscar avaliações:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Avaliações</h1>
            <div className="action-buttons">
                <button onClick={fetchAvaliacoes}>Listar Avaliações</button>
                <Link to="/avaliacoes/new" className="action-button">
                    Adicionar Nova Avaliação
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="avaliacao-list">
                {avaliacoes.length > 0 && (
                    <table className="avaliacao-table">
                        <thead>
                        <tr>
                            <th>ID da Avaliação</th>
                            <th>CPF do Comprador</th>
                            <th>Código do Produto</th>
                            <th>ID da Venda</th>
                            <th>Nota</th>
                        </tr>
                        </thead>
                        <tbody>
                        {avaliacoes.map(avaliacao => (
                            <tr key={avaliacao.idAvaliacao}>
                                <td>{avaliacao.idAvaliacao}</td>
                                <td>{avaliacao.cpfComprador}</td>
                                <td>{avaliacao.codigoProduto}</td>
                                <td>{avaliacao.idVenda}</td>
                                <td>{avaliacao.nota.toFixed(1)}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default AvaliacaoPage;
