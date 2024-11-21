import React, { useState } from 'react';
import '../styles/Usuario.css';
import axios from 'axios';

const UsuarioPage = () => {
    const [usuarios, setUsuarios] = useState([]); // Estado para armazenar os usuários
    const [error, setError] = useState(null); // Estado para erros
    const [filter, setFilter] = useState(''); // Estado para o campo de busca
    const [showSearchBar, setShowSearchBar] = useState(false); // Controla se a barra de busca deve ser exibida

    const fetchUsuarios = () => {
        axios.get('http://localhost:8080/api/usuarios')
            .then(response => {
                setUsuarios(response.data); // Atualiza o estado com os usuários
                setError(null); // Limpa qualquer erro anterior
                setShowSearchBar(true); // Exibe a barra de busca
            })
            .catch(err => {
                setError('Erro ao buscar usuários');
                console.error('Erro ao buscar usuários:', err);
            });
    };

    const handleFilterChange = (e) => {
        setFilter(e.target.value); // Atualiza o estado do filtro com o valor digitado
    };

    // Filtra os usuários com base no filtro (nome, email, telefone ou usuário)
    const filteredUsuarios = usuarios.filter((usuario) => {
        const nomeReal = usuario.nomeReal || ''; // Garante que o valor seja uma string
        const email = usuario.email || ''; // Garante que o valor seja uma string
        const telefone1 = usuario.telefone1 ? usuario.telefone1.toString() : ''; // Converte para string se necessário
        const telefone2 = usuario.telefone2 ? usuario.telefone2.toString() : ''; // Converte para string se necessário
        const nomeUsuario = usuario.nomeUsuario || ''; // Garante que o valor seja uma string

        // Verifica se o filtro está presente em algum dos campos
        return (
            nomeReal.toLowerCase().includes(filter.toLowerCase()) ||
            email.toLowerCase().includes(filter.toLowerCase()) ||
            telefone1.includes(filter) ||
            telefone2.includes(filter) ||
            nomeUsuario.toLowerCase().includes(filter.toLowerCase())
        );
    });

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Usuários</h1>
            <div className="action-buttons">
                <button onClick={fetchUsuarios}>Listar Usuários</button>
                <button onClick={() => window.location.href = '/usuarios/list'} className="action-button">
                    Gerenciamento Usuário
                </button>
            </div>

            {error && <p className="error-message">{error}</p>}

            {/* Barra de Pesquisa - só aparece após clicar em Listar Usuários */}
            {showSearchBar && (
                <div className="search-container">
                    <input
                        type="text"
                        placeholder="Buscar por nome, email, telefone ou usuário..."
                        value={filter}
                        onChange={handleFilterChange}
                        className="search-input"
                    />
                </div>
            )}

            <div className="usuario-list">
                {filteredUsuarios.length > 0 ? (
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
                        {filteredUsuarios.map(usuario => (
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
                ) : (
                    showSearchBar && <p>Nenhum usuário encontrado.</p>
                )}
            </div>
        </div>
    );
};

export default UsuarioPage;
