import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/AdicionarProdutoCarrinho.css';
import axios from 'axios';
// nao ta pegando do banco, esta aparecendo vazio.
const AdicionarProdutoCarrinhoPage = () => {
    const [produtosCarrinho, setProdutosCarrinho] = useState([]); // Estado para armazenar os produtos no carrinho
    const [error, setError] = useState(null); // Estado para erros

    const fetchProdutosCarrinho = () => {
        axios.get('http://localhost:8080/api/adicionar-produto-carrinho')
            .then(response => {
                // Ajusta os dados recebidos para o formato esperado
                const produtosCarrinhoFormatados = response.data.map(produto => ({
                    id: produto.id || produto.id_produto, // Verificar nome correto do campo
                    cpfComprador: produto.cpfComprador || produto.cpf_comprador,
                    codigoProduto: produto.codigoProduto || produto.codigo_produto,
                    idCarrinho: produto.idCarrinho || produto.id_carrinho,
                    quantidade: produto.quantidade || produto.quantidade,
                }));
                setProdutosCarrinho(produtosCarrinhoFormatados);
                setError(null);
            })
            .catch(err => {
                setError('Erro ao buscar produtos no carrinho');
                console.error('Erro ao buscar produtos no carrinho:', err);
            });
    };

    return (
        <div className="entity-page">
            <h1>Gerenciamento de Produtos no Carrinho</h1>
            <div className="action-buttons">
                <button onClick={fetchProdutosCarrinho}>Listar Produtos no Carrinho</button>
                <Link to="/adicionar-produto-carrinho/new" className="action-button">
                    Adicionar Novo Produto ao Carrinho
                </Link>
            </div>

            {error && <p className="error-message">{error}</p>}

            <div className="produtos-carrinho-list">
                {produtosCarrinho.length > 0 && (
                    <table className="produtos-carrinho-table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>CPF do Comprador</th>
                            <th>Código do Produto</th>
                            <th>ID do Carrinho</th>
                            <th>Quantidade</th>
                        </tr>
                        </thead>
                        <tbody>
                        {produtosCarrinho.map(produto => (
                            <tr key={produto.id}>
                                <td>{produto.id}</td>
                                <td>{produto.cpfComprador}</td>
                                <td>{produto.codigoProduto}</td>
                                <td>{produto.idCarrinho}</td>
                                <td>{produto.quantidade}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
};

export default AdicionarProdutoCarrinhoPage;
