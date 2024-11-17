import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/FavoritarProduto.css';
import axios from 'axios';

const FavoritarProdutoPage = () => {
    const [favoritos, setFavoritos] = useState([]); // Estado para armazenar os produtos favoritos
    const [error, setError] = useState(null); // Estado para erros

    const fetchFavoritos = () => {
        axios.get('http://localhost:8080/api/favoritar-produtos')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const favoritosFormatados = response.data.map(favorito => ({
                    idLista: favorito.idLista || favorito.id_lista,
                    cpfComprador: favorito.cpfComprador || favorito.cpf_comprador,
                    codigoProduto: favorito.codigoProduto || favorito.codigo_produto,
                    nomeLista: favorito.nomeLista || favorito.nome_lista,
                    dataCriacao: favorito.dataCriacao || favorito.data_criacao,
                }));
                setFavoritos(favoritosFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar produtos favoritos');
                console.error('Erro ao buscar produtos favoritos:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Produtos Favoritos</h1>
            <div className="action-buttons">
                <button onClick={fetchFavoritos}>Listar Produtos Favoritos</button>
                <Link to="/favoritar-produtos/new" className="action-button">
                    Adicionar Novo Produto Favorito
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="favoritos-list">
                {favoritos.length > 0 && (
                    <table className="favoritos-table">
                        <thead>
                        <tr>
                            <th>ID da Lista</th>
                            <th>CPF do Comprador</th>
                            <th>Código do Produto</th>
                            <th>Nome da Lista</th>
                            <th>Data de Criação</th>
                        </tr>
                        </thead>
                        <tbody>
                        {favoritos.map(favorito => (
                            <tr key={favorito.idLista}>
                                <td>{favorito.idLista}</td>
                                <td>{favorito.cpfComprador}</td>
                                <td>{favorito.codigoProduto}</td>
                                <td>{favorito.nomeLista}</td>
                                <td>
                                    {favorito.dataCriacao
                                        ? new Date(favorito.dataCriacao).toLocaleDateString()
                                        : "N/A"}
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default FavoritarProdutoPage;
