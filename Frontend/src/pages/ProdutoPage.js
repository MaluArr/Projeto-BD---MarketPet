import React, { useState } from 'react';
import '../styles/Produto.css';
import axios from 'axios';

const ProdutoPage = () => {
    const [produtos, setProdutos] = useState([]); // Estado para armazenar os produtos
    const [error, setError] = useState(null); // Estado para erros

    const fetchProdutos = () => {
        axios.get('http://localhost:8080/api/produtos')
            .then(response => {
                setProdutos(response.data); // Atualiza o estado com os produtos
                setError(null); // Limpa qualquer erro anterior
            })
            .catch(err => {
                setError('Erro ao buscar produtos');
                console.error('Erro ao buscar produtos:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Produtos</h1>
            <div className="action-buttons">
                <button onClick={fetchProdutos}>Listar Produtos</button>
                <button onClick={() => window.location.href = '/produtos/list'} className="action-button">
                    Gerenciamento Produto
                </button>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="produto-list">
                {produtos.length > 0 && (
                    <table className="produto-table">
                        <thead>
                        <tr>
                            <th>Código</th>
                            <th>Descrição</th>
                            <th>Categoria</th>
                            <th>Preço</th>
                            <th>Dimensões</th>
                        </tr>
                        </thead>
                        <tbody>
                        {produtos.map(produto => (
                            <tr key={produto.codigoProduto}>
                                <td>{produto.codigoProduto}</td>
                                <td>{produto.descricao}</td>
                                <td>{produto.categoria}</td>
                                <td>R$ {produto.preco.toFixed(2)}</td>
                                <td>{produto.dimensoes || 'Não especificado'}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default ProdutoPage;
