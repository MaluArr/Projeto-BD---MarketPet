// src/pages/UsuarioPage.js
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import '../styles/Usuario.css';

const UsuarioPage = () => {
    const [usuarios, setUsuarios] = useState([]);

    useEffect(() => {
        fetchUsuarios();
    }, []);

    const fetchUsuarios = async () => {
        try {
            const response = await axios.get('/api/usuarios');
            setUsuarios(response.data);
        } catch (error) {
            console.error('Erro ao buscar usuários:', error);
        }
    };

    const handleDelete = async (cpf) => {
        if (window.confirm('Tem certeza que deseja excluir este usuário?')) {
            try {
                await axios.delete(`/api/usuarios/${cpf}`);
                fetchUsuarios(); // Atualiza a lista após a exclusão
            } catch (error) {
                console.error('Erro ao excluir usuário:', error);
            }
        }
    };

    return (
        <div className="usuario-page">
            <h1>Gerenciamento de Usuários</h1>
            <Link to="/usuarios/new" className="btn btn-primary">Adicionar Novo Usuário</Link>
            <table className="usuario-table">
                <thead>
                <tr>
                    <th>CPF</th>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {usuarios.map((usuario) => (
                    <tr key={usuario.cpf}>
                        <td>{usuario.cpf}</td>
                        <td>{usuario.nome}</td>
                        <td>{usuario.email}</td>
                        <td>
                            <Link to={`/usuarios/${usuario.cpf}`} className="btn btn-info">Detalhes</Link>
                            <Link to={`/usuarios/${usuario.cpf}/edit`} className="btn btn-warning">Editar</Link>
                            <button onClick={() => handleDelete(usuario.cpf)} className="btn btn-danger">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default UsuarioPage;