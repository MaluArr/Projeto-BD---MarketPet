import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import AtendimentoService from '../services/AtendimentoService';
import '../styles/Atendimento.css';

const AtendimentoList = () => {
    const [atendimentos, setAtendimentos] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchAtendimentos();
    }, []);

    const fetchAtendimentos = async () => {
        try {
            const data = await AtendimentoService.fetchAtendimentos();
            setAtendimentos(data);
        } catch (error) {
            setError('Falha ao carregar atendimentos');
        }
    };

    const handleDelete = async (idAtendimento) => {
        try {
            await AtendimentoService.deleteAtendimento(idAtendimento);
            setAtendimentos(atendimentos.filter(atendimento => atendimento.idAtendimento !== idAtendimento));
        } catch (error) {
            setError('Falha ao deletar atendimento');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Atendimentos</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/atendimentos/new" className="add-button">Adicionar Novo Atendimento</Link>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>CPF Funcionário</th>
                    <th>CPF Usuário</th>
                    <th>ID Chat</th>
                    <th>Data</th>
                    <th>Categoria</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {atendimentos.map(atendimento => (
                    <tr key={atendimento.idAtendimento}>
                        <td>{atendimento.idAtendimento}</td>
                        <td>{atendimento.cpfFuncionario}</td>
                        <td>{atendimento.cpfUsuario}</td>
                        <td>{atendimento.idChat}</td>
                        <td>{atendimento.dataAtendimento}</td>
                        <td>{atendimento.categoria}</td>
                        <td>
                            <Link to={`/atendimentos/${atendimento.idAtendimento}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(atendimento.idAtendimento)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default AtendimentoList;