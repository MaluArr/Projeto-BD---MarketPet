import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import AtendenteService from '../services/AtendenteService';
import '../styles/Atendente.css';

const AtendenteList = () => {
    const [atendentes, setAtendentes] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchAtendentes();
    }, []);

    const fetchAtendentes = async () => {
        try {
            const data = await AtendenteService.fetchAtendentes();
            setAtendentes(data);
        } catch (error) {
            setError('Falha ao carregar atendentes');
        }
    };

    const handleDelete = async (idAtendente) => {
        try {
            await AtendenteService.deleteAtendente(idAtendente);
            setAtendentes(atendentes.filter(atendente => atendente.idAtendente !== idAtendente));
        } catch (error) {
            setError('Falha ao deletar atendente');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Atendentes</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/atendentes/new" className="add-button">Adicionar Novo Atendente</Link>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>CPF do Funcionário</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {atendentes.map(atendente => (
                    <tr key={atendente.idAtendente}>
                        <td>{atendente.idAtendente}</td>
                        <td>{atendente.cpfFuncionario}</td>
                        <td>
                            <Link to={`/atendentes/${atendente.idAtendente}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(atendente.idAtendente)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default AtendenteList;