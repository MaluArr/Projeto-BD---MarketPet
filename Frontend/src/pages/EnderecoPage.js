import React, { useState } from 'react';
import '../styles/Endereco.css';
import axios from 'axios';

const EnderecoPage = () => {
    const [enderecos, setEnderecos] = useState([]); // Estado para armazenar os endereços
    const [error, setError] = useState(null); // Estado para erros
    const [filter, setFilter] = useState(''); // Estado para o campo de busca
    const [filterBy, setFilterBy] = useState('cep'); // Estado para o critério de busca
    const [showSearchBar, setShowSearchBar] = useState(false); // Controla se a barra de busca deve ser exibida

    const fetchEnderecos = () => {
        axios.get('http://localhost:8080/api/enderecos')
            .then(response => {
                setEnderecos(response.data); // Atualiza o estado com os endereços
                setError(null); // Limpa qualquer erro anterior
                setShowSearchBar(true); // Exibe a barra de busca
            })
            .catch(err => {
                setError('Erro ao buscar endereços');
                console.error('Erro ao buscar endereços:', err);
            });
    };

    const handleFilterChange = (e) => {
        setFilter(e.target.value); // Atualiza o estado do filtro com o valor digitado
    };

    const handleFilterByChange = (e) => {
        setFilterBy(e.target.value); // Atualiza o critério de busca selecionado
    };

    // Filtra os endereços com base no critério de busca selecionado
    const filteredEnderecos = enderecos.filter((endereco) => {
        const fieldValue = endereco[filterBy]?.toString().toLowerCase() || ''; // Garante que o valor seja uma string
        return fieldValue.includes(filter.toLowerCase());
    });

    const handleRedirect = (url) => {
        window.location.href = url; // Redirecionamento usando window.location
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Endereços</h1>
            <div className="action-buttons">
                <button onClick={fetchEnderecos}>Listar Endereços</button>
                <button onClick={() => handleRedirect('/enderecos/list')} className="action-button">
                    Gerenciamento Endereço
                </button>
            </div>

            {error && <p className="error-message">{error}</p>}

            {/* Barra de Pesquisa e Filtro - só aparecem após clicar em Listar Endereços */}
            {showSearchBar && (
                <div className="search-container">
                    <select
                        value={filterBy}
                        onChange={handleFilterByChange}
                        className="filter-dropdown"
                    >
                        <option value="cep">CEP</option>
                        <option value="rua">Rua</option>
                        <option value="bairro">Bairro</option>
                        <option value="cidade">Cidade</option>
                        <option value="estado">Estado</option>
                        <option value="complemento">Complemento</option>
                    </select>
                    <input
                        type="text"
                        placeholder={`Buscar por ${filterBy}...`}
                        value={filter}
                        onChange={handleFilterChange}
                        className="search-input"
                    />
                </div>
            )}

            <div className="endereco-list">
                {filteredEnderecos.length > 0 ? (
                    <table className="endereco-table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>CEP</th>
                            <th>Rua</th>
                            <th>Número</th>
                            <th>Bairro</th>
                            <th>Cidade</th>
                            <th>Estado</th>
                            <th>Complemento</th>
                        </tr>
                        </thead>
                        <tbody>
                        {filteredEnderecos.map(endereco => (
                            <tr key={endereco.idEndereco}>
                                <td>{endereco.idEndereco}</td>
                                <td>{endereco.cep}</td>
                                <td>{endereco.rua}</td>
                                <td>{endereco.numero}</td>
                                <td>{endereco.bairro}</td>
                                <td>{endereco.cidade}</td>
                                <td>{endereco.estado}</td>
                                <td>{endereco.complemento || 'N/A'}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                ) : (
                    showSearchBar && <p>Nenhum endereço encontrado.</p>
                )}
            </div>
        </div>
    );
};

export default EnderecoPage;
