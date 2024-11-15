import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import FuncionarioService from '../services/FuncionarioService';
import '../styles/Funcionario.css';

const FuncionarioList = () => {
    const [funcionarios, setFuncionarios] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchFuncionarios();
    }, []);

    const fetchFuncionarios = async () => {
        try {
            const data = await FuncionarioService.fetchFuncionarios();
            setFuncionarios(data);
        } catch (error) {
            setError('Falha ao carregar funcionários');
        }
    };

    const handleDelete = async (cpfFuncionario) => {
        try {
            await FuncionarioService.deleteFuncionario(cpfFuncionario);
            setFuncionarios(funcionarios.filter(funcionario => funcionario.cpfFuncionario !== cpfFuncionario));
        } catch (error) {
            setError('Falha ao deletar funcionário');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Funcionários</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/funcionarios/new" className="add-button">Adicionar Novo Funcionário</Link>
            <table>
                <thead>
                <tr>
                    <th>CPF</th>
                    <th>Nome</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {funcionarios.map(funcionario => (
                    <tr key={funcionario.cpfFuncionario}>
                        <td>{funcionario.cpfFuncionario}</td>
                        <td>{funcionario.nome}</td>
                        <td>
                            <Link to={`/funcionarios/${funcionario.cpfFuncionario}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(funcionario.cpfFuncionario)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default FuncionarioList;