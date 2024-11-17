import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Usuario.css';
import axios from 'axios';

const UsuarioPage = () => {
    const [usuarios, setUsuarios] = useState([]); // Estado para armazenar os usuários
    const [error, setError] = useState(null); // Estado para erros

    const fetchUsuarios = () => {
        axios.get('http://localhost:8080/api/usuarios')
            .then(response => {
                setUsuarios(response.data); // Atualiza o estado com os usuários
                setError(null); // Limpa qualquer erro anterior
            })
            .catch(err => {
                setError('Erro ao buscar usuários');
                console.error('Erro ao buscar usuários:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Usuários</h1>
            <div className="action-buttons">
                <button onClick={fetchUsuarios}>Listar Usuários</button>
                <button onClick={() => window.location.href = '/usuarios/list'} className="action-button">
                    Adicionar Novo Usuário
                </button>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="usuario-list">
                {usuarios.length > 0 && (
                    <table className="usuario-table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Usuário</th>
                            <th>Telefone 1</th>
                            <th>Telefone 2</th>
                        </tr>
                        </thead>
                        <tbody>
                        {usuarios.map(usuario => (
                            <tr key={usuario.id}>
                                <td>{usuario.id}</td>
                                <td>{usuario.nomeReal}</td>
                                <td>{usuario.email}</td>
                                <td>{usuario.nomeUsuario}</td>
                                <td>{usuario.telefone1}</td>
                                <td>{usuario.telefone2}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default UsuarioPage;
