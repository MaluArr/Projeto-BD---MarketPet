import React, { useState } from 'react';
import '../styles/Funcionario.css';
import axios from 'axios';

const FuncionarioPage = () => {
    const [funcionarios, setFuncionarios] = useState([]); // Estado para armazenar os funcionários
    const [error, setError] = useState(null); // Estado para erros
    const [filter, setFilter] = useState(''); // Estado para o campo de busca
    const [showSearchBar, setShowSearchBar] = useState(false); // Controla se a barra de busca deve ser exibida

    const fetchFuncionarios = () => {
        axios.get('http://localhost:8080/api/funcionarios')
            .then(response => {
                setFuncionarios(response.data); // Atualiza o estado com os funcionários
                setError(null); // Limpa qualquer erro anterior
                setShowSearchBar(true); // Exibe a barra de busca
            })
            .catch(err => {
                setError('Erro ao buscar funcionários');
                console.error('Erro ao buscar funcionários:', err);
            });
    };

    const handleFilterChange = (e) => {
        setFilter(e.target.value); // Atualiza o estado do filtro com o valor digitado
    };

    // Filtra os funcionários com base no filtro (CPF ou Nome)
    const filteredFuncionarios = funcionarios.filter((funcionario) => {
        const cpf = funcionario.cpfFuncionario ? funcionario.cpfFuncionario.toString() : ''; // Converte para string se necessário
        const nome = funcionario.nome || ''; // Garante que o valor seja uma string

        // Verifica se o filtro está presente em algum dos campos
        return (
            cpf.includes(filter) ||
            nome.toLowerCase().includes(filter.toLowerCase())
        );
    });

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Funcionários</h1>
            <div className="action-buttons">
                <button onClick={fetchFuncionarios}>Listar Funcionários</button>
                <button onClick={() => window.location.href = '/funcionarios/list'} className="action-button">
                    Gerenciamento Funcionário
                </button>
            </div>

            {error && <p className="error-message">{error}</p>}

            {/* Barra de Pesquisa - só aparece após clicar em Listar Funcionários */}
            {showSearchBar && (
                <div className="search-container">
                    <input
                        type="text"
                        placeholder="Buscar por CPF ou Nome..."
                        value={filter}
                        onChange={handleFilterChange}
                        className="search-input"
                    />
                </div>
            )}

            <div className="funcionario-list">
                {filteredFuncionarios.length > 0 ? (
                    <table className="funcionario-table">
                        <thead>
                        <tr>
                            <th>CPF</th>
                            <th>Nome</th>
                        </tr>
                        </thead>
                        <tbody>
                        {filteredFuncionarios.map(funcionario => (
                            <tr key={funcionario.cpfFuncionario}>
                                <td>{funcionario.cpfFuncionario}</td>
                                <td>{funcionario.nome}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                ) : (
                    showSearchBar && <p>Nenhum funcionário encontrado.</p>
                )}
            </div>
        </div>
    );
};

export default FuncionarioPage;
