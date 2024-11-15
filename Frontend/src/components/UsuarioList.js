import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import UsuarioService from '../services/UsuarioService';
import '../styles/Usuario.css';

const UsuarioList = () => {
    const [usuarios, setUsuarios] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchUsuarios();
    }, []);

    const fetchUsuarios = async () => {
        try {
            const data = await UsuarioService.fetchUsuarios();
            setUsuarios(data);
        } catch (error) {
            setError('Falha ao carregar usuários');
        }
    };

    const handleDelete = async (cpf) => {
        try {
            await UsuarioService.deleteUsuario(cpf);
            setUsuarios(usuarios.filter(usuario => usuario.cpf !== cpf));
        } catch (error) {
            setError('Falha ao deletar usuário');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Usuários</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/usuarios/new" className="add-button">Adicionar Novo Usuário</Link>
            <table>
                <thead>
                <tr>
                    <th>CPF</th>
                    <th>Nome Real</th>
                    <th>Email</th>
                    <th>Telefone</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {usuarios.map(usuario => (
                    <tr key={usuario.cpf}>
                        <td>{usuario.cpf}</td>
                        <td>{usuario.nomeReal}</td>
                        <td>{usuario.email}</td>
                        <td>{usuario.telefone1}</td>
                        <td>
                            <Link to={`/usuarios/${usuario.cpf}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(usuario.cpf)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default UsuarioList;