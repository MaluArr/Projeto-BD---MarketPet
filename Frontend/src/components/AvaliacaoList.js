import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import AvaliacaoService from '../services/AvaliacaoService';
import '../styles/Avaliacao.css';

const AvaliacaoList = () => {
    const [avaliacoes, setAvaliacoes] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchAvaliacoes();
    }, []);

    const fetchAvaliacoes = async () => {
        try {
            const data = await AvaliacaoService.fetchAvaliacoes();
            setAvaliacoes(data);
        } catch (error) {
            setError('Falha ao carregar avaliações');
        }
    };

    const handleDelete = async (idAvaliacao) => {
        try {
            await AvaliacaoService.deleteAvaliacao(idAvaliacao);
            setAvaliacoes(avaliacoes.filter(avaliacao => avaliacao.idAvaliacao !== idAvaliacao));
        } catch (error) {
            setError('Falha ao deletar avaliação');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Avaliações</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/avaliacoes/new" className="add-button">Adicionar Nova Avaliação</Link>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>CPF Comprador</th>
                    <th>Código Produto</th>
                    <th>ID Venda</th>
                    <th>Nota</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {avaliacoes.map(avaliacao => (
                    <tr key={avaliacao.idAvaliacao}>
                        <td>{avaliacao.idAvaliacao}</td>
                        <td>{avaliacao.cpfComprador}</td>
                        <td>{avaliacao.codigoProduto}</td>
                        <td>{avaliacao.idVenda}</td>
                        <td>{avaliacao.nota}</td>
                        <td>
                            <Link to={`/avaliacoes/${avaliacao.idAvaliacao}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(avaliacao.idAvaliacao)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default AvaliacaoList;