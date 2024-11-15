import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import AdicionarProdutoCarrinhoService from '../services/AdicionarProdutoCarrinhoService';
import '../styles/AdicionarProdutoCarrinho.css';

const AdicionarProdutoCarrinhoList = () => {
    const [produtosCarrinho, setProdutosCarrinho] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchProdutosCarrinho();
    }, []);

    const fetchProdutosCarrinho = async () => {
        try {
            const data = await AdicionarProdutoCarrinhoService.fetchAdicionarProdutoCarrinho();
            setProdutosCarrinho(data);
        } catch (error) {
            setError('Falha ao carregar produtos no carrinho');
        }
    };

    const handleDelete = async (id) => {
        try {
            await AdicionarProdutoCarrinhoService.deleteAdicionarProdutoCarrinho(id);
            setProdutosCarrinho(produtosCarrinho.filter(item => item.id !== id));
        } catch (error) {
            setError('Falha ao deletar produto do carrinho');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Produtos no Carrinho</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/adicionar-produto-carrinho/new" className="add-button">Adicionar Novo Produto ao Carrinho</Link>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>CPF Comprador</th>
                    <th>Código Produto</th>
                    <th>ID Carrinho</th>
                    <th>Quantidade</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {produtosCarrinho.map(item => (
                    <tr key={item.id}>
                        <td>{item.id}</td>
                        <td>{item.comprador.cpf}</td>
                        <td>{item.produto.codigoProduto}</td>
                        <td>{item.carrinho.idCarrinho}</td>
                        <td>{item.quantidade}</td>
                        <td>
                            <Link to={`/adicionar-produto-carrinho/${item.id}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(item.id)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default AdicionarProdutoCarrinhoList;