import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import ProdutoService from '../services/ProdutoService';
import '../styles/Produto.css';

const ProdutoList = () => {
    const [produtos, setProdutos] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchProdutos();
    }, []);

    const fetchProdutos = async () => {
        try {
            const data = await ProdutoService.fetchProdutos();
            setProdutos(data);
        } catch (error) {
            setError('Falha ao carregar produtos');
        }
    };

    const handleDelete = async (codigoProduto) => {
        try {
            await ProdutoService.deleteProduto(codigoProduto);
            setProdutos(produtos.filter(produto => produto.codigoProduto !== codigoProduto));
        } catch (error) {
            setError('Falha ao deletar produto');
        }
    };

    return (
        <div className="entity-list">
            <h1>Lista de Produtos</h1>
            {error && <p className="error-message">{error}</p>}
            <Link to="/produtos/new" className="add-button">Adicionar Novo Produto</Link>
            <table>
                <thead>
                <tr>
                    <th>Código</th>
                    <th>Descrição</th>
                    <th>Categoria</th>
                    <th>Preço</th>
                    <th>Nota</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                {produtos.map(produto => (
                    <tr key={produto.codigoProduto}>
                        <td>{produto.codigoProduto}</td>
                        <td>{produto.descricao}</td>
                        <td>{produto.categoria}</td>
                        <td>{produto.preco}</td>
                        <td>{produto.nota}</td>
                        <td>
                            <Link to={`/produtos/${produto.codigoProduto}/edit`} className="edit-button">Editar</Link>
                            <button onClick={() => handleDelete(produto.codigoProduto)} className="delete-button">Excluir</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ProdutoList;